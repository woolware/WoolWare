package wool.ware.client.module.impl.ghost;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.input.ClickMouseEvent;
import wool.ware.client.module.Module;
import wool.ware.client.utils.CombatUtil;
import wool.ware.client.utils.value.impl.NumberValue;

import java.awt.*;

public class Reach extends Module {
    private NumberValue<Float> range = new NumberValue<>("Range", 3.1F, 3F, 5.0F, 0.01F);

    public Reach() {
        super("Reach", Category.GHOST, new Color(0xA4A29E).getRGB());
        setDescription("Increase your reach");
    }

    @Handler
    public void onUpdate(ClickMouseEvent event) {
        final Object[] objects = CombatUtil.getEntityCustom(getMc().thePlayer.rotationPitch, getMc().thePlayer.rotationYaw, range.getValue(), 0, 0.0F);
        if (objects == null) {
            return;
        }
        getMc().objectMouseOver = new MovingObjectPosition((Entity) objects[0], (Vec3) objects[1]);
        getMc().pointedEntity = (Entity)objects[0];
    }
}