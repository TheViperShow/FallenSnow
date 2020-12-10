package studio.thevipershow.fallensnow.animations;

import lombok.Getter;
import org.bukkit.Particle;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class ConfigurableParticleAnimation extends AbstractParticleAnimation {

    private final double range, speed;
    private final long amount;

    public ConfigurableParticleAnimation(@NotNull Particle particle, double range, double speed, long amount) {
        super(particle);
        this.range = range;
        this.speed = speed;
        this.amount = amount;
    }

}
