package be.dezijwegel.events;

import be.dezijwegel.runnables.ManaRegeneration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener{
    
    private ManaRegeneration manaReg;
    
    public PlayerLeaveListener(ManaRegeneration manaReg)
    {
        this.manaReg = manaReg;
    }
    
    @EventHandler
    public void onPlayerleave(PlayerQuitEvent e)
    {
        manaReg.removePlayerData(e.getPlayer());
    }
}
