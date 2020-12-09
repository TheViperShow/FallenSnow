package studio.thevipershow.fallensnow.worlds;

import lombok.Data;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

@Data
public abstract class AbstractAutomaticWorldRemover<T extends Plugin> implements AutomaticWorldRemover<T> {

    protected final T plugin;
    protected final AbstractWorldsHolder<T, ? extends AutomaticWorldRemover<T>> abstractWorldsHolder;

    public AbstractAutomaticWorldRemover(T plugin, AbstractWorldsHolder<T, ? extends AutomaticWorldRemover<T>> abstractWorldsHolder) {
        this.plugin = plugin;
        this.abstractWorldsHolder = abstractWorldsHolder;
    }

    @Override
    public final void selfRegister() {
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Get the object of an AbstractWorldHolder.
     *
     * @return The AbstractWorldHolder.
     */
    @Override
    public final @NotNull AbstractWorldsHolder<T, ? extends AutomaticWorldRemover<T>> getAbstractWorldsHolder() {
        return this.abstractWorldsHolder;
    }
}
