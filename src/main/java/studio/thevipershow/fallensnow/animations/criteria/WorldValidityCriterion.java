package studio.thevipershow.fallensnow.animations.criteria;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.animations.PlayerCriterion;
import studio.thevipershow.fallensnow.worlds.ValidWorldsHolder;

@RequiredArgsConstructor
public final class WorldValidityCriterion implements PlayerCriterion {

    @Getter
    private final ValidWorldsHolder validWorldsHolder;

    @Override
    public final boolean matchesCriterion(@NotNull Player player) {
        var world = player.getWorld();
        return validWorldsHolder.getValidWorlds().stream().anyMatch(w -> w.equals(world));
    }
}
