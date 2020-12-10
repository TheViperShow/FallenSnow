package studio.thevipershow.fallensnow.animations.criteria;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.animations.PlayerCriterion;

public final class RainingCriterion implements PlayerCriterion {

    @Override
    public final boolean matchesCriterion(@NotNull Player player) {
        return !player.getWorld().isThundering();
    }
}
