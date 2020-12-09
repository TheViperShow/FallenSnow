package studio.thevipershow.fallensnow.worlds;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface ValidWorldsHolder {

    /**
     * Get all of the worlds that are considered valid for particles.
     * @return The worlds.
     */
    @NotNull
    Collection<World> getValidWorlds();

    /**
     * Remove ad world from valid worlds.
     * @param world The world.
     */
    default void removeWorld(@NotNull World world) {
        getValidWorlds().remove(world);
    }

    /**
     * Add a world to the valid worlds.
     * @param world The world.
     */
    default void addWorld(@NotNull World world) {
        getValidWorlds().add(world);
    }

    /**
     * Add all loaded worlds into valid worlds.
     * @param plugin The plugin.
     */
    default void addAllLoaded(@NotNull Plugin plugin) {
        getValidWorlds().addAll(plugin.getServer().getWorlds());
    }

}
