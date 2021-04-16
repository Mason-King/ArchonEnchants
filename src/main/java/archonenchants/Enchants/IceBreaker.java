package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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
    public IceBreaker(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Ice Breaker";
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
    public void iceBreaker(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Block b = e.getClickedBlock();
            if(b.getType().equals(Material.ICE)) {
                e.setCancelled(true);
                Location loc = b.getLocation();
                loc.getWorld().dropItemNaturally(loc, new ItemStack(b.getType()));
            }
        }
    }

}
