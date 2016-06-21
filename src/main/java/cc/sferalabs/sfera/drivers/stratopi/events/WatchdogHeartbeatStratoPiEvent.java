/**
 * 
 */
package cc.sferalabs.sfera.drivers.stratopi.events;

import cc.sferalabs.sfera.events.BooleanEvent;
import cc.sferalabs.sfera.events.Node;

/**
 * Event triggered when the heartbeat state changes.
 * 
 * @sfera.event_id heartbeat
 * @sfera.event_val true when high
 * @sfera.event_val false when low
 * 
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class WatchdogHeartbeatStratoPiEvent extends BooleanEvent implements StratoPiEvent {

	/**
	 * @param source
	 *            the source node
	 * @param value
	 *            the new value
	 */
	public WatchdogHeartbeatStratoPiEvent(Node source, Boolean value) {
		super(source, "heartbeat", value);
	}

}
