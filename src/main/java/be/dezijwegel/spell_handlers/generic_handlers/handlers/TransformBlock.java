/*
 * Geen license header toegevoegd
 * Dieter Nuytemans
 */
package be.dezijwegel.spell_handlers.generic_handlers.handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * @author Dieter
 */
public class TransformBlock extends SpellHandler {

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

}
