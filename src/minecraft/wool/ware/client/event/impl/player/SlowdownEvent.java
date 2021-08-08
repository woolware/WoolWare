package wool.ware.client.event.impl.player;

import wool.ware.client.event.cancelable.CancelableEvent;

public class SlowdownEvent extends CancelableEvent {

	private Type type;
	
	public SlowdownEvent(final Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public enum Type {
		Item, Sprinting, SoulSand, Water
	}
}
