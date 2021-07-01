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

public class Leaps extends Enchantment implements Listener {


    public Leaps(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Leaps";
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
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("BOOTS")) {
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void onClose(ArmorEquipEvent e) {
        Player p = (Player) e.getPlayer();
        if(e.getNewArmorPiece() != null && e.getNewArmorPiece().getType() != Material.AIR && e.getType().equals(ArmorType.BOOTS)) {
            if(Main.hasEnchantment(e.getNewArmorPiece(), this)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, Main.getLevel(e.getNewArmorPiece(), this) -1));
            }
        }
        if(e.getOldArmorPiece() != null && e.getOldArmorPiece().getType() != Material.AIR && e.getType().equals(ArmorType.BOOTS)) {
            if(Main.hasEnchantment(e.getOldArmorPiece(), this)) {
                p.removePotionEffect(PotionEffectType.JUMP);
            }
        }
    }

}
