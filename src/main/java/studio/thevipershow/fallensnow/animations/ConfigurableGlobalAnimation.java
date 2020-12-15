package studio.thevipershow.fallensnow.animations;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.animations.criteria.CriterionClassObtainer;

@Getter
public abstract class ConfigurableGlobalAnimation<T extends ConfigurableParticleAnimation, S extends Plugin> implements GlobalAnimation<T> {

    protected final HashMap<CriterionClassObtainer, PlayerCriterion<?>> criterionHashMap = new HashMap<>();
    protected final S plugin;
    protected final T configurableAnimation;

    public ConfigurableGlobalAnimation(@NotNull S plugin, @NotNull T configurableAnimation, @NotNull PlayerCriterion<?>... particleAffectCriteria) {
        this.plugin = plugin;
        for (PlayerCriterion<?> playerCriterion : particleAffectCriteria) {
            this.criterionHashMap.put(playerCriterion.getEnumType(), playerCriterion);
        }
        this.configurableAnimation = configurableAnimation;
    }

    public <Q extends PlayerCriterion<?>> Q getCriterion(CriterionClassObtainer criterionClassObtainer) {
        final PlayerCriterion<?> criterion = this.criterionHashMap.get(criterionClassObtainer);
        if (criterion != null) {
            return (Q) criterion;
        } else {
            return null;
        }
    }

    /**
     * Add a criterion that will be used before checking whether if to use the animation for a single player or not.
     * @param criterion The criterion.
     */
    public void addCriterion(PlayerCriterion<?> criterion) {
        this.criterionHashMap.put(criterion.getEnumType(), criterion);
    }

    /**
     * Get all of the players that match the criterion.
     * @return All of the players that match the criterion.
     */
    @Override
    public @NotNull Collection<Player> getAffectedPlayers() {
        return plugin.getServer().getOnlinePlayers().stream()
                .filter(player -> this.criterionHashMap.values().stream().allMatch(criterion -> criterion.matchesCriterion(player)))
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Get the used animation.
     * @return The animation.
     */
    @Override
    public @NotNull T getAnimation() {
        return configurableAnimation;
    }
}
