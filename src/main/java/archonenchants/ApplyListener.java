package archonenchants;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
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
                        if(tooEnchant.containsEnchantment(ench)) {
                            if(tooEnchant.getEnchantmentLevel(ench) > (Integer) entry.getValue()) {
                                System.out.println("higher");
                                return;
                            } else if(tooEnchant.getEnchantmentLevel(ench) < (Integer) entry.getValue()) {
                                System.out.println("lower");
                                tooEnchant.removeEnchantment(ench);
                                enchantItem(tooEnchant, ench, (Integer) entry.getValue());
                                e.setCancelled(true);
                                p.setItemOnCursor(new ItemStack(Material.AIR));
                                book.setType(Material.AIR);
                            } else {
                                System.out.println("equal");
                                tooEnchant.removeEnchantment(ench);
                                enchantItem(tooEnchant, ench, (Integer) entry.getValue() + 1);
                                e.setCancelled(true);
                                p.setItemOnCursor(new ItemStack(Material.AIR));
                                book.setType(Material.AIR);
                            }
                        } else {
                            enchantItem(tooEnchant, ench, (Integer) entry.getValue());
                            e.setCancelled(true);
                            p.setItemOnCursor(new ItemStack(Material.AIR));
                            book.setType(Material.AIR);
                        }
                        return;
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
