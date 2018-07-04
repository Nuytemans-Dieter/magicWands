package be.dezijwegel.spell_handlers.generic_handlers;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public abstract class TeleportationHandler {

    public abstract void teleport(Entity entity, Location location);
}
