/**
 * 
 */
package cc.sferalabs.sfera.drivers.strato_pi.events;

import cc.sferalabs.sfera.events.BooleanEvent;
import cc.sferalabs.sfera.events.Node;

/**
 * Event triggered when the buzzer switches on or off.
 * 
 * @sfera.event_id buzzer
 * @sfera.event_val true when the buzzer is on
 * @sfera.event_val false when the buzzer is off
 * 
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class BuzzerStratoPiEvent extends BooleanEvent implements StratoPiEvent {

	/**
	 * @param source
	 *            the source node
	 * @param value
	 *            the new value
	 */
	public BuzzerStratoPiEvent(Node source, Boolean value) {
		super(source, "buzzer", value);
	}

}
