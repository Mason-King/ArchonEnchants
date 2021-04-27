package archonenchants.Commands;

import archonenchants.Main;
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
                System.out.println(main.getCustomenchants());
                for(Enchantment e : main.getCustomenchants()) {
                    if(e.getName().equalsIgnoreCase(enchant) && e.getName() != null) {
                       p.getInventory().addItem(addBookEnchantment(new ItemStack(Material.ENCHANTED_BOOK), e, level));
                    }
                }
            }
        } else if(args[0].equalsIgnoreCase("enchant")) {
            String enchant = args[1];
            int level = Integer.valueOf(args[2]);
            for(Enchantment e : main.getCustomenchants()) {
                System.out.println(e.getName());
                System.out.println(enchant + " args");
                if(e.getName().equalsIgnoreCase(enchant) && e.getName() != null) {
                    System.out.println(e.getItemTarget().includes(p.getInventory().getItemInMainHand().getType()));
                    p.getInventory().getItemInMainHand().addUnsafeEnchantment(e, level);
                }
            }
        }
        return false;
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
