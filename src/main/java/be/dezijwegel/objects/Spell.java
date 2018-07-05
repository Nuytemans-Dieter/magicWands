package be.dezijwegel.objects;

import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@EqualsAndHashCode
public class Spell {

    private final String name;
    private final String description;
    private final SpellHandler handler;
    private final int manaCost;
    @Setter
    private Wand wand;

    /**
     * Create a spell with given name, message that will be sent to the player upon casting the spell
     * and message that wil be sent to the player upon selecting the Spell.
     * Enables a cooldown (in 0,1 seconds, 10 would mean 1 second)
     *
     * @param name
     * @param description
     * @param handler     for the spell
     * @param cost
     */
    public Spell(String name, String description, SpellHandler handler, int cost) {
        this.name = name;
        this.description = description;
        this.handler = handler;
        this.manaCost = cost;
    }



}
