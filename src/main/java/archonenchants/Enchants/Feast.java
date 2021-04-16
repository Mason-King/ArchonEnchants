package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

import javax.naming.Name;

public class Feast extends Enchantment implements Listener {
    public Feast(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Feast";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return false;
    }

    @EventHandler
    public void feast(FoodLevelChangeEvent e) {
        Player p = (Player) e.getEntity();
        for(ItemStack s : Main.getArmor(p)) {
            if(s.containsEnchantment(this)) {
                e.setCancelled(true);
            }
        }
    }

}
