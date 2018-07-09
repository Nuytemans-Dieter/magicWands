package be.dezijwegel;

import be.dezijwegel.events.MagicListener;
import be.dezijwegel.events.PlayerJoinListener;
import be.dezijwegel.events.PlayerLeaveListener;
import be.dezijwegel.objects.PlayerDataList;
import be.dezijwegel.objects.Spell;
import be.dezijwegel.objects.Wand;
import be.dezijwegel.runnables.ManaRegeneration;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


/**
 * @author Dieter Nuytemans, Perotin
 */
public class MagicWands extends JavaPlugin {
    /*
    TODO list.
    1. Refactor loadSpells to use abstracted classes
    2. I'm working on this: The PlayerInteractEvent in MagicListener has to be rewritten to fit the new system
    3. Decide how the active spell is determined and implement this
       (old way was cycling through a list on right click)
     */


    static ArrayList<Spell> spells;
    @Getter
    private static ArrayList<Wand> wands;
    @Getter
    @NonNull
    private static MagicWands instance;
    private @Getter PlayerDataList playerDataList;
    
    @Override
    public void onEnable() {
        instance = this;
        spells = new ArrayList<>();
        wands = new ArrayList<>();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new MagicListener(), this);
        playerDataList = new PlayerDataList();
        ManaRegeneration manaReg = new ManaRegeneration(playerDataList, 5);     
        //'5' above is the amount of mana that gets added to all players each cycle, 
        //it's a fixed value right now but can be made variable in the future
        //Perhaps we can work with player-specific values or group based values?
        //The regeneration rate needs some more thought
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(playerDataList), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(playerDataList), this);
        manaReg.runTaskTimer(this, 0, 20);
    }


//    // started refactoring, will finish.
//    private void loadSpells() {
//        ConfigurationUtilities spellsFile = new ConfigurationUtilities(ConfigurationUtilities.FileType.SPELLS, this);
//
//        for (String path : spellsFile.getConfiguration().getConfigurationSection("spells").getKeys(false)) {
//                String name = spellsFile.getString("spells." + path + ".name");
//                String description = spellsFile.getString("spells." + path + ".description");
//                int cooldown = spellsFile.getInt("spells." + path + ".cooldown");
//                int cost = spellsFile.getInt("spells." + path + ".cost");
//                String stringHandler = spellsFile.getString("spells." + path + ".handler");
//                if (stringHandler == null) stringHandler = "";
//
//                SpellHandler handler;
//
//
//                if (stringHandler.equalsIgnoreCase("ItemRain")) {
//                    ItemStack action = spellsFile.getItemStack("spells." + path + ".action");
//                    //As for now, this requires a Location which is not known at loading time
//                    //The same goes for the comments below, I commented them out since
//                    //This part of the code is currently not used anyway
//
//                    //handler = new ItemRain(action);
//                } else if (stringHandler.equalsIgnoreCase("SpawnBlock")) {
//                    Material action = null;
//                    try {
//                        action = Material.valueOf(spellsFile.getString("spells." + path + ".action"));
//                    } catch (Exception e) {
//                        getLogger().log(Level.INFO, "{0} is not a valid action for SpawnBlock, please check your getConfig() file (at spell " + path + ")", action);
//                    } finally {
//                        //handler = action != null ? new SpawnBlock(action) : null;
//                    }
//                } else if (stringHandler.equalsIgnoreCase("TransformBlock")) {
//                    Material action = null;
//                    try {
//                        action = Material.valueOf(spellsFile.getString("spells." + path + ".action"));
//                    } catch (Exception e) {
//                        getLogger().log(Level.INFO, "{0} is not a valid action for TransformBlock, please check your getConfig() file (at spell " + path + ")", action);
//                    } finally {
//                        handler = new TransformBlock(action);
//                    }
//                } else if (stringHandler.equalsIgnoreCase("Explosion")) {
//                    int action = spellsFile.getInt("spells." + path + ".action");
//                    handler = new Explosion(action);
//                } else if (stringHandler.equalsIgnoreCase("TeleportLocation")) {
//                    int action = spellsFile.getInt("spells." + path + ".action");
//                    handler = new TeleportLocation(action);
//                } else if (stringHandler.equalsIgnoreCase("SpawnEntity")) {
//                    EntityType action = null;
//                    try {
//                        action = EntityType.valueOf(spellsFile.getString("spells." + path + ".action"));
//                    } catch (Exception e) {
//                        getLogger().log(Level.INFO, "{0} is not a valid action for SpawnEntity, please check your getConfig() file (at spell " + path + ")", action);
//                    } finally {
//                        handler = action != null ? new SpawnEntity(action) : null;
//                    }
//                } else {
//                    handler = null;
//                    getLogger().log(Level.INFO, "[MagicWands] Handler {0} of spell " + path + " is not recognized! Please make sure this spell has a handler added and if so, confirm that the plugin supports this handler.", stringHandler);
//                }
//
//                //spells.put(path, new Spell(name, description, handler, cooldown, cost));
//            }
//
//
//        if (spellsFile.contains("wands")) {
//            if (!spells.isEmpty()) {
//                spellsFile.getConfigurationSection("wands").getKeys(false).stream().forEach((path) -> {
//                    Wand wand = new Wand(getConfig().getString("wands." + path + ".name"));
//                    getConfig().getStringList("wands." + path + ".spells").stream().forEach((strSpell) -> {
//                        wand.addSpell(spells.get(strSpell));
//                    });
//
//                    wands.add(wand);
//                });
//            } else {
//                getLogger().info("[MagicWands]No spells found, the wands won't be loaded!");
//            }
//        }
//    }

}
