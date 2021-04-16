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

public class Hercules extends Enchantment implements Listener {
    public Hercules(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Hurcules";
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
        return EnchantmentTarget.WEAPON;
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
    public void hercules(EntityDamageByEntityEvent e) {
        Player p = (Player) e.getDamager();
        if (p.getInventory().getItemInMainHand().containsEnchantment(this)) {
            if(new Random().nextInt(100) < 20 * p.getInventory().getItemInMainHand().getEnchantmentLevel(this)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5, p.getInventory().getItemInMainHand().getEnchantmentLevel(this)));
            }
        } else {
            return;
        }
    }

}
