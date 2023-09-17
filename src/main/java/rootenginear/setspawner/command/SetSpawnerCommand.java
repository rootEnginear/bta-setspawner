package rootenginear.setspawner.command;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntityMobSpawner;
import net.minecraft.core.net.command.CommandError;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;
import net.minecraft.core.net.command.ServerCommand;
import net.minecraft.core.world.World;
import net.minecraft.server.MinecraftServer;

public class SetSpawnerCommand extends ServerCommand {
    public static boolean isWaitingRightclick = false;
    public static String mob;
    public static World world;

    public SetSpawnerCommand(MinecraftServer server) {
        super(server, "setspawner");
    }

    @Override
    public boolean execute(CommandHandler commandHandler, CommandSender commandSender, String[] strings) {
        if (strings.length < 1) {
            return false;
        }
        if (!commandSender.isPlayer()) {
            throw new CommandError("Must be used by a player!");
        }
        world = commandSender.getPlayer().world;
        mob = strings[0];

        commandSender.sendMessage("Right click at the spawner to set to " + mob);
        isWaitingRightclick = true;
        return true;
    }

    @Override
    public boolean opRequired(String[] args) {
        return true;
    }

    @Override
    public void sendCommandSyntax(CommandHandler commandHandler, CommandSender commandSender) {
        commandSender.sendMessage("/setspawner <PascalCasedMobName>");
    }

    public static boolean onRightClick(int x, int y, int z) {
        if (isWaitingRightclick) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (tileEntity instanceof TileEntityMobSpawner) {
                ((TileEntityMobSpawner) tileEntity).setMobID(mob);
                world.setBlockTileEntity(x, y, z, tileEntity);
                world.markBlockNeedsUpdate(x, y, z);

                isWaitingRightclick = false;
                return true;
            }
        }
        return false;
    }
}
