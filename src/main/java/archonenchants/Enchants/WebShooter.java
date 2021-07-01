package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.naming.Name;
import java.util.Random;

public class WebShooter extends Enchantment implements Listener {
    public WebShooter(int key) {
        super(key);
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
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("BOW")) {
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void getArrow(EntityShootBowEvent e) {
        if(e.getForce() == 1.0) {
            if(e.getProjectile() instanceof Arrow) {
                Entity arrow = e.getProjectile();
                e.getProjectile().setMetadata("charged", new FixedMetadataValue(Main.getInstance(), arrow));
            }
        }
    }

    @EventHandler
    public void headshot(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Arrow)) return;
        Player shot = (Player) e.getEntity();
        Arrow attacker = (Arrow) e.getDamager();
        Player shooter = null;
        if(attacker.getShooter() instanceof Player) {
            shooter = (Player) attacker.getShooter();
        } else {
            return;
        }
        if(attacker.getMetadata("charged") != null) {
            if(Main.hasEnchantment(shooter.getItemInHand(), this)) {
                if(new Random().nextInt(100) <= 10 * Main.getLevel(shooter.getItemInHand(), this)) {
                    Location l = shot.getLocation();
                    Block b = l.getBlock();
                    b.setType(Material.WEB);
                    l.add(0, 1, 0);
                    b = l.getBlock();
                    b.setType(Material.WEB);
                }
            }
        }
    }

}
