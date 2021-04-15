package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CannonBreaker extends Enchantment implements Listener {
    public CannonBreaker(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Cannon Breaker";
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

    @EventHandler
    public void cannonbreaker(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        ItemStack i = p.getInventory().getItemInMainHand();
        if(!i.containsEnchantment(this)) return;
        Block b = e.getBlock();
        if(b.getType().equals(Material.DISPENSER)) {

        }
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return false;
    }
}
