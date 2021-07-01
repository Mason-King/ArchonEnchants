package archonenchants.Enchants;

import archonenchants.Main;
import me.swanis.mobcoins.events.MobCoinsReceiveEvent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MobHunter extends Enchantment implements Listener {
    public MobHunter(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "MobHunter";
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
    public void onGain(MobCoinsReceiveEvent e) {
        Player p = (Player) e.getProfile().getPlayer();
        if(Main.hasEnchantment(p.getInventory().getItemInHand(), this)) {
            if(new Random().nextInt(100) <= 100 * Main.getLevel(p.getInventory().getItemInHand(), this)) {
                e.setAmount(e.getAmount() * 2);
            }
        }
    }

}
