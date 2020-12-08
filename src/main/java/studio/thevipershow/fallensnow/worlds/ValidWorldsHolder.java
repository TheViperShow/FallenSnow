package studio.thevipershow.fallensnow.worlds;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface ValidWorldsHolder {

    @NotNull
    Collection<World> getValidWorlds();

    default void removeWorld(@NotNull World world) {
        getValidWorlds().remove(world);
    }

    default void addWorld(@NotNull World world) {
        getValidWorlds().add(world);
    }

    default void addAllLoaded(@NotNull Plugin plugin) {
        getValidWorlds().addAll(plugin.getServer().getWorlds());
    }

}
