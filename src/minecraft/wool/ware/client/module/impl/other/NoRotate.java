package wool.ware.client.module.impl.other;

import java.awt.Color;

import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.game.PacketEvent;
import wool.ware.client.module.Module;


public class NoRotate extends Module {
    public NoRotate() {
        super("NoRotate", Category.OTHER, new Color(0x9D9798).getRGB());
        setRenderLabel("No Rotate");
        setDescription("Cancel ncp rotation flags.");
    }
    
    @Handler
    public void handle(PacketEvent event) {
        if (!event.isSending() && event.getPacket() instanceof S08PacketPlayerPosLook) {
            S08PacketPlayerPosLook packet = (S08PacketPlayerPosLook) event.getPacket();
            if (getMc().thePlayer != null && getMc().theWorld != null && getMc().thePlayer.rotationYaw != -180 && getMc().thePlayer.rotationPitch != 0) {
                packet.yaw = getMc().thePlayer.rotationYaw;
                packet.pitch = getMc().thePlayer.rotationPitch;
            }
        }
    }
}
