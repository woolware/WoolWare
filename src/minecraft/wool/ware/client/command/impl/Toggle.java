package wool.ware.client.command.impl;


import java.util.Objects;

import wool.ware.client.Wool;
import wool.ware.client.command.Command;
import wool.ware.client.module.Module;
import wool.ware.client.utils.Printer;

public class Toggle extends Command {

	public Toggle() {
		super("Toggle",new String[]{"t","toggle"});
	}

	@Override
	public void onRun(final String[] s) {
		if (s.length <= 1) {
			Printer.print("Not enough args.");
			return;
		}
			for (Module m : Wool.INSTANCE.getModuleManager().getModuleMap().values()) {
				if (m.getLabel().toLowerCase().equals(s[1])) {
					m.toggle();
					Wool.INSTANCE.getNotificationManager().addNotification("Toggled " + (Objects.nonNull(m.getRenderLabel()) ? m.getRenderLabel():m.getLabel()), 2000);
                    Printer.print("Toggled " + m.getLabel());
					break;
				}
			}
	}
}
