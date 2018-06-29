package be.dezijwegel;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Dieter Nuytemans
 */
public class Wand extends ItemStack{
    
    private SpellList spells;
    
    public Wand()
    {
        spells = new SpellList();
        setType(Material.STICK);
        setAmount(1);
        ItemMeta m = getItemMeta();
        m.setDisplayName("§4Magic Wand");
        setItemMeta(m);
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
    
    public boolean equals(ItemStack i)
    {
        if (getType().equals(i.getType()))
            if (getItemMeta().equals(i.getItemMeta()))
                return true;
        
        return false;
    }
}
