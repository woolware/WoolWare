package wool.ware.client.command.impl;

import org.apache.commons.lang3.text.WordUtils;

import wool.ware.client.Wool;
import wool.ware.client.command.Command;
import wool.ware.client.utils.Printer;

public class Help extends Command {

	public Help() {
		super("Help", new String[]{"h", "help"});
	}

	@Override
	public void onRun(final String[] s) {
		Wool.INSTANCE.getCommandManager().getCommandMap().values().forEach(command -> {
			Printer.print(WordUtils.capitalizeFully(command.getLabel()));
		});
	}
}
