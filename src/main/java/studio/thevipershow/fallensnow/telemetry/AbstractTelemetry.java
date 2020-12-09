package studio.thevipershow.fallensnow.telemetry;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;

@Data
@RequiredArgsConstructor
public abstract class AbstractTelemetry<T extends ChartGenerator, S extends JavaPlugin> implements TelemetryService, TelemetryStatus {

    protected final S plugin;
    protected final int metricsID;
    protected boolean isTelemetryStarted = false;
    protected T chartGenerator = null;

}
