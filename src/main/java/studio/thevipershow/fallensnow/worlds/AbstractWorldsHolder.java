package studio.thevipershow.fallensnow.worlds;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
public abstract class AbstractWorldsHolder<T extends Plugin, S extends AutomaticWorldRemover<T>> implements ValidWorldsHolder {

    protected final T plugin;
    protected final Set<World> enabledWorlds = new HashSet<>();
    protected S worldRemover;

    /**
     * Get all of the worlds that are considered valid for particles.
     *
     * @return The worlds.
     */
    @Override
    public final @NotNull Collection<World> getValidWorlds() {
        return this.enabledWorlds;
    }

    /**
     * Remove undesired worlds.
     */
    public abstract void removeUndesiredWorlds();

}
