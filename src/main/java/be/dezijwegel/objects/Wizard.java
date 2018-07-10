package be.dezijwegel.objects;

import be.dezijwegel.runnables.ManaRegeneration;
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
public class Wizard {


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
    /**
     * @apiNote mana regen runnable, can vary between players
     */
    private ManaRegeneration manaTask;

    private @Getter List<Wand> wands;

    public Wizard(Player player, int maxMana, ManaRegeneration regeneration) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.maxMana = maxMana;
        this.currentMana = maxMana;
        this.manaTask = regeneration;

    }


    
}
