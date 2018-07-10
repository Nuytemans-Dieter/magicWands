package be.dezijwegel.spell_handlers.generic_handlers.handlers;

import be.dezijwegel.objects.Wizard;
import be.dezijwegel.spell_handlers.generic_handlers.TransformHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Set;

public class SpawnBlock extends TransformHandler {


    private final Material material;

    public SpawnBlock(Wizard caster, Location location, Entity toSpawn, Material material) {
        super(caster, location, toSpawn);
        this.material = material;
    }

    /**
     * @deprecated
     **/
    public boolean run(Player player) {
            Block b = player.getTargetBlock((Set<Material>) null, 15);
            if (!b.getType().equals(Material.AIR)) {
                Location loc = b.getLocation();
                if (b.getType().equals(Material.GRASS)) loc.setY(loc.getY() + 1);
                Block newBlock = player.getWorld().getBlockAt(loc);
                if (newBlock.getType().equals(Material.AIR) || b.getType().equals(Material.GRASS)) {
                    transform(newBlock.getType(), newBlock.getLocation());
                    return true;
                } else {
                    player.sendMessage("§3[magicWands]§cBlocks cannot be spawned in this location!");
                }
            } else {
                player.sendMessage("§3[magicWands]§cThis spell has to be cast on nearby ground!");
            }

        return false;
    }


    @Override
    public void transform(Object object, Object newObject) {
        Location location = (Location) getObject();
        location.getBlock().setType((Material) getNewObject());
    }
}
