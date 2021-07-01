package archonenchants.Enchants;

import archonenchants.Custom.AllyManager;
import archonenchants.Custom.GuardianAlly;
import archonenchants.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Guardian extends Enchantment implements Listener {

    //Done
    AllyManager allyManager = AllyManager.getInstance();

    public Guardian(int key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Guardian";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if(item.getType().toString().endsWith("HELMET") || item.getType().toString().endsWith("CHESTPLATE") || item.getType().toString().endsWith("LEGGINGS") || item.getType().toString().endsWith("BOOTS")) {            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void guardian(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        for(ItemStack is : Main.getArmor(p)) {
            if(is == null) continue;
            if(Main.hasEnchantment(is, this)) {
                if(new Random().nextInt(100) <= 10 * Main.getLevel(is, this)) {
                    Location l = p.getLocation();
                    spawnAllies(p, (LivingEntity) e.getDamager(), GuardianAlly.AllyType.IRON_GOLEM);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onAllyTarget(EntityTargetEvent e) {
        if (e.getTarget() instanceof Player && e.getEntity() instanceof LivingEntity) {
            if (allyManager.isAlly((Player) e.getTarget(), (LivingEntity) e.getEntity())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onAllyDeath(EntityDeathEvent e) {
        if (allyManager.isAllyMob(e.getEntity())) {
            e.setDroppedExp(0);
            e.getDrops().clear();
        }
    }

    @EventHandler
    public void onAllyDespawn(ChunkUnloadEvent e) {
        if (e.getChunk().getEntities().length > 0) {
            for (Entity entity : e.getChunk().getEntities()) {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    if (allyManager.isAllyMob(livingEntity)) {
                        allyManager.getAllyMob(livingEntity).forceRemoveAlly();
                    }
                }
            }
        }
    }

    private String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private void spawnAllies(Player player, LivingEntity enemy, GuardianAlly.AllyType allyType) {
        GuardianAlly guardianAlly = new GuardianAlly(player, allyType);
        guardianAlly.spawnAlly(player.getLocation());
        guardianAlly.attackEnemy(enemy);
    }

}