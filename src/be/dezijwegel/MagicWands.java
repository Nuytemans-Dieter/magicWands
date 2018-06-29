package be.dezijwegel;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Dieter Nuytemans
 */
public class MagicWands extends JavaPlugin{

    @Override
    public void onEnable()
    {
        getLogger().info("Making the magic happen!");
        getServer().getPluginManager().registerEvents(new MagicListener(),this);
    }
    
    @Override
    public void onDisable(){}
}
