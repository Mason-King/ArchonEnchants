package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;


public class AntiTank extends Enchantment implements Listener {

    //Completed!
    /**
     * AntiTank enchantment
     * 20% chance to deal double damage when applied to armor
     *
     * @param key - Enchantment name
     */
    public AntiTank(int key) {
        super(key);
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
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("HELMET") || item.getType().toString().endsWith("CHESTPLATE") || item.getType().toString().endsWith("LEGGINGS") || item.getType().toString().endsWith("BOOTS")) {
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void antiTank(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
        Player damager = (Player) e.getDamager();
        Player damaged = (Player) e.getEntity();
        for(ItemStack i : Main.getArmor(damager)) {
            if(i == null || !Main.hasEnchantment(i, this)) continue;
            if(new Random().nextInt(100) <= 20 * Main.getLevel(i, this)) {
                e.setDamage(e.getDamage() * 1.25);
                return;
            }
        }
    }

}
