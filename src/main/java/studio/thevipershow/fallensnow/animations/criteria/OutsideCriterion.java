package studio.thevipershow.fallensnow.animations.criteria;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.animations.PlayerCriterion;

public final class OutsideCriterion implements PlayerCriterion<CriterionClass> {

    /**
     * Get representing enum.
     *
     * @return The Enum.
     */
    @Override
    public final CriterionClass getEnumType() {
        return CriterionClass.OUTSIDE;
    }

    @Override
    public final boolean matchesCriterion(@NotNull Player player) {
        var location = player.getLocation();
        var world = location.getWorld();
        if (world == null) {
            return true;
        }
        var highestY = world.getHighestBlockYAt(location);
        return location.getY() >= highestY;
    }
}
