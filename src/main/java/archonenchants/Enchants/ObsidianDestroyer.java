package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import javax.naming.Name;
import java.util.List;

public class ObsidianDestroyer extends Enchantment implements Listener {
    public ObsidianDestroyer(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "ObsidianDestroyer";
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
        if(item.getType().toString().endsWith("PICKAXE")) {
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void onBreak(BlockDamageEvent e) {
        Player p = (Player) e.getPlayer();
        if(Main.hasEnchantment(p.getInventory().getItemInHand(), this) && e.getBlock().getType().equals(Material.OBSIDIAN)) {
            e.setInstaBreak(true);
        }
    }

}
