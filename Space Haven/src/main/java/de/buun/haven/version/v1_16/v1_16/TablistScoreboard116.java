package de.buun.haven.version.v1_16.v1_16;

import de.buun.haven.Loggers;
import de.buun.haven.scroreboard.AbstractTablistScoreboard;
import de.buun.haven.util.PacketSender;
import de.buun.haven.util.Reflections;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class TablistScoreboard116 implements AbstractTablistScoreboard {

    private final Scoreboard nmsScoreboard;
    private final Map<UUID, String> playerTeams;

    public TablistScoreboard116(){
        this.nmsScoreboard = new Scoreboard();
        this.playerTeams = new HashMap<>();
    }

    public void registerTeam(Player player, String prefix, String suffix, int weight){
        String teamName = weight + player.getUniqueId().toString();
        teamName = teamName.substring(0, 15);
        ScoreboardTeam team = resetScoreboardTeam(nmsScoreboard, teamName);
        team.setPrefix(IChatBaseComponent.ChatSerializer.a(getJson(prefix)));
        team.setSuffix(IChatBaseComponent.ChatSerializer.a(getJson(suffix)));
        setTeamColor(team, prefix.charAt(prefix.length() - 1));
        playerTeams.put(player.getUniqueId(), teamName);
        if(!team.getPlayerNameSet().contains(player.getName())){
            team.getPlayerNameSet().add(player.getName());
        }
    }

    public void sendUpdate(Player player, Player updated){
        ScoreboardTeam team = this.nmsScoreboard.getPlayerTeam(playerTeams.get(updated.getUniqueId()));
        if(team == null) {
            Loggers.logError(new NullPointerException("Scoreboard team is null!"));
            return;
        }
        PacketPlayOutScoreboardTeam removePacket = new PacketPlayOutScoreboardTeam(team, (byte) 1);
        PacketPlayOutScoreboardTeam addPacket = new PacketPlayOutScoreboardTeam(team, (byte) 0);
        PacketSender.sendPackets(player, removePacket, addPacket);
    }

    public void unregisterTeam(Player player){
        ScoreboardTeam playerTeam = this.nmsScoreboard.getPlayerTeam(
                this.playerTeams.remove(player.getUniqueId()));
        this.nmsScoreboard.removeTeam(playerTeam);
    }

    private ScoreboardTeam resetScoreboardTeam(Scoreboard scoreboard, String teamName){
        ScoreboardTeam team = scoreboard.getTeam(teamName);
        if(team != null) scoreboard.removeTeam(team);
        return scoreboard.createTeam(teamName);
    }

    private void setTeamColor(ScoreboardTeam team, char character){
        EnumChatFormat format = Arrays.stream(EnumChatFormat.values())
                .filter(enumChatFormat -> Reflections.getFieldValue(enumChatFormat, "character").equals(character))
                .collect(Collectors.toList()).get(0);
        if(format != null){
            team.setColor(format);
        }
    }

}
