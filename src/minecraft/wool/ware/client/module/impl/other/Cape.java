package wool.ware.client.module.impl.other;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import wool.ware.client.Wool;
import wool.ware.client.module.Module;


public class Cape extends Module {

    public Cape() {
        super("Cape", Module.Category.PLAYER, 0xffaEfdEe);
        setHidden(true);
    }

    public ResourceLocation getCape() {
        return new ResourceLocation("textures/client/cape/cape.png");
    }

    public boolean canRender(AbstractClientPlayer player) {
        if(player == getMc().thePlayer) return true;
        return isEnabled() && Wool.INSTANCE.getFriendManager().isFriend(player.getName());
    }
}
