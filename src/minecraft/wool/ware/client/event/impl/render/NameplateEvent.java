package wool.ware.client.event.impl.render;

import net.minecraft.entity.Entity;
import wool.ware.client.event.cancelable.CancelableEvent;


public class NameplateEvent extends CancelableEvent {
    private Entity entity;

    public NameplateEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
