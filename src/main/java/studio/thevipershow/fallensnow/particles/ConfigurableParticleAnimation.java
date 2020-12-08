package studio.thevipershow.fallensnow.particles;

import lombok.Getter;
import org.bukkit.Particle;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class ConfigurableParticleAnimation extends AbstractParticleAnimation {

    private final double range, speed;
    private final int amount;

    public ConfigurableParticleAnimation(@NotNull Particle particle, double range, double speed, int amount) {
        super(particle);
        this.range = range;
        this.speed = speed;
        this.amount = amount;
    }

}
