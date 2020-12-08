package studio.thevipershow.fallensnow.worlds;

import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface AutomaticWorldRemover<T extends Plugin> extends Listener {

    @NotNull
    AbstractWorldsHolder<T, ? extends AutomaticWorldRemover<T>> getAbstractWorldsHolder();

    void selfRegister();

    void worldLoad(@NotNull WorldLoadEvent worldLoadEvent);

    void worldUnload(@NotNull WorldUnloadEvent worldUnloadEvent);
}
