package studio.thevipershow.fallensnow.particles;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public final class SnowAnimation extends ConfigurableParticleAnimation {

    public SnowAnimation(@NotNull Particle particle, double range, double speed, int amount) {
        super(particle, range, speed, amount);
    }

    /**
     * Run an animation for a player.
     * @param player The player who will receive the animation.
     */
    @Override
    public final void doAnimation(@NotNull Player player) {
        var x = player.getLocation().getX();
        var y = player.getLocation().getY();
        var z = player.getLocation().getZ();
        for (int k = 0; k <= getAmount(); k++) {
            player.spawnParticle(getParticle(), x, y, z, 1, getRange(), getRange(), getRange(), getSpeed(), null);
        }
    }
}
