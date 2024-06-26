package twilightforest;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandTFProgress extends CommandBase {

    String[] bosses = new String[] { "none", "naga", "lich", "mooshroom", "hydra", "knights", "urghast", "yeti",
            "snowqueen", "giants", "final" };

    @Override
    public String getCommandName() {
        return "tfprogress";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "tfprogress <player> <boss>";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 2) {
            throw new WrongUsageException("tfprogress <player> <boss>");
        } else {
            EntityPlayerMP player = getPlayer(sender, args[0]);
            int bossIndex = getBossIndex(args[1]);

            func_152373_a(
                    sender,
                    this,
                    "Setting player %s progress to past boss %s.",
                    player.getCommandSenderName(),
                    bosses[bossIndex]);

            setProgress(player, bossIndex);
        }
    }

    private void setProgress(EntityPlayerMP player, int bossIndex) {

        // give previous achievements, if necessary
        if (bossIndex > 0) {
            for (int i = 0; i < bossIndex; i++) {
                setProgress(player, i);
            }
        }

        // give achievements
        switch (bossIndex) {
            case 1 -> {
                player.triggerAchievement(TFAchievementPage.twilightPortal);
                player.triggerAchievement(TFAchievementPage.twilightArrival);
                player.triggerAchievement(TFAchievementPage.twilightHunter);
                player.triggerAchievement(TFAchievementPage.twilightKillNaga);
                player.triggerAchievement(TFAchievementPage.twilightProgressNaga);
            }
            case 2 -> {
                player.triggerAchievement(TFAchievementPage.twilightKillLich);
                player.triggerAchievement(TFAchievementPage.twilightProgressLich);
            }
            case 3 -> player.triggerAchievement(TFAchievementPage.twilightProgressLabyrinth);
            case 4 -> {
                player.triggerAchievement(TFAchievementPage.twilightKillHydra);
                player.triggerAchievement(TFAchievementPage.twilightProgressHydra);
            }
            case 5 -> {
                player.triggerAchievement(TFAchievementPage.twilightProgressTrophyPedestal);
                player.triggerAchievement(TFAchievementPage.twilightProgressKnights);
            }
            case 6 -> player.triggerAchievement(TFAchievementPage.twilightProgressUrghast);
            case 7 -> player.triggerAchievement(TFAchievementPage.twilightProgressYeti);
            case 8 -> player.triggerAchievement(TFAchievementPage.twilightProgressGlacier);
            case 9 -> player.triggerAchievement(TFAchievementPage.twilightProgressTroll);
            case 10 -> {
                player.triggerAchievement(TFAchievementPage.twilightProgressThorns);
                player.triggerAchievement(TFAchievementPage.twilightProgressCastle);
            }
        }
    }

    private int getBossIndex(String string) {
        for (int i = 0; i < bosses.length; i++) {
            if (bosses[i].equalsIgnoreCase(string)) {
                return i;
            }
        }

        return 0;
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, this.getListOfPlayers())
                : (args.length == 2 ? getListOfStringsMatchingLastWord(args, bosses) : null);
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.getServer().getAllUsernames();
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     */
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return p_82358_2_ == 0;
    }
}
