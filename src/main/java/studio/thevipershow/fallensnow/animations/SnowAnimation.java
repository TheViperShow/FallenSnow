package studio.thevipershow.fallensnow.animations;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public final class SnowAnimation extends ConfigurableParticleAnimation {

    public SnowAnimation(@NotNull Particle particle, double range, double speed, long amount) {
        super(particle, range, speed, amount);
    }

    /**
     * Run an animation for a player.
     * @param player The player who will receive the animation.
     */
    @Override
    public final void doAnimation(@NotNull Player player) {
        final var location = player.getLocation();
        final var x = location.getX();
        final var y = location.getY();
        final var z = location.getZ();
        final var particle = getParticle();
        final var range = getRange();
        final var speed = getSpeed();

        for (short k = 0; k <= getAmount(); k++) {
            player.spawnParticle(particle, x, y, z, 1, range, range, range, speed, null);
        }
    }
}
