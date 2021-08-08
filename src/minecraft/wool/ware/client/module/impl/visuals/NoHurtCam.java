package wool.ware.client.module.impl.visuals;

import java.awt.*;

import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.render.HurtcamEvent;
import wool.ware.client.module.Module;

public class NoHurtCam extends Module {

    public NoHurtCam() {
        super("Hurt Cam", Category.VISUALS, new Color(0xA4A29E).getRGB());
    }
    @Handler
    public void Hurtcam(HurtcamEvent event) {
        event.setCanceled(true);
    }
}
