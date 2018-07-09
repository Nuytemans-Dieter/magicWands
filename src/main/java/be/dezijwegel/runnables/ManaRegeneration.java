package be.dezijwegel.runnables;

import be.dezijwegel.objects.PlayerData;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ManaRegeneration extends BukkitRunnable{

    private HashMap<UUID, PlayerData> list = null;
    /**
     * @apiNote The amount of mana that gets added every cycle
     */
    private final int regenMana;
    

    public ManaRegeneration(int regenMana)
    {
        this.regenMana = regenMana;
    }
    
    public void addPlayerData(PlayerData pd)    
    {
        list.put(pd.getPlayer().getUniqueId(), pd);
    }
    
    public void removePlayerData(Player pd)
    {
        list.remove(pd.getUniqueId());
    }
    
    @Override
    public void run() {
        list.entrySet().stream().forEach((entry) -> {
            entry.getValue().addMana(regenMana);
        });
    }

    
}
