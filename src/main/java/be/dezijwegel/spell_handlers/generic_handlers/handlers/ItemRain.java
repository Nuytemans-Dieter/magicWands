package be.dezijwegel.spell_handlers.generic_handlers.handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

/**
 * @author Dieter
 */
public class ItemRain extends SpellHandler {
    private ItemStack item;

    public ItemRain(ItemStack item) {
        this.item = item;
    }

    @Override
    public boolean cast(Player player) {
        if (super.cast(player)) {
            Location loc = player.getTargetBlock((Set<Material>) null, 15).getLocation();
            loc.setY(loc.getY() + 10);

            if (isValidLocation(loc)) {
                for (int x = -2; x < 3; x++) {
                    for (int z = -2; z < 3; z++) {
                        Location copy = new Location(loc.getWorld(), loc.getX() + x, loc.getY(), loc.getZ() + z);
                        player.getWorld().dropItem(loc, item);
                    }
                }
            } else {
                player.sendMessage("§3[magicWands]§cThis spell has to be cast in a very open area!");
            }
        }
        return false;
    }

    public boolean isValidLocation(Location loc) {
        Location copy = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
        for (int y = 2; y < 10; y++) {
            for (int x = -2; x < 3; x++) {
                for (int z = -2; z < 3; z++) {
                    loc = new Location(copy.getWorld(), copy.getX() + x, copy.getY() + y, copy.getZ() + z);
                    if (!loc.getBlock().getType().equals(Material.AIR)) return false;
                }
            }
        }
        return true;
    }
}
