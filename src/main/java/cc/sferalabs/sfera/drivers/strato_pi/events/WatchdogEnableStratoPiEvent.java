/**
 * 
 */
package cc.sferalabs.sfera.drivers.strato_pi.events;

import cc.sferalabs.sfera.events.BooleanEvent;
import cc.sferalabs.sfera.events.Node;

/**
 * Event triggered when the hardware watchdog is enables or disabled.
 * 
 * @sfera.event_id watchdog
 * @sfera.event_val true when enabled
 * @sfera.event_val false when disabled
 * 
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class WatchdogEnableStratoPiEvent extends BooleanEvent implements StratoPiEvent {

	/**
	 * @param source
	 *            the source node
	 * @param value
	 *            the new value
	 */
	public WatchdogEnableStratoPiEvent(Node source, Boolean value) {
		super(source, "watchdog", value);
	}

}
