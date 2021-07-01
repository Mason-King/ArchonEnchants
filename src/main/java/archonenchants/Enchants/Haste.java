package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Haste extends Enchantment implements Listener {
    public Haste(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Haste";
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
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("SHOVEL") || item.getType().toString().endsWith("SWORD") || item.getType().toString().endsWith("PICKAXE") || item.getType().toString().endsWith("AXE")) {
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void haste(PlayerItemHeldEvent e) {
        Player p = (Player) e.getPlayer();
        while(Main.hasEnchantment(p.getInventory().getItemInHand(), this)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, p.getInventory().getItemInHand().getEnchantmentLevel(this)));
        }
        p.removePotionEffect(PotionEffectType.FAST_DIGGING);
    }

}
