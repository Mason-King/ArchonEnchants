package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class IceBreaker extends Enchantment implements Listener {
    public IceBreaker(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "IceBreaker";
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
    public void iceBreaker(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        if(e.getAction().equals(Action.LEFT_CLICK_BLOCK) && Main.hasEnchantment(p.getInventory().getItemInHand(), this)) {
            Block b = e.getClickedBlock();
            if(b.getType().equals(Material.ICE)) {
                e.setCancelled(true);
                Location loc = b.getLocation();
                loc.getWorld().dropItemNaturally(loc, new ItemStack(b.getType()));
                loc.getBlock().setType(Material.AIR);
            }
        }
    }

}
