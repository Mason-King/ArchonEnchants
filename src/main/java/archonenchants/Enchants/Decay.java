package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class Decay extends Enchantment implements Listener {
    public Decay(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Decay";
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
    public void decay(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
        Player damaged = (Player) e.getEntity();
        Player damager = (Player) e.getDamager();
        e.getDamage(EntityDamageEvent.DamageModifier.ARMOR);
    }

}
