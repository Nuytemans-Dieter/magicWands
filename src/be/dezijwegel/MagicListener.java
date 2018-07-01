package be.dezijwegel;

import be.dezijwegel.spellHandlers.Explosion;
import be.dezijwegel.spellHandlers.HandlerLess;
import be.dezijwegel.spellHandlers.ItemRain;
import be.dezijwegel.spellHandlers.SpawnBlock;
import be.dezijwegel.spellHandlers.SpawnEntity;
import be.dezijwegel.spellHandlers.SpellHandler;
import be.dezijwegel.spellHandlers.TeleportLocation;
import be.dezijwegel.spellHandlers.TransformBlock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import static org.bukkit.Bukkit.getLogger;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Dieter Nuytemans
 */
public class MagicListener implements Listener {
    
    private HashMap<String, Spell> spells;
    private ArrayList<Wand> wands;
    
    /**
     * Initializes all wands
     * @param config
     */
    public MagicListener(FileConfiguration config)
    {   
        spells = new HashMap<>();
        wands = new ArrayList<>();
        if (config.contains("spells")) {
            for(String path : config.getConfigurationSection("spells").getKeys(false))
            {
                String name = config.getString("spells." + path + ".name");
                String description = config.getString("spells." + path + ".description");
                int cooldown = config.getInt("spells." + path + ".cooldown");
                int cost = config.getInt("spells." + path + ".cost");
                String stringHandler = config.getString("spells." + path + ".handler");
                if (stringHandler == null) stringHandler = "";
                
                SpellHandler handler;
                if (stringHandler.equalsIgnoreCase("ItemRain"))
                { 
                    ItemStack action = config.getItemStack("spells." + path + ".action");
                    handler = new ItemRain(action);
                } else if (stringHandler.equalsIgnoreCase("SpawnBlock"))
                {
                    Material action = null;
                    try {
                        action = Material.valueOf(config.getString("spells." + path + ".action"));
                    } catch(Exception e)
                    {
                        getLogger().log(Level.INFO, "{0} is not a valid action for SpawnBlock, please check your config file (at spell " + path + ")", action);
                    } finally {
                        handler = action != null ? new SpawnBlock(action) : new HandlerLess();
                    }
                } else if (stringHandler.equalsIgnoreCase("TransformBlock")) 
                {
                    Material action = null;
                    try {
                        action = Material.valueOf(config.getString("spells." + path + ".action"));
                    } catch(Exception e)
                    {
                        getLogger().log(Level.INFO, "{0} is not a valid action for TransformBlock, please check your config file (at spell " + path + ")", action);
                    } finally {
                        handler = new TransformBlock(action);
                    }
                } else if (stringHandler.equalsIgnoreCase("Explosion"))
                {
                    int action = config.getInt("spells." + path + ".action");
                    handler = new Explosion(action);
                } else if (stringHandler.equalsIgnoreCase("TeleportLocation")) 
                {
                    int action = config.getInt("spells." + path + ".action");
                    handler = new TeleportLocation(action);
                }else if(stringHandler.equalsIgnoreCase("SpawnEntity"))
                {
                    EntityType action = null;
                    try {
                        action = EntityType.valueOf(config.getString("spells." + path + ".action"));
                    } catch(Exception e)
                    {
                        getLogger().log(Level.INFO, "{0} is not a valid action for SpawnEntity, please check your config file (at spell " + path + ")", action);
                    } finally {
                        handler = action != null ? new SpawnEntity(action) : new HandlerLess();
                    }
                } else {
                    handler = new HandlerLess();
                    getLogger().log(Level.INFO, "[MagicWands] Handler {0} of spell " + path + " is not recognized! Please make sure this spell has a handler added and if so, confirm that the plugin supports this handler.", stringHandler);
                }
                
                spells.put(path, new Spell(path, name, description, handler, cooldown, cost));
            }
        }
        
        if (config.contains("wands"))
        {
            if (!spells.isEmpty())
            {
                config.getConfigurationSection("wands").getKeys(false).stream().forEach((path) -> {
                    Wand wand = new Wand(config.getString("wands." + path + ".name"));
                    config.getStringList("wands." + path + ".spells").stream().forEach((strSpell) -> {
                        wand.addSpell(spells.get(strSpell));
                    });
                    
                    wands.add(wand);
                });
            } else {
                getLogger().info("[MagicWands]No spells found, the wands won't be loaded!");
            }
        }
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        for (Wand wand : wands)
        {
            e.getPlayer().getInventory().addItem(wand);
        }
    }
    
    
    @EventHandler
    public void onClick(PlayerInteractEvent e)
    {
        if (e.getItem() != null)
        {   
            Wand wand = null;
            for (Wand wandTest : wands)
            {
                if (wandTest.equals(e.getItem()))
                {
                    wand = wandTest;
                }
            }
            
            if (wand == null) return;
            
            e.setCancelled(true);
            
            //Check if it is held in the main hand
            if (e.getHand() == EquipmentSlot.HAND)
            {   //Figure out whether it was a left/right click and act accordingly
                Player p = e.getPlayer();
                if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)
                { //Cast spell
                    if(wand.getSpell(p) != null){
                        if(wand.getSpell(p).cast(p))
                            p.sendMessage("§3[magicWands] §bYou cast " + wand.getSpell(p).getCastName() + "!");}
                    else p.sendMessage("§3[magicWands] §cThis wand doesn't have a spell selected :(, please ask an admin to check the config file.");
                } else if (e.getAction() == Action.RIGHT_CLICK_AIR ||e.getAction() == Action.RIGHT_CLICK_BLOCK)
                { //Next spell
                    Spell next;
                    if((next = wand.nextSpell(p)) != null)
                        p.sendMessage("§3[magicWands] §bYou will now use " + next.getCastName() + " (" + next.getDescr() + ")");
                    else p.sendMessage("§3[magicWands] §cThis wand doesn't have any spells yet :(, please ask an admin to check the config file.");
                }
            }
        }
    }
    
    @EventHandler
    public void onLeave(PlayerQuitEvent e)
    {
        //Remove player from all lists
    }
}
