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

/**
 * 
 */
package cc.sferalabs.sfera.drivers.strato_pi.events;

import cc.sferalabs.sfera.events.BooleanEvent;
import cc.sferalabs.sfera.events.Node;

/**
 * Event triggered when Strato Pi starts or stops using the battery power.
 * 
 * @author Giampiero Baggiani
 * 
 * @sfera.event_id battery
 * @sfera.event_val true when on battery power
 * @sfera.event_val false when not on battery power
 *
 * @version 1.0.0
 *
 */
public class BatteryStratoPiEvent extends BooleanEvent implements StratoPiEvent {

	/**
	 * @param source
	 *            the source node
	 * @param value
	 *            the new value
	 */
	public BatteryStratoPiEvent(Node source, Boolean value) {
		super(source, "battery", value);
	}

}
