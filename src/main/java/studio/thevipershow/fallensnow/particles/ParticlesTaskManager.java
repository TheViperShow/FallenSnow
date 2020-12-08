package studio.thevipershow.fallensnow.particles;

import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;

public interface ParticlesTaskManager<T extends GlobalAnimation<?>> {

    T getAnimation();

    @Nullable
    BukkitTask getRunningTask();

    void startGlobalEffect();

    default void stopGlobalTask() {
        var task = getRunningTask();
        var canWeCancel = !task.isCancelled();
        if (canWeCancel) {
            task.cancel();
        }
    }
}
