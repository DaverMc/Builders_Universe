package de.daver.antientity;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WhitelistReloadCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!command.getLabel().equalsIgnoreCase("aereload")) return false;
        if(!commandSender.hasPermission("antientity.reload")) return false;

        AntiEntity.getWhitelist().reload();
        AntiEntity.getWhitelist().searchWhitelisted();
        commandSender.sendMessage("§4Anti§cEntity  §dWhitelist was succesfully reloaded!");

        return true;
    }
}
