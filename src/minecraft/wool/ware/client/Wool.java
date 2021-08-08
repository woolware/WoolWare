package wool.ware.client;

import net.minecraft.client.Minecraft;
import wool.ware.client.arraylist.ArrayListManager;
import wool.ware.client.command.CommandManager;
import wool.ware.client.config.ConfigManager;
import wool.ware.client.event.bus.EventBus;
import wool.ware.client.friend.FriendManager;
import wool.ware.client.gui.account.system.AccountManager;
import wool.ware.client.macro.MacroManager;
import wool.ware.client.module.ModuleManager;
import wool.ware.client.notification.NotificationManager;
import wool.ware.client.utils.thealtening.AltService;
import wool.ware.client.waypoint.WaypointManager;

import java.io.File;

public enum Wool {
    INSTANCE;
    private File dir;
    private EventBus eventBus = new EventBus();
    private ModuleManager moduleManager = new ModuleManager();
    private NotificationManager notificationManager = new NotificationManager();
    private ArrayListManager arraylistManager = new ArrayListManager();
    private CommandManager commandManager = new CommandManager();
    private AltService altService = new AltService();
    private FriendManager friendManager;
    private ConfigManager configManager;
    private WaypointManager waypointManager;
    private MacroManager macroManager;
    private AccountManager accountManager;
    private boolean autoRelogTheAltening = false;
    private boolean autoRelogNormal = false;
    public void startClient() {
        dir = new File(Minecraft.getMinecraft().mcDataDir,"WoolWare");
        moduleManager.setDir(new File(dir, "modules"));
        moduleManager.initializeModules();
        moduleManager.loadModules();
        friendManager = new FriendManager(dir);
        configManager = new ConfigManager(new File(dir, "configs"));
        configManager.load();
        waypointManager = new WaypointManager(dir);
        macroManager = new MacroManager(dir);
        commandManager.initialize();
        accountManager = new AccountManager(dir);
    }

    public void endClient() {
        moduleManager.saveModules();
        friendManager.getFriendSaving().saveFile();
        macroManager.save();
        accountManager.save();
    }

    public void switchToMojang() {
        try {
            altService.switchService(AltService.EnumAltService.MOJANG);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Couldnt switch to modank altservice");
        }
    }
    public void switchToTheAltening() {
        try {
            altService.switchService(AltService.EnumAltService.THEALTENING);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Couldnt switch to altening altservice");
        }
    }

    public ArrayListManager getArraylistManager() {
        return arraylistManager;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public File getDir() {
        return dir;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public FriendManager getFriendManager() {
        return friendManager;
    }

    public WaypointManager getWaypointManager() {
        return waypointManager;
    }

    public MacroManager getMacroManager() {
        return macroManager;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }

    public AltService getAltService() {
        return altService;
    }

    public boolean isAutoRelogTheAltening() {
        return autoRelogTheAltening;
    }

    public void setAutoRelogTheAltening(boolean autoRelogTheAltening) {
        this.autoRelogTheAltening = autoRelogTheAltening;
    }

    public boolean isAutoRelogNormal() {
        return autoRelogNormal;
    }

    public void setAutoRelogNormal(boolean autoRelogNormal) {
        this.autoRelogNormal = autoRelogNormal;
    }
}
