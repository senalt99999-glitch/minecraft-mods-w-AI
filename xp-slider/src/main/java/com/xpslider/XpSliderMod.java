package com.xpslider;

import net.fabricmc.api.ClientModInitializer;

public class XpSliderMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        XpSliderConfig.load();
    }
}
