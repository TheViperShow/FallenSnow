package studio.thevipershow.fallensnow.animations;

import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;

public interface ParticlesTaskManager<T extends GlobalAnimation<?>> {

    /**
     * Get the used animation.
     * @return The animation.
     */
    T getAnimation();

    /**
     * Get the task that is responsible for the effects.
     * @return
     */
    @Nullable
    BukkitTask getRunningTask();

    /**
     * Start the global task for the effect.
     */
    void startGlobalEffect();

    /**
     * Stops the global task if it is running.
     */
    default void stopGlobalTask() {
        var task = getRunningTask();
        if (task != null) {
            var canWeCancel = !task.isCancelled();
            if (canWeCancel) {
                task.cancel();
            }
        }
    }
}
