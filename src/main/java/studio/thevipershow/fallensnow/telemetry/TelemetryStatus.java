package studio.thevipershow.fallensnow.telemetry;

@FunctionalInterface
public interface TelemetryStatus {

    /**
     * Get the status of the telemetry service.
     * @return The telemetry status.
     */
    boolean isTelemetryEnabled();
}
