package studio.thevipershow.fallensnow.particles.criteria;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.particles.PlayerCriterion;

public final class OutsideCriterion implements PlayerCriterion {

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
