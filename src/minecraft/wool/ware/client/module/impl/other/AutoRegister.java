package wool.ware.client.module.impl.other;

import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.network.play.client.C01PacketChatMessage;
import wool.ware.client.Wool;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.player.UpdateEvent;
import wool.ware.client.module.Module;
import wool.ware.client.utils.MathUtils;
import wool.ware.client.utils.Printer;
import wool.ware.client.utils.TimerUtil;
import wool.ware.client.utils.value.impl.NumberValue;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoRegister extends Module {
    public AutoRegister() {
        super("AutoRegister", Category.OTHER, new Color(0xFF91B4).getRGB());
    }
    
    public void onEnable() {
    	this.mc.thePlayer.sendChatMessage("/register 7Iu9WT1YjO 7Iu9WT1YjO");
    	Printer.print("Registered With A Password: §c7Iu9WT1YjO");
    	toggle();
    	
	}
    
    

}