package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Grind extends Enchantment implements Listener {

    //Completed

    public Grind(int key) {
        super(key);
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
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("SWORD")) {
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void grind(EntityDeathEvent e) {
        if(e.getEntity() instanceof Player || !(e.getEntity().getKiller() instanceof Player)) return;
        Player p = e.getEntity().getKiller();
        if(Main.hasEnchantment(p.getInventory().getItemInHand(), this)) {
            int tooAdd = e.getDroppedExp() / Main.getLevel(p.getInventory().getItemInHand(), this);
            e.setDroppedExp(e.getDroppedExp() + tooAdd);
            return;
        }
    }
}
