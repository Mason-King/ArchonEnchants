package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Grind extends Enchantment implements Listener {
    public Grind(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Grind";
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
    public void grind(EntityDeathEvent e) {
        if(e.getEntity() instanceof Player || !(e.getEntity().getKiller() instanceof Player)) return;
        Player p = e.getEntity().getKiller();
        if(p.getInventory().getItemInMainHand().containsEnchantment(this)) {
            int tooAdd = e.getDroppedExp() / p.getInventory().getItemInMainHand().getEnchantmentLevel(this);
            e.setDroppedExp(e.getDroppedExp() + tooAdd);
            return;
        }
    }
}
