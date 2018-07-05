package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import org.bukkit.entity.Entity;

public abstract class DamageHandler extends SpellHandler{

    public abstract void damage(Entity entity, int damage);
}
