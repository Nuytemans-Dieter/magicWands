package be.dezijwegel.spell_handlers.generic_handlers.handlers;

import be.dezijwegel.spell_handlers.generic_handlers.SpawnHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;


public class ItemRain extends SpawnHandler {

    public ItemRain(Entity entity, Location location) {

        super(location, entity);
    }


    /**
     * @deprecated
     **/
//    public boolean run(Player player) {
//            Location loc = player.getTargetBlock((Set<Material>) null, 15).getLocation();
//            loc.setY(loc.getY() + 10);
//
//            if (isValidLocation(loc)) {
//                for (int x = -2; x < 3; x++) {
//                    for (int z = -2; z < 3; z++) {
//                        Location copy = new Location(loc.getWorld(), loc.getX() + x, loc.getY(), loc.getZ() + z);
//                        run();
//                    }
//                }
//            } else {
//                player.sendMessage("§3[magicWands]§cThis spell has to be cast in a very open area!");
//            }
//
//        return false;
//    }



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

    @Override
    public void spawn(Entity item, Location location) {
        location.getWorld().spawnEntity(location, item.getType());
    }
}
