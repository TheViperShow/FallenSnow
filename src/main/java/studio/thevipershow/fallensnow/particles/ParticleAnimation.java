package studio.thevipershow.fallensnow.particles;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ParticleAnimation {

    void doAnimation(@NotNull Player player);
}
