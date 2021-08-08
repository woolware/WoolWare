package wool.ware.client.module.impl.other;

import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.game.PacketEvent;
import wool.ware.client.module.Module;
import wool.ware.client.utils.Printer;
import wool.ware.client.utils.value.impl.BooleanValue;

import java.awt.*;

public class AntiFreeze extends Module {
    private BooleanValue safetp = new BooleanValue("SafeTP",true);
    public AntiFreeze() {
        super("AntiFreeze", Category.OTHER, new Color(0xA25B41).getRGB());
        setDescription("Anti freeze screen");
        setRenderLabel("Anti Freeze");
    }

    @Handler
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof S2DPacketOpenWindow) {
            S2DPacketOpenWindow packetOpenWindow = (S2DPacketOpenWindow) event.getPacket();
            if (packetOpenWindow.getWindowTitle().getUnformattedText().toLowerCase().contains("frozen")) {
                event.setCanceled(true);
            }
        }

        if (event.getPacket() instanceof S02PacketChat && safetp.isEnabled()) {
            S02PacketChat packet = (S02PacketChat) event.getPacket();
            if (packet.getChatComponent().getFormattedText().contains("is currently frozen, you may not attack.")) {
                getMc().thePlayer.setPositionAndUpdate(0, -999, 0);
                Printer.print("The person you tried to attack was frozen, teleported you to spawn.");
            }
        }
    }
}
