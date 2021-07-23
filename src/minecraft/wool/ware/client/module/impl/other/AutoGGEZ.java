package wool.ware.client.module.impl.other;

import java.awt.Color;

import net.minecraft.network.play.server.S02PacketChat;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.game.PacketEvent;
import wool.ware.client.module.Module;

public class AutoGGEZ extends Module {
    private final String[] nigga = new String[]{"Deseja jogar novamente?", "1st Killer - ", "1st Place - ", "Winner: ", " - Damage Dealt - ", "1st - ", "Winning Team - ", "Winners: ", "Winner: ", "Winning Team: ", " win the game!", "Top Seeker: ","1st Place: ","Last team standing!","Winner #1 (", "Top Survivors","Winners - "};

    public AutoGGEZ() {
        super("AutoGGEZ", Category.OTHER, new Color(0xAEFFDE).getRGB());
        setDescription("Automatically ez ggs to be gay");
        setRenderLabel("Auto GG EZ");
    }

    @Handler
    public void onPacket(PacketEvent event) {
        if (!event.isSending()) {
            if (event.getPacket() instanceof S02PacketChat) {
                S02PacketChat packet = (S02PacketChat) event.getPacket();
                for (String str : nigga) {
                    if (packet.getChatComponent().getUnformattedText().contains(str)) {
                        getMc().thePlayer.sendChatMessage("gg ez");
                    }
                }
            }
        }
    }
}