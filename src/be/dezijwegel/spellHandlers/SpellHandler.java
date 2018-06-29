package be.dezijwegel.spellHandlers;

import java.util.HashMap;
import org.bukkit.entity.Player;

/**
 *
 * @author Dieter
 */
public class SpellHandler {
    
    private long cooldown = 0;
    private HashMap<Player,Long> playerRecords = new HashMap<>();
    
    /**
     * Check if a player can cast a spell already 
     * @param p
     * @return true: player may cast, false: player may not cast yet
     */
    public boolean canCastAlready(Player p)
    {
        if (playerRecords.containsKey(p)) 
            return System.currentTimeMillis()/100 - playerRecords.get(p) > cooldown;
        else return true;
    }
    
    /**
     * Add a player and current time to the cooldown tracker 
     * @param p 
     */
    public void setCast(Player p)
    {
        playerRecords.put(p, System.currentTimeMillis()/100);
    }
    
    /**
     * Get the time a player has to wait before they can cast this spell again
     * @param p
     * @return 
     */
    public long getWaitTime(Player p)
    {
        return cooldown - (System.currentTimeMillis()/100 - playerRecords.get(p));
    }
    
    /**
     * Set the cooldown for a given spell (in ticks)
     * 10 ticks means one second here
     * @param ticks 
     */
    public void setCooldown(long ticks)
    {
        cooldown = ticks;
    }
    
    /**
     * Used to execute a cast spell
     * @param player who cast the spell
     * @return 
     */
    public boolean cast(Player player) {
        if (canCastAlready(player)) 
        {
            return true;
        }else {
            player.sendMessage("§3[magicWands]§c You have to wait " + getWaitTime(player)/10 + " seconds before you can cast this spell again!");
            return false;
        }
    }
}
