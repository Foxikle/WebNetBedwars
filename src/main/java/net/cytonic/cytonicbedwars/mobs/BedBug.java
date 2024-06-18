package net.cytonic.cytonicbedwars.mobs;

import net.cytonic.cytonicbedwars.CytonicBedWars;
import net.cytonic.cytonicbedwars.data.objects.Team;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Silverfish;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.entity.Player;

import java.util.function.Predicate;

public class BedBug extends Silverfish {

    public BedBug(Team spawningTeam, Location spawnLocation) {
        super(EntityType.SILVERFISH, ((CraftWorld) spawnLocation.getWorld()).getHandle().getLevel());

        this.setAggressive(true);
        this.setPos(spawnLocation.x(), spawnLocation.y(), spawnLocation.z());
        this.setCustomName(Component.literal(ChatColor.translateAlternateColorCodes('&', spawningTeam.prefix()) + ChatColor.BOLD + "Bed Bug"));
        this.setCustomNameVisible(true);
        this.setHealth(20);
        this.setTarget(null);
        this.goalSelector.removeAllGoals(goal -> true);

        Predicate<LivingEntity> predicate = livingEntity -> {
            if(livingEntity.getType() == EntityType.PLAYER) {
                Player p = (Player) livingEntity.getBukkitEntity();
                Team playerTeam = CytonicBedWars.INSTANCE.getGameManager().getPlayerTeam(p.getUniqueId());
                if(playerTeam == null ) {
                    return false;
                }
                return !playerTeam.displayName().equals(spawningTeam.displayName());
            }
            return false;
        };

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ClimbOnTopOfPowderSnowGoal(this, this.level()));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, net.minecraft.world.entity.player.Player.class, true, predicate));

        ((CraftWorld) spawnLocation.getWorld()).getHandle().addFreshEntity(this);
    }
}
