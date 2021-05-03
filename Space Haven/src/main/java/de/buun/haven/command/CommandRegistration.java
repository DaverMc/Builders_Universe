package de.buun.haven.command;

import de.buun.haven.BuildersUniverseException;
import de.buun.haven.Loggers;
import de.buun.haven.SpacePlugin;
import de.buun.haven.util.Reflections;
import de.buun.haven.util.Registration;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.*;
import java.util.stream.Collectors;

public class CommandRegistration implements Registration<AbstractCommand> {

    @Getter
    private final Map<String, AbstractCommand> commandMap;

    public CommandRegistration(){
        this.commandMap = new HashMap<>();
    }

    @Override
    public void register(AbstractCommand value) {
        if(!bukkitRegister(value)) return;
       commandMap.put(value.getName(), value);
       Loggers.logInfo("Registered /" + value.getName() + " commmand!");
    }

    private boolean bukkitRegister(AbstractCommand command){
        SimpleCommandMap bukkitMap = getCommandMap();
        if(bukkitMap == null) return false;

        BukkitCommand bukkitCommand = new BukkitCommand(command.getName()) {
            @Override
            public boolean execute(CommandSender commandSender, String s, String[] strings) {
                new CommandExecution(command, commandSender, strings);
                return true;
            }
        };

        setAliases(command, bukkitCommand);
        bukkitMap.register(command.getName(), bukkitCommand);

        return true;
    }

    private void setAliases(AbstractCommand command, BukkitCommand bukkitCommand){
        if(!command.getClass().isAnnotationPresent(CommandModifier.class)) return;
        List<String> aliases = new ArrayList<>();
        CommandModifiers modifiers = command.getClass().getAnnotation(CommandModifiers.class);
        List<CommandModifier> commandModifiers = Arrays.stream(modifiers.value())
                .filter(commandModifier -> commandModifier.value() == ModifierType.ALIAS).collect(Collectors.toList());
        commandModifiers.forEach(commandModifier -> aliases.addAll(Arrays.asList(commandModifier.string())));

        bukkitCommand.setAliases(aliases);
    }

    private SimpleCommandMap getCommandMap(){
        SimpleCommandMap map = Reflections.getFieldValue(Bukkit.getServer(), "commandMap");
        if(map == null){
            Loggers.logError(new BuildersUniverseException("The Spigot Command Map was not found!"));
        }
        return map;
    }

    @Override
    public void unregister(AbstractCommand value) {
        commandMap.remove(value.getName());
        SimpleCommandMap map = getCommandMap();
        if(map == null) return;

        Map<String, Command> cmdMap = Reflections.getFieldValue(map, "knownCommands");
        if(cmdMap == null){
            Loggers.logError(new BuildersUniverseException("The KnownCommandMap Map was not found!"));
            return;
        }
        cmdMap.remove(value.getName());
        Reflections.setFieldValue(map, "knownCommands", cmdMap);

    }
}
