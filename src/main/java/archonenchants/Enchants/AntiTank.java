package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;


public class AntiTank extends Enchantment {

    public AntiTank(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "AntiTank";
    }

    @Override
    public int getMaxLevel() {
        return 2;
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
    public void antiTank(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
        Player damager = (Player) e.getDamager(), damaged = (Player) e.getEntity();
        if(damaged.getInventory().getHelmet().containsEnchantment(this) || damaged.getInventory().getChestplate().containsEnchantment(this) || damaged.getInventory().getLeggings().containsEnchantment(this) || damaged.getInventory().getBoots().containsEnchantment(this)) {
            Random rand = new Random();
            int chance = rand.nextInt(100);
            if(chance < 21) {
                damager.setHealth(damager.getHealth() - 2);
            }
        }
    }

}
