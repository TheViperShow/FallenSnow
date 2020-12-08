package studio.thevipershow.fallensnow.particles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public abstract class AbstractParticlesTaskManager<T extends Plugin, S extends GlobalAnimation<?>> implements ParticlesTaskManager<S> {

    private final S globalAnimation;
    @Getter
    private final T plugin;
    @Setter
    private BukkitTask task;

    @Override
    public @Nullable BukkitTask getRunningTask() {
        return this.task;
    }

    @Override
    public S getAnimation() {
        return globalAnimation;
    }
}
