package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Indestructible extends Enchantment implements Listener {
    public Indestructible(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Indestructible";
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("HELMET")) {
            return true;
        } else {
            return false;
        }
    }

    private static List<Player> toBreak = new ArrayList<>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof  Player) || !(e.getEntity() instanceof  Player)) return;
        Player p = (Player) e.getDamager();
        Player damaged = (Player) e.getEntity();
        if(Main.hasEnchantment(p.getInventory().getItemInHand(), this)) {
            toBreak.add(damaged);
            return;
        }
    }

    @EventHandler
    public void onBreak(PlayerItemDamageEvent e) {
        Player p = e.getPlayer();
        if(toBreak.contains(p)) {
            e.setDamage((int) (e.getDamage() / 0.5));
            toBreak.remove(p);
            return;
        }
    }

}
 