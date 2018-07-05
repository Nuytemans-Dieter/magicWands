package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public abstract class TeleportationHandler extends SpellHandler{

    public abstract void teleport(Entity entity, Location location);
}
