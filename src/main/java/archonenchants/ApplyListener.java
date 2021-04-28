package archonenchants;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
        ItemStack book = e.getCursor();
        ItemStack tooEnchant = e.getCurrentItem();
        List<Enchantment> enchants = main.getCustomenchants();
        for(Enchantment ench : enchants) {
            for(Map.Entry entry : book.getEnchantments().entrySet()) {
                if(ench.equals(entry.getKey()))              {
                    enchantItem(tooEnchant, ench, (Integer) entry.getValue());
                    e.setCancelled(true);
                    book.setType(Material.AIR);
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
        if(im.getLore() == null) {
            lore = new ArrayList<>();
        } else {
            lore = im.getLore();
        }
        lore.add(chat("&7" + e.getName() + " " + roman));
        im.setLore(lore);
        is.setItemMeta(im);
    }

    private String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
