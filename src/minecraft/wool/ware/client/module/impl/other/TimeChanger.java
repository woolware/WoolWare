package wool.ware.client.module.impl.other;

import com.google.common.eventbus.Subscribe;

import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.game.PacketEvent;
import wool.ware.client.event.impl.game.TickEvent;
import wool.ware.client.module.Module;
import wool.ware.client.utils.value.impl.NumberValue;

import java.awt.*;

public class TimeChanger extends Module {
    public NumberValue<Long> time = new NumberValue<>("Time", 18400L, 0L, 24000L, 100L);
    public TimeChanger() {
        super("TimeChanger", Category.OTHER, new Color(0x8D9D3C).getRGB());
        setRenderLabel("Time Changer");
        setDescription("Change client-side time.");
    }
}
