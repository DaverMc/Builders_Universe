package de.buun.haven.version.v1_16.v1_16;

import de.buun.haven.Loggers;
import de.buun.haven.scroreboard.AbstractScoreboard;
import de.buun.haven.util.PacketSender;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SidebarScoreboard116 implements AbstractScoreboard {
    private final String[] lines;
    private final Scoreboard nmsScoreboard;
    private final ScoreboardObjective objective;

    public SidebarScoreboard116(){
        lines = new String[15];
        this.nmsScoreboard = new Scoreboard();
        this.objective = this.nmsScoreboard.registerObjective("Test", IScoreboardCriteria.DUMMY, IChatBaseComponent.ChatSerializer.a(" "), IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
    }

    @Override
    public void send(Player player){
        PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(objective, 1);
        PacketPlayOutScoreboardObjective addPacket = new PacketPlayOutScoreboardObjective(objective, 0);
        PacketPlayOutScoreboardDisplayObjective displayPacket = new PacketPlayOutScoreboardDisplayObjective(1, objective);

        PacketSender.send(player, removePacket);
        PacketSender.send(player, addPacket);
        PacketSender.send(player, displayPacket);

        for (int i = 0; i < lines.length; i++) {
            String name = UUID.randomUUID().toString().substring(0, 15);
            PacketSender.send(player, new PacketPlayOutScoreboardScore(ScoreboardServer.Action.CHANGE, name, lines[i], i));
        }
    }

    @Override
    public void setValue(int index, String value) {
        if(index >= lines.length){
            Loggers.logError(new ArrayIndexOutOfBoundsException("You can only have 15 lines in sidebar"));
            return;
        }
        lines[index] = value;
    }

    @Override
    public String getValue(int index) {
        if(index >= lines.length){
            Loggers.logError(new ArrayIndexOutOfBoundsException("You can only have 15 lines in sidebar"));
            return " ";
        }
        return lines[index];
    }
}
