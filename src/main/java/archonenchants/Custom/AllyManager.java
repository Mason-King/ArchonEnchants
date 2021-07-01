package archonenchants.Custom;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.*;

public class AllyManager {

    private static AllyManager instance = new AllyManager();
    private List<GuardianAlly> allies = new ArrayList<>();
    private Map<UUID, List<GuardianAlly>> allyOwners = new HashMap<>();

    public List<GuardianAlly> getAllies() {
        return allies;
    }

    public void addAlly(GuardianAlly ally) {
        if(ally != null) {
            allies.add(ally);
            UUID owner = ally.getOwner().getUniqueId();
            if(allyOwners.containsKey(owner)) {
                allyOwners.get(owner).add(ally);
            } else {
                List<GuardianAlly> allies2 = new ArrayList<>();
                allies2.add(ally);
                allyOwners.put(owner, allies2);
            }
        }
    }

    public void forceRemoveAllies() {
        allies.forEach(ally -> ally.getAlly().remove());
        allies.clear();
        allyOwners.clear();
    }

    public void removeAlly(GuardianAlly ally) {
        if(ally != null) {
            allies.remove(ally);
            UUID owner = ally.getOwner().getUniqueId();
            if(allyOwners.containsKey(owner)) {
                allyOwners.get(owner).add(ally);
                if(allyOwners.get(owner).isEmpty()) {
                    allyOwners.remove(owner);
                }
            }
        }
    }

    public void setEnemy(Player owner, LivingEntity enemy) {
        allyOwners.getOrDefault(owner.getUniqueId(), new ArrayList<>()).forEach(ally -> ally.attackEnemy(enemy));
    }

    public boolean isAlly(Player player, LivingEntity livingEntity) {
        if (isAllyMob(livingEntity)) {
            return isAlly(player, getAllyMob(livingEntity));
        }
        return false;
    }

    public boolean isAlly(Player player, GuardianAlly ally) {
        return ally.getOwner().getUniqueId() == player.getUniqueId();
    }

    public boolean isAllyMob(LivingEntity livingEntity) {
        for (GuardianAlly ally : allies) {
            if (ally.getAlly().getUniqueId() == livingEntity.getUniqueId()) {
                return true;
            }
        }
        return false;
    }

    public GuardianAlly getAllyMob(LivingEntity livingEntity) {
        for (GuardianAlly ally : allies) {
            if (ally.getAlly().getUniqueId() == livingEntity.getUniqueId()) {
                return ally;
            }
        }
        return null;
    }

    public static AllyManager getInstance() {
        return instance;
    }

}
