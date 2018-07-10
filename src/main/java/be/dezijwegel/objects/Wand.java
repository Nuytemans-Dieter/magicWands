package be.dezijwegel.objects;

import be.dezijwegel.exceptions.SpellNotFoundException;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

@Getter
public class Wand extends ItemStack {


    private ArrayList<Spell> spells;
    @Setter
    private int currentSpellIndex;
    private final Wizard wizard;
    private final String name;

    public Wand(String name, Wizard wizard) {
        this.currentSpellIndex = 0;
        this.spells = new ArrayList<>();
        this.wizard = wizard;
        this.name = name;
        super.setType(Material.STICK);
        ItemMeta m = getItemMeta();
        m.setDisplayName(name);
        setItemMeta(m);
    }
    
    /**
     * Add a spell to this list
     *
     * @param spell
     */
    public void addSpell(Spell spell) {
        spells.add(spell);
    }

    public void removeSpell(Spell spell) {
        spells.remove(spell);
    }

    /**
     * Get the current Spell a player is at
     * @return Spell
     */
    public Spell getCurrentSpell() {
        return spells.get(currentSpellIndex);
    }

    
    /**
     * Set the current spell for wand
     * @param spell to set as current, must be in spells list
     */
    public void setCurrentSpell(Spell spell) {
        int index = 0;
        for (val spellMatch : getSpells()) {
            if (spellMatch.equals(spell)) {
                setCurrentSpellIndex(index);
            }
            index++;
        }
        try {
            throw new SpellNotFoundException(spell, wizard.getPlayer().getName(), getName());
        } catch (SpellNotFoundException exception) {
            exception.printStackTrace();
        }
    }

}
