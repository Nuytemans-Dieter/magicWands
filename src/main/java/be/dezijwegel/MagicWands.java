package be.dezijwegel;

import be.dezijwegel.events.MagicListener;
import be.dezijwegel.files.ConfigurationUtilities;
import be.dezijwegel.objects.Spell;
import be.dezijwegel.objects.Wand;
import be.dezijwegel.spell_handlers.SpellHandler;
import be.dezijwegel.spell_handlers.generic_handlers.handlers.SpawnEntity;
import be.dezijwegel.spell_handlers.generic_handlers.handlers.TeleportLocation;
import be.dezijwegel.spell_handlers.generic_handlers.handlers.TransformBlock;
import be.dezijwegel.spell_handlers.nongeneric_handlers.Explosion;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;


/**
 * @author Dieter Nuytemans, Perotin
 */
public class MagicWands extends JavaPlugin {
    /*
    TODO list.
    1. Refactor loadSpells to use abstracted classes
    2. Fix the remaining exceptions (Handlers & comments in the main class)
     */


    static HashMap<String, Spell> spells;
    @Getter
    private static ArrayList<Wand> wands;
    @Getter
    @NonNull
    private static MagicWands instance;

    @Override
    public void onEnable() {
        instance = this;
        spells = new HashMap<>();
        wands = new ArrayList<>();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new MagicListener(), this);
    }

    // started refactoring, will finish.
    private void loadSpells() {
        ConfigurationUtilities spellsFile = new ConfigurationUtilities(ConfigurationUtilities.FileType.SPELLS, this);

        for (String path : spellsFile.getConfiguration().getConfigurationSection("spells").getKeys(false)) {
                String name = spellsFile.getString("spells." + path + ".name");
                String description = spellsFile.getString("spells." + path + ".description");
                int cooldown = spellsFile.getInt("spells." + path + ".cooldown");
                int cost = spellsFile.getInt("spells." + path + ".cost");
                String stringHandler = spellsFile.getString("spells." + path + ".handler");
                if (stringHandler == null) stringHandler = "";

                SpellHandler handler;


                if (stringHandler.equalsIgnoreCase("ItemRain")) {
                    ItemStack action = spellsFile.getItemStack("spells." + path + ".action");
                    //As for now, this requires a Location which is not known at loading time
                    //The same goes for the comments below, I commented them out since
                    //This part of the code is currently not used anyway
                    
                    //handler = new ItemRain(action); 
                } else if (stringHandler.equalsIgnoreCase("SpawnBlock")) {
                    Material action = null;
                    try {
                        action = Material.valueOf(spellsFile.getString("spells." + path + ".action"));
                    } catch (Exception e) {
                        getLogger().log(Level.INFO, "{0} is not a valid action for SpawnBlock, please check your getConfig() file (at spell " + path + ")", action);
                    } finally {
                        //handler = action != null ? new SpawnBlock(action) : null;
                    }
                } else if (stringHandler.equalsIgnoreCase("TransformBlock")) {
                    Material action = null;
                    try {
                        action = Material.valueOf(spellsFile.getString("spells." + path + ".action"));
                    } catch (Exception e) {
                        getLogger().log(Level.INFO, "{0} is not a valid action for TransformBlock, please check your getConfig() file (at spell " + path + ")", action);
                    } finally {
                        handler = new TransformBlock(action);
                    }
                } else if (stringHandler.equalsIgnoreCase("Explosion")) {
                    int action = spellsFile.getInt("spells." + path + ".action");
                    handler = new Explosion(action);
                } else if (stringHandler.equalsIgnoreCase("TeleportLocation")) {
                    int action = spellsFile.getInt("spells." + path + ".action");
                    handler = new TeleportLocation(action);
                } else if (stringHandler.equalsIgnoreCase("SpawnEntity")) {
                    EntityType action = null;
                    try {
                        action = EntityType.valueOf(spellsFile.getString("spells." + path + ".action"));
                    } catch (Exception e) {
                        getLogger().log(Level.INFO, "{0} is not a valid action for SpawnEntity, please check your getConfig() file (at spell " + path + ")", action);
                    } finally {
                        handler = action != null ? new SpawnEntity(action) : null;
                    }
                } else {
                    handler = null;
                    getLogger().log(Level.INFO, "[MagicWands] Handler {0} of spell " + path + " is not recognized! Please make sure this spell has a handler added and if so, confirm that the plugin supports this handler.", stringHandler);
                }

                //spells.put(path, new Spell(name, description, handler, cooldown, cost));
            }
        

        if (spellsFile.contains("wands")) {
            if (!spells.isEmpty()) {
                spellsFile.getConfigurationSection("wands").getKeys(false).stream().forEach((path) -> {
                    Wand wand = new Wand(getConfig().getString("wands." + path + ".name"));
                    getConfig().getStringList("wands." + path + ".spells").stream().forEach((strSpell) -> {
                        wand.addSpell(spells.get(strSpell));
                    });

                    wands.add(wand);
                });
            } else {
                getLogger().info("[MagicWands]No spells found, the wands won't be loaded!");
            }
        }
    }

}
