package studio.thevipershow.fallensnow.worlds;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractAutomaticWorldRemover<T extends Plugin> implements AutomaticWorldRemover<T> {

    protected final T plugin;
    protected final AbstractWorldsHolder<T, ? extends AutomaticWorldRemover<T>> abstractWorldsHolder;

    public AbstractAutomaticWorldRemover(T plugin, AbstractWorldsHolder<T, ? extends AutomaticWorldRemover<T>> abstractWorldsHolder) {
        this.plugin = plugin;
        this.abstractWorldsHolder = abstractWorldsHolder;
    }

    @Override
    public final void selfRegister() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @NotNull
    @Override
    public final AbstractWorldsHolder<T, ? extends AutomaticWorldRemover<T>> getAbstractWorldsHolder() {
        return abstractWorldsHolder;
    }
}
