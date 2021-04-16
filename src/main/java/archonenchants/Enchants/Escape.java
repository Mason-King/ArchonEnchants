package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Escape extends Enchantment implements Listener {

    public Escape(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Escape";
    }

    @Override
    public int getMaxLevel() {
        return 3;
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
    public void escape(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        if(p.getInventory().getBoots().containsEnchantment(this)) {
            if(p.getHealth() < 6) {
                if (new Random().nextInt(100) <= 30 * p.getInventory().getBoots().getEnchantmentLevel(this)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 3));
                }
            }
        }
    }
}
