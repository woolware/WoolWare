package wool.ware.client.module.impl.player;


import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.player.UpdateEvent;
import wool.ware.client.module.Module;

import java.awt.*;

public class AntiVoid extends Module {
    public AntiVoid() {
        super("AntiVoid", Category.PLAYER, new Color(223, 233, 233).getRGB());
        setDescription("Flags you back when falling into the void");
        setRenderLabel("Anti Void");
    }

    @Handler
    public void onUpdate(UpdateEvent event) {
        if(event.isPre()) {
            if (getMc().thePlayer.fallDistance > 2.5) {
                if (getMc().thePlayer.posY < 0) {
                    event.setY(event.getY() + 4.42f);
                } else {
                    for (int i = (int) Math.ceil(getMc().thePlayer.posY); i >= 0; i--) {
                        if (getMc().theWorld.getBlockState(new BlockPos(getMc().thePlayer.posX, i, getMc().thePlayer.posZ)).getBlock() != Blocks.air) {
                            return;
                        }
                    }
                    event.setY(event.getY() + 4.42f);
                }
            }
        }
    }
}
