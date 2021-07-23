package wool.ware.client.event.impl.render;

import wool.ware.client.event.Event;

public class Render3DEvent extends Event {

	private float partialTicks;

	public Render3DEvent(final float partialTicks) {
		this.partialTicks = partialTicks;
	}

	public float getPartialTicks() {
		return partialTicks;
	}
}
