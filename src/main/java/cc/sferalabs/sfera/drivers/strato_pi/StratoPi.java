/*-
 * +======================================================================+
 * Strato Pi
 * ---
 * Copyright (C) 2016 Sfera Labs S.r.l.
 * ---
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * -======================================================================-
 */

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

	private int pollInterval = 100;

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

		pollInterval = config.get("pollInterval", pollInterval);

		return true;
	}

	@Override
	protected boolean loop() throws InterruptedException {
		try {
			Bus.postIfChanged(new WatchdogEnableStratoPiEvent(this, strato.getWatchdogEnable()));
			Bus.postIfChanged(new WatchdogHeartbeatStratoPiEvent(this, strato.getWatchdogHeartbeat()));
			Bus.postIfChanged(new WatchdogTimeoutStratoPiEvent(this, strato.getWatchdogTimeout()));
			Bus.postIfChanged(new ShutdownStratoPiEvent(this, strato.getShutdown()));
			Bus.postIfChanged(new BuzzerStratoPiEvent(this, strato.getBuzzer()));
			Bus.postIfChanged(new BatteryStratoPiEvent(this, strato.getBattery()));
			Thread.sleep(pollInterval);
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
	 *            {@code true} for high, {@code false} for low, {@code null} for
	 *            inverting the current value
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
