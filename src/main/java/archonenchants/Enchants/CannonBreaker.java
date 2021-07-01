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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CannonBreaker extends Enchantment implements Listener {
    //Completed

    public CannonBreaker(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "CannonBreaker";
    }

    public String getDescription() {
        return "Instantly break dispensers, pistons, glowstone, and carpets";
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
    public void cannonbreaker(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        ItemStack i = p.getInventory().getItemInHand();
        if(!Main.hasEnchantment(i, this) || !e.getAction().equals(Action.LEFT_CLICK_BLOCK)) return;
        Block b = e.getClickedBlock();
        if(b.getType().equals(Material.DISPENSER) || b.getType().equals(Material.PISTON_BASE) || b.getType().equals(Material.GLOWSTONE) || b.getType().name().endsWith("carpet")) {
            e.setCancelled(true);
            Location l = b.getLocation();
            l.getWorld().dropItemNaturally(l, new ItemStack(b.getType()));
            b.setType(Material.AIR);
        }
    }
}
