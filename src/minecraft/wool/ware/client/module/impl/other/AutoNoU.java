package wool.ware.client.module.impl.other;

import java.awt.Color;
import java.util.Random;

import net.minecraft.network.play.server.S02PacketChat;
import wool.ware.client.Wool;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.game.PacketEvent;
import wool.ware.client.module.Module;

public class AutoNoU extends Module {
    private final String[] nigga = new String[]{"gay", "fag", "stupid",
            "retard", "idiot", "skid", "paste", "loser", "cheater", "hacker", "retard",
            "kys", "neck your self", "delete self", "cunt", "suck", "kill yourself", "lixo"};

    public AutoNoU() {
        super("AutoNoU", Category.OTHER, new Color(0xAEFFDE).getRGB());
        setDescription("no you are not");
        setRenderLabel("AutoNoU");
    }

    @Handler
    public void onPacket(PacketEvent event) {
        if (!event.isSending()) {
            if (event.getPacket() instanceof S02PacketChat) {
                S02PacketChat packet = (S02PacketChat) event.getPacket();
                for (String str : nigga) {
                    if (packet.getChatComponent().getUnformattedText().contains(str)) {
                    	String[] pre = new String[]{"no u"};
    					Random rand = new Random();
    					int i = rand.nextInt(pre.length - 1);
    					String message = pre[i].concat(" ");
    					mc.thePlayer.sendChatMessage(message);
    					Wool.INSTANCE.getNotificationManager().addNotification("Detected A Salty Noob, Auto no u", 2500);
                    }
                }
            }
        }
    }
}