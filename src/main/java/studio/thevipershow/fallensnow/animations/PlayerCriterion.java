package studio.thevipershow.fallensnow.animations;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.animations.criteria.CriterionClassObtainer;

public interface PlayerCriterion<T extends Enum<T> & CriterionClassObtainer> {

    /**
     * Get representing enum.
     * @return The Enum.
     */
    T getEnumType();

    /**
     * A criteria matcher.
     * @param player The player that the criterion should be tested against.
     * @return True if the player matches the criteria, false otherwise.
     */
    boolean matchesCriterion(@NotNull Player player);
}
