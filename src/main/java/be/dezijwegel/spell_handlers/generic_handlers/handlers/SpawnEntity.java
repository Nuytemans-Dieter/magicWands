package be.dezijwegel.spell_handlers.generic_handlers.handlers;

import be.dezijwegel.spell_handlers.generic_handlers.SpawnHandler;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class SpawnEntity extends SpawnHandler {


    public SpawnEntity(Location location, Entity toSpawn) {

        super(location, toSpawn);
    }

    /**
     * @deprecated
     */
    public boolean run(Player player) {
//        if (super.cast(player)) {
//            Location loc = player.getTargetBlock((Set<Material>) null, 30).getLocation();
//            loc.setY(loc.getY() + 1);
//            Material mat = player.getWorld().getBlockAt(loc).getType();
//            if (mat.equals(Material.AIR) || mat.equals(Material.GRASS)) {
//                player.getWorld().spawnEntity(loc, this.entity);
//                super.setCast(player);
//                return true;
//            } else {
//                player.sendMessage("§3[magicWands]§cThis location is not eligible to summon an entity!");
//                return false;
//            }
//        }

        return false;
    }

    @Override
    public void spawn(Entity entity, Location location) {
        location.getWorld().spawnEntity(location, entity.getType());
    }
}
