package be.dezijwegel.objects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Dieter Nuytemans
 */
public class Wand extends ItemStack{
    
    private SpellList spells;
    
    public Wand(String name)
    {
        spells = new SpellList();
        setType(Material.STICK);
        setAmount(1);
        ItemMeta m = getItemMeta();
        m.setDisplayName(name);
        setItemMeta(m);
    }
    
    /**
     * Add a spell to the current wand
     * @param spell 
     */
    public void addSpell(Spell spell)
    {
        spells.addSpell(spell);
    }
    
    /**
     * Get a custom wand ItemStack 
     * @return ItemStack
     */
    public ItemStack getWand()
    {
        return (ItemStack) this;
    }
    
    /**
     * Get the list of spells
     * @return SpellList
     */
    public SpellList getSpellList()
    {
        return spells;
    }
    
    /**
     * Cycle to the next spell for a player and get the spell
     * @param player 
     * @return  
     */
    public Spell nextSpell(Player player)
    {
        return spells.nextSpell(player);
    }
    
    /**
     * Get the current spell of a player
     * @param player 
     * @return  
     */
    public Spell getSpell(Player player)
    {
        return spells.getSpell(player);
    }
    
    public boolean equals(ItemStack i)
    {
        if (getType().equals(i.getType()))
            if (getItemMeta().equals(i.getItemMeta()))
                return true;
        
        return false;
    }
}
