package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.objects.PlayerData;
import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public abstract class SpawnHandler extends SpellHandler {


    @Getter
    @NonNull
    protected final Location location;
    @Getter
    @NonNull
    protected final Entity toSpawn;

    public SpawnHandler(PlayerData caster, Location location, Entity toSpawn) {
        super(caster);
        this.location = location;
        this.toSpawn = toSpawn;
    }

    public abstract void spawn(Entity entity, Location location);

    public void run() {
        spawn(toSpawn, location);
    }
}





