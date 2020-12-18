package studio.thevipershow.fallensnow.animations;

import lombok.val;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public final class SnowAnimation extends ConfigurableParticleAnimation {

    public SnowAnimation(@NotNull Particle particle, double range, double speed, long amount) {
        super(particle, range, speed, amount);
    }

    /**
     * Run an animation for a player.
     *
     * @param player The player who will receive the animation.
     */
    @Override
    public final void doAnimation(@NotNull Player player) {
        val location = player.getLocation();
        val x = location.getX();
        val y = location.getY();
        val z = location.getZ();
        val particle = getParticle();
        val range = getRange();
        val speed = getSpeed();

        for (short k = 0; k <= getAmount(); k++) {
            player.spawnParticle(particle, x, y, z, 1, range, range, range, speed, null);
        }
    }
}
