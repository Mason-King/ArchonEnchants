package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Rampage extends Enchantment implements Listener {
    public Rampage(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Rampage";
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
    public void onKill(PlayerDeathEvent e) {
        if(!(e.getEntity() instanceof Player) || !(e.getEntity().getKiller() instanceof  Player)) return;
        Player p = e.getEntity();
        Player killer = p.getKiller();
        if(killer.getInventory().getItemInMainHand().containsEnchantment(this)) {
            for(ItemStack i : Main.getArmor(p)) {
                if(i.getType().name().startsWith("DIAMOND")) {
                    if(new Random().nextInt(100) <= 20 * p.getInventory().getItemInMainHand().getEnchantmentLevel(this)) {
                        killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 3));
                        killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 15, 3));
                    }
                }
            }
        }
    }

}
