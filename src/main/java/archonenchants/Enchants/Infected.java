package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Infected extends Enchantment implements Listener {
    //Completed
    public Infected(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Infected";
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
        return EnchantmentTarget.WEAPON;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("SWORDS")) {
            return true;
        } else {
            return false;
        }
    }

    private static List<Player> blocked = new ArrayList<>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)) return;
        Player Damager = (Player) e.getDamager();
        Player Damaged = (Player) e.getEntity();
        if(Main.hasEnchantment(Damager.getInventory().getItemInHand(), this)) {
            if(Damaged == Damager && e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) return;
            blocked.add(Damaged);
            new BukkitRunnable() {
                @Override
                public void run() {
                    blocked.remove(Damaged);
                    cancel();
                }
            }.runTaskTimer(Main.getInstance(), 200, 1);
        }
    }

    @EventHandler
    public void onThrow(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(blocked.contains(p)) {
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if(p.getInventory().getItemInHand().getType().equals(Material.ENDER_PEARL)) {
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }

}
