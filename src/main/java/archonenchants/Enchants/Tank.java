package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class Tank extends Enchantment implements Listener {
    public Tank(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Tank";
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
        return EnchantmentTarget.ARMOR;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("LEGGINGS") || item.getType().toString().endsWith("CHESTPLATE")) {
            return true;
        } else {
            return false;
        }
    }

    private HashMap<Player, Integer> combos = new HashMap<>();

    @EventHandler
    public void tank(EntityDamageByEntityEvent e){
        if(!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof  Player)) return;
        Player p = (Player) e.getDamager();
        Player damaged = (Player) e.getEntity();
        int i = 0;
        for(ItemStack it : Main.getArmor(p)) {
            if(it == null) continue;
            if(Main.hasEnchantment(it, this)) {
                i++;
            }
        }
        if(i >= 2) {
            if(combos.containsKey(damaged)) {
                combos.remove(damaged);
            }
            if(!combos.containsKey(p)) {
                combos.put(p, 1);
            } else {
                int num = combos.get(p) + 1;
                combos.replace(p, num);
                e.setDamage(e.getDamage() + (combos.get(p) / 1.5));
            }
        }
    }
}
