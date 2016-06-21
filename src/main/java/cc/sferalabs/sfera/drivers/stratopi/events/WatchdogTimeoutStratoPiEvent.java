/**
 * 
 */
package cc.sferalabs.sfera.drivers.stratopi.events;

import cc.sferalabs.sfera.events.BooleanEvent;
import cc.sferalabs.sfera.events.Node;

/**
 * Event triggered when the watchdog timeout state changes.
 * 
 * @sfera.event_id timeout
 * @sfera.event_val true when the watchdog timeout has expired
 * @sfera.event_val false otherwise
 * 
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class WatchdogTimeoutStratoPiEvent extends BooleanEvent implements StratoPiEvent {

	/**
	 * @param source
	 *            the source node
	 * @param value
	 *            the new value
	 */
	public WatchdogTimeoutStratoPiEvent(Node source, Boolean value) {
		super(source, "timeout", value);
	}

}
