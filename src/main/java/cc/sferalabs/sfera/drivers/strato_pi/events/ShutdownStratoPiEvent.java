/**
 * 
 */
package cc.sferalabs.sfera.drivers.strato_pi.events;

import cc.sferalabs.sfera.events.BooleanEvent;
import cc.sferalabs.sfera.events.Node;

/**
 * Event triggered when the shutdown state changes.
 * 
 * @sfera.event_id shutdown
 * @sfera.event_val true when the shutdown cycle is in process
 * @sfera.event_val false otherwise
 * 
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class ShutdownStratoPiEvent extends BooleanEvent implements StratoPiEvent {

	/**
	 * @param source
	 *            the source node
	 * @param value
	 *            the new value
	 */
	public ShutdownStratoPiEvent(Node source, Boolean value) {
		super(source, "shutdown", value);
	}

}
