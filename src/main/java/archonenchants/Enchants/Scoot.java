package archonenchants.Enchants;

import archonenchants.Main;
import com.codingforcookies.armorequip.ArmorEquipEvent;
import org.bukkit.Material;
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

public class Scoot extends Enchantment implements Listener {
    public Scoot(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Scoot";
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
        return EnchantmentTarget.ARMOR_FEET;
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
    public void onEquip(ArmorEquipEvent e) {

        Player p = e.getPlayer();

        if (checkIfBoots(e.getNewArmorPiece())) {
            if(e.getNewArmorPiece().containsEnchantment(this)) {
                System.out.println(p.getInventory().getBoots().getEnchantmentLevel(this));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, p.getInventory().getBoots().getEnchantmentLevel(this)));
            }
        }
    }

    @EventHandler
    public void scoot(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        System.out.println("1");
        if (p.getInventory().getBoots().containsEnchantment(this)) {
            System.out.println("2");
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, p.getInventory().getBoots().getEnchantmentLevel(this)));
        } else {
            System.out.println("3");
            p.removePotionEffect(PotionEffectType.SPEED);
        }
    }

    public static boolean checkIfBoots(ItemStack i) {
        if(i.getType() == Material.LEATHER_BOOTS) {
            return true;
        }
        if(i.getType() == Material.IRON_BOOTS) {
            return true;
        }
        if(i.getType() == Material.IRON_BOOTS) {
            return true;
        }
        if(i.getType() == Material.DIAMOND_BOOTS) {
            return true;
        }
        return false;
    }

}


