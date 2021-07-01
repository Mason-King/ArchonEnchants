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

public class Fish extends Enchantment implements Listener {
    //Completed
    public Fish(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Fish";
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
        return EnchantmentTarget.ARMOR_HEAD;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("HELMET")) {
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void fish(ArmorEquipEvent e) {
        Player p = (Player) e.getPlayer();
        ItemStack[] armor = Main.getArmor(p);
        //Check if they are equiping it
        if(e.getNewArmorPiece() != null && e.getNewArmorPiece().getType() != Material.AIR) {
            //if they are and the item is a helmet and has the enchant we add the effect
            if(e.getType().equals(ArmorType.HELMET) && Main.hasEnchantment(e.getNewArmorPiece(), this)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 0));
            }
        }
        //Check when they remove the enchant
        if(e.getOldArmorPiece() != null && e.getOldArmorPiece().getType() != Material.AIR) {
            if(e.getType().equals(ArmorType.HELMET) && Main.hasEnchantment(e.getOldArmorPiece(), this)) {
                p.removePotionEffect(PotionEffectType.WATER_BREATHING);
            }
        }
    }

}
