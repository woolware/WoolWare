package wool.ware.client.event;

import net.minecraft.network.Packet;
import wool.ware.client.event.Event;

public class EventReceivePacket extends Event {

    public Packet packet;
    private boolean outgoing;

    public EventReceivePacket(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }
    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public boolean isOutgoing() {
        return this.outgoing;
    }

    public boolean isIncoming() {
        return !this.outgoing;
    }
}
