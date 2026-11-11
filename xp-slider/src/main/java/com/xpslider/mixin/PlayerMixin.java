package com.xpslider.mixin;

import com.xpslider.XpSliderConfig;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @ModifyVariable(method = "giveExperiencePoints", at = @At("HEAD"), argsOnly = true)
    private int xpslider$multiply(int points) {
        int multiplier = XpSliderConfig.getMultiplier();
        if (points <= 0 || multiplier <= 1) return points;
        if (!((Object) this instanceof ServerPlayer player)) return points;

        // Singleplayer only: integrated server, never a dedicated server, never a LAN world with guests.
        MinecraftServer server = player.level().getServer();
        if (server == null || server.isDedicatedServer() || !server.isSingleplayer()
                || server.getPlayerCount() > 1) {
            return points;
        }
        return (int) Math.min((long) points * multiplier, Integer.MAX_VALUE);
    }
}
