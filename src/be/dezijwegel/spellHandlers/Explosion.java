package be.dezijwegel.spellHandlers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author Dieter
 */
public class Explosion extends SpellHandler{
    
    private int size;
    
    public Explosion(int size)
    {
        this.size = size;
    }
    
    @Override
    public boolean cast (Player player)
    {
        if (super.cast(player))
        {
            Location loc = player.getTargetBlock(null,15).getLocation();
            player.getWorld().createExplosion(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), size, false, false);
            super.setCast(player);
            return true;
        }
        return false;
   }
}
