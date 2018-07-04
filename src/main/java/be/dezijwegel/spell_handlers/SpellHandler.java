package be.dezijwegel.spell_handlers;

import be.dezijwegel.MagicWands;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author Dieter
 */
public class SpellHandler {


    @Getter
    @Setter
    private long cooldown = 0;
    private HashMap<UUID, Long> isInCooldown = new HashMap<>();
    @Setter
    private int cost;
    @Getter
    private Player caster;

    /**
     * Check if a player can cast a spell already
     *
     * @param p
     * @return true: player may cast, false: player may not cast yet
     */
    public boolean isCastable(Player p) {
        if (isInCooldown.containsKey(p.getUniqueId()))
            return System.currentTimeMillis() / 100 - isInCooldown.get(p.getUniqueId()) > cooldown;
        else return true;


    }

    /**
     * Add a player and current time to the cooldown tracker
     *
     * @param p
     */

    public void setCast(Player p) {
        isInCooldown.put(p.getUniqueId(), System.currentTimeMillis() / 100);
        new BukkitRunnable() {
            @Override
            public void run() {
                isInCooldown.remove(p.getUniqueId());
            }
        }.runTaskLater(MagicWands.getInstance(), cooldown - System.currentTimeMillis() / 100);
    }

    /**
     * Get the time a player has to wait before they can cast this spell again
     *
     * @param p
     * @return
     */
    public long getWaitTime(Player p) {
        return cooldown - (System.currentTimeMillis() / 100 - isInCooldown.get(p.getUniqueId()));
    }


    /**
     * set the cost of this spell
     * @param cost
     */

    /**
     * Used to execute a cast spell
     *
     * @param player who cast the spell
     * @return
     */
    public boolean cast(Player player) {
        if (isCastable(player)) {
            return true;
        } else {
            player.sendMessage("§3[magicWands]§c You have to wait " + getWaitTime(player) / 10 + " seconds before you can cast this spell again!");
            return false;
        }
    }
}
