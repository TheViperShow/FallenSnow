package studio.thevipershow.fallensnow;

import co.aikar.commands.CommandManager;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import studio.thevipershow.fallensnow.animations.AbstractConfigurableParticlesTaskManager;
import studio.thevipershow.fallensnow.animations.ConfigurableGlobalAnimation;
import studio.thevipershow.fallensnow.animations.FallenSnowParticlesTaskManager;
import studio.thevipershow.fallensnow.animations.GlobalAnimation;
import studio.thevipershow.fallensnow.animations.SnowAnimation;
import studio.thevipershow.fallensnow.commands.FallenSnowCommand;
import studio.thevipershow.fallensnow.config.ConfigurationManager;
import studio.thevipershow.fallensnow.telemetry.AbstractTelemetry;
import studio.thevipershow.fallensnow.telemetry.BStatsChartGenerator;
import studio.thevipershow.fallensnow.telemetry.FallenSnowTelemetry;
import studio.thevipershow.fallensnow.worlds.AbstractWorldsHolder;
import studio.thevipershow.fallensnow.worlds.FallenSnowAutomaticWorldRemover;
import studio.thevipershow.fallensnow.worlds.FallenSnowWorldsHolder;

@Getter
public final class FallenSnow extends JavaPlugin {

    private ConfigurationManager configurationManager;
    private AbstractTelemetry<BStatsChartGenerator, FallenSnow> telemetry;
    private AbstractWorldsHolder<FallenSnow, FallenSnowAutomaticWorldRemover> worldsHolder;
    private AbstractConfigurableParticlesTaskManager<FallenSnow, ? extends ConfigurableGlobalAnimation<? extends SnowAnimation, FallenSnow>> particlesTaskManager;
    private PaperCommandManager commandManager;

    @Override
    public final void onEnable() { // called when the plugin is enabling.
        configurationManager = ConfigurationManager.getInstance(this);
        configurationManager.loadAllConfigs();

        worldsHolder = new FallenSnowWorldsHolder(this);
        worldsHolder.addAllLoaded(this);
        worldsHolder.removeUndesiredWorlds();

        particlesTaskManager = new FallenSnowParticlesTaskManager(this);
        particlesTaskManager.startGlobalEffect();

        commandManager = new PaperCommandManager(this);
        commandManager.enableUnstableAPI("help");
        commandManager.registerCommand(FallenSnowCommand.getInstance(this));

        telemetry = new FallenSnowTelemetry(this);
        telemetry.startTelemetry();
    }

}
