package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.naming.Name;
import java.util.Random;

public class WebShooter extends Enchantment implements Listener {
    public WebShooter(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Web Shooter";
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
    public void web(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof  Player)) return;
        if(e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
            Player p = (Player) e.getDamager();
            Player damaged = (Player) e.getEntity();
            if(p.getInventory().getItemInMainHand().containsEnchantment(this)) {
                if(new Random().nextInt(100) <= 20 * p.getInventory().getItemInMainHand().getEnchantmentLevel(this)) {
                    Location l = damaged.getLocation();
                    l.getBlock().setType(Material.COBWEB);
                    l.add(0,1,0);
                    l.getBlock().setType(Material.COBWEB);
                }
            }
        }
    }

}
