package wool.ware.client.module.impl.movement;

import net.minecraft.block.BlockAir;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.game.PacketEvent;
import wool.ware.client.event.impl.player.UpdateEvent;
import wool.ware.client.module.Module;
import wool.ware.client.utils.Printer;
import wool.ware.client.utils.TimerUtil;

import org.lwjgl.input.Keyboard;

import java.awt.*;


public class Sprint extends Module {
    private boolean print;
    private StringBuilder builder = new StringBuilder();

    public Sprint() {
        super("Sprint", Category.MOVEMENT, new Color(0, 255, 0).getRGB());
    }

    @Handler
    public void onUpdate(UpdateEvent event) {
        if (getMc().thePlayer == null) return;
        getMc().thePlayer.setSprinting(canSprint());
    }

     private boolean canSprint() {
        return getMc().thePlayer.getFoodStats().getFoodLevel() > 7 && (getMc().gameSettings.keyBindForward.isKeyDown() | getMc().gameSettings.keyBindBack.isKeyDown() || getMc().gameSettings.keyBindLeft.isKeyDown() || getMc().gameSettings.keyBindRight.isKeyDown());
    }
}
