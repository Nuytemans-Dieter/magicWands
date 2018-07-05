package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Entity;

@RequiredArgsConstructor
public abstract class DamageHandler extends SpellHandler{

    @Getter
    @NonNull
    protected Entity entity;
    @Getter
    @NonNull
    protected int damage;

    public abstract void damage(Entity entity, int damage);
}
