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

import javax.naming.Name;
import java.util.Random;

public class Berserk extends Enchantment implements Listener {
    //completed

    public Berserk(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Berserk";
    }

    public String getDescription() {
        return "Chance to gain strength III when you're low on health";
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
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("HELMET") || item.getType().toString().endsWith("CHESTPLATE") || item.getType().toString().endsWith("LEGGINGS") || item.getType().toString().endsWith("BOOTS")) {            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void berserk(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
        Player damaged = (Player) e.getEntity();
        for(ItemStack i : Main.getArmor(damaged)) {
            if(i == null || !Main.hasEnchantment(i, this)) continue;
            if(damaged.getHealth() < 6 && new Random().nextInt(100) <= 20 * Main.getLevel(i, this)) {
                damaged.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 2));
            }
        }
    }
}
