package be.dezijwegel;

import be.dezijwegel.spellHandlers.SpellHandler;
import org.bukkit.entity.Player;

/**
 *
 * @author Dieter Nuytemans
 */
public class Spell {
    
    private String name;
    private String castName = null;
    private String descr = null;
    private SpellHandler handler;
    
    /**
     * @deprecated
     * Create a spell with given name and handler
     * @param name 
     * @param h 
     */
    public Spell(String name, SpellHandler h)
    {
        this.name = name;
        this.handler = h;
    }
    
    /**
     * @deprecated 
     * Create a spell with given name and message that will be sent to the player upon casting the spell
     * @param name
     * @param castName 
     * @param h Handler for the spell
     */
    public Spell(String name, String castName, SpellHandler h)
    {
        this.name = name;
        this.castName = castName;
        this.handler = h;
    }
    
    /**
     * Create a spell with given name, message that will be sent to the player upon casting the spell
     * and message that wil be sent to the player upon selecting the Spell
     * @param name
     * @param castName 
     * @param description
     * @param h Handler for the spell
     */
    public Spell(String name, String castName, String description, SpellHandler h)
    {
        this.name = name;
        this.castName = castName;
        this.descr = description;
        this.handler = h;
    }
    
        /**
     * Create a spell with given name, message that will be sent to the player upon casting the spell
     * and message that wil be sent to the player upon selecting the Spell.
     * Enables a cooldown (in 0,1 seconds, 10 would mean 1 second)
     * @param name
     * @param castName 
     * @param description
     * @param h Handler for the spell
     * @param cooldown 
     */
    public Spell(String name, String castName, String description, SpellHandler h, long cooldown)
    {
        this.name = name;
        this.castName = castName;
        this.descr = description;
        this.handler = h;
        h.setCooldown(cooldown);
    }
    
    /**
     * Run the handler of this spell
     * @param player 
     * @return boolean: success/fail
     */
    public boolean cast(Player player)
    {
        return handler.cast(player);
    }
    
    /**
     * Get the name of the Spell
     * @return String
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get the spellname the player shall see upon casting the spell
     * @return String
     */
    public String getCastName()
    {
        return castName;
    }
    
    /**
     * Get a description of the spell that the player shall recieve upon selecting the spell
     * @return String
     */
    public String getDescr()
    {
        return descr;
    }
}
