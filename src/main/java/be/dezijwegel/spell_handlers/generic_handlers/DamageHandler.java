package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.objects.PlayerData;
import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.entity.Entity;

public abstract class DamageHandler extends SpellHandler{

    @Getter
    @NonNull
    protected Entity entity;
    @Getter
    @NonNull
    protected int damage;

    public DamageHandler(Entity entity, int damage, PlayerData playerData) {
        super(playerData);
        this.entity = entity;
        this.damage = damage;
    }

    public abstract void damage(Entity entity, int damage);

    public void run() {
        damage(entity, damage);
    }
}
