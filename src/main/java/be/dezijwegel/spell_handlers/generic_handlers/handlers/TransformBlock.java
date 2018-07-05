package be.dezijwegel.spell_handlers.generic_handlers.handlers;

import be.dezijwegel.spell_handlers.generic_handlers.TransformHandler;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Set;
import org.bukkit.entity.Entity;

public class TransformBlock extends TransformHandler {

    private Material material;

    /**
     * Material that the targeted block will be transformed into
     *
     * @param material
     */
    public TransformBlock(Material material) {
        this.material = material;
    }

    @Override
    public boolean cast(Player player) {
        if (super.cast(player)) {
            Block b = player.getTargetBlock((Set<Material>) null, 15);
            if (!b.getType().equals(Material.AIR)) {
                b.setType(material);
                super.setCast(player);
                return true;
            } else {
                player.sendMessage("§3[magicWands]§cThis spell has to be cast on a block!");
            }
        }
        return false;
    }

    
    @Override
    public void transform(Object block) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
