package be.dezijwegel.spell_handlers.generic_handlers.handlers;

import be.dezijwegel.spell_handlers.generic_handlers.TeleportationHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Set;
import org.bukkit.entity.Entity;

public class TeleportLocation extends TeleportationHandler {

    private int maxDistance;

    public TeleportLocation(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean cast(Player player) {
        if (super.cast(player)) {
            Location loc = player.getTargetBlock((Set<Material>) null, maxDistance).getLocation();
            player.teleport(loc);
            return true;
        }
        return false;
    }

    @Override
    public void teleport(Entity entity, Location location) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
