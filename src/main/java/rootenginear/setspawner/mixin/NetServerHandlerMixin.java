package rootenginear.setspawner.mixin;

import net.minecraft.core.net.packet.Packet15Place;
import net.minecraft.server.net.handler.NetServerHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rootenginear.setspawner.command.SetSpawnerCommand;

@Mixin(value = {NetServerHandler.class}, remap = false)
public class NetServerHandlerMixin {
    @Inject(method = "handlePlace", at = @At("HEAD"), cancellable = true)
    void handlePlace(Packet15Place packet, CallbackInfo ci) {
        if (SetSpawnerCommand.onRightClick(packet.xPosition, packet.yPosition, packet.zPosition)) {
            ci.cancel();
        }
    }
}
