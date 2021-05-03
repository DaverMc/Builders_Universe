package de.buun.haven.command;

import de.buun.haven.Loggers;
import de.buun.haven.util.Reflections;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Method;

public class CommandExecution {

    private String maxKey;
    private int maxWeight;

    public CommandExecution(AbstractCommand command, CommandSender sender, String[] args){

        Method subCommand = getSubCommandMethod(command, args);
        if(subCommand == null){
            command.run(sender, args);
            return;
        }

        String[] newArgs = trimArgs(args, subCommand);
        Reflections.invokeMethod(command, subCommand, newArgs);
    }


    private Method getSubCommandMethod(AbstractCommand command, String[] args){
        this.maxKey = "";

        command.getSubCommands().keySet().forEach(key ->{
            String[] subCommandName = key.split(" ");
            if(args.length < subCommandName.length) return;
            int length = calcLength(subCommandName, args);
            this.maxKey = (length > this.maxWeight) ? key : this.maxKey;
            this.maxWeight = length;
        });

        return command.getSubCommands().get(this.maxKey);
    }

    private int calcLength(String[] subCommand, String[] args){
        for(int i = 0; i < subCommand.length; i++){
            if(!args[i].equalsIgnoreCase(subCommand[i])) return i;
        }
        return 0;
    }

    private String[] trimArgs(String[] oldArgs, Method method){
        SubCommand subCommand = method.getAnnotation(SubCommand.class);
        if(subCommand == null){
            Loggers.logError(new IllegalArgumentException("The method doesn't have an SubCommand implementation!"));
            return new String[0];
        }
        if(this.maxWeight - 1 == 0) return new String[0];
        String[] newArgs = new String[oldArgs.length - this.maxWeight];
        System.arraycopy(oldArgs, this.maxWeight - 1, newArgs, 0, newArgs.length);
        return newArgs;
    }

}
