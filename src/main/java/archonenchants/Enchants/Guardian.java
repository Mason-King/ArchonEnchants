package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import javax.naming.Name;
import java.util.List;
import java.util.Random;

public class Guardian extends Enchantment implements Listener {
    public Guardian(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Guardian";
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
    public void guardian(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        for(ItemStack is : Main.getArmor(p)) {
            if(is.containsEnchantment(this)) {
                if(new Random().nextInt(100) <= 10 * is.getEnchantmentLevel(this)) {
                    Location l = p.getLocation();
                    l.getWorld().spawnEntity(l, EntityType.IRON_GOLEM);
                }
            }
        }
    }

}