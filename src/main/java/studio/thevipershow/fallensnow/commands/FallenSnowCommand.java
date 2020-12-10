package studio.thevipershow.fallensnow.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import studio.thevipershow.fallensnow.FallenSnow;
import studio.thevipershow.fallensnow.animations.criteria.CriterionClass;
import studio.thevipershow.fallensnow.animations.criteria.ToggleStatusCriterion;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@CommandAlias("fsnow|fallensnow")
public final class FallenSnowCommand extends BaseCommand {

    private static FallenSnowCommand instance = null;
    private final FallenSnow fallenSnow;
    private static final String logoPrefix = "&8[&bFallenSnow&8]&7: ";

    private static String color(@NotNull final String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static FallenSnowCommand getInstance(@NotNull FallenSnow fallenSnow) {
        if (instance == null) {
            instance = new FallenSnowCommand(fallenSnow);
        }
        return instance;
    }

    @HelpCommand
    public static void onHelp(CommandSender sender, CommandHelp commandHelp) {
        sender.sendMessage(color(logoPrefix + "Help page"));
        commandHelp.showHelp();
    }

    @Subcommand("toggle")
    @Description("Use this command to toggle snow particles.")
    public final void toggleSnow(Player player) {
        ToggleStatusCriterion toggleStatusCriterion = fallenSnow.getParticlesTaskManager().getAnimation().getCriterion(CriterionClass.TOGGLE);
        if (toggleStatusCriterion == null) {
            player.sendMessage(color(logoPrefix + "&csomething went wrong..."));
            return;
        }
        boolean currentStatus = toggleStatusCriterion.getCurrentStatus(player);
        if (currentStatus) {
            player.sendMessage(color(logoPrefix + "You have &cdisabled &7particles!"));
        } else {
            player.sendMessage(color(logoPrefix + "You have &aenabled &7particles!"));
        }
        toggleStatusCriterion.togglePlayerStatus(player);
    }
}
