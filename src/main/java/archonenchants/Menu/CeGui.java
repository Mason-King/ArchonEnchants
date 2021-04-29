package archonenchants.Menu;

import archonenchants.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CeGui {

    Main main = Main.getInstance();
    File file = new File(main.getInstance().getDataFolder().getAbsolutePath() + "/ce.yml");
    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
    private Gui ceGui;

    public Gui gui() {
        ceGui = new Gui(config.getString(chat(config.getString("title"))), config.getInt("size"))
                .c();

        List<String> format = config.getStringList("format");
        makeFormat(config, ceGui, format, "items");

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
                            ItemStack stack = new ItemStack(Material.matchMaterial(config.getString(keyForItems + "." + individual + ".material")));
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
                            ItemStack stack = new ItemStack(Material.matchMaterial(config.getString(keyForItems + "." + individual + ".material")));
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
