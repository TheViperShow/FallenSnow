package studio.thevipershow.fallensnow.particles;

import lombok.Getter;
import org.bukkit.Particle;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class AbstractParticleAnimation implements ParticleAnimation {

    private final Particle particle;

    public AbstractParticleAnimation(@NotNull Particle particle) {
        this.particle = particle;
    }

}
