package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class SpawnHandler<T> extends SpellHandler {


    @Getter
    @NonNull
    protected Location location;
    @Getter
    @NonNull
    protected T toSpawn;


    public abstract void spawn(T entity, Location location);
}





