package be.dezijwegel.spell_handlers.generic_handlers.handlers;

import be.dezijwegel.spell_handlers.generic_handlers.SpawnHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Set;

public class SpawnBlock extends SpawnHandler {


    public SpawnBlock(Material toSpawn, Location location) {
        super(location, toSpawn);
    }

    @Override
    public boolean cast(Player player) {
        if (super.cast(player)) {
            Block b = player.getTargetBlock((Set<Material>) null, 15);
            if (!b.getType().equals(Material.AIR)) {
                Location loc = b.getLocation();
                if (b.getType().equals(Material.GRASS)) loc.setY(loc.getY() + 1);
                Block newBlock = player.getWorld().getBlockAt(loc);
                if (newBlock.getType().equals(Material.AIR) || b.getType().equals(Material.GRASS)) {
                    spawn(newBlock.getType(), newBlock.getLocation());
                    super.setCast(player);
                    return true;
                } else {
                    player.sendMessage("§3[magicWands]§cBlocks cannot be spawned in this location!");
                }
            } else {
                player.sendMessage("§3[magicWands]§cThis spell has to be cast on nearby ground!");
            }
        }
        return false;
    }

    @Override
    public void spawn(Object materialType, Location location) {
        Material material = (Material) materialType;
        location.getBlock().setType(material);

    }
    
}
