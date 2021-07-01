package archonenchants.Enchants;

import archonenchants.ArmorEquipAPI.ArmorEquipEvent;
import archonenchants.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Beast extends Enchantment implements Listener {

    //Completed
    /**
     * Beast enchantment
     * Applies strength 2 when applied to all 4 peices of armor
     *
     * @param key - Enchantment name
     */
    public Beast(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Beast";
    }

    public String getDescription() {
        return "Unlimited strength when applied to all 4 pieces of armor";
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
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("HELMET") || item.getType().toString().endsWith("CHESTPLATE") || item.getType().toString().endsWith("LEGGINGS") || item.getType().toString().endsWith("BOOTS")) {            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void onClose(ArmorEquipEvent e) {
        Player p = (Player) e.getPlayer();
        //Get they are equiping it
        if(e.getNewArmorPiece() != null && e.getNewArmorPiece().getType() != Material.AIR) {
            //Loop through all the armor they have on
            int count = 0;
            for(ItemStack i : Main.getArmor(p)) {
                //If the item is null, air, or does not contain the beast enchant return and stop
                if(i == null || i.getType().equals(Material.AIR) || !Main.hasEnchantment(i, this)) continue;
                //the item isnt null or air and does have the enchant, now we check if the rest has the enchant
                count++;
            }
            if(count == 3) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
                return;
            }
        }
        //Now well check for un equi and do the same thing
        if(e.getOldArmorPiece() != null && e.getOldArmorPiece().getType() != Material.AIR) {
            //Loop through all the armor they have on
            int count = 0;
            for(ItemStack i : Main.getArmor(p)) {
                //If the item is null, air, or does not contain the beast enchant return and stop
                if(i == null || i.getType().equals(Material.AIR) || !Main.hasEnchantment(i, this)) continue;
                //the item isnt null or air and does have the enchant, now we check if the rest has the enchant
                count--;
            }
            if(count < 4) {
                p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                return;
            }
        }

    }

}
