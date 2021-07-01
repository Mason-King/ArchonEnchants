package archonenchants.Enchants;

import archonenchants.Main;
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

public class Shield extends Enchantment implements Listener {
    public Shield(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Shield";
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
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("SWORDS")) {
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void onhit(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getDamager();
        if(Main.hasEnchantment(p.getInventory().getItemInHand(), this)) {
            if(new Random().nextInt(100) <= 20 * Main.getLevel(p.getInventory().getItemInHand(), this)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0));
            }
        }
    }

}
