package be.dezijwegel.objects;

import be.dezijwegel.MagicWands;
import be.dezijwegel.files.ConfigurationUtilities;
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
    private String name;
    /**
     * @apiNote mana regen runnable, can vary between players
     */
    private ManaRegeneration manaTask;

    private @Getter List<Wand> wands;

    public Wizard(Player player, int maxMana, ManaRegeneration regeneration) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.maxMana = maxMana;
        this.currentMana = maxMana;
        this.manaTask = regeneration;
        manaTask.runTaskTimerAsynchronously(MagicWands.getInstance(), 0, manaTask.getCycle() * 20);

    }

    /**
     * make sure to check player is not null first!
     */
    public void cast() {
        Wand wand = getCurrentWand();
        ConfigurationUtilities messages = new ConfigurationUtilities(ConfigurationUtilities.FileType.MESSAGES, MagicWands.getInstance());
        if (wand != null) {
            if (currentMana >= wand.getCurrentSpell().getManaCost()) {
                wand.getCurrentSpell().getHandler().run();
                player.sendMessage(messages.getString("casted-spell").replace("$spell$", wand.getCurrentSpell().getName()));
            }
            // TODO put wait time in message somewhere
            player.sendMessage(messages.getString("not-enough-mana").replace("$spell$", wand.getCurrentSpell().getName())
                    .replace("$mana$", wand.getCurrentSpell().getManaCost() - getCurrentMana() + ""));
        }
    }

    public Wand getCurrentWand() {
        if (player != null) {
            /** find the wand they are using */
            for (Wand wand : getWands()) {
                if (player.getInventory().getItemInMainHand().isSimilar(wand)) {
                    return wand;
                }
            }
        }
        return null;
    }


    
}
