package wool.ware.client.module.impl.player;

import net.minecraft.block.BlockAir;
import net.minecraft.util.BlockPos;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.player.UpdateEvent;
import wool.ware.client.module.Module;
import wool.ware.client.utils.value.impl.BooleanValue;

import java.awt.*;


public class NoFall extends Module {
    private BooleanValue hypixel = new BooleanValue("Hypixel", true);

    public NoFall() {
        super("NoFall", Category.PLAYER, new Color(0xE5CDF0).getRGB());
    }

    @Handler
    public void onUpdate(UpdateEvent event) {
        if (hypixel.isEnabled()) {
            if (event.isPre() && getMc().thePlayer.fallDistance >= 3.0 && isBlockUnder()) {
                getMc().thePlayer.fallDistance = 0;
                event.setOnGround(true);
            }
        } else {
            if (event.isPre() && getMc().thePlayer.fallDistance >= 3.0) {
                event.setOnGround(true);
            }
        }
    }
    private boolean isBlockUnder() {
        for (int i = (int) (getMc().thePlayer.posY - 1.0); i > 0; --i) {
            BlockPos pos = new BlockPos(getMc().thePlayer.posX, i, getMc().thePlayer.posZ);
            if (getMc().theWorld.getBlockState(pos).getBlock() instanceof BlockAir) continue;
            return true;
        }
        return false;
    }
}
