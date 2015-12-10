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
public class WatchdogEnableStratoPiEvent extends BooleanEvent implements StratoPiEvent {

	/**
	 * @param source
	 * @param value
	 */
	public WatchdogEnableStratoPiEvent(Node source, Boolean value) {
		super(source, "watchdog", value);
	}

}
