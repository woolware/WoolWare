package wool.ware.client.module.impl.other;

import net.minecraft.block.BlockAir;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import wool.ware.client.Wool;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.game.PacketEvent;
import wool.ware.client.module.Module;
import wool.ware.client.utils.MathUtils;
import wool.ware.client.utils.Printer;
import wool.ware.client.utils.value.impl.BooleanValue;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class Detector extends Module {
    private BooleanValue autodisable = new BooleanValue("Autodisable", true);
    private BooleanValue unblock = new BooleanValue("Unblock", true);
    private BooleanValue chat = new BooleanValue("Chat", true);
    private BooleanValue strict = new BooleanValue("Strict", true);
    private BooleanValue suspect = new BooleanValue("CheatingWarning", true);
    private BooleanValue autoNoU = new BooleanValue("AutoNoU", true);

    private final List<String> hackMatches = Arrays.asList("hack", "report", "cheat",
            "aura", "speed", "record", "what client", "ban", "bhop", "bunny hop", "hax");

    private final List<String> noUMatches = Arrays.asList("gay", "fag", "stupid",
            "retard", "idiot", "skid", "loser", "cheater", "retard",
            "kys", "neck your self", "delete self", "cunt", "suck", "kill yourself");

    public Detector() {
        super("Detector", Category.OTHER, new Color(0xA75E62).getRGB());
    }

    @Handler
    public void onPacket(PacketEvent event) {
        if (getMc().thePlayer == null || getMc().theWorld == null) {
            return;
        }
        if (!event.isSending()) {
            if (event.getPacket() instanceof S08PacketPlayerPosLook) {
                final S08PacketPlayerPosLook packet = (S08PacketPlayerPosLook) event.getPacket();
                final Vec3 tppos = new Vec3(packet.getX(), packet.getY(), packet.getY());
                double dist = tppos.squareDistanceTo(getMc().thePlayer.getPositionVector());
                if (unblock.getValue()) {
                    getMc().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                }
                if (autodisable.getValue() && (!(getMc().theWorld.getBlockState(new BlockPos(getMc().thePlayer.posX, getMc().thePlayer.posY - 1.0, getMc().thePlayer.posZ)).getBlock() instanceof BlockAir) || !(getMc().theWorld.getBlockState(new BlockPos(getMc().thePlayer.posX, getMc().thePlayer.posY - 2.0, getMc().thePlayer.posZ)).getBlock() instanceof BlockAir))) {
                    if (Wool.INSTANCE.getModuleManager().getModule("speed").isEnabled() || Wool.INSTANCE.getModuleManager().getModule("flight").isEnabled() || Wool.INSTANCE.getModuleManager().getModule("longjump").isEnabled()) {
                        Wool.INSTANCE.getModuleManager().getModule("speed").setEnabled(false);
                        Wool.INSTANCE.getModuleManager().getModule("flight").setEnabled(false);
                        Wool.INSTANCE.getModuleManager().getModule("longjump").setEnabled(false);
                        Wool.INSTANCE.getNotificationManager().addNotification("Lag Back/Teleport! Turned Off Some Mods", 2500);
                        return;
                    }
                }
            } else if (chat.getValue() && event.getPacket() instanceof S02PacketChat) {
                final S02PacketChat chatPacket = (S02PacketChat) event.getPacket();
                final String message = chatPacket.getChatComponent().getUnformattedText();
                if (message.toLowerCase().contains(getMc().getSession().getUsername().toLowerCase()) || !strict.getValue()) {
                    if (autoNoU.isEnabled()) {
                        for (String noUMatch : noUMatches) {
                            if (!message.contains(noUMatch) || message.contains(getMc().thePlayer.getName())) {
                                continue;
                            }
                            getMc().thePlayer.sendChatMessage("no u " + MathUtils.getRandomInRange(1E5, 1E8));
                            Wool.INSTANCE.getNotificationManager().addNotification("Detected A Salty Noob, Auto no u", 2500);
                            break;
                        }
                    }
                    if (suspect.isEnabled()) {
                        for (String hackMatch : hackMatches) {
                            if (!message.contains(hackMatch)) {
                                continue;
                            }
                            Wool.INSTANCE.getNotificationManager().addNotification("Suspected Of Cheating!", 2500);
                            break;
                        }
                    }
                }
                if (message.equalsIgnoreCase("ground items will be removed in ")) {
                    Wool.INSTANCE.getNotificationManager().addNotification("Ground items will be gone in " + message.split("be removed in ")[1], 2500);
                }
            }
        }
    }
}
