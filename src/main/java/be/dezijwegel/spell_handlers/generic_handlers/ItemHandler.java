package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import org.bukkit.inventory.ItemStack;

public abstract class ItemHandler extends SpellHandler{
    
    public abstract void spawnItem(ItemStack item);
    
}
