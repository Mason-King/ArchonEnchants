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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PurchaseGui {

        Main main = Main.getInstance();
        File file = new File(main.getInstance().getDataFolder().getAbsolutePath() + "/purchase.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        private Gui ceGui;

        public Gui gui() {
            ceGui = new Gui(chat(config.getString("title")), config.getInt("size"))
                    .c();

            List<String> format = config.getStringList("format");
            makeFormat(config, ceGui, format, "items");

            ceGui.onClick(e -> {
               int slot = e.getSlot();
               Player p = (Player) e.getWhoClicked();
               if(slot == config.getInt("close")) {
                   p.closeInventory();
               }
               if(slot == config.getInt("one")) {
                   if(p.getLevel() >= 15) {
                       p.getInventory().addItem(purchaseRand(1));
                       p.setLevel(p.getLevel() - 15);
                   } else {
                       p.sendMessage(chat(main.getConfig().getString("invalidAmount")));
                       return;
                   }
               } else if(slot == config.getInt("two")) {
                   if(p.getLevel() >= 30) {
                       p.getInventory().addItem(purchaseRand(2));
                       p.setLevel(p.getLevel() - 30);
                   } else {
                       p.sendMessage(chat(main.getConfig().getString("invalidAmount")));
                       return;
                   }
               } else if(slot == config.getInt("three")) {
                   if(p.getLevel() >= 45) {
                       p.getInventory().addItem(purchaseRand(3));
                       p.setLevel(p.getLevel() - 45);
                   } else {
                       p.sendMessage(chat(main.getConfig().getString("invalidAmount")));
                       return;
                   }
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

    public ItemStack purchaseRand(int tier) {
        ItemStack book = null;
        List<Enchantment> enchants = Main.getInstance().getCustomenchants();
        int random = new Random().nextInt(enchants.size());
        Enchantment e = enchants.get(random);
        if(tier == 1) {
            int rand = new Random().nextInt(10);
            if(rand > 8) {
                return enchantBook(new ItemStack(Material.BOOK), e, 2);
            } else {
                return enchantBook(new ItemStack(Material.BOOK), e, 1);
            }
        } else if(tier == 2) {
            int rand = new Random().nextInt(10);
            if(rand > 8) {
                return enchantBook(new ItemStack(Material.BOOK), e, 3);
            } else {
                return enchantBook(new ItemStack(Material.BOOK), e, 2);
            }
        } else if(tier == 3) {
            int rand = new Random().nextInt(10);
            if(rand > 6) {
                return enchantBook(new ItemStack(Material.BOOK), e, 3);
            } else {
                return enchantBook(new ItemStack(Material.BOOK), e, 2);
            }
        }
        return book;
    }

    public ItemStack enchantBook(ItemStack is, Enchantment e, int level) {
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
        im.setDisplayName(chat(main.getConfig().getString("enchantmentBookName").replace("{name}", e.getName()).replace("{level}", roman)));
        im.setLore(lore);
        is.setItemMeta(im);
        return is;
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