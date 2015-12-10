/**
 * 
 */
package cc.sferalabs.sfera.drivers.strato.events;

import cc.sferalabs.sfera.events.BooleanEvent;
import cc.sferalabs.sfera.events.Node;

/**
 *
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class BatteryStratoPiEvent extends BooleanEvent implements StratoPiEvent {

	/**
	 * @param source
	 * @param value
	 */
	public BatteryStratoPiEvent(Node source, Boolean value) {
		super(source, "battery", value);
	}

}
