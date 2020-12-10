package studio.thevipershow.fallensnow.animations.criteria;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.animations.PlayerCriterion;

@RequiredArgsConstructor
public enum CriterionClass implements CriterionClassObtainer {

    OUTSIDE(OutsideCriterion.class),
    PERMISSION(PermissionCriterion.class),
    PLAYER_HEIGHT(PlayerHeightCriterion.class),
    RAINING(RainingCriterion.class),
    WORLD_VALIDITY(WorldValidityCriterion.class),
    TOGGLE(ToggleStatusCriterion.class)
    ;

    private final Class<? extends PlayerCriterion<?>> playerCriterion;

    @Override
    public final @NotNull Class<? extends PlayerCriterion<?>> getCriterionClass() {
        return this.playerCriterion;
    }
}
