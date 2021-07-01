package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import javax.naming.Name;
import java.util.List;
import java.util.Random;

public class Reborn extends Enchantment implements Listener {
    //works
    public Reborn(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Reborn";
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
        if(item.getType().toString().endsWith("HELMET") || item.getType().toString().endsWith("CHESTPLATE") || item.getType().toString().endsWith("LEGGINGS") || item.getType().toString().endsWith("BOOTS")) {
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void reborn(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        Player damager = (Player) e.getDamager();
        for(ItemStack i : Main.getArmor(p)) {
            if(i == null) continue;
            if(Main.hasEnchantment(i, this)) {
                if(new Random().nextInt(100) <= 20 * Main.getLevel(i, this)) {
                    p.setHealth(20);
                }
            }
        }
    }

}
