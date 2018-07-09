package be.dezijwegel.runnables;

import be.dezijwegel.objects.PlayerData;
import be.dezijwegel.objects.PlayerDataList;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ManaRegeneration extends BukkitRunnable{

    private PlayerDataList list;
    /**
     * @apiNote The amount of mana that gets added every cycle
     */
    private final int regenMana;
    
    public ManaRegeneration(PlayerDataList list, int regenMana)
    {
        this.list = list;
        this.regenMana = regenMana;
    }
    
    
    @Override
    public void run() {
        list.getList().entrySet().stream().forEach((entry) -> {
            entry.getValue().addMana(regenMana);
        });
    }

    
}
