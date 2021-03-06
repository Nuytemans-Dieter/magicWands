package be.dezijwegel.spell_handlers.generic_handlers.handlers;

import be.dezijwegel.spell_handlers.generic_handlers.TeleportationHandler;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class TeleportLocation extends TeleportationHandler {

    private final int maxDistance;

    public TeleportLocation(Entity teleport, Location location, int maxDistance) {
        super(teleport, location);
        this.maxDistance = maxDistance;
    }

    /**
     * @deprecated
     */
    public boolean cast(Player player) {
//        if (super.cast(player)) {
//            Location loc = player.getTargetBlock((Set<Material>) null, maxDistance).getLocation();
//            player.teleport(loc);
//            return true;
//        }
        return false;
    }

    @Override
    public void teleport(Entity entity, Location location) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
