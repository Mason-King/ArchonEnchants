package archonenchants;

import archonenchants.ArmorEquipAPI.ArmorListener;
import archonenchants.Commands.CeCommand;
import archonenchants.Enchants.*;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin implements Listener {

    private static Main instance;
    public static ArrayList<Enchantment> custom_enchants = new ArrayList<>();
    List<Enchantment> tier1 = new ArrayList<>();
    List<Enchantment> tier2 = new ArrayList<>();
    List<Enchantment> tier3 = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new ArmorListener(getConfig().getStringList("blocked")), this);

        this.getCommand("ce").setExecutor(new CeCommand());

        AntiTank antiTank = new AntiTank(101);
        Beast beast = new Beast(102);
        Berserk berserk = new Berserk(103);
        Blaze blaze = new Blaze(104);
        CannonBreaker cannonBreaker = new CannonBreaker(105);
        DeathGrip deathGrip = new DeathGrip(106);
        Decay decay = new Decay(107);
        Escape escape = new Escape(108);
        Feast feast = new Feast(109);
        Fish fish = new Fish(110);
        Grind grind = new Grind(111);
        Guardian guardian = new Guardian(112);
        Haste haste = new Haste(113);
        HeadShot headShot = new HeadShot(114);
        Hercules hercules = new Hercules(115);
        IceBreaker iceBreaker = new IceBreaker(116);
        Indestructible indestructible = new Indestructible(117);
        Infected infected = new Infected(118);
        Infusion infusion = new Infusion(119);
        Leaps leaps = new Leaps(120);
        MobHunter mobHunter = new MobHunter(121);
        ObsidianDestroyer obsidianDestroyer = new ObsidianDestroyer(122);
        Rampage rampage = new Rampage(123);
        Reborn reborn = new Reborn(124);
        Scoot scoot = new Scoot(125);
        Shield shield = new Shield(126);
        Tank tank = new Tank(127);
        Trapped trapped = new Trapped(128);
        Turtle turtle = new Turtle(129);
        Venom venom = new Venom(130);
        WebShooter webShooter = new WebShooter(131);
        registerEnchantment(antiTank);
        registerEnchantment(beast);
        registerEnchantment(berserk);
        registerEnchantment(blaze);
        registerEnchantment(cannonBreaker);
        registerEnchantment(deathGrip);
        registerEnchantment(decay);
        registerEnchantment(escape);
        registerEnchantment(feast);
        registerEnchantment(fish);
        registerEnchantment(grind);
        registerEnchantment(guardian);
        registerEnchantment(haste);
        registerEnchantment(headShot);
        registerEnchantment(hercules);
        registerEnchantment(iceBreaker);
        registerEnchantment(indestructible);
        registerEnchantment(infected);
        registerEnchantment(infusion);
        registerEnchantment(leaps);
        registerEnchantment(mobHunter);
        registerEnchantment(obsidianDestroyer);
        registerEnchantment(rampage);
        registerEnchantment(reborn);
        registerEnchantment(scoot);
        registerEnchantment(shield);
        registerEnchantment(tank);
        registerEnchantment(trapped);
        registerEnchantment(turtle);
        registerEnchantment(venom);
        registerEnchantment(webShooter);
        this.getServer().getPluginManager().registerEvents(antiTank, this);
        this.getServer().getPluginManager().registerEvents(beast, this);
        this.getServer().getPluginManager().registerEvents(berserk, this);
        this.getServer().getPluginManager().registerEvents(blaze, this);
        this.getServer().getPluginManager().registerEvents(cannonBreaker, this);
        this.getServer().getPluginManager().registerEvents(deathGrip, this);
        this.getServer().getPluginManager().registerEvents(decay, this);
        this.getServer().getPluginManager().registerEvents(escape, this);
        this.getServer().getPluginManager().registerEvents(feast, this);
        this.getServer().getPluginManager().registerEvents(fish, this);
        this.getServer().getPluginManager().registerEvents(grind, this);
        this.getServer().getPluginManager().registerEvents(guardian, this);
        this.getServer().getPluginManager().registerEvents(haste, this);
        this.getServer().getPluginManager().registerEvents(headShot, this);
        this.getServer().getPluginManager().registerEvents(hercules, this);
        this.getServer().getPluginManager().registerEvents(iceBreaker, this);
        this.getServer().getPluginManager().registerEvents(indestructible, this);
        this.getServer().getPluginManager().registerEvents(infected, this);
        this.getServer().getPluginManager().registerEvents(infusion, this);
        this.getServer().getPluginManager().registerEvents(leaps, this);
        this.getServer().getPluginManager().registerEvents(mobHunter, this);
        this.getServer().getPluginManager().registerEvents(obsidianDestroyer, this);
        this.getServer().getPluginManager().registerEvents(rampage, this);
        this.getServer().getPluginManager().registerEvents(reborn, this);
        this.getServer().getPluginManager().registerEvents(scoot, this);
        this.getServer().getPluginManager().registerEvents(shield, this);
        this.getServer().getPluginManager().registerEvents(tank, this);
        this.getServer().getPluginManager().registerEvents(trapped, this);
        this.getServer().getPluginManager().registerEvents(turtle, this);
        this.getServer().getPluginManager().registerEvents(venom, this);
        this.getServer().getPluginManager().registerEvents(webShooter, this);
        this.getServer().getPluginManager().registerEvents(new ApplyListener(), this);
        this.getServer().getPluginManager().registerEvents(this, this);
        saveResource("ce.yml", false);
        saveResource("purchase.yml", false);
        saveResource("info.yml", false);


    }

    @Override
    public void onDisable() {
        for(Enchantment ench : custom_enchants) {
            try {
                Field byIdField = Enchantment.class.getDeclaredField("byId");
                Field byNameField = Enchantment.class.getDeclaredField("byName");

                byIdField.setAccessible(true);
                byNameField.setAccessible(true);

                HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
                HashMap<Integer, Enchantment> byName = (HashMap<Integer, Enchantment>) byNameField.get(null);

                if (byId.containsKey(ench.getId())) {
                    byId.remove(ench.getId());
                }

                if (byName.containsKey(ench.getName())) {
                    byName.remove(ench.getName());
                }
            } catch (Exception ignored) {
            }
        }
    }


    @EventHandler
    public void MobKill(EntityDeathEvent e) {
        if(!(e.getEntity().getKiller() instanceof Player)) return;
        Player p = e.getEntity().getKiller();
        ItemStack hand = p.getInventory().getItemInHand();
        ItemStack weapon = hand.clone();
        net.minecraft.server.v1_8_R3.ItemStack nbtWeapon = CraftItemStack.asNMSCopy(weapon);
        //Here we are checking if the item has a nbt tag already and if not making a new one
        NBTTagCompound nbt = (nbtWeapon.hasTag()) ? nbtWeapon.getTag() : new NBTTagCompound();
        if(weapon.getType().toString().endsWith("_SWORD") || weapon.getType().toString().endsWith("_AXE") || weapon.getType().equals(Material.BOW)) {
            //Here we will have two branches, One for if the item in the hand has a lore one if not.
            if(weapon.getItemMeta().hasLore()) {
                //Has a lore
                List<String> lore = weapon.getItemMeta().getLore();
                //Here we will also check if we need to add one or, create a new nbt.
                if(nbt.getBoolean("statTrack")) {
                    int mobs = nbt.getInt("mobs") + 1;
                    nbt.setInt("mobs", mobs);
                    nbtWeapon.setTag(nbt);
                    weapon = CraftItemStack.asBukkitCopy(nbtWeapon);
                    int index = lore.size() - 1;
                    lore.remove(index);
                    lore.add(index, chat(getConfig().getString("statMobsKilled").replace("{mobs}", String.valueOf(nbt.getInt("mobs")))));
                    ItemMeta im = weapon.getItemMeta();
                    im.setLore(lore);
                    weapon.setItemMeta(im);
                    int slot = 0;
                    for (ItemStack itemStack : p.getInventory().getContents()) {
                        if (itemStack != null && !itemStack.getType().equals(Material.AIR) && itemStack.equals(hand)) {
                            p.getInventory().setItem(slot, null);
                            p.getInventory().setItem(slot, weapon);
                            return;
                        }
                        slot++;
                    }
                } else {
                    //Stat track inst made yet!
                    nbt.setInt("mobs", 1);
                    nbt.setBoolean("statTrack", true);
                    nbt.setInt("kills", 0);
                    nbtWeapon.setTag(nbt);
                    weapon = CraftItemStack.asBukkitCopy(nbtWeapon);
                    lore.add(chat(getConfig().getString("statPlayerKills").replace("{players}", String.valueOf(nbt.getInt("kills")))));
                    lore.add(chat(getConfig().getString("statMobsKilled").replace("{mobs}", String.valueOf(nbt.getInt("mobs")))));
                    ItemMeta im = weapon.getItemMeta();
                    im.setLore(lore);
                    weapon.setItemMeta(im);
                    int slot = 0;
                    for (ItemStack itemStack : p.getInventory().getContents()) {
                        if (itemStack != null && !itemStack.getType().equals(Material.AIR) && itemStack.equals(hand)) {
                            p.getInventory().setItem(slot, null);
                            p.getInventory().setItem(slot, weapon);
                            return;
                        }
                        slot++;
                    }
                }
            } else {
                //No lore, so we create a new one
                List<String> lore = new ArrayList<>();
                if(nbt.getInt("mobs") == 0) {
                    //We need to create The stat track
                    nbt.setInt("mobs", 1);
                    nbt.setBoolean("statTrack", true);
                    nbt.setInt("kills", 0);
                    nbtWeapon.setTag(nbt);
                    weapon = CraftItemStack.asBukkitCopy(nbtWeapon);
                    lore.add(chat(getConfig().getString("statPlayerKills").replace("{players}", String.valueOf(nbt.getInt("kills")))));
                    lore.add(chat(getConfig().getString("statMobsKilled").replace("{mobs}", String.valueOf(nbt.getInt("mobs")))));
                    ItemMeta im = weapon.getItemMeta();
                    im.setLore(lore);
                    weapon.setItemMeta(im);
                    int slot = 0;
                    for (ItemStack itemStack : p.getInventory().getContents()) {
                        if (itemStack != null && !itemStack.getType().equals(Material.AIR) && itemStack.equals(hand)) {
                            p.getInventory().setItem(slot, null);
                            p.getInventory().setItem(slot, weapon);
                            return;
                        }
                        slot++;
                    }
                }
            }
        } else return;
    }

    @EventHandler
    public void PlayerKill(PlayerDeathEvent e) {
        if(!(e.getEntity().getKiller() instanceof Player)) return;
        Player p = e.getEntity().getKiller();
        ItemStack hand = p.getInventory().getItemInHand();
        ItemStack weapon = hand.clone();
        net.minecraft.server.v1_8_R3.ItemStack nbtWeapon = CraftItemStack.asNMSCopy(weapon);
        //Here we are checking if the item has a nbt tag already and if not making a new one
        NBTTagCompound nbt = (nbtWeapon.hasTag()) ? nbtWeapon.getTag() : new NBTTagCompound();
        if(weapon.getType().toString().endsWith("_SWORD") || weapon.getType().toString().endsWith("_AXE") || weapon.getType().equals(Material.BOW)) {
            //Here we will have two branches, One for if the item in the hand has a lore one if not.
            if(weapon.getItemMeta().hasLore()) {
                //Has a lore
                List<String> lore = weapon.getItemMeta().getLore();
                //Here we will also check if we need to add one or, create a new nbt.
                if(nbt.getBoolean("statTrack")) {
                    int mobs = nbt.getInt("kills") + 1;
                    nbt.setInt("kills", mobs);
                    nbtWeapon.setTag(nbt);
                    weapon = CraftItemStack.asBukkitCopy(nbtWeapon);
                    int index = lore.size() - 1;
                    lore.remove(index);
                    lore.add(index, chat(getConfig().getString("statPlayerKills").replace("{kills}", String.valueOf(nbt.getInt("kills")))));
                    ItemMeta im = weapon.getItemMeta();
                    im.setLore(lore);
                    weapon.setItemMeta(im);
                    int slot = 0;
                    for (ItemStack itemStack : p.getInventory().getContents()) {
                        if (itemStack != null && !itemStack.getType().equals(Material.AIR) && itemStack.equals(hand)) {
                            p.getInventory().setItem(slot, null);
                            p.getInventory().setItem(slot, weapon);
                            return;
                        }
                        slot++;
                    }
                } else {
                    //Stat track inst made yet!
                    nbt.setInt("mobs", 0);
                    nbt.setBoolean("statTrack", true);
                    nbt.setInt("kills", 1);
                    nbtWeapon.setTag(nbt);
                    weapon = CraftItemStack.asBukkitCopy(nbtWeapon);
                    lore.add(chat(getConfig().getString("statPlayerKills").replace("{players}", String.valueOf(nbt.getInt("kills")))));
                    lore.add(chat(getConfig().getString("statMobsKilled").replace("{mobs}", String.valueOf(nbt.getInt("mobs")))));
                    ItemMeta im = weapon.getItemMeta();
                    im.setLore(lore);
                    weapon.setItemMeta(im);
                    int slot = 0;
                    for (ItemStack itemStack : p.getInventory().getContents()) {
                        if (itemStack != null && !itemStack.getType().equals(Material.AIR) && itemStack.equals(hand)) {
                            p.getInventory().setItem(slot, null);
                            p.getInventory().setItem(slot, weapon);
                            return;
                        }
                        slot++;
                    }
                }
            } else {
                //No lore, so we create a new one
                List<String> lore = new ArrayList<>();
                if(!nbt.getBoolean("statTrack")) {
                    //We need to create The stat track
                    nbt.setInt("mobs", 0);
                    nbt.setBoolean("statTrack", true);
                    nbt.setInt("kills", 1);
                    nbtWeapon.setTag(nbt);
                    weapon = CraftItemStack.asBukkitCopy(nbtWeapon);
                    lore.add(chat(getConfig().getString("statPlayerKills").replace("{players}", String.valueOf(nbt.getInt("kills")))));
                    lore.add(chat(getConfig().getString("statMobsKilled").replace("{mobs}", String.valueOf(nbt.getInt("mobs")))));
                    ItemMeta im = weapon.getItemMeta();
                    im.setLore(lore);
                    weapon.setItemMeta(im);
                    int slot = 0;
                    for (ItemStack itemStack : p.getInventory().getContents()) {
                        if (itemStack != null && !itemStack.getType().equals(Material.AIR) && itemStack.equals(hand)) {
                            p.getInventory().setItem(slot, null);
                            p.getInventory().setItem(slot, weapon);
                            return;
                        }
                        slot++;
                    }
                }
            }
        } else return;
    }


    public static Main getInstance() {
        return instance;
    }

    public static ItemStack[] getArmor(Player p) {
        ItemStack[] armor = new ItemStack[]{
                p.getInventory().getHelmet(),
                p.getInventory().getChestplate(),
                p.getInventory().getLeggings(),
                p.getInventory().getBoots()
        };
        return armor;
    }

    private String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static boolean hasEnchantment(ItemStack is, Enchantment e) {
        if(is.getEnchantments().size() < 1) return false;
        for(Map.Entry entry : is.getEnchantments().entrySet()) {
            if(entry.getKey().equals(e)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    public static int getLevel(ItemStack is, Enchantment e) {
        for(Map.Entry entry : is.getEnchantments().entrySet()) {
            if(entry.getKey().equals(e)) {
                return (int) entry.getValue();
            } else {
                return 0;
            }
        }
        return 0;
    }


    public  void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;

        }
        if(registered){
            System.out.println(enchantment.getName() + " has been registered.");
            custom_enchants.add(enchantment);
        }
    }

    public ArrayList<Enchantment> getCustomenchants() {
        return custom_enchants;
    }



}
