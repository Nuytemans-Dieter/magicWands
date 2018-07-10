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
    1. Refactor loadSpells to use abstracted classes: Done. See ConfigurationUtilities.loadSpells for implementation
    2. I'm working on this: The PlayerInteractEvent in MagicListener has to be rewritten to fit the new system
    3. Decide how the active spell is determined and implement this
       (old way was cycling through a list on right click)

       Just some more notes: We need to think some more about the structure of our objects. I think we should have it more orientated around Spells than Handlers.
        Basically we'll have 3 types of spells,
            1: Created & loaded through spells.yml by use of generic and non-generic handlers
            2: Created and loaded through our plugin itself with more custom handlers for more intricate abilities
            3: Created and loaded through other plugins with their own custom handlers

        At a certain point we have to forsake configurability for intricacy, we can still have configurable spells but it is unrealistic to make spells with lots of
        particles, and computations be configurable.
        If we can maintain a system where others can easily load in their own spells via a plugin then that is a pretty good alternative to a spells.yml


        As for our object structure I think it should be something like this:
            Wizard (Player object, I renamed it) -> Wands -> Spells -> Handler

            With this in mind we have to refactor parts of our objects to fit this code. So for example we  CAN store the caster in a Wand, Spell, or Handler and so on
            in classes that subclass them but we have to keep in mind recursion and when we structure them.

            Here is a little tree that can help us:
            Wizard CAN have wands and wands CAN have a wizard attached, Wands CAN have spells and spells CAN have a wand object, Spells MUST have a handler

            So our relation ships look a little something like this
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
