package archonenchants.Custom;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BlockProcessInfo {

    private ItemStack item;
    private Block block;
    private List<ItemStack> drops;
    private boolean isSpawner;

    public BlockProcessInfo(ItemStack item, Block block) {
        this.item = item;
        this.block = block;
        this.drops = new ArrayList<>(block.getDrops(item));
        isSpawner = block.getType() == Material.MOB_SPAWNER;
    }

    public ItemStack getItem() {
        return item;
    }

    public Block getBlock() {
        return block;
    }

    public List<ItemStack> getDrops() {
        return drops;
    }

    public boolean isSpawner() {
        return isSpawner;
    }

}
