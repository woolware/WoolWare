package wool.ware.client.module.impl.movement;

import net.minecraft.network.play.client.*;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.game.PacketEvent;
import wool.ware.client.event.impl.player.UpdateEvent;
import wool.ware.client.module.Module;
import wool.ware.client.utils.value.impl.EnumValue;

import java.awt.*;

public class Sneak extends Module {
    private EnumValue<Sneak.mode> Mode = new EnumValue<>("Mode", mode.VANILLA);
    private boolean snuck = false;
    public Sneak() {
        super("Sneak", Category.MOVEMENT, new Color(0, 255, 0).getRGB());
    }

    public enum mode {
        VANILLA, NCP, DEV
    }

    @Override
    public void onDisable() {
        if (getMc().thePlayer == null || getMc().theWorld == null) return;
        if (Mode.getValue() == mode.VANILLA) {
            getMc().thePlayer.sendQueue.addToSendQueueNoEvents(new C0BPacketEntityAction(getMc().thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
        }
        snuck = false;
    }

    @Override
    public void onEnable() {
        if (getMc().thePlayer == null || getMc().theWorld == null) return;
        if (Mode.getValue() == mode.VANILLA) {
            getMc().thePlayer.sendQueue.addToSendQueueNoEvents(new C0BPacketEntityAction(getMc().thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
        }
    }

    @Handler
    public void onPacket(PacketEvent event) {
        if (event.isSending()) {
            if (event.getPacket() instanceof C0BPacketEntityAction) {
                C0BPacketEntityAction packet = (C0BPacketEntityAction) event.getPacket();
                if (packet.getAction().equals(C0BPacketEntityAction.Action.START_SNEAKING) && Mode.getValue() == mode.VANILLA && !snuck) {
                    snuck = true;
                } else if (packet.getAction().equals(C0BPacketEntityAction.Action.START_SNEAKING) || packet.getAction().equals(C0BPacketEntityAction.Action.STOP_SNEAKING)) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @Handler
    public void onUpdate(UpdateEvent event) {
        switch (Mode.getValue()) {
            case NCP:
                if (event.isPre()) {
                    getMc().thePlayer.sendQueue.addToSendQueueNoEvents(new C0BPacketEntityAction(getMc().thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
                } else {
                    getMc().thePlayer.sendQueue.addToSendQueueNoEvents(new C0BPacketEntityAction(getMc().thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
                }
                break;
            case DEV:
                break;
        }
    }
}
