package cc.sferalabs.sfera.drivers.strato;

import java.io.IOException;

import cc.sferalabs.sfera.core.Configuration;
import cc.sferalabs.sfera.drivers.Driver;
import cc.sferalabs.sfera.drivers.strato.events.BatteryStratoPiEvent;
import cc.sferalabs.sfera.drivers.strato.events.BuzzerStratoPiEvent;
import cc.sferalabs.sfera.drivers.strato.events.ShutdownStratoPiEvent;
import cc.sferalabs.sfera.drivers.strato.events.WatchdogEnableStratoPiEvent;
import cc.sferalabs.sfera.drivers.strato.events.WatchdogHeartbeatStratoPiEvent;
import cc.sferalabs.sfera.drivers.strato.events.WatchdogTimeoutStratoPiEvent;
import cc.sferalabs.sfera.events.Bus;

/**
 *
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class StratoPi extends Driver {

	private cc.sferalabs.libs.strato.StratoPi strato;

	public StratoPi(String id) {
		super(id);
	}

	@Override
	protected boolean onInit(Configuration config) throws InterruptedException {
		try {
			strato = new cc.sferalabs.libs.strato.StratoPi();
		} catch (IOException e) {
			log.error("Error initializing Strato Pi", e);
			return false;
		}

		// TODO remove
		// strato.setWatchdog(config.get("watchdog", false));
		// strato.setPowerFailureShutdownMinutes(config.get("power_failure_shutdown_minutes",
		// -1));
		// strato.setQuiet(config.get("quiet", false));

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
		// TODO Auto-generated method stub

	}

	/**
	 * @param enabled
	 * @throws IOException
	 */
	public void setWatchdog(boolean enabled) throws IOException {
		strato.setWatchdogEnable(enabled);
	}

	/**
	 * @param high
	 * @throws IOException
	 */
	public void setHeartbeat(Boolean high) throws IOException {
		if (high == null) {
			strato.heartbeat();
		} else {
			strato.setWatchdogHeartbeat(high);
		}
	}

	/**
	 * @throws IOException
	 */
	public void shutdown() throws IOException {
		strato.shutdown();
	}

	/**
	 * @param on
	 * @throws IOException
	 */
	public void setBuzzer(boolean on) throws IOException {
		strato.setBuzzer(on);
	}

	/**
	 * @param on
	 * @param off
	 * @param times
	 * @throws IOException
	 * @throws InterruptedException
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
