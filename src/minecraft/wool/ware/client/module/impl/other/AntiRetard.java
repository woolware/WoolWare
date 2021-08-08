package wool.ware.client.module.impl.other;

import java.awt.Color;
import java.util.Random;

import net.minecraft.network.play.server.S02PacketChat;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.game.PacketEvent;
import wool.ware.client.module.Module;

public class AntiRetard extends Module {
    private final String[] nigga = new String[]{"hack", "report", "cheat",
            "aura", "speed", "record", "what client", "ban", "bhop", "bunny hop", "hax"};

    public AntiRetard() {
        super("AntiRetard", Category.OTHER, new Color(0xAEFFDE).getRGB());
        setDescription("Deal With Hackusators");
        setRenderLabel("AntiRetard");
    }

    @Handler
    public void onPacket(PacketEvent event) {
        if (!event.isSending()) {
            if (event.getPacket() instanceof S02PacketChat) {
                S02PacketChat packet = (S02PacketChat) event.getPacket();
                for (String str : nigga) {
                    if (packet.getChatComponent().getUnformattedText().contains(str)) {
                    	String[] pre = new String[]{"here's your tickets to the juice wrld concert",
    							"i don't cheat, you just need to click faster",
    							"i hope you fall off a cliff",
    							"i don't cheat it's just my RTX 3090 with mega power support",
    							"if lixo means you are gay then you are",
    							"imagine getting femboy'd",
    							"i speak english not your gibberish",
    							"i understand why your parents abused you",
    							"i'd tell you to uninstall, but your aim is so bad you wouldn't hit the button",
    							"im not saying you're worthless, but i'd unplug ur life support to charge my phone",
    							"need some pvp advice?",
    							"you do be lookin' kinda bad at the game",
    							};
    					Random rand = new Random();
    					int i = rand.nextInt(pre.length - 1);
    					String message = pre[i].concat(" ");
    					mc.thePlayer.sendChatMessage(message);
                    }
                }
            }
        }
    }
}