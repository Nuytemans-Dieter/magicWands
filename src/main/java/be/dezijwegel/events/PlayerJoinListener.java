package be.dezijwegel.events;

import be.dezijwegel.objects.PlayerData;
import be.dezijwegel.objects.PlayerDataList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener{
    
    private PlayerDataList players;
    
    public PlayerJoinListener(PlayerDataList players)
    {
        this.players = players;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        //100 is the max amount of mana, this should be loaded from storage in the future
        players.addPlayerData(new PlayerData(e.getPlayer(), 100));
    }
}
