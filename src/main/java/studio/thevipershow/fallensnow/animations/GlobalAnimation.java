package studio.thevipershow.fallensnow.animations;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface GlobalAnimation<T extends AbstractParticleAnimation> {

    @NotNull
    T getAnimation();

    @NotNull
    Collection<Player> getAffectedPlayers();

    default void doGlobalAnimation() {
        var animation = getAnimation();
        getAffectedPlayers().forEach(animation::doAnimation);
    }
}
