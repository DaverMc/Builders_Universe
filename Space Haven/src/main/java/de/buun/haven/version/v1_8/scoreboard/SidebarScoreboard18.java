package de.buun.haven.version.v1_8.scoreboard;

import de.buun.haven.Loggers;
import de.buun.haven.scroreboard.AbstractScoreboard;
import de.buun.haven.util.PacketSender;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.Player;

public class SidebarScoreboard18 implements AbstractScoreboard {

    private final ScoreboardScore[] lines;
    private final Scoreboard nmsScoreboard;
    private final ScoreboardObjective objective;

    public SidebarScoreboard18(){
        lines = new ScoreboardScore[15];
        this.nmsScoreboard = new Scoreboard();
        this.objective = this.nmsScoreboard.registerObjective("Test", IScoreboardCriteria.b);
    }

    @Override
    public void send(Player player){
        PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(objective, 1);
        PacketPlayOutScoreboardObjective addPacket = new PacketPlayOutScoreboardObjective(objective, 0);
        PacketPlayOutScoreboardDisplayObjective displayPacket = new PacketPlayOutScoreboardDisplayObjective(1, objective);

        PacketSender.send(player, removePacket);
        PacketSender.send(player, addPacket);
        PacketSender.send(player, displayPacket);

        for (ScoreboardScore line : lines) {
            PacketSender.send(player, new PacketPlayOutScoreboardScore(line));
        }
    }

    @Override
    public void setValue(int index, String value) {
        if(index >= lines.length){
            Loggers.logError(new ArrayIndexOutOfBoundsException("You can only have 15 lines in sidebar"));
            return;
        }
        lines[index] = new ScoreboardScore(this.nmsScoreboard, this.objective, value);
    }

    @Override
    public String getValue(int index) {
        if(index >= lines.length){
            Loggers.logError(new ArrayIndexOutOfBoundsException("You can only have 15 lines in sidebar"));
            return " ";
        }
        return lines[index].getPlayerName();
    }
}
