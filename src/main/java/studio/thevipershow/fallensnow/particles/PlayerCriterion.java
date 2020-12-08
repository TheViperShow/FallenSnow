package studio.thevipershow.fallensnow.particles;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface PlayerCriterion {

    boolean matchesCriterion(@NotNull Player player);
}
