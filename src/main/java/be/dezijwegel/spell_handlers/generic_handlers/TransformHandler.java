package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class TransformHandler<T, E> extends SpellHandler {


    @Getter
    @NonNull
    protected T object;
    @Getter
    @NonNull
    protected E newObject;

    public abstract void transform(T object, E newObject);

}
