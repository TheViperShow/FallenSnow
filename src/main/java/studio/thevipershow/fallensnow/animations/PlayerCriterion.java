package studio.thevipershow.fallensnow.animations;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface PlayerCriterion {

    /**
     * A criteria matcher.
     * @param player The player that the criterion should be tested against.
     * @return True if the player matches the criteria, false otherwise.
     */
    boolean matchesCriterion(@NotNull Player player);
}
