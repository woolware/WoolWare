package wool.ware.client.module.impl.other;

import org.apache.commons.lang3.StringUtils;

import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.game.TickEvent;
import wool.ware.client.module.Module;
import wool.ware.client.utils.value.impl.BooleanValue;
import wool.ware.client.utils.value.impl.EnumValue;
import wool.ware.client.utils.value.impl.NumberValue;

import java.awt.*;


public class Animation extends Module {
    public EnumValue<mode> Mode = new EnumValue<>("Mode",mode.WOOL);
    public NumberValue<Float> X = new NumberValue<>("X", 0.0F, -2.0F, 2.0F, 0.1F);
    public NumberValue<Float> Y = new NumberValue<>("Y", 0.0F, -2.0F, 2.0F, 0.1F);
    public NumberValue<Float> ZOOM = new NumberValue<>("Zoom",0.0F, -2.0F, 2.0F, 0.1F);
    public NumberValue<Float> SCALE = new NumberValue<>("Scale", 2.0F, -4.0F, 4.0F, 0.1F);
    public Animation() {
        super("Animation", Category.OTHER, new Color(0xFF4BAA).getRGB());
        setRenderLabel("Animation");
    }
    @Handler
    public void onTick(TickEvent event) {
        setSuffix(StringUtils.capitalize(Mode.getValue().toString().toLowerCase()));
    }
    public enum mode {
		OLD, NORMAL, HIDE, SLIDE, AUTOBAD, EXHID, SOFT, LIFE, TABLE, VIRTUE, REMIX, EXHIBITION, SIGMA, SENSATION, EXHIBITION2, TAP, STAB, KNIFE, 
		MAT, SWANG, SWANK, SWING, WIZZARD, WOOL, BLUE, SWIRL, CUSTOM
		
        
    }
}
