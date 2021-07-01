package archonenchants.Enchants;

import archonenchants.Custom.BlockProcessInfo;
import archonenchants.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Infusion extends Enchantment implements Listener {
    //Completed

    public Infusion(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Infusion";
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
        return EnchantmentTarget.TOOL;
    }


    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("PICKAXE") || item.getType().toString().endsWith("SHOVEL")) {
            return true;
        } else {
            return false;
        }
    }

    public static HashMap<Player, HashMap<Block, BlockFace>> blocks = new HashMap<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
            ItemStack item = player.getInventory().getItemInHand();
            Block b = e.getClickedBlock();
            if(Main.hasEnchantment(item, this)) {
                HashMap<Block, BlockFace> blockFace = new HashMap<>();
                blockFace.put(b, e.getBlockFace());
                blocks.put(player,blockFace);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        ItemStack item = player.getInventory().getItemInHand();
        if(blocks.containsKey(player)) {
            if(blocks.get(player).containsKey(block) && Main.hasEnchantment(item, this)) {
                e.setCancelled(true);
                BlockFace face = blocks.get(player).get(block);
                blocks.remove(player);
                List<Block> blockList = getBlocks(block.getLocation(), face, Main.getLevel(item, this));
                Location origonalBlockLoc = block.getLocation();
                List<BlockProcessInfo> finalBlockList = new ArrayList<>();
                for(Block b : blockList) {
                    if(!b.getType().equals(Material.AIR) && !b.getType().equals(Material.BEDROCK) && !b.getType().equals(Material.AIR)) {
                        b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(b.getType()));
                        b.setType(Material.AIR);
                    } else {
                        continue;
                    }
                }
            }
        }
    }

    private List<Block> getBlocks(Location loc, BlockFace blockFace, Integer depth) {
        Location loc2 = loc.clone();
        switch (blockFace) {
            case SOUTH:
                loc.add(-1, 1, -depth);
                loc2.add(1, -1, 0);
                break;
            case WEST:
                loc.add(depth, 1, -1);
                loc2.add(0, -1, 1);
                break;
            case EAST:
                loc.add(-depth, 1, 1);
                loc2.add(0, -1, -1);
                break;
            case NORTH:
                loc.add(1, 1, depth);
                loc2.add(-1, -1, 0);
                break;
            case UP:
                loc.add(-1, -depth, -1);
                loc2.add(1, 0, 1);
                break;
            case DOWN:
                loc.add(1, depth, 1);
                loc2.add(-1, 0, -1);
                break;
            default:
                break;
        }
        List<Block> blockList = new ArrayList<>();
        int topBlockX = (Math.max(loc.getBlockX(), loc2.getBlockX()));
        int bottomBlockX = (Math.min(loc.getBlockX(), loc2.getBlockX()));
        int topBlockY = (Math.max(loc.getBlockY(), loc2.getBlockY()));
        int bottomBlockY = (Math.min(loc.getBlockY(), loc2.getBlockY()));
        int topBlockZ = (Math.max(loc.getBlockZ(), loc2.getBlockZ()));
        int bottomBlockZ = (Math.min(loc.getBlockZ(), loc2.getBlockZ()));
        for (int x = bottomBlockX; x <= topBlockX; x++) {
            for (int z = bottomBlockZ; z <= topBlockZ; z++) {
                for (int y = bottomBlockY; y <= topBlockY; y++) {
                    blockList.add(loc.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blockList;
    }

}
