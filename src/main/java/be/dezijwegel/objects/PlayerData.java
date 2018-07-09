package be.dezijwegel.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * Class for storing player data like mana and wands
 **/
@Getter
@Setter
public class PlayerData {


    /**
     * @apiNote player object, make sure to null check on leave event
     **/
    private Player player;
    /**
     * @apiNote current mana of player
     **/
    private int currentMana;
    /**
     * @apiNote maximum mana of player
     */
    private final int maxMana;
    private final UUID uuid;

    private @Getter List<Wand> wands;

    public PlayerData(Player player, int maxMana) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.maxMana = maxMana;
        this.currentMana = maxMana;

    }

    /**
     * add a given amount of mana to the Player's current mana
     * @param mana 
     */
    public void addMana(int mana)
    {
        currentMana += mana;
        if (currentMana > maxMana) currentMana = maxMana;
    }
    
}
