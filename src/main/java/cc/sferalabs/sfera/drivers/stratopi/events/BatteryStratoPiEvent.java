/**
 * 
 */
package cc.sferalabs.sfera.drivers.stratopi.events;

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
