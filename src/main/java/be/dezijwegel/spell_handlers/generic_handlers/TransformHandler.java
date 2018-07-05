package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.spell_handlers.SpellHandler;


/** Class used for parenting all handlers that transform a block or entity
 */
/** 
 * I used a generic type as we want to allow both blocks and entities to be transformed
 * Let me know your thoughts on this
 */
public abstract class TransformHandler<T> extends SpellHandler{

    public abstract void transform(T entity);
    
}
