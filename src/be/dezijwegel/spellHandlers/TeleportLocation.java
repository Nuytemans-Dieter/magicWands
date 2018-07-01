package be.dezijwegel.spellHandlers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author Dieter
 */
public class TeleportLocation extends SpellHandler{
    
    private int maxDistance;
    
    public TeleportLocation(int maxDistance)
    {
        this.maxDistance = maxDistance;
    }
    
    @Override
    public boolean cast(Player player)
    {
        if (super.cast(player))
        {
            Location loc = player.getTargetBlock(null,maxDistance).getLocation();
            player.teleport(loc);
            return true;
        }
        return false;
    }
}
