package archonenchants.Enchants;

import archonenchants.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trapped extends Enchantment implements Listener {
    public Trapped(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Trapped";
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
        if(item.getType().toString().endsWith("SWORD")) {
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void trapped(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof  Player)) return;
        Player damager = (Player) e.getDamager();
        Player p = (Player)e.getEntity();
        if(Main.hasEnchantment(p.getInventory().getItemInHand(), this)) {
            if(new Random().nextInt(100) <= 15 * Main.getLevel(p.getInventory().getItemInHand(), this)) {{
                buildIronCageAround(e.getDamager(),5, 5, true);
                p.teleport(damager.getLocation());
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < blocks.size(); i++) {
                            Block b = blocks.get(i);
                            b.setType(types.get(i));
                        }
                        cancel();
                    }
                }.runTaskTimer(Main.getInstance(), 120, 1);
            }}
        }
    }

    private List<Material> types = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();

    public void buildIronCageAround(Entity ent, int sideLength, int height, boolean wantRoof) {
        Material fence = Material.IRON_FENCE;
        Material roof = Material.STONE;
        Location entLoc = ent.getLocation();


        int delta = (sideLength / 2);
        Location corner1 = new Location(entLoc.getWorld(), entLoc.getBlockX() + delta, entLoc.getBlockY() + 1, entLoc.getBlockZ() - delta);
        Location corner2 = new Location(entLoc.getWorld(), entLoc.getBlockX() - delta, entLoc.getBlockY() + 1, entLoc.getBlockZ() + delta);
        int minX = Math.min(corner1.getBlockX(), corner2.getBlockX());
        int maxX = Math.max(corner1.getBlockX(), corner2.getBlockX());
        int minZ = Math.min(corner1.getBlockZ(), corner2.getBlockZ());
        int maxZ = Math.max(corner1.getBlockZ(), corner2.getBlockZ());

        for(int x = minX; x <= maxX; x++) {
            for(int y = 0; y < height; y++) {
                for(int z = minZ; z <= maxZ; z++) {
                    if((x == minX || x == maxX) || (z == minZ || z == maxZ)) {
                        Block b = corner1.getWorld().getBlockAt(x, entLoc.getBlockY() + y, z);
                        types.add(b.getType());
                        b.setType(fence);
                        blocks.add(b);
                    }
                    if(y == height - 1 && wantRoof) {
                        Block b = corner1.getWorld().getBlockAt(x, entLoc.getBlockY() + y + 1, z);
                        if(b.getType() == Material.AIR) {
                            types.add(b.getType());
                            b.setType(roof);
                            blocks.add(b);
                        }
                    }

                    if(y == 0 && wantRoof) {
                        Block b = corner1.getWorld().getBlockAt(x, entLoc.getBlockY() - y - 1, z);
                        if(b.getType() == Material.AIR) {
                            types.add(b.getType());
                            b.setType(roof);
                            blocks.add(b);
                        }
                    }
                }
            }
        }
    }

}
