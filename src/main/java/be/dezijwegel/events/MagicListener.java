package be.dezijwegel.events;

import be.dezijwegel.MagicWands;
import be.dezijwegel.objects.Wand;
import java.util.List;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;

public class MagicListener implements Listener {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //Temporary, for wand testing purposes
        UUID uuid = event.getPlayer().getUniqueId();
        List<Wand> playerWands = MagicWands.getInstance().getPlayerDataList().getPlayerWands(uuid);
        for (Wand wand : playerWands) {
            event.getPlayer().getInventory().addItem(wand);
        }
    }


    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            UUID uuid = event.getPlayer().getUniqueId();
            Wand wand = null;
            for (Wand wandCheck : MagicWands.getInstance().getPlayerDataList().getPlayerWands(uuid)) {
                if (event.getItem().getType().equals(Material.STICK) && 
                    event.getItem().getItemMeta().equals(wandCheck.getItemMeta())) {
                        wand = wandCheck;
                        break;
                }
            }

            if (wand == null) return;

            event.setCancelled(true);

            //Check if it is held in the main hand
            if (event.getHand() == EquipmentSlot.HAND) {   //Figure out whether it was a left/right click and act accordingly
                Player player = event.getPlayer();
                if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) { //Cast spell
                    if (wand.getCurrentSpell() != null) {
                        if (wand.getCurrentSpell().cast())
                            player.sendMessage("§3[MagicWands] §bYou cast " + wand.getCurrentSpell().getName() + "!");
                    } else
                        player.sendMessage("§3[magicWands] §cThis wand doesn't have a spell selected :(, please ask an admin to check the config file.");
                } else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) { //Next spell
//                    Spell next;
//                    if ((next = wand.nextSpell(player)) != null)
//                        player.sendMessage("§3[MagicWands] §bYou will now use " + next.getName() + " (" + next.getDescription() + ")");
//                    else
//                        player.sendMessage("§3[MagicWands] §cThis wand doesn't have any spells yet :(, please ask an admin to check the config file.");
                }
            }
        }
    }
}
