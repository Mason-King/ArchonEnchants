package archonenchants.Enchants;

import archonenchants.Main;
import me.swanis.mobcoins.events.MobCoinsReceiveEvent;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MobHunter extends Enchantment implements Listener {
    public MobHunter(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Mob Hunter";
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
    public void onGain(MobCoinsReceiveEvent e) {
        Player p = (Player) e.getProfile().getPlayer();
        if(p.getInventory().getItemInMainHand().containsEnchantment(this)) {
            if(new Random().nextInt(100) <= 20 * p.getInventory().getItemInMainHand().getEnchantmentLevel(this)) {
                e.setAmount(e.getAmount() * 2);
            }
        }
    }

}
