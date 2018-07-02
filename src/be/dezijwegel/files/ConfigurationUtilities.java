package be.dezijwegel.files;

import be.dezijwegel.MagicWands;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
     *
     * @param type of file
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

    public Object get(String path) {
        return configuration.get(path);
    }

    public String getString(String path) {
        return configuration.getString(path);
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
