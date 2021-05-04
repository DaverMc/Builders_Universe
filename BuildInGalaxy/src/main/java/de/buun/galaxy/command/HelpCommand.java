package de.buun.galaxy.command;

import de.buun.galaxy.BuildInGalaxy;
import de.buun.haven.command.AbstractCommand;
import org.bukkit.command.CommandSender;

public class HelpCommand extends AbstractCommand {


    protected HelpCommand() {
        super("bhelp");
    }

    @Override
    protected void run(CommandSender sender, String[] args) {
        BuildInGalaxy.getInstance().getFramework().getCommandRegistration().getCommandMap().forEach((key, command) -> {
            StringBuilder builder = new StringBuilder();
            command.getDescription().forEach(k -> builder.append(" " + k));
            sender.sendMessage("§7- /" + key + "  " + builder.toString());
        });
    }
}
