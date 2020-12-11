package studio.thevipershow.fallensnow;

import co.aikar.commands.PaperCommandManager;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import studio.thevipershow.fallensnow.animations.AbstractConfigurableParticlesTaskManager;
import studio.thevipershow.fallensnow.animations.ConfigurableGlobalAnimation;
import studio.thevipershow.fallensnow.animations.FallenSnowParticlesTaskManager;
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
@Setter
public final class FallenSnow extends JavaPlugin {

    private ConfigurationManager configurationManager;
    private AbstractTelemetry<BStatsChartGenerator, FallenSnow> telemetry;
    private AbstractWorldsHolder<FallenSnow, FallenSnowAutomaticWorldRemover> worldsHolder;
    private AbstractConfigurableParticlesTaskManager<FallenSnow, ? extends ConfigurableGlobalAnimation<? extends SnowAnimation, FallenSnow>> particlesTaskManager;
    private PaperCommandManager commandManager;

    public final void assignConfigurations() {
        configurationManager = ConfigurationManager.getInstance(this);
        configurationManager.loadAllConfigs();
    }

    public final void assignWorldsHolder() {
        worldsHolder = new FallenSnowWorldsHolder(this);
        worldsHolder.addAllLoaded(this);
        worldsHolder.removeUndesiredWorlds();
    }

    public final void assignParticlesTaskManager() {
        particlesTaskManager = new FallenSnowParticlesTaskManager(this);
        particlesTaskManager.startGlobalEffect();
    }

    public final void assignCommand() {
        commandManager = new PaperCommandManager(this);
        commandManager.enableUnstableAPI("help");
        commandManager.registerCommand(FallenSnowCommand.getInstance(this));
    }

    public final void assignTelemetry() {
        telemetry = new FallenSnowTelemetry(this);
        telemetry.startTelemetry();
    }

    @Override
    public final void onEnable() { // called when the plugin is enabling.
        assignConfigurations();
        assignWorldsHolder();
        assignParticlesTaskManager();
        assignCommand();
        assignTelemetry();
    }

}
