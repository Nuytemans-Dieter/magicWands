package be.dezijwegel.events;

import be.dezijwegel.objects.PlayerDataList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener{
    
    private PlayerDataList players;
    
    public PlayerLeaveListener(PlayerDataList players)
    {
        this.players = players;
    }
    
    @EventHandler
    public void onPlayerleave(PlayerQuitEvent e)
    {
        players.removePlayerData(e.getPlayer());
    }
}
