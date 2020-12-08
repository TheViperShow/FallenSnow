package studio.thevipershow.fallensnow.particles.criteria;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.particles.PlayerCriterion;

@Getter
@RequiredArgsConstructor
public final class PlayerHeightCriterion implements PlayerCriterion {

    private final long requiredHeight;

    @Override
    public final boolean matchesCriterion(@NotNull Player player) {
        return player.getLocation().getBlockY() >= requiredHeight;
    }
}
