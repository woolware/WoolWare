package wool.ware.client.module.impl.visuals;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.play.client.C03PacketPlayer;
import wool.ware.client.Wool;
import wool.ware.client.event.bus.*;
import wool.ware.client.event.impl.player.*;
import wool.ware.client.module.Module;
import wool.ware.client.utils.MathUtils;
import wool.ware.client.utils.Printer;
import wool.ware.client.utils.value.impl.EnumValue;

import org.apache.commons.lang3.*;

import java.awt.*;

public class Transform extends Module {
    public EnumValue<mode> Mode = new EnumValue<>("Mode",mode.XD);

    private float FallStack;

    public Transform() {
        super("Transform", Category.VISUALS, new Color(0xA4A29E).getRGB());
    }

    @Handler
    public void onUpdate(UpdateEvent event) {
        setSuffix(StringUtils.capitalize(Mode.getValue().name().toLowerCase()));
        if (Mode.getValue() == mode.XD) {
            if (event.isPre()) {
                if (getMc().thePlayer.getHeldItem() != null) {
                    getMc().thePlayer.renderArmPitch -= 18;
                }
            }
        }
        if (Mode.getValue() == mode.XDLOL) {
            if (event.isPre()) {
                if (getMc().thePlayer.getHeldItem() != null) {
                    getMc().thePlayer.renderArmYaw *= 1;
                }
            }
        }
    
    if (Mode.getValue() == mode.SMALL)
    	if(event.isPre()) {
    		GlStateManager.translate(0.75, -0.15, -1);
    		
    	}
	}

    @Override
    public void onEnable() {
        if (getMc().thePlayer == null || getMc().theWorld == null) return;
        FallStack = 0;
    }

    public enum mode {
        XD, XDLOL, SMALL
    }
}
