package archonenchants.Commands;

import archonenchants.Main;
import archonenchants.Roman;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CeCommand implements CommandExecutor {

    Main main = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(args[0].equalsIgnoreCase("give")) {
            if(args.length < 3) {
                p.sendMessage(chat("&c&lArchonEnchants &7| Invalid Enchantment"));
            } else {
                String enchant = args[1];
                int level = Integer.valueOf(args[2]);
                for(Enchantment e : main.getCustomenchants()) {
                    if(e.getName().equalsIgnoreCase(enchant) && e.getName() != null) {
                       p.getInventory().addItem(enchantBook(e, level));
                    }
                }
            }
        } else if(args[0].equalsIgnoreCase("enchant")) {
            String enchant = args[1];
            int level = Integer.valueOf(args[2]);
            for(Enchantment e : main.getCustomenchants()) {
                if(e.getName().equalsIgnoreCase(enchant) && e.getName() != null) {
                    enchantItem(p.getInventory().getItemInMainHand(), e, level);
                }
            }
        }
        return false;
    }

    public ItemStack enchantBook(Enchantment e, int level) {
        ItemStack i = new ItemStack(Material.matchMaterial(main.getConfig().getString("enchantmentBookMaterial")));
        ItemMeta im = i.getItemMeta();
        i.addUnsafeEnchantment(e, level);
        List<String> lore = new ArrayList<>();
        for(String s : main.getConfig().getStringList("enchantedBookLore")) {
            lore.add(chat(s));
        }
        im.setLore(lore);
        im.setDisplayName(chat(main.getConfig().getString("enchantmentBookName").replace("{name}", e.getName()).replace("{level}", Roman.toRoman(level))));
        i.setItemMeta(im);
        return i;
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

    public ItemStack addBookEnchantment(ItemStack item, Enchantment enchantment, int level){
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return item;
    }

    private String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
