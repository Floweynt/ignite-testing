package com.floweytf.test.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnderMan.class)
public abstract class EnderManMixin extends Monster {
    protected EnderManMixin(EntityType<? extends Monster> type, Level world) {
        super(type, world);
    }

    @ModifyExpressionValue(
        method = "hurt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/monster/EnderMan;hurtWithCleanWater(Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/entity/projectile/ThrownPotion;F)Z"
        )
    )
    private boolean test_v0(
        boolean original,
        @Share("hasEscaped") LocalBooleanRef ref
    ) {
        ref.set(original);
        return original;
    }
}