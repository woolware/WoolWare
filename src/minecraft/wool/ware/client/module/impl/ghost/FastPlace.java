package wool.ware.client.module.impl.ghost;

import wool.ware.client.event.EventTarget;
import wool.ware.client.event.EventUpdate;
import wool.ware.client.module.Module;
import wool.ware.client.module.Module.Category;

public class FastPlace extends Module {
	public FastPlace() {
		super("FastPlace", Category.GHOST, 0);
	}

	@EventTarget
	public void onUpdate(EventUpdate event) {
		if (this.isToggled()) {
			mc.rightClickDelayTimer = 0;
		}
	}

	public void onDisable() {
		mc.rightClickDelayTimer = 6;
			super.onDisable();
		}

	@Override
    public void onEnable() {
    	super.onEnable();
    }
}