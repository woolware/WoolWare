package wool.ware.client.event.impl.player;

import net.minecraft.entity.Entity;
import wool.ware.client.event.cancelable.CancelableEvent;

public class StepEvent extends CancelableEvent {

	/**
	 * The entity stepping.
	 */
	private Entity entity;

	/**
	 * The step height.
	 */
	private float height;

	private boolean pre;
	
	public StepEvent(final Entity entity, final boolean pre) {
		this.entity = entity;
		this.height = entity.stepHeight;
		this.pre = pre;
	}

	public StepEvent(final Entity entity) {
		this.entity = entity;
		this.height = entity.stepHeight;
	}
	
	public Entity getEntity() {
		return entity;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isPre() {
		return pre;
	}
}
