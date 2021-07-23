package wool.ware.client.module.impl.visuals;

import java.awt.Color;

import wool.ware.client.module.Module;
import wool.ware.client.Wool;
import wool.ware.client.module.Module.Category;

public class FemBoy extends Module {

	  public FemBoy() {
	        super("FemBoy", Category.VISUALS, new Color(0xA75E72).getRGB());
	  }
	  
	public void onEnable() {
		
		  
		  Wool.INSTANCE.getNotificationManager().addNotification("FemBoys Are Cute UwU :3", 50000);
		
	}
	
	public void onDisable() {
		
		  
		  Wool.INSTANCE.getNotificationManager().addNotification("Why You Want To Get Rid Of Femboys? :(", 5000);
		
	}

}
