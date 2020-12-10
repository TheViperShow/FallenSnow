package studio.thevipershow.fallensnow.commands;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.config.KeyHolder;

@RequiredArgsConstructor
public enum CommandNode implements KeyHolder {

    ;

    private final String commandKey;

    /**
     * Get this command's key, also known as base argument.
     * @return The command's key.
     */
    @Override
    public final @NotNull String getKey() {
        return this.commandKey;
    }
}
