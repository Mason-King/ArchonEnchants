package archonenchants.Menu;

import archonenchants.Main;
import archonenchants.Roman;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.sound.sampled.Line;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CeGui {

    Main main = Main.getInstance();
    File file = new File(main.getInstance().getDataFolder().getAbsolutePath() + "/ce.yml");
    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
    private Gui ceGui;

    public Gui gui() {
        ceGui = new Gui(chat(config.getString("title")), config.getInt("size"))
                .c();

        List<String> format = config.getStringList("format");
        makeFormat(config, ceGui, format, "items");

        ceGui.onClick(e -> {
           int slot = e.getSlot();
           if(slot == config.getInt("purchase")) {
               new PurchaseGui().gui().show((Player) e.getWhoClicked());
           }
           if(slot == config.getInt("info")) {
               new InfoGui().gui().show((Player) e.getWhoClicked());
           }
        });

        return ceGui;
    }

    public void makeFormat(FileConfiguration config, Gui gui, List<String> toFormat, String keyForItems) {

        int size = gui.getInventory().getSize();
        if(toFormat.size() == size / 9) {
            for(int i = 0; i < (size / 9); i++) {
                String s = toFormat.get(i);
                for(int z = 0; z < 9; z++) {
                    String removeSpaces = s.replaceAll(" ", "");
                    char individual = removeSpaces.charAt(z);
                    if(i > 0) {
                        if(config.get(keyForItems + "." + individual) == null) {
                            continue;
                        } else {
                            ItemStack stack = null;
                            if(config.getInt(keyForItems + "." + individual + ".data") == 0) {
                                stack = new ItemStack(Material.matchMaterial(config.getString(keyForItems + "." + individual + ".material")));
                            } else {
                                stack = new ItemStack(Material.matchMaterial(config.getString(keyForItems + "." + individual + ".material")),1, (short) config.getInt(keyForItems + "." + individual + ".data"));
                            }
                            ItemMeta im = stack.getItemMeta();
                            im.setDisplayName(chat(config.getString(keyForItems + "." + individual + ".name")));
                            im.setLore(chat(config.getStringList(keyForItems + "." + individual + ".lore")));
                            stack.setItemMeta(im);
                            gui.i((9 * i) + z, stack);
                        }
                    } else {
                        if(config.get(keyForItems + "." + individual) == null) {
                            continue;
                        } else {
                            ItemStack stack = null;
                            if(config.getInt(keyForItems + "." + individual + ".data") == 0) {
                                stack = new ItemStack(Material.matchMaterial(config.getString(keyForItems + "." + individual + ".material")));
                            } else {
                                stack = new ItemStack(Material.matchMaterial(config.getString(keyForItems + "." + individual + ".material")),1, (short) config.getInt(keyForItems + "." + individual + ".data"));
                            }
                            ItemMeta im = stack.getItemMeta();
                            im.setDisplayName(config.getString(keyForItems + "." + individual + ".name"));
                            im.setLore(chat(config.getStringList(keyForItems + "." + individual + ".lore")));
                            stack.setItemMeta(im);
                            gui.i(z, stack);
                        }
                    }
                }

            }

        }
    }

    private String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public List<String> chat(List<String> list) {
        List<String> colored = new ArrayList<>();
        for(String s : list) {
            colored.add(chat(s));
        }
        return colored;
    }

}
