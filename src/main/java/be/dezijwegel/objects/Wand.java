package be.dezijwegel.objects;

import be.dezijwegel.exceptions.SpellNotFoundException;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Wand extends ItemStack {


    private List<Spell> spells;
    @Setter
    private int currentSpellIndex;
    @Setter
    private Wizard wizard;
    private final String name;
    private final UUID wandUUID;

    /**
     * Use for default wand with no stats loaded or wizard object attached
     *
     * @param name of wand
     */
    public Wand(String name) {
        this.currentSpellIndex = 0;
        this.spells = new ArrayList<>();
        this.wizard = null;
        this.name = name;
        this.wandUUID = UUID.randomUUID();
        super.setType(Material.STICK);
        ItemMeta m = getItemMeta();
        m.setDisplayName(name);
        setItemMeta(m);
    }

    /**
     * Use for loading in wands with stats
     *
     * @param name
     * @param spells
     * @param wizard
     */
    public Wand(String name, ArrayList<Spell> spells, Wizard wizard) {
        this.name = name;
        this.wandUUID = UUID.randomUUID();
        this.spells = spells;
        this.wizard = wizard;
        this.currentSpellIndex = 0;

        super.setType(Material.STICK);
        ItemMeta m = getItemMeta();
        m.setDisplayName(name);
        setItemMeta(m);
    }


    public void addSpell(Spell spell) {
        spells.add(spell);
    }

    public void removeSpell(Spell spell) {
        spells.remove(spell);
    }

    /**
     * Get the current Spell a player is at
     * M
     * @return Spell
     */
    public Spell getCurrentSpell() {
        return getSpells().get(getCurrentSpellIndex());

    }

    /**
     * Make sure wizard is not null before calling
     *
     * @param spell to check wait time for
     * @return time to cast current spell in seconds
     */
    public int getWaitTimeFor(Spell spell) {
        if (getWizard().getCurrentMana() < spell.getManaCost()) {
            int manaNeeded = spell.getManaCost() - getWizard().getCurrentMana();
            /**  mana getting / time*/
            int rate = getWizard().getManaTask().getRegenMana() / getWizard().getManaTask().getCycle();

            return manaNeeded / rate;
        }
        return 0;
    }


    /**
     * Set the current spell for wand
     *
     * @param spell to set as current, must be in spells list
     */
    public void setCurrentSpell(Spell spell) {
        int index = 0;
        for (val spellMatch : getSpells()) {
            if (spellMatch.equals(spell)) {
                setCurrentSpellIndex(index);
                return;
            }
            index++;
        }
        try {
            throw new SpellNotFoundException(spell, getWizard().getName(), getName());
        } catch (SpellNotFoundException exception) {
            exception.printStackTrace();
        }
    }

}
