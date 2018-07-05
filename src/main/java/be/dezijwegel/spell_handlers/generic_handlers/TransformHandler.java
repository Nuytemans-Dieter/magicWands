package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.objects.PlayerData;
import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.Getter;
import lombok.NonNull;


public abstract class TransformHandler<T, E> extends SpellHandler {


    @Getter
    @NonNull
    protected T object;
    @Getter
    @NonNull
    protected E newObject;

    public TransformHandler(PlayerData caster, T object, E newObject) {
        super(caster);
        this.object = object;
        this.newObject = newObject;
    }

    public abstract void transform(T object, E newObject);

    public void run() {
        transform(object, newObject);
    }

}
