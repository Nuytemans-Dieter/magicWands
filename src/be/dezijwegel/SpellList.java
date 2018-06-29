package be.dezijwegel;

import be.dezijwegel.spellHandlers.Explosion;
import be.dezijwegel.spellHandlers.FurnaceFire;
import be.dezijwegel.spellHandlers.ItemRain;
import be.dezijwegel.spellHandlers.SpawnEntity;
import be.dezijwegel.spellHandlers.TransformBlock;
import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Dieter
 */
public class SpellList {
    
    private ArrayList<Spell> spells;
    private int currentSpell = 0;
    
    public SpellList()
    {
        spells = new ArrayList<>();
        
        //Create and add spells
        spells.add(new Spell("explosion", "Crepitus", "Make an explosion happen", new Explosion(), 100));
        
        //spells.add(new Spell("furnace_fire", "Fornacem Ignis", "Lit a furnace", new FurnaceFire()));
        
        spells.add(new Spell("spawn_zombie", "Pariunt Zombie", "Spawn a zombie", new SpawnEntity(EntityType.ZOMBIE), 100));
        spells.add(new Spell("spawn_skeleton", "Pariunt Skeleton", "Spawn a skeleton", new SpawnEntity(EntityType.SKELETON), 500));
        spells.add(new Spell("spawn_creeper", "Pariunt Creeper", "Spawn a creeper", new SpawnEntity(EntityType.CREEPER), 1000));
        
        spells.add(new Spell("spawn_cow", "Pariunt Cow", "Spawn a cow", new SpawnEntity(EntityType.COW), 20));
        spells.add(new Spell("spawn_pig", "Pariunt Pig", "Spawn a pig", new SpawnEntity(EntityType.PIG), 20));
        spells.add(new Spell("spawn_chicken", "Pariunt Chicken", "Spawn a chicken", new SpawnEntity(EntityType.CHICKEN), 20));
        
        spells.add(new Spell("trans_cobblestone", "Verto Cobblestone", "Morph a block into cobblestone", new TransformBlock(Material.COBBLESTONE), 1));
        spells.add(new Spell("trans_craftingTable", "Verto Crafting Table", "Morph a block into a crafting table", new TransformBlock(Material.WORKBENCH), 100));
        spells.add(new Spell("trans_chest", "Verto Chest", "Morph a block into a chest", new TransformBlock(Material.CHEST), 100));
        spells.add(new Spell("trans_water", "Verto Water", "Morph a block into water", new TransformBlock(Material.WATER), 100));
        spells.add(new Spell("trans_lava", "Verto Lava", "Morph a block into lava", new TransformBlock(Material.LAVA), 1000));
        
        spells.add(new Spell("rain_iron", "Pluviam Iron", "Let it rain iron ingots", new ItemRain(new ItemStack(Material.IRON_INGOT)), 1000));
        spells.add(new Spell("rain_redstone", "Pluviam Redstone", "Let it rain redstone", new ItemRain(new ItemStack(Material.REDSTONE, 3)), 1000));
    }
    
    /**
     * Get the current spell
     * @return 
     */
    public Spell getSpell()
    {
        return spells.get(currentSpell);
    }
    
    /**
     * Switch to the next spell in the list and return the message for selection
     * @return String
     */
    public Spell nextSpell()
    {
        if (currentSpell == spells.size()-1) currentSpell = 0;
        else currentSpell++;
        return spells.get(currentSpell);
    }
}
