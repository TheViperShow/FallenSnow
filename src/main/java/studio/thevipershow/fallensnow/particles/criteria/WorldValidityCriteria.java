package studio.thevipershow.fallensnow.particles.criteria;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.particles.PlayerCriterion;
import studio.thevipershow.fallensnow.worlds.ValidWorldsHolder;

@RequiredArgsConstructor
@Getter
public final class WorldValidityCriteria implements PlayerCriterion {

    private final ValidWorldsHolder validWorldsHolder;

    @Override
    public final boolean matchesCriterion(@NotNull Player player) {
        var world = player.getWorld();
        return validWorldsHolder.getValidWorlds().stream().anyMatch(w -> w.equals(world));
    }
}
