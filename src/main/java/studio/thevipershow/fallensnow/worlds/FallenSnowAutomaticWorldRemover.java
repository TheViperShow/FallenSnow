package studio.thevipershow.fallensnow.worlds;

import org.bukkit.event.EventHandler;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.FallenSnow;

public final class FallenSnowAutomaticWorldRemover extends AbstractAutomaticWorldRemover<FallenSnow> {
    public FallenSnowAutomaticWorldRemover(@NotNull FallenSnow plugin, @NotNull AbstractWorldsHolder<FallenSnow, FallenSnowAutomaticWorldRemover> abstractWorldsHolder) {
        super(plugin, abstractWorldsHolder);
    }

    @Override
    @EventHandler
    public final void worldLoad(@NotNull WorldLoadEvent worldLoadEvent) {
        super.abstractWorldsHolder.addWorld(worldLoadEvent.getWorld());
    }

    @Override
    @EventHandler
    public final void worldUnload(@NotNull WorldUnloadEvent worldUnloadEvent) {
        super.abstractWorldsHolder.removeWorld(worldUnloadEvent.getWorld());
    }
}
