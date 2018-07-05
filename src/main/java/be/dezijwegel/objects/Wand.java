package be.dezijwegel.objects;

import java.util.ArrayList;
import java.util.HashMap;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Getter
public class Wand extends ItemStack {

    private ArrayList<Spell> spells;
    private HashMap<Player, Integer> currentSpell;

    public Wand(String name) {
        currentSpell = new HashMap<>();
        spells = new ArrayList<>();
        
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

    /**
     * Get the current Spell a player is at
     *
     * @param player
     * @return
     */
    public Spell getSpell(Player player) {
        if (currentSpell.containsKey(player)) {
            return spells.size() > 0 ? spells.get(currentSpell.get(player)) : null;
        } else {
            currentSpell.put(player, 0);
            return spells.size() > 0 ? spells.get(0) : null;
        }
    }

    /**
     * Switch to the next spell in the list for a player and return the Spell
     *
     * @param player
     * @return String
     */
    public Spell nextSpell(Player player) {
        if (currentSpell.containsKey(player)) {
            if (currentSpell.get(player) == spells.size() - 1) currentSpell.put(player, 0);
            else currentSpell.put(player, currentSpell.get(player) + 1);
            return spells.get(currentSpell.get(player));
        } else {
            currentSpell.put(player, 0);
            return spells.size() > 0 ? spells.get(0) : null;
        }

    }

    public boolean equals(ItemStack i) {
        if (getType().equals(i.getType()))
            if (getItemMeta().equals(i.getItemMeta()))
                return true;

        return false;
    }
}
