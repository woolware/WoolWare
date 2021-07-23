package wool.ware.client.command.impl;

import wool.ware.client.Wool;
import wool.ware.client.command.Command;
import wool.ware.client.utils.Printer;

public class Modules extends Command {

    public Modules() {
        super("Modules", new String[]{"modules","mods","m"});
    }

    @Override
    public void onRun(final String[] s) {
        StringBuilder mods = new StringBuilder("Modules (" + Wool.INSTANCE.getModuleManager().getModuleMap().values().size() + "): ");
        Wool.INSTANCE.getModuleManager().getModuleMap().values()
                .forEach(mod -> mods.append(mod.isEnabled() ? "\247a" : "\247c").append(mod.getLabel()).append("\247r, "));
        Printer.print(mods.toString().substring(0, mods.length() - 2));
    }
}