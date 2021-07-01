package archonenchants.Custom;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class GuardianAlly {

    private AllyManager allyManager = AllyManager.getInstance();
    private AllyType type;
    private Player owner;
    private LivingEntity ally;
    private GuardianAlly instance;

    public GuardianAlly(Player owner, AllyType type) {
        this.type = type;
        this.owner = owner;
        this.instance = this;
    }

    public AllyType getType() {
        return type;
    }

    public Player getOwner() {
        return owner;
    }

    public LivingEntity getAlly() {
        return ally;
    }

    public void spawnAlly(Location loc) {
        ally = (LivingEntity) loc.getWorld().spawnEntity(loc, type.entityType);
        ally.setMaxHealth(type.maxHealth);
        ally.setHealth(type.maxHealth);
        ally.setCustomName(ChatColor.translateAlternateColorCodes('&', "&c&l" + type.getDefaultName().replaceAll("%player%", owner.getName())));
        ally.setCustomNameVisible(true);
        allyManager.addAlly(instance);
    }

    public void attackEnemy(LivingEntity enemy) {
        IronGolem iron = (IronGolem)  ally;
        iron.setTarget(enemy);
    }

    public void forceRemoveAlly() {
        allyManager.removeAlly(instance);
        ally.remove();
    }

    public enum AllyType {

        IRON_GOLEM("Iron-Golem", "&6%player%'s Golem", EntityType.IRON_GOLEM, 100);

        private String configName;
        private String defaultName;
        private EntityType entityType;
        private int maxHealth;

        private AllyType(String configName, String defaultName, EntityType entityType, int maxHealth) {
            this.configName = configName;
            this.defaultName = defaultName;
            this.entityType = entityType;
            this.maxHealth = maxHealth;
        }

        public String getConfigName() {
            return configName;
        }

        public String getDefaultName() {
            return defaultName;
        }

        public EntityType getEntityType() {
            return entityType;
        }

        public int getMaxHealth() {
            return maxHealth;
        }
    }
}
