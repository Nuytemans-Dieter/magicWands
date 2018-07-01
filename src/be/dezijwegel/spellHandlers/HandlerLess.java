package be.dezijwegel.spellHandlers;

import static org.bukkit.Bukkit.getLogger;
import org.bukkit.entity.Player;

/**
 *
 * @author Dieter
 */
public class HandlerLess extends SpellHandler{
    
    @Override
    public boolean cast(Player player)
    {
        player.sendMessage("§3[magicWands]§cThis spell doesn't have a spell handler! Please report this to an admin.");
        getLogger().info("[magicWands]A player just tried to cast a spell that doesn't have a handler set! Please review your config file.");
        return false;
    }
}
