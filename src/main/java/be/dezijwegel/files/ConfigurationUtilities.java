package be.dezijwegel.files;

import be.dezijwegel.MagicWands;
import be.dezijwegel.objects.Spell;
import be.dezijwegel.spell_handlers.SpellHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * created by @Perotin
 * Used for handling custom yaml files
 */

/*
 Mostly boiler plate code from Bukkit's Wiki, we shouldn't be using the config.yml for storing spells and this
 Allows us to be more expansive in the future when it comes to files
 */
public class ConfigurationUtilities {

    private FileConfiguration configuration;
    private File file;
    private MagicWands plugin;
    private FileType type;

    /**
     * @param type   of file
     * @param plugin
     */
    public ConfigurationUtilities(FileType type, MagicWands plugin) {
        switch (type) {
            case SPELLS:
                this.file = new File(plugin.getDataFolder(), "spells.yml");
                this.configuration = YamlConfiguration.loadConfiguration(file);
                this.type = type;
                break;
            case MESSAGES:
                this.file = new File(plugin.getDataFolder(), "messages.yml");
                this.configuration = YamlConfiguration.loadConfiguration(file);
                this.type = type;
                break;
        }

    }

    public static void loadSpells() {
        ConfigurationUtilities file = new ConfigurationUtilities(FileType.SPELLS, MagicWands.getInstance());
        for (String spellName : file.getConfiguration().getConfigurationSection("spells").getKeys(false)) {
            String description = file.getConfiguration().getString("spells." + spellName + ".description");
            int cost = file.getInt("spells." + spellName + ".cost");

            SpellHandler spellHandler = null;
            try {
                spellHandler = (SpellHandler) Class.forName(file.getString("spells." + spellName + ".handler")).newInstance();
            } catch (Exception ex) {
                Bukkit.getConsoleSender().sendMessage("Spell handler not found for " + spellName + "!");
                Bukkit.getConsoleSender().sendMessage(spellName + " will not be loaded!");
                continue;

            }
            if (spellHandler != null) {
                MagicWands.getSpells().add(new Spell(spellName, description, spellHandler, cost));
            }


        }
    }
    public FileConfiguration getConfiguration() {
        return this.configuration;
    }

    public Object get(String path) {
        return configuration.get(path);
    }

    public String getString(String path) {
        String string = ChatColor.translateAlternateColorCodes('&', configuration.getString(path));
        if (string.contains("$prefix$")) {
            string.replace("$prefix$", getConfiguration().getString("prefix"));
        }
        return string;
    }


    public int getInt(String path) {
        return configuration.getInt(path);
    }

    public ItemStack getItemStack(String path) {
        return configuration.getItemStack(path);
    }
    
    public boolean contains(String path) {
        return configuration.contains(path);
    }
    
    public ConfigurationSection getConfigurationSection(String path) {
        return configuration.getConfigurationSection(path);
    }
    
    public void reloadFile() {
        if (configuration == null) {
            switch (type) {
                case SPELLS:
                    file = new File(plugin.getDataFolder(), "spells.yml");
                    break;
                case MESSAGES:
                    file = new File(plugin.getDataFolder(), "messages.yml");
                    break;
            }
        }
        configuration = YamlConfiguration.loadConfiguration(file);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        try {
            switch (type) {
                case SPELLS:
                    defConfigStream = new InputStreamReader(plugin.getResource("spells.yml"), "UTF8");
                    break;
                case MESSAGES:
                    defConfigStream = new InputStreamReader(plugin.getResource("messages.yml"), "UTF8");
                    break;
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            configuration.setDefaults(defConfig);
        }
    }

    public void saveDefaultConfig() {
        if (file == null) {
            switch (type) {
                case MESSAGES:
                    file = new File(plugin.getDataFolder(), "messages.yml");
                    break;
                case SPELLS:
                    file = new File(plugin.getDataFolder(), "spells.yml");
            }
        }
        if (!file.exists()) {
            switch (type) {
                case SPELLS:
                    plugin.saveResource("spells.yml", false);
                    break;
                case MESSAGES:
                    plugin.saveResource("messages.yml", false);
            }
        }
    }


    public enum FileType {
        SPELLS, MESSAGES
    }


}
