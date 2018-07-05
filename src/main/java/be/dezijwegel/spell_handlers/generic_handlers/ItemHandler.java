package be.dezijwegel.spell_handlers.generic_handlers;

import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public abstract class ItemHandler extends SpellHandler{

    @Getter
    @NonNull
    protected ItemStack item;
    @Getter
    @NonNull
    protected Player player;

    public abstract void giveItem(ItemStack item, Player toGive);

}
