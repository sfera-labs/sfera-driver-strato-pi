package cc.sferalabs.sfera.drivers.strato_pi;

import java.io.IOException;

import cc.sferalabs.sfera.core.Configuration;
import cc.sferalabs.sfera.drivers.Driver;
import cc.sferalabs.sfera.drivers.strato_pi.events.BatteryStratoPiEvent;
import cc.sferalabs.sfera.drivers.strato_pi.events.BuzzerStratoPiEvent;
import cc.sferalabs.sfera.drivers.strato_pi.events.ShutdownStratoPiEvent;
import cc.sferalabs.sfera.drivers.strato_pi.events.WatchdogEnableStratoPiEvent;
import cc.sferalabs.sfera.drivers.strato_pi.events.WatchdogHeartbeatStratoPiEvent;
import cc.sferalabs.sfera.drivers.strato_pi.events.WatchdogTimeoutStratoPiEvent;
import cc.sferalabs.sfera.events.Bus;

/**
 *
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class StratoPi extends Driver {

	private cc.sferalabs.libs.strato_pi.StratoPi strato;

	public StratoPi(String id) {
		super(id);
	}

	@Override
	protected boolean onInit(Configuration config) throws InterruptedException {
		try {
			strato = new cc.sferalabs.libs.strato_pi.StratoPi();
		} catch (IOException e) {
			log.error("Error initializing Strato Pi", e);
			return false;
		}

		return true;
	}

	@Override
	protected boolean loop() throws InterruptedException {
		try {
			Bus.postIfChanged(new WatchdogEnableStratoPiEvent(this, strato.getWatchdogEnable()));
			Bus.postIfChanged(
					new WatchdogHeartbeatStratoPiEvent(this, strato.getWatchdogHeartbeat()));
			Bus.postIfChanged(new WatchdogTimeoutStratoPiEvent(this, strato.getWatchdogTimeout()));
			Bus.postIfChanged(new ShutdownStratoPiEvent(this, strato.getShutdown()));
			Bus.postIfChanged(new BuzzerStratoPiEvent(this, strato.getBuzzer()));
			Bus.postIfChanged(new BatteryStratoPiEvent(this, strato.getBattery()));
			Thread.sleep(100);
			return true;
		} catch (IOException | IllegalStateException e) {
			log.error("Loop error", e);
			return false;
		}
	}

	@Override
	protected void onQuit() {

	}

	/**
	 * Enables or disables the hardware watchdog.
	 * 
	 * @param enabled
	 *            {@code true} to enable, {@code false} to disable
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public void setWatchdog(boolean enabled) throws IOException {
		strato.setWatchdogEnable(enabled);
	}

	/**
	 * Sets the heartbeat pin to the specified value
	 * 
	 * @param high
	 *            {@code true} for high, {@code false} for low
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public void setHeartbeat(Boolean high) throws IOException {
		if (high == null) {
			strato.heartbeat();
		} else {
			strato.setWatchdogHeartbeat(high);
		}
	}

	/**
	 * requests tio initiate the shutdown cycle.
	 * 
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public void shutdown() throws IOException {
		strato.shutdown();
	}

	/**
	 * Sets the buzzer on or off
	 * 
	 * @param on
	 *            {@code true} to set the buzzer on, {@code false} to set it off
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public void setBuzzer(boolean on) throws IOException {
		strato.setBuzzer(on);
	}

	/**
	 * Plays the specified number of beeps from the buzzer with the specified
	 * intervals.
	 * 
	 * @param on
	 *            time in milliseconds the buzzer is on
	 * @param off
	 *            time in milliseconds the buzzer is off
	 * @param times
	 *            number of beeps (on/off cycles)
	 * @throws IOException
	 *             if an I/O error occurs
	 * @throws InterruptedException
	 *             if the current thread is interrupted
	 */
	public void beep(int on, int off, int times) throws IOException, InterruptedException {
		for (int i = 1; i <= times; i++) {
			setBuzzer(true);
			Thread.sleep(on);
			setBuzzer(false);
			if (i != times) {
				Thread.sleep(off);
			}
		}
	}

}
