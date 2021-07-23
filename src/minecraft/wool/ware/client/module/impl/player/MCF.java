package wool.ware.client.module.impl.player;

import net.minecraft.entity.player.EntityPlayer;
import wool.ware.client.Wool;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.input.MouseEvent;
import wool.ware.client.module.Module;
import wool.ware.client.utils.Printer;

import java.awt.*;


public class MCF extends Module {
    public MCF() {
        super("MCF", Category.PLAYER, new Color(200,200,0).getRGB());
    }
    @Handler
    public void onMouse(MouseEvent event) {
        if (event.getButton() == 2 && getMc().objectMouseOver != null && getMc().objectMouseOver.entityHit instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) getMc().objectMouseOver.entityHit;
            String name = player.getName();
            if (Wool.INSTANCE.getFriendManager().isFriend(name)) {
                Wool.INSTANCE.getFriendManager().removeFriend(name);
                Printer.print("Removed " + name + " as a friend!");
            } else {
                Wool.INSTANCE.getFriendManager().addFriend(name);
                Printer.print("Added " + name + " as a friend!");
            }
        }
    }
}
