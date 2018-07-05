package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

@RequiredArgsConstructor
public abstract class TeleportationHandler extends SpellHandler {

    @Getter
    @NonNull
    protected Entity teleport;
    @Getter
    @NonNull
    protected Location location;

    public abstract void teleport(Entity entity, Location location);
}
