package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.Material;
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
    //Done
    public Rampage(int key) {
        super(key);
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
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("Swords")) {
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        if(!(e.getEntity() instanceof Player) || !(e.getEntity().getKiller() instanceof  Player)) return;
        Player p = e.getEntity();
        Player killer = p.getKiller();
        if(Main.hasEnchantment(killer.getInventory().getItemInHand(), this)) {
            for(ItemStack i : Main.getArmor(p)) {
                if(i == null) continue;
                if(i.getType().equals(Material.DIAMOND_HELMET) || i.getType().equals(Material.DIAMOND_CHESTPLATE) || i.getType().equals(Material.DIAMOND_LEGGINGS) || i.getType().equals(Material.DIAMOND_BOOTS)) {
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 2));
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 2));
                    return;
                }
            }
        }
    }

}
