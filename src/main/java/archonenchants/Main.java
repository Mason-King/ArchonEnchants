package archonenchants;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public static ItemStack[] getArmor(Player p) {
        ItemStack[] armor = new ItemStack[]{
                p.getInventory().getHelmet(),
                p.getInventory().getChestplate(),
                p.getInventory().getLeggings(),
                p.getInventory().getBoots()
        };
        return armor;
    }
}
