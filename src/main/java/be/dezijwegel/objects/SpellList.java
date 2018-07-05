package be.dezijwegel.objects;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class SpellList {

    private ArrayList<Spell> spells;
    private HashMap<Player, Integer> currentSpell;

    public SpellList() {
        currentSpell = new HashMap<>();
        spells = new ArrayList<>();
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

    /**
     * Add a spell to this list
     *
     * @param spell
     */
    public void addSpell(Spell spell) {
        spells.add(spell);
    }
}
