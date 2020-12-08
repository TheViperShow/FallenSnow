package studio.thevipershow.fallensnow.particles.criteria;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.particles.PlayerCriterion;

@Getter
@RequiredArgsConstructor
public abstract class PermissionCriterion implements PlayerCriterion {

    private final String permission;

    @Override
    public boolean matchesCriterion(@NotNull Player player) {
        return player.hasPermission(this.permission);
    }
}
