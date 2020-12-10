package studio.thevipershow.fallensnow.animations.criteria;

import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.animations.PlayerCriterion;

@FunctionalInterface
public interface CriterionClassObtainer {

    @NotNull
    Class<? extends PlayerCriterion<?>> getCriterionClass();
}
