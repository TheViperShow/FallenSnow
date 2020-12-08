package studio.thevipershow.fallensnow.particles;

import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class AbstractConfigurableParticlesTaskManager<T extends Plugin, S extends GlobalAnimation<?>> extends AbstractParticlesTaskManager<T,S> {

    private final double speed;

    public AbstractConfigurableParticlesTaskManager(@NotNull S globalAnimation, @NotNull T plugin, double speed) {
        super(globalAnimation, plugin);
        this.speed = speed;
    }

    @Override
    public void startGlobalEffect() {
        getPlugin().getServer().getScheduler().runTaskTimer(getPlugin(), () -> getAnimation().doGlobalAnimation(), 20L, (long) (20L * speed));
    }
}
