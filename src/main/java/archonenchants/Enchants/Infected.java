package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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
    public Infected(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
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

    private List<Player> blocked = new ArrayList<>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)) return;
        Player Damager = (Player) e.getDamager();
        Player Damaged = (Player) e.getEntity();
        if(Damager.getInventory().getItemInMainHand().containsEnchantment(this)) {
            if(Damaged == Damager && e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) return;
            blocked.add(Damaged);
            new BukkitRunnable() {
                @Override
                public void run() {
                    blocked.remove(Damaged);
                    cancel();
                }
            }.runTaskTimer(Main.getInstance(), 10, 1);
        }
    }

    @EventHandler
    public void onThrow(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && p.getInventory().getItemInMainHand().equals(Material.ENDER_PEARL) && blocked.contains(p)) e.setCancelled(true);
    }

}
