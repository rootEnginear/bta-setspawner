package rootenginear.setspawner.mixin;

import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.Commands;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rootenginear.setspawner.command.SetSpawnerCommand;

import java.util.List;

@Mixin(value = {Commands.class}, remap = false)
public class CommandsMixin {
    @Shadow
    public static List<Command> commands;

    @Inject(method = "initServerCommands", at = @At("TAIL"))
    private static void initSetSpawnerCommand(MinecraftServer server, CallbackInfo ci) {
        commands.add(new SetSpawnerCommand(server));
    }
}
