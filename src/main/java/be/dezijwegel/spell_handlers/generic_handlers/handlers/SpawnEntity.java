package be.dezijwegel.spell_handlers.generic_handlers.handlers;

import be.dezijwegel.spell_handlers.generic_handlers.SpawnHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Set;
import org.bukkit.entity.Entity;

public class SpawnEntity extends SpawnHandler {

    private EntityType entity;

    public SpawnEntity(EntityType entity) {
        this.entity = entity;
    }

    @Override
    public boolean cast(Player player) {
        if (super.cast(player)) {
            Location loc = player.getTargetBlock((Set<Material>) null, 30).getLocation();
            loc.setY(loc.getY() + 1);
            Material mat = player.getWorld().getBlockAt(loc).getType();
            if (mat.equals(Material.AIR) || mat.equals(Material.GRASS)) {
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

    @Override
    public void spawn(Entity entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
