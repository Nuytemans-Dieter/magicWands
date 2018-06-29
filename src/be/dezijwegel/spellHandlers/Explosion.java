package be.dezijwegel.spellHandlers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author Dieter
 */
public class Explosion extends SpellHandler{
    
    @Override
    public boolean cast (Player player)
    {
        if (super.cast(player))
        {
            Location loc = player.getTargetBlock(null,15).getLocation();
            player.getWorld().createExplosion(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), 7, false, false);
            super.setCast(player);
            return true;
        }
        return false;
   }
}
