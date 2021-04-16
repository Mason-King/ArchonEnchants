package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class HeadShot extends Enchantment implements Listener {


    public HeadShot(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Head shot";
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
        return EnchantmentTarget.BOW;
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
    public void headshot(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof  Player)) return;
        Player shot = (Player) e.getEntity();
        Player shooter = (Player) e.getDamager();
        if(e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
            if(shooter.getInventory().getItemInMainHand().containsEnchantment(this)) {
                if(new Random().nextInt(100) <= 20 * shooter.getInventory().getItemInMainHand().getEnchantmentLevel(this)) {
                    e.setDamage(4);
                }
            }
        }
    }


}
