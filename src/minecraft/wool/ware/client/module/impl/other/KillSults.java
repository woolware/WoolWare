package wool.ware.client.module.impl.other;

import java.awt.Color;
import java.util.Random;

import net.minecraft.network.play.server.S02PacketChat;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.game.PacketEvent;
import wool.ware.client.module.Module;

public class KillSults extends Module {
    private final String[] nigga = new String[]{"morreu para", "by", "von", "por", "la vida", "la quito"};

    public KillSults() {
        super("KillSults", Category.OTHER, new Color(0xAEFFDE).getRGB());
        setDescription("Automatically sends an insult on kill");
        setRenderLabel("KillSults");
    }

    @Handler
    public void onPacket(PacketEvent event) {
        if (!event.isSending()) {
            if (event.getPacket() instanceof S02PacketChat) {
                S02PacketChat packet = (S02PacketChat) event.getPacket();
                for (String str : nigga) {
                    if (packet.getChatComponent().getUnformattedText().contains(str)) {
                    	String[] pre = new String[]{
                    			"here's your tickets to the juice wrld concert",
    							"hey look! it's a fortnite player",
    							"i bet you probably shop at Costco",
    							"i don't cheat, you just need to click faster",
    							"i hope you fall off a cliff",
    							"i speak english not your gibberish",
    							"i understand why your parents abused you",
    							"i'd tell you to uninstall, but your aim is so bad you wouldn't hit the button",
    							"im not saying you're worthless, but i'd unplug ur life support to charge my phone",
    							"need some pvp advice?",
    							"you do be lookin' kinda bad at the game",
    							"you look like you were drawn with my left hand",
    							"you pressed the wrong button when you installed Minecraft",
    							"you should look into buying a client",
    							"you're so white that you don't play on vanilla, you play on clear",
    							"your difficulty settings must be stuck on easy",
    							"drown in your own salt",
    							"even your mom is better than you in this game",
    							"go back to fortnite you degenerate",
    							"go commit stop breathing plz",
    							"go play roblox you worthless fucking degenerate,", 
    							"go take a long walk on a short bridge",
    							"i swear on jhalt, you got shit on hard than archy",
    							"if the body is 70% water then how is your body 100% salt?",
    							"lol you probably speak dog eater",
    							"mans probably got an error on his hello world program lmao",
    							"mans probably plays fortnite lmao",
    							"no top hat, you're more trash than my garbage can",
    							"plz no repotr i no want ban plesae!",
    							"final come home",
    							"rage at me on discord laughflowglue#0001",
    							"rage at me on discord Developing();#7777",
    							"report me im really scared",
    							"seriously? go back to fortnite monkey brain",
    							"some kids were dropped at birth, but you got thrown at the wall",
    							"you really like taking L's",
    							"you're the type of guy to quickdrop irl",
    							"you're the type to get 3rd place in a 1v1",
    							"your iq is that of a steve",
    							"your parents abandoned you, then the orphanage did the same",
    							"you go to the doctors and they say you shrunk",
    							"woolware, drop kicking lil kids since 2019",
    							"who would win? $400,000 per year anticheat or a single packet",
    							"is watchdog watching a dog or a dog watching a watch?",
    							"yo mama so fat, she sat on an iphone and it became an ipad",
    							"on black friday, black people die",
    							"search up blue waffle on google, it's so cute",
    							"this anticheat is disabled as you, fucking vegetable",
    							"you smell like a moldy ballsack",
    							"your grandmother has chlamydia",
    							"your aim is like a toddler with parkinson's trying to aim a water gun",
    							"welcome to my basement",
    							"i'd insult you after that death but it's funnier to let you imagine what i'm calling you",
    							"yo whens the documentary crew coming to your house to film the next episode of my 600 pound life?",
    							"you have the iq of a table",
    							"you are the type of person to think FOV increases reach",
    							"you're so gay you bought the iphone 5c instead of a newer phone because of the colors",
    							"your iq is the same of a rock",
    							"you probably bought vape v4",
    							"you shouldn't be running away with all these monkeys coming after you",
    							"yes, record me, send the footage straight to child lover tenebrous",
    							"your killaura was coded in scratch with help from zhn",
    							"you deserved to be bhopped on",
    							"your birth certificate was an apology letter from the condom factory",
    							"always remember you're unique - just like everyone else",
    							"how do you keep an idiot amused? watch this message until it fades away",
    							"if practice makes perfect, and nobody's perfect, why practice?",
    							"if i could rearrange the alphabet, i'd put U and I as far away as possible",
    							"i'd smack you, but that would be animal abuse",
    							"if i wanted to kill myself, i'd climb your ego and jump to your IQ",
    							"this kid is so annoying, he made his happy meal cry",
    							"your face makes onions cry",
    							"you are like a cloud, when you disappear it's a beautiful day",
    							"you bring everyone so much joy! you know, when you leave the room. but, still",
    							"you are missing a brain",
    							"are you a primate?",
    							"you're so ugly your portraits hang themselves", 
    							"you probably support frost client", 
    							"You should add me on discord Developing();#7777 and get woolware for free!", 
    							"if i would want to kill myself i would climb to your ego and jump to your iq level", 
    							"buy woolware client at woolware client dot store", 
    							"so bad lmao better buy woolware at woolware dot store", 
    							"woolware client dot store for cheap client :D", 
    							"buy woolware now at woolware.store"
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