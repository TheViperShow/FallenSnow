package studio.thevipershow.fallensnow.particles;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ParticleAnimation {

    /**
     * Run an animation for a player.
     * @param player The player who will receive the animation.
     */
    void doAnimation(@NotNull Player player);
}
