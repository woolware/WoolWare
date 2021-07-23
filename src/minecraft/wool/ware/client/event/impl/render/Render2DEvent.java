package wool.ware.client.event.impl.render;

import net.minecraft.client.gui.ScaledResolution;
import wool.ware.client.event.Event;


public class Render2DEvent extends Event {
    private float partialTicks;
    private ScaledResolution scaledResolution;

    public Render2DEvent(float partialTicks, ScaledResolution scaledResolution) {
        this.partialTicks = partialTicks;
        this.scaledResolution = scaledResolution;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public ScaledResolution getScaledResolution() {
        return scaledResolution;
    }
}
