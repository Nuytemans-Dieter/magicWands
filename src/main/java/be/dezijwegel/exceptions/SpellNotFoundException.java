package be.dezijwegel.exceptions;

import be.dezijwegel.objects.Spell;

public class SpellNotFoundException extends Exception {

    public SpellNotFoundException(Spell spell, String player, String wand) {
        super("Spell " + spell.getName() + " not found for " + player + "'s wand called " + wand);
    }
}
