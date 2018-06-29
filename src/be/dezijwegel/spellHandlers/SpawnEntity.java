package be.dezijwegel.spellHandlers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 *
 * @author Dieter
 */
public class SpawnEntity extends SpellHandler {
    
    private EntityType entity;
    
    public SpawnEntity(EntityType entity)
    {
        this.entity = entity;
    }
    
    @Override
    public boolean cast(Player player)
    {
        if (super.cast(player))
        {
            Location loc = player.getTargetBlock(null,30).getLocation();
            loc.setY(loc.getY()+1);
            Material mat = player.getWorld().getBlockAt(loc).getType();
            if(mat.equals(Material.AIR) || mat.equals(Material.GRASS))
            {
                player.getWorld().spawnEntity(loc, this.entity);
                super.setCast(player);
                return true;
            } else {
                player.sendMessage("§3[magicWands]§cThis location is not eligible to summon an entity!");
                return false;
            }
        }
        return false;
    }
}
