package de.buun.haven.command;

import lombok.Getter;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCommand {

    @Getter
    private String name;
    @Getter
    private List<String> description;
    @Getter
    private Map<String, Method> subCommands;

    protected AbstractCommand(String name){
        this.name = name;
        this.subCommands = getSubCommandMethods();
    }

    protected AbstractCommand(String name, List<String> description){
        this(name);
        this.description = description;
    }

    public void registerSubCommand(AbstractSubCommand subCommand){
         Arrays.stream(subCommand.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(SubCommand.class))
                .forEach(method -> {
                    SubCommand anno = method.getAnnotation(SubCommand.class);
                    this.getSubCommandMethods().put(subCommand.getName() + " " + anno.name(), method);
                });
    }

    private Map<String, Method> getSubCommandMethods(){
        Map<String, Method> map = new HashMap<>();
        Arrays.stream(getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(SubCommand.class))
                .forEach(method -> {
                    SubCommand anno = method.getAnnotation(SubCommand.class);
                    map.put(anno.name(), method);
                });
        return map;
    }

    protected abstract void run(CommandSender sender, String[] args);

}
