package studio.thevipershow.fallensnow.particles.criteria;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.particles.PlayerCriterion;

public final class RainingCriterion implements PlayerCriterion {

    @Override
    public final boolean matchesCriterion(@NotNull Player player) {
        return !player.getWorld().isThundering();
    }
}
