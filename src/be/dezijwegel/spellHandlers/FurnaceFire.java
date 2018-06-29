package be.dezijwegel.spellHandlers;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;

/**
 *
 * @author Dieter
 */
public class FurnaceFire extends SpellHandler{
    
    @Override
    public boolean cast(Player player)
    {
        if (super.cast(player))
        {
            Block b = player.getTargetBlock(null,10);
            if(b.getType().equals(Material.FURNACE))
            {
                ((Furnace)b.getState()).setBurnTime((short) 2000);
                ((Furnace)b.getState()).setCookTime((short) 2000);
                ((Furnace)b.getState()).update();
                super.setCast(player);
                return true;
            } else {
                player.sendMessage("§3[magicWands]§cThis spell can only be cast on a furnace!");
                return false;
            }
        }
        return false;
    }
}
