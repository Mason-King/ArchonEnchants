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

public class Escape extends Enchantment implements Listener {

    //Working!
    public Escape(int key) {
        super(key);
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
    public void escape(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
        Player damaged = (Player) e.getEntity();
        ItemStack[] armor = Main.getArmor(damaged);
        if(armor[3] == null || !Main.hasEnchantment(armor[3], this)) return;
        if(damaged.getHealth() < 6) {
            damaged.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 2));
        }
    }
}
