package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.Getter;
import lombok.NonNull;


public abstract class TransformHandler<T, E> implements SpellHandler {


    @Getter
    @NonNull
    protected T object;
    @Getter
    @NonNull
    protected E newObject;

    public TransformHandler(T object, E newObject) {
        this.object = object;
        this.newObject = newObject;
    }

    public abstract void transform(T object, E newObject);

    public void run() {
        transform(object, newObject);
    }

}
