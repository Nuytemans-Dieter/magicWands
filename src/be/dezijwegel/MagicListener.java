package be.dezijwegel;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 *
 * @author Dieter Nuytemans
 */
public class MagicListener implements Listener {
    
    Wand wand = new Wand();
    SpellList spells = wand.getSpellList();
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        e.getPlayer().getInventory().addItem(wand);
    }
    
    @EventHandler
    public void onClick(PlayerInteractEvent e)
    {
        //Check if the right item is held
        if (e.getItem() != null && wand.equals(e.getItem()))
        {   //Check if it is held in the main hand
            if (e.getHand() == EquipmentSlot.HAND)
            {   //Figure out whether it was a left/right click and act accordingly
                Player p = e.getPlayer();
                if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)
                { //Cast spell
                    if(spells.getSpell().cast(p))
                        p.sendMessage("§3[magicWands]§bYou cast " + spells.getSpell().getCastName() + "!");
                } else if (e.getAction() == Action.RIGHT_CLICK_AIR ||e.getAction() == Action.RIGHT_CLICK_BLOCK)
                { //Next spell
                    p.sendMessage("§3[magicWands]§bYou will now use " + spells.nextSpell().getCastName() + " (" + spells.getSpell().getDescr() + ")");
                }
            }
        }
    }
}
