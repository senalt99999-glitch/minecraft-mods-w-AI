package com.xpslider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;

/** Integer slider from 1x to 100x. */
public class XpSliderWidget extends AbstractSliderButton {
    public XpSliderWidget(int x, int y, int width, int height) {
        super(x, y, width, height, Component.empty(),
                (XpSliderConfig.getMultiplier() - XpSliderConfig.MIN)
                        / (double) (XpSliderConfig.MAX - XpSliderConfig.MIN));
        // Disabled while connected to a multiplayer server (the multiplier never applies there).
        Minecraft mc = Minecraft.getInstance();
        if (mc.level != null && mc.getSingleplayerServer() == null) {
            this.active = false;
        }
        updateMessage();
    }

    private int current() {
        return XpSliderConfig.MIN
                + (int) Math.round(this.value * (XpSliderConfig.MAX - XpSliderConfig.MIN));
    }

    @Override
    protected void updateMessage() {
        this.setMessage(Component.literal("XP Multiplier: " + current() + "x"));
    }

    @Override
    protected void applyValue() {
        XpSliderConfig.setMultiplier(current());
    }
}
