package wool.ware.client.command.impl;


import java.io.IOException;

import wool.ware.client.Wool;
import wool.ware.client.command.Command;
import wool.ware.client.config.Config;
import wool.ware.client.utils.Printer;

public class ConfigCMD extends Command {

    public ConfigCMD() {
        super("Config", new String[]{"c", "config", "configs"});
    }

    @Override
    public void onRun(final String[] s) {
    	if (s.length == 1) {
            Printer.print("config create/save configname ((keys) if you want to save with keybinds) - saves a config.");
            Printer.print("config override configname ((keys) if you want to override with keybinds) - overrides existing configs.");
            Printer.print("config delete/remove configname - removes a config.");
    		return;
    	}
        switch (s[1]) {
            case "list":
                if (!Wool.INSTANCE.getConfigManager().getConfigs().isEmpty()) {
                    Printer.print("Current Configs:");
                    Wool.INSTANCE.getConfigManager().getConfigs().forEach(cfg -> {
                        Printer.print(cfg.getName());
                    });
                } else {
                    Printer.print("You have no saved configs!");
                }
                break;
            case "help":
                Printer.print("config list - shows all configs.");
                Printer.print("config create/save configname ((keys) if you want to save with keybinds) - saves a config.");
                Printer.print("config override configname ((keys) if you want to override with keybinds) - overrides existing configs.");
                Printer.print("config delete/remove configname - removes a config.");
                break;
            case "create":
            case "save":
                if (!Wool.INSTANCE.getConfigManager().isConfig(s[2])) {
                    Wool.INSTANCE.getConfigManager().saveConfig(s[2], s.length > 3 && s[3].equalsIgnoreCase("keys"));
                    Wool.INSTANCE.getConfigManager().getConfigs().add(new Config(s[2]));
                    Printer.print("Created a config named " + s[2] + (s.length > 3 && s[3].equalsIgnoreCase("keys") ? " with keys included" : "") + "!");
                    Wool.INSTANCE.getNotificationManager().addNotification("Created a config named " + s[2] + (s.length > 3 && s[3].equalsIgnoreCase("keys") ? " with keys included" : "") + "!",2000);
                } else {
                    Printer.print(s[2] + " is already a saved config!");
                    Wool.INSTANCE.getNotificationManager().addNotification( s[2] + " is already a saved config!",2000);
                }
                break;
            case "delete":
            case "remove":
                if (Wool.INSTANCE.getConfigManager().isConfig(s[2])) {
                    Wool.INSTANCE.getConfigManager().deleteConfig(s[2]);
                    Printer.print("Deleted the config named " + s[2] + "!");
                    Wool.INSTANCE.getNotificationManager().addNotification( "Deleted the config named " + s[2] + "!",2000);
                } else {
                    Printer.print(s[2] + " is not a saved config!");
                    Wool.INSTANCE.getNotificationManager().addNotification( s[2] + " is not a saved config!",2000);
                }
                break;
            case "reload":
                Wool.INSTANCE.getConfigManager().getConfigs().clear();
                Wool.INSTANCE.getConfigManager().load();
                Printer.print("Reloaded all saved configs. Current number of configs: " + Wool.INSTANCE.getConfigManager().getConfigs().size() + "!");
                Wool.INSTANCE.getNotificationManager().addNotification( "Reloaded all saved configs. Current number of configs: " + Wool.INSTANCE.getConfigManager().getConfigs().size() + "!",2000);
                break;
            case "clear":
                try {
                    if (!Wool.INSTANCE.getConfigManager().getConfigs().isEmpty()) {
                        Wool.INSTANCE.getConfigManager().clear();
                        Wool.INSTANCE.getConfigManager().getConfigs().clear();
                        Printer.print("Cleared all saved configs!");
                        Wool.INSTANCE.getNotificationManager().addNotification( "Cleared all saved configs!",2000);
                    } else {
                        Printer.print("You have no saved configs!");
                        Wool.INSTANCE.getNotificationManager().addNotification( "You have no saved configs!",2000);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "override":
                if (Wool.INSTANCE.getConfigManager().isConfig(s[2])) {
                    Wool.INSTANCE.getConfigManager().saveConfig(s[2], s.length > 3 && s[3].equalsIgnoreCase("keys"));
                    Printer.print("Overrode the config named " + s[2] + (s.length > 3 && s[3].equalsIgnoreCase("keys") ? " with keys included" : "") + "!");
                    Wool.INSTANCE.getNotificationManager().addNotification( "Overrode the config named " + s[2] + (s.length > 3 && s[3].equalsIgnoreCase("keys") ? " with keys included" : "") + "!",2000);
                } else {
                    Printer.print(s[2] + " is not a saved config!");
                    Wool.INSTANCE.getNotificationManager().addNotification( s[2] + " is not a saved config!",2000);
                }
                break;
            case "load":
                if (Wool.INSTANCE.getConfigManager().isConfig(s[2])) {
                    Wool.INSTANCE.getConfigManager().loadConfig(s[2]);
                    Printer.print("Loaded the config named " + s[2] + "!");
                    Wool.INSTANCE.getNotificationManager().addNotification( "Loaded the config named " + s[2] + "!",2000);
                } else {
                    Printer.print(s[2] + " is not a saved config!");
                    Wool.INSTANCE.getNotificationManager().addNotification( s[2] + " is not a saved config!",2000);
                }
                break;
        }
    }
}
