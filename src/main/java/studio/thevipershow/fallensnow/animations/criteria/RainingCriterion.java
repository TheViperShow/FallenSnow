package studio.thevipershow.fallensnow.animations.criteria;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.animations.PlayerCriterion;

public final class RainingCriterion implements PlayerCriterion<CriterionClass> {

    @Override
    public final CriterionClass getEnumType() {
        return CriterionClass.RAINING;
    }

    @Override
    public final boolean matchesCriterion(@NotNull Player player) {
        return !player.getWorld().isThundering();
    }
}
