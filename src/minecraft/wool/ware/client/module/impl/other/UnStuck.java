package wool.ware.client.module.impl.other;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.bus.Priority;
import wool.ware.client.event.impl.game.PacketEvent;
import wool.ware.client.event.impl.player.MotionEvent;
import wool.ware.client.module.Module;
import wool.ware.client.utils.TimerUtil;
import wool.ware.client.utils.value.impl.BooleanValue;
import wool.ware.client.utils.value.impl.NumberValue;

import java.awt.*;

public class UnStuck extends Module {
    private final NumberValue<Long> delay = new NumberValue<>("Delay", 500L, 50L, 1000L, 50L);
    private final BooleanValue automated = new BooleanValue("Automated", true);
    private int setbackCount;
    private TimerUtil timer = new TimerUtil();
    public UnStuck() {
        super("UnStuck", Category.OTHER, new Color(125, 125, 215).getRGB());
    }

    @Handler
    public void packetEvent(PacketEvent e) {
        if (e.isSending()) {
            if (e.getPacket() instanceof C03PacketPlayer) {
                if (isStuck() || !automated.isEnabled()) {
                    e.setCanceled(true);
                }
            }
        } else {

            if (e.getPacket() instanceof S08PacketPlayerPosLook) {
                if (!timer.reach(delay.getValue())) {
                    setbackCount++;
                } else {
                    setbackCount = 1;
                }
                timer.reset();
            }
        }
    }

    @Handler(value = Priority.HIGHEST)
    public void moveEvent(MotionEvent e) {
        if (isStuck() || !automated.isEnabled()) {
            e.setX(getMc().thePlayer.motionX = 0);
            e.setY(getMc().thePlayer.motionY = 0);
            e.setZ(getMc().thePlayer.motionZ = 0);
        }
    }

    private boolean isStuck() {
        return setbackCount > 5 && !timer.reach(delay.getValue());
    }
}
