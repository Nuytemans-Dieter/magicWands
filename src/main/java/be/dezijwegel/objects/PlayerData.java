package be.dezijwegel.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

public class PlayerData {
    
    private @Getter Player player;
    private @Getter @Setter int mana;
    
    public PlayerData(Player player, int mana)
    {
        this.player = player;
        this.mana = mana;
    }
    
}
