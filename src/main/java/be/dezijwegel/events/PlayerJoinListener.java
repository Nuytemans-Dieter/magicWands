package be.dezijwegel.events;

import be.dezijwegel.objects.PlayerData;
import be.dezijwegel.runnables.ManaRegeneration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener{
    
    private ManaRegeneration manaReg;
    
    public PlayerJoinListener(ManaRegeneration manaReg)
    {
        this.manaReg = manaReg;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        //100 is the max amount of mana, this should be loaded from storage in the future
        manaReg.addPlayerData(new PlayerData(e.getPlayer(), 100));
    }
}
