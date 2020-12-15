package studio.thevipershow.fallensnow.animations;

import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class AbstractAsyncConfigurableParticlesTaskManager<T extends Plugin, S extends ConfigurableGlobalAnimation<?, T>> extends AbstractParticlesTaskManager<T,S> {

    private final double speed;

    public AbstractAsyncConfigurableParticlesTaskManager(@NotNull S globalAnimation, @NotNull T plugin, double speed) {
        super(globalAnimation, plugin);
        this.speed = speed;
    }

    @Override
    public void startGlobalEffect() {
        setTask(getPlugin().getServer().getScheduler().runTaskTimerAsynchronously(getPlugin(), () -> getAnimation().doGlobalAnimation(), 20L, (long) (20L * speed)));
    }
}
