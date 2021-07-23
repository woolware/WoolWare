package wool.ware.client.event.impl.game;

import net.minecraft.client.multiplayer.WorldClient;
import wool.ware.client.event.Event;

public class WorldLoadEvent extends Event {
    private WorldClient worldClient;

    public WorldLoadEvent(WorldClient worldClient) {
        this.worldClient = worldClient;
    }

    public WorldClient getWorldClient() {
        return worldClient;
    }
}
