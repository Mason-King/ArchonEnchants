package archonenchants;

import archonenchants.Enchants.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

public final class Main extends JavaPlugin {

    private static Main instance;
    public static ArrayList<Enchantment> custom_enchants = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        AntiTank antiTank = new AntiTank("AntiTank");
        Beast beast = new Beast("Beast");
        Berserk berserk = new Berserk("Berserk");
        Blaze blaze = new Blaze("Blaze");
        CannonBreaker cannonBreaker = new CannonBreaker("Cannon Breaker");
        DeathGrip deathGrip = new DeathGrip("Death Grip");
        Decay decay = new Decay("Decay");
        Escape escape = new Escape("Escape");
        Feast feast = new Feast("Feast");
        Fish fish = new Fish("Fish");
        Grind grind = new Grind("Grind");
        Guardian guardian = new Guardian("Guardian");
        Haste haste = new Haste("Haste");
        HeadShot headShot = new HeadShot("HeadShot");
        Hercules hercules = new Hercules("Hercules");
        IceBreaker iceBreaker = new IceBreaker("Icebreaker");
        Indestructible indestructible = new Indestructible("Indestructible");
        Infected infected = new Infected("Infected");
        Infusion infusion = new Infusion("Infusion");
        Leaps leaps = new Leaps("Leaps");
        MobHunter mobHunter = new MobHunter("MobHunter");
        ObsidianDestroyer obsidianDestroyer = new ObsidianDestroyer("ObsidianDestroyer");
        Rampage rampage = new Rampage("Rampage");
        Reborn reborn = new Reborn("Reborn");
        Scoot scoot = new Scoot("Scoot");
        Shield shield = new Shield("Shield");
        Tank tank = new Tank("Tank");
        Trapped trapped = new Trapped("Trapped");
        Turtle turtle = new Turtle("Turtle");
        Venom venom = new Venom("Venom");
        WebShooter webShooter = new WebShooter("Webshooter");
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

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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

    public  void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            custom_enchants.add(enchantment);
        }
    }

    public ArrayList<Enchantment> getCustomenchants() {
        return custom_enchants;
    }



}
