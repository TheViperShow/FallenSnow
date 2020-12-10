package studio.thevipershow.fallensnow.animations.criteria;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.animations.PlayerCriterion;

@Getter
@RequiredArgsConstructor
public final class PlayerHeightCriterion implements PlayerCriterion<CriterionClass> {

    private final long requiredHeight;

    @Override
    public final CriterionClass getEnumType() {
        return CriterionClass.PLAYER_HEIGHT;
    }

    @Override
    public final boolean matchesCriterion(@NotNull Player player) {
        return player.getLocation().getBlockY() >= requiredHeight;
    }
}
