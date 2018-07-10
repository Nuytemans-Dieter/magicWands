package be.dezijwegel.runnables;

import be.dezijwegel.objects.Wizard;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class ManaRegeneration extends BukkitRunnable implements Cancellable {

    /**
     * time in seconds for mana to be regenerated
     */
    @Getter
    @Setter
    private int cycle;
    /**
     * @apiNote The amount of mana that gets added every cycle
     */
    @Getter
    @Setter
    private int regenMana;
    /**
     * @apiNote cancellable for use in potential spells that stop mana regen
     */
    private boolean cancelled;
    @Getter
    private final Wizard wizard;
    @Getter
    private final UUID wizardUUID;


    public ManaRegeneration(int cycleSeconds, int mana, Wizard wizard) {
        this.cycle = cycleSeconds;
        this.regenMana = mana;
        this.wizard = wizard;
        this.cancelled = false;
        this.wizardUUID = wizard.getUuid();
    }

    /** @apiNote run asynchronously every cycle */
    @Override
    public void run() {
        wizard.setCurrentMana(wizard.getCurrentMana()+ getRegenMana());
    }


    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
