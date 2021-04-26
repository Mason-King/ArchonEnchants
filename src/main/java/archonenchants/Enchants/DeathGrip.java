package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import javax.naming.Name;
import java.util.HashMap;

public class DeathGrip extends Enchantment implements Listener {
    public DeathGrip(String key) {
        super(new NamespacedKey(Main.getInstance(), key));
    }

    @Override
    public String getName() {
        return "Death Grip";
    }

    public String getDescription() {
        return "Keep your sword after death";
    }

    @Override
    public int getMaxLevel() {
        return 2;
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

    private HashMap<Player, ItemStack> items = new HashMap<>();

    @EventHandler
    public void deathGrip(PlayerDeathEvent e) {
        Player p = (Player) e.getEntity();
        ItemStack[] inv = p.getInventory().getContents();
        for(ItemStack is : inv) {
            if(is.getType().name().endsWith("sword")) {
                if(is.containsEnchantment(this)) {
                    e.getDrops().remove(is);
                    items.put(p, is);
                }
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (items.containsKey(p)) {
            p.getInventory().addItem(items.get(p));
            items.remove(p);
        }
    }

}
