package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;


public class AntiTank extends Enchantment implements Listener {

    /**
     * AntiTank enchantment
     * 20% chance to deal double damage when applied to armor
     *
     * @param key - Enchantment name
     */
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
        Player damaged = (Player) e.getEntity();
        for(ItemStack i : Main.getArmor(damaged)) {
            if(!i.containsEnchantment(this) || i == null ) return;
            if(new Random().nextInt(100) <= 20 * i.getEnchantmentLevel(this)) e.setDamage(e.getDamage() * 2);
        }
    }

}
