package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public abstract class TeleportationHandler implements SpellHandler {

    @Getter
    @NonNull
    protected Entity teleport;
    @Getter
    @NonNull
    protected Location location;

    public TeleportationHandler(Entity teleport, Location location) {
        this.teleport = teleport;
        this.location = location;
    }

    public abstract void teleport(Entity entity, Location location);

    public void run() {
        teleport(teleport, location);
    }
}
