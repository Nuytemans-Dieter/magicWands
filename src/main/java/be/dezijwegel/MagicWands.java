package be.dezijwegel;

import be.dezijwegel.events.MagicListener;
import be.dezijwegel.events.PlayerJoinListener;
import be.dezijwegel.events.PlayerLeaveListener;
import be.dezijwegel.files.ConfigurationUtilities;
import be.dezijwegel.objects.Spell;
import be.dezijwegel.objects.Wizard;
import be.dezijwegel.spell_handlers.SpellHandler;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * @author Dieter Nuytemans, Perotin
 */
public class MagicWands extends JavaPlugin {
    /*
    TODO list.
    1. A GUI that sells wands/spells to players (very low priority right now)
    2. System to change the active spell for a user (using the toolbar on right clicking with the wand)
        -> + we need to implement this in MagicListener
        -> We should switch between the regular inventory and the spells inventory upon right click with a wand
          (perhaps we could add a currently inactive Inventory field in Wizard)
        -> Spells could contain an ItemStack that will be displayed in the spells inventory
           with a name and a description
        -> A listener that gets executed upon spell selection that changes the active spell to the
           one that was clicked

       Just some more notes: We need to think some more about the structure of our objects. I think we should have it more orientated around Spells than Handlers.
        Basically we'll have 3 types of spells,
            1: Created & loaded through spells.yml by use of generic and non-generic handlers
            2: Created and loaded through our plugin itself with more custom handlers for more intricate abilities
            3: Created and loaded through other plugins with their own custom handlers

        At a certain point we have to forsake configurability for intricacy, we can still have configurable spells but it is unrealistic to make spells with lots of
        particles, and computations be configurable.
        If we can maintain a system where others can easily load in their own spells via a plugin then that is a pretty good alternative to a spells.yml


        This will be our object structure:
            Wizard -> Wands -> Spells -> Handler

            With this in mind we have to refactor parts of our objects to fit this code. So for example we  CAN store the caster in a Wand, Spell, or Handler and so on
            in classes that subclass them but we have to keep in mind recursion and when we structure them.

        Our relationships:
            Wizard CAN have wands and wands CAN have a wizard attached, Wands CAN have spells and spells CAN have a wand object, Spells MUST have a handler
            Wizard <-> Wand <-> Spells <-> Handler
                        */


    /**
     * @apiNote only store the default of every spell here
     */
    @Getter
    static ArrayList<Spell> spells;
    @Getter
    @NonNull
    private static MagicWands instance;
    @NonNull
    @Getter
    private static HashSet<Wizard> players;
    @Getter
    private static HashSet<SpellHandler> handlers = new HashSet<>();


    @Override
    public void onEnable() {
        instance = this;
        spells = new ArrayList<>();

        players = new HashSet<>();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new MagicListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(), this);
        ConfigurationUtilities.loadSpells();

    }


}
