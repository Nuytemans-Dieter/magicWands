package be.dezijwegel.objects;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import org.bukkit.entity.Player;

public class PlayerDataList{

    private @Getter HashMap<UUID, PlayerData> list = null;
    
    public PlayerData getPlayerData(UUID uuid)
    {
        return list.get(uuid);
    }
    
    public List<Wand> getPlayerWands(UUID uuid)
    {
        return list.get(uuid).getWands();
    }
    
    public void addPlayerData(PlayerData pd)    
    {
        list.put(pd.getPlayer().getUniqueId(), pd);
    }
    
    
    public void removePlayerData(Player pd)
    {
        list.remove(pd.getUniqueId());
    }


    
}
