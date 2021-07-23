package wool.ware.client.command.impl;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import wool.ware.client.Wool;
import wool.ware.client.command.Command;
import wool.ware.client.utils.Printer;


public class Macros extends Command {

    public Macros() {
        super("Macros", new String[]{"macros", "mac", "macro"});
    }

    @Override
    public void onRun(final String[] args) {
        switch (args[1].toLowerCase()) {
            case "list":
                if (Wool.INSTANCE.getMacroManager().getMacros().isEmpty()) {
                    Printer.print("Your macro list is empty.");
                    return;
                }
                Printer.print("Your macros are:");
                Wool.INSTANCE.getMacroManager().getMacros().values().forEach(macro -> Printer.print("Label: " + macro.getLabel() + ", Keybind: " + Keyboard.getKeyName(macro.getKey()) + ", Text: " + macro.getText() + "."));
                break;
            case "reload":
                Wool.INSTANCE.getMacroManager().clearMacros();
                Wool.INSTANCE.getMacroManager().load();
                Printer.print("Reloaded macros.");
                break;
            case "remove":
            case "delete":
                if (args.length < 3) {
                    Printer.print("Invalid args.");
                    return;
                }
                if (Wool.INSTANCE.getMacroManager().isMacro(args[2])) {
                    Wool.INSTANCE.getMacroManager().removeMacroByLabel(args[2]);
                    Printer.print("Removed a macro named " + args[2] + ".");
                    if (Wool.INSTANCE.getMacroManager().getMacroFile().exists()) {
                        Wool.INSTANCE.getMacroManager().save();
                    } else {
                        try {
                            Wool.INSTANCE.getMacroManager().getMacroFile().createNewFile();
                            Wool.INSTANCE.getMacroManager().save();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Printer.print(args[2] + " is not a macro.");
                }
                break;
            case "clear":
                if (Wool.INSTANCE.getMacroManager().getMacros().isEmpty()) {
                    Printer.print("Your macro list is empty.");
                    return;
                }
                Printer.print("Cleared all macros.");
                Wool.INSTANCE.getMacroManager().clearMacros();
                if (Wool.INSTANCE.getMacroManager().getMacroFile().exists()) {
                    Wool.INSTANCE.getMacroManager().save();
                } else {
                    try {
                        Wool.INSTANCE.getMacroManager().getMacroFile().createNewFile();
                        Wool.INSTANCE.getMacroManager().save();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "add":
            case "create":
                if (args.length < 5) {
                    Printer.print("Invalid args.");
                    return;
                }
                int keyCode = Keyboard.getKeyIndex(args[3].toUpperCase());
                if (keyCode != -1 && !Keyboard.getKeyName(keyCode).equals("NONE")) {
                    if (Wool.INSTANCE.getMacroManager().getMacroByKey(keyCode) != null) {
                        Printer.print("There is already a macro bound to that key.");
                        return;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 4; i < args.length; i++) {
                        stringBuilder.append(args[i]);
                        if (i != args.length - 1) stringBuilder.append(" ");
                    }
                    Wool.INSTANCE.getMacroManager().addMacro(args[2], keyCode, stringBuilder.toString());
                    Printer.print("Bound a macro named " + args[2] + " to the key " + Keyboard.getKeyName(keyCode) + ".");
                    if (Wool.INSTANCE.getMacroManager().getMacroFile().exists()) {
                        Wool.INSTANCE.getMacroManager().save();
                    } else {
                        try {
                            Wool.INSTANCE.getMacroManager().getMacroFile().createNewFile();
                            Wool.INSTANCE.getMacroManager().save();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Printer.print("That is not a valid key code.");
                }
                break;
        }
    }
}