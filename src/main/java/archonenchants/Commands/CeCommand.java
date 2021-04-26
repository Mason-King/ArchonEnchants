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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CeCommand implements CommandExecutor {

    Main main = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(args[0].equalsIgnoreCase("give")) {
            if(args.length < 2) {
                p.sendMessage(chat("&c&lArchonEnchants &7| Invalid Enchantment"));
            } else {
                String enchant = args[1];
                int level = Integer.valueOf(args[2]);
                for(Enchantment e : main.getCustomenchants()) {
                    if(e.getName().equalsIgnoreCase(enchant)) {
                       p.getInventory().addItem(createBook(e, level));
                    }
                }
            }
        }
        return false;
    }

    private ItemStack createBook(Enchantment E, int level) {
        ItemStack i = new ItemStack(Material.BOOK);
        ItemMeta im = i.getItemMeta();
        im.addEnchant(E, level, false);
        im.setDisplayName(chat("&c&l" + E.getName()));
        i.setItemMeta(im);
        return i;
    }

    private String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
