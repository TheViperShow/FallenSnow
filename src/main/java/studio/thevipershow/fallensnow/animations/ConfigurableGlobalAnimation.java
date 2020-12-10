package studio.thevipershow.fallensnow.animations;

import com.google.common.collect.ImmutableList;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public abstract class ConfigurableGlobalAnimation<T extends ConfigurableParticleAnimation, S extends Plugin> implements GlobalAnimation<T> {

    private final List<PlayerCriterion> particleAffectCriteria = new ArrayList<>();
    private final S plugin;
    private final T configurableAnimation;

    public ConfigurableGlobalAnimation(@NotNull S plugin, @NotNull T configurableAnimation, @NotNull PlayerCriterion... particleAffectCriteria) {
        this.plugin = plugin;
        this.particleAffectCriteria.addAll(ImmutableList.copyOf(particleAffectCriteria));
        this.configurableAnimation = configurableAnimation;
    }

    /**
     * Add a criterion that will be used before checking whether if to use the animation for a single player or not.
     * @param criterion The criterion.
     */
    public void addCriterion(PlayerCriterion criterion) {
        this.particleAffectCriteria.add(criterion);
    }

    /**
     * Get all of the players that match the criterion.
     * @return All of the players that match the criterion.
     */
    @Override
    public @NotNull Collection<Player> getAffectedPlayers() {
        return plugin.getServer().getOnlinePlayers().stream()
                .filter(player -> particleAffectCriteria.stream().allMatch(criterion -> criterion.matchesCriterion(player)))
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
