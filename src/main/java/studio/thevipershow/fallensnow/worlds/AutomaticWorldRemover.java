package studio.thevipershow.fallensnow.worlds;

import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface AutomaticWorldRemover<T extends Plugin> extends Listener {

    /**
     * Get the object of an AbstractWorldHolder.
     * @return The AbstractWorldHolder.
     */
    @NotNull
    AbstractWorldsHolder<T, ? extends AutomaticWorldRemover<T>> getAbstractWorldsHolder();

    /**
     * Self register as a global listener.
     */
    void selfRegister();

    /**
     * Called when a world loads.
     * @param worldLoadEvent The event.
     */
    void worldLoad(@NotNull WorldLoadEvent worldLoadEvent);

    /**
     * Called when a world unloads.
     * @param worldUnloadEvent The event.
     */
    void worldUnload(@NotNull WorldUnloadEvent worldUnloadEvent);
}
