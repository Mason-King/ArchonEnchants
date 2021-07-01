package archonenchants;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplyListener implements Listener {

    Main main = Main.getInstance();

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack book = e.getCursor();
        ItemStack tooEnchant = e.getCurrentItem();
        if(p.getGameMode().equals(GameMode.CREATIVE)) return;
        if(!book.getType().equals(Material.matchMaterial(main.getConfig().getString("enchantmentBookMaterial")))) return;
        List<Enchantment> enchants = main.getCustomenchants();
        for(Enchantment ench : enchants) {
            for(Map.Entry entry : book.getEnchantments().entrySet()) {
                if(ench.equals(entry.getKey())) {
                    if(!ench.canEnchantItem(tooEnchant)) return;
                    //Create a check to see if it already has it!
                    if(hasEnchantment(tooEnchant, ench)) {
                        int level = getLevel(tooEnchant, ench);
                        if(level > (int) entry.getValue()) {
                            //the current level is higher
                        } else if(level <= (int) entry.getValue()) {
                            //the current level is less so well add one too the current level
                            if(level + 1 > ench.getMaxLevel()) return;
                            removeEnchant(tooEnchant, ench);
                            enchantItem(tooEnchant, ench, level + 1);
                            e.setCancelled(true);
                            p.setItemOnCursor(new ItemStack(Material.AIR));
                            book.setType(Material.AIR);
                        }
                    } else {
                        if(tooEnchant.containsEnchantment(ench)) return;
                        enchantItem(tooEnchant, ench, (Integer) entry.getValue());
                        e.setCancelled(true);
                        p.setItemOnCursor(new ItemStack(Material.AIR));
                        book.setType(Material.AIR);
                        return;
                    }
                }
            }
        }
        return;
    }

    public void enchantItem(ItemStack is, Enchantment e, int level) {
        String roman = Roman.toRoman(level);
        is.addUnsafeEnchantment(e, level);
        ItemMeta im = is.getItemMeta();
        List<String> lore = null;
        net.minecraft.server.v1_8_R3.ItemStack nbtItem = CraftItemStack.asNMSCopy(is);
        NBTTagCompound nbt = (nbtItem.hasTag()) ? nbtItem.getTag() : new NBTTagCompound();
        if(im.getLore() == null) {
            lore = new ArrayList<>();
        } else {
            lore = im.getLore();
        }
        if(nbt.getBoolean("statTrack")) {
            int index = lore.size() - 2;
            lore.add(index, chat("&7" + e.getName() + " " + roman));
        } else {
            lore.add(chat("&7" + e.getName() + " " + roman));
        }
        im.setLore(lore);
        is.setItemMeta(im);
    }

    public boolean  hasEnchantment(ItemStack is, Enchantment e) {
        for(Map.Entry entry : is.getEnchantments().entrySet()) {
            if(entry.getKey().equals(e)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public int getLevel(ItemStack is, Enchantment e) {
        for(Map.Entry entry : is.getEnchantments().entrySet()) {
            if(entry.getKey().equals(e)) {
                return (int) entry.getValue();
            } else {
                return 0;
            }
        }
        return 0;
    }

    public void removeEnchant(ItemStack is, Enchantment e) {
        is.removeEnchantment(e);
        ItemMeta im = is.getItemMeta();
        List<String> lore = im.getLore();
        for(int i = 0; i < lore.size(); i++) {
            if(lore.get(i).contains(e.getName())) {
                lore.remove(i);
                break;
            } else {
                continue;
            }
        }
        im.setLore(lore);
        is.setItemMeta(im);
    }

    private String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
