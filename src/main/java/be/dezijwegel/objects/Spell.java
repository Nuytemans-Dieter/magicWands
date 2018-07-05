package be.dezijwegel.objects;

import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.Getter;
import org.bukkit.entity.Player;


@Getter
public class Spell {

    private String name;
    private String description = null;
    private SpellHandler handler;


    /**
     * @param name
     * @param handler for the spell
     * @deprecated Create a spell with given name and message that will be sent to the player upon casting the spell
     */
    public Spell(String name, SpellHandler handler) {
        this.name = name;
        this.handler = handler;
    }

    /**
     * Create a spell with given name, message that will be sent to the player upon casting the spell
     * and message that wil be sent to the player upon selecting the Spell.
     * Enables a cooldown (in 0,1 seconds, 10 would mean 1 second)
     *
     * @param name
     * @param description
     * @param handler     for the spell
     * @param cooldown
     * @param cost
     */
    public Spell(String name, String description, SpellHandler handler, long cooldown, int cost) {
        this.name = name;
        this.description = description;
        this.handler = handler;
        handler.setCooldown(cooldown);
        handler.setCost(cost);

    }

    /**
     * Run the handler of this spell
     *
     * @param player
     * @return boolean: success/fail
     */
    public boolean cast(Player player) {
        return handler.cast(player);
    }


}
