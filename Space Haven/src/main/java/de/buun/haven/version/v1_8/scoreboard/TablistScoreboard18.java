package de.buun.haven.version.v1_8.scoreboard;

import de.buun.haven.Loggers;
import de.buun.haven.scroreboard.AbstractTablistScoreboard;
import de.buun.haven.util.PacketSender;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardTeam;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TablistScoreboard18 implements AbstractTablistScoreboard {

    private final Scoreboard nmsScoreboard;
    private final Map<UUID, String> playerTeams;

    public TablistScoreboard18(){
        this.nmsScoreboard = new Scoreboard();
        this.playerTeams = new HashMap<>();
    }


    @Override
    public void unregisterTeam(Player player) {
        ScoreboardTeam playerTeam = this.nmsScoreboard.getPlayerTeam(
                this.playerTeams.remove(player.getUniqueId()));
        this.nmsScoreboard.removeTeam(playerTeam);
    }

    @Override
    public void registerTeam(Player player, String prefix, String suffix, int weight) {
        String teamName = weight + player.getUniqueId().toString();
        teamName = teamName.substring(0, 15);
        ScoreboardTeam team = resetScoreboardTeam(nmsScoreboard, teamName);
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        playerTeams.put(player.getUniqueId(), teamName);
        if(!team.getPlayerNameSet().contains(player.getName())){
            team.getPlayerNameSet().add(player.getName());
        }
    }

    @Override
    public void sendUpdate(Player player, Player updated) {
        ScoreboardTeam team = this.nmsScoreboard.getPlayerTeam(playerTeams.get(updated.getUniqueId()));
        if(team == null){
            Loggers.logError(new NullPointerException("Scoreboard team is null!"));
            return;
        }
        PacketPlayOutScoreboardTeam removePacket = new PacketPlayOutScoreboardTeam(team, (byte) 1);
        PacketPlayOutScoreboardTeam addPacket = new PacketPlayOutScoreboardTeam(team, (byte) 0);
        PacketSender.sendPackets(player, removePacket, addPacket);
    }

    private ScoreboardTeam resetScoreboardTeam(Scoreboard scoreboard, String teamName){
        ScoreboardTeam team = scoreboard.getTeam(teamName);
        if(team != null) scoreboard.removeTeam(team);
        return scoreboard.createTeam(teamName);
    }
}
