package wool.ware.client.module.impl.other;

import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.network.play.client.C01PacketChatMessage;
import wool.ware.client.Wool;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.player.UpdateEvent;
import wool.ware.client.module.Module;
import wool.ware.client.utils.MathUtils;
import wool.ware.client.utils.TimerUtil;
import wool.ware.client.utils.value.impl.NumberValue;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spammer extends Module {
    private TimerUtil timer = new TimerUtil();
    private int index = 0;
    private List<String> StringList;
    private Random random = new Random();
    private NumberValue<Double> delay = new NumberValue<>("Delay",1.0,0.1,10.0,0.1);
    public Spammer() {
        super("Spammer", Category.OTHER, new Color(0xFF91B4).getRGB());
    }

    @Handler
    public void onUpdate(UpdateEvent event) {
        if (getMc().theWorld == null) return;
        if (timer.sleep((int) (delay.getValue() * 1000))) {
            if (index < StringList.size()) {
                final ArrayList<NetworkPlayerInfo> niggas = new ArrayList<>(getMc().thePlayer.sendQueue.getPlayerInfoMap());
                final String msg = StringList.get(index).replace("%RANDOMPLAYER%", niggas.size() < 2 ? "": niggas.get(random.nextInt(niggas.size())).getGameProfile().getName()).replace("%INVISIBLE%","\u061C").replace("%RANDOMNUMBER%", String.valueOf(MathUtils.getRandomInRange(10000, 99999)));
                getMc().thePlayer.sendQueue.getNetworkManager().sendPacket(new C01PacketChatMessage(msg));
                index++;
            } else {
                index = 0;
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        StringList.clear();
        StringList = null;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        try {
            File file = new File(Wool.INSTANCE.getDir(), "spam.txt");
            if (file.exists()) {
                List<String> lines = Files.readAllLines(file.toPath());
                if (lines.size() > 0) {
                    StringList = lines;
                    return;
                }
            }
            file.createNewFile();
            StringList = new ArrayList<>();
            StringList.add("WoolWare is the best client! %RANDOMNUMBER%");
        } catch (IOException ignored) {
        }
        index = 0;
    }
}