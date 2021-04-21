package archonenchants;

import archonenchants.Enchants.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

public final class Main extends JavaPlugin {

    private static Main instance;
    public static ArrayList<Enchantment> custom_enchants = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        AntiTank antiTank = new AntiTank("AntiTank");
        Beast beast = new Beast("Beast");
        Berserk berserk = new Berserk("Berserk");
        Blaze blaze = new Blaze("Blaze");
        CannonBreaker cannonBreaker = new CannonBreaker("Cannon Breaker");
        registerEnchantment(antiTank);
        this.getServer().getPluginManager().registerEvents(antiTank, this);

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

    public  void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            custom_enchants.add(enchantment);
        }
    }



}
