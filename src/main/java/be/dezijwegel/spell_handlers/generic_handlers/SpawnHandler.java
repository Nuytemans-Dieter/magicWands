package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public abstract class SpawnHandler implements SpellHandler {


    @Getter
    @NonNull
    protected final Location location;
    @Getter
    @NonNull
    protected final Entity toSpawn;

    public SpawnHandler(Location location, Entity toSpawn) {
        this.location = location;
        this.toSpawn = toSpawn;
    }

    public abstract void spawn(Entity entity, Location location);


    public void run() {
        spawn(toSpawn, location);
    }
}





