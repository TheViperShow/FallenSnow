package studio.thevipershow.fallensnow.animations.criteria;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.animations.PlayerCriterion;

public final class ToggleStatusCriterion implements PlayerCriterion<CriterionClass> {

    private final HashMap<UUID, Boolean> statusMap = new HashMap<>();

    /**
     * Get's a player current status.
     * @param player The player.
     * @return True by default or when toggled, false otherwise.
     */
    public final boolean getCurrentStatus(Player player) {
        return statusMap.getOrDefault(player.getUniqueId(), true);
    }

    /**
     * Flips someone's current status.
     * If they have snow enabled they will go to false,
     * If they have snow disabled they will go back to true.
     * Note that by default when a player joins
     * he's not in the map so he does not have a status
     *
     * @param player The player.
     */
    public final void togglePlayerStatus(@NotNull Player player) {
        UUID uuid = player.getUniqueId();
        this.statusMap.put(uuid, !getCurrentStatus(player));
    }

    /**
     * Sets all values to default.
     */
    public final void clearAllStatuses() {
        this.statusMap.clear();
    }

    /**
     * Get representing enum.
     *
     * @return The Enum.
     */
    @Override
    public final CriterionClass getEnumType() {
        return CriterionClass.TOGGLE;
    }

    /**
     * A criteria matcher.
     *
     * @param player The player that the criterion should be tested against.
     * @return True if the player matches the criteria, false otherwise.
     */
    @Override
    public final boolean matchesCriterion(@NotNull Player player) {
        return getCurrentStatus(player);
    }
}
