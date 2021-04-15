package archonenchants;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }
}
