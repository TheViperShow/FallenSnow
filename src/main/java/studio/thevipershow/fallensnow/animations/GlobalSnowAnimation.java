package studio.thevipershow.fallensnow.animations;

import lombok.Getter;
import org.bukkit.Particle;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.FallenSnow;
import studio.thevipershow.fallensnow.config.snow.SnowTomlConfig;
import studio.thevipershow.fallensnow.config.snow.SnowValues;
import studio.thevipershow.fallensnow.animations.criteria.*;

@Getter
public final class GlobalSnowAnimation extends ConfigurableGlobalAnimation<SnowAnimation, FallenSnow> {

    public GlobalSnowAnimation(@NotNull SnowTomlConfig snowTomlConfig, @NotNull FallenSnow fallenSnow) {
        super(fallenSnow, new SnowAnimation(Particle.valueOf(snowTomlConfig.getConfigValue(SnowValues.SNOW_PARTICLE, String.class)), snowTomlConfig.getConfigValue(SnowValues.SNOW_RANGE, Double.class), snowTomlConfig.getConfigValue(SnowValues.SNOW_SPEED, Double.class), snowTomlConfig.getConfigValue(SnowValues.SNOW_AMOUNT, Long.class)));
        addSnowCriteria(snowTomlConfig);
    }

    private void addSnowCriteria(SnowTomlConfig snowTomlConfig) {
        var permEnableCheck = new VisualizeSnowPermissionCriterion();
        addCriterion(permEnableCheck);

        var snowAbove = snowTomlConfig.getConfigValue(SnowValues.SNOW_ABOVE_Y, Boolean.class);
        if (snowAbove != null && snowAbove) {
            var heightSnow = snowTomlConfig.getConfigValue(SnowValues.Y_LEVEL, Long.class);
            if (heightSnow != null) {
                addCriterion(new PlayerHeightCriterion(heightSnow));
            }
        }

        var badWeather = snowTomlConfig.getConfigValue(SnowValues.SNOW_WHEN_RAINING, Boolean.class);
        if (badWeather != null && badWeather) {
            addCriterion(new RainingCriterion());
        }

        var effectOutside = snowTomlConfig.getConfigValue(SnowValues.SNOW_ONLY_OUTSIDE, Boolean.class);
        if (effectOutside != null && effectOutside) {
            addCriterion(new OutsideCriterion());
        }

        var specifyWorlds = snowTomlConfig.getConfigValue(SnowValues.SPECIFY_ENABLED_WORLDS, Boolean.class);
        if (specifyWorlds != null && specifyWorlds) {
            addCriterion(new WorldValidityCriterion(getPlugin().getWorldsHolder()));
        }
    }

}
