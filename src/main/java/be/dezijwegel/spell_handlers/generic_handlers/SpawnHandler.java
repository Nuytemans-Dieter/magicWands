package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import org.bukkit.entity.Entity;

public abstract class SpawnHandler extends SpellHandler{

    public abstract void spawn(Entity entity);
}

