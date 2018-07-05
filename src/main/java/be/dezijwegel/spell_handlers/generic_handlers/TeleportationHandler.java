package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.objects.PlayerData;
import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public abstract class TeleportationHandler extends SpellHandler {

    @Getter
    @NonNull
    protected Entity teleport;
    @Getter
    @NonNull
    protected Location location;

    public TeleportationHandler(PlayerData caster, Entity teleport, Location location) {
        super(caster);
        this.teleport = teleport;
        this.location = location;
    }

    public abstract void teleport(Entity entity, Location location);

    public void run() {
        teleport(teleport, location);
    }
}
