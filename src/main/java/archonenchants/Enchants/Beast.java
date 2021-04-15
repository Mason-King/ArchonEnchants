package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Beast extends Enchantment implements Listener {
    public Beast(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Beast";
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
    public void onClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        ItemStack helmet = p.getInventory().getHelmet(), chestplate = p.getInventory().getChestplate(), leggings = p.getInventory().getLeggings(), boots = p.getInventory().getBoots();
        if(helmet.containsEnchantment(this) && chestplate.containsEnchantment(this) && leggings.containsEnchantment(this) && boots.containsEnchantment(this)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.HARM, Integer.MAX_VALUE, 1));
        } else {
            p.removePotionEffect(PotionEffectType.HARM);
        }


    }

}
