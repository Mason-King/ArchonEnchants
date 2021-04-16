package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Fish extends Enchantment implements Listener {
    public Fish(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Fish";
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
        return EnchantmentTarget.ARMOR_HEAD;
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
    public void fish(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        if(p.getInventory().getHelmet().containsEnchantment(this)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 1));
        } else {
            p.removePotionEffect(PotionEffectType.WATER_BREATHING);
        }
    }

}
