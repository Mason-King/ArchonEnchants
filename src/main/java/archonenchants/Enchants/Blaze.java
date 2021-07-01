package archonenchants.Enchants;

import archonenchants.ArmorEquipAPI.ArmorEquipEvent;
import archonenchants.ArmorEquipAPI.ArmorType;
import archonenchants.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Blaze extends Enchantment implements Listener {
    //Completed
    public Blaze(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Blaze";
    }

    public String getDescription() {
        return "Unlimited fire resistance";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_TORSO;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("CHESTPLATE")) {
            return true;
        } else {
            return false;
        }
    }
    @EventHandler
    public void blaze(ArmorEquipEvent e) {
        Player p = (Player) e.getPlayer();
        ItemStack[] armor = Main.getArmor(p);
        //Check if they are equiping it
        if(e.getNewArmorPiece() != null && e.getNewArmorPiece().getType() != Material.AIR) {
            //if they are and the item is a helmet and has the enchant we add the effect
            if(e.getType().equals(ArmorType.HELMET) && Main.hasEnchantment(e.getNewArmorPiece(), this)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
            }
        }
        //Check when they remove the enchant
        if(e.getOldArmorPiece() != null && e.getOldArmorPiece().getType() != Material.AIR) {
            if(e.getType().equals(ArmorType.HELMET) && Main.hasEnchantment(e.getOldArmorPiece(), this)) {
                p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            }
        }

    }
}
