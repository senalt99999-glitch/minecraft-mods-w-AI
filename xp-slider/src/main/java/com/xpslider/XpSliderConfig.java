package com.xpslider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Stores the multiplier (1-100) in config/xp-multiplier-slider.json. */
public final class XpSliderConfig {
    public static final int MIN = 1;
    public static final int MAX = 100;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static volatile int multiplier = 1;

    private XpSliderConfig() {}

    private static Path file() {
        return FabricLoader.getInstance().getConfigDir().resolve("xp-multiplier-slider.json");
    }

    public static int getMultiplier() {
        return multiplier;
    }

    public static void setMultiplier(int value) {
        int clamped = Math.max(MIN, Math.min(MAX, value));
        if (clamped == multiplier) return;
        multiplier = clamped;
        save();
    }

    public static void load() {
        try {
            Path f = file();
            if (Files.exists(f)) {
                JsonObject json = GSON.fromJson(Files.readString(f), JsonObject.class);
                if (json != null && json.has("xp_multiplier")) {
                    multiplier = Math.max(MIN, Math.min(MAX, json.get("xp_multiplier").getAsInt()));
                }
            } else {
                save();
            }
        } catch (Exception e) {
            multiplier = 1;
        }
    }

    private static void save() {
        try {
            JsonObject json = new JsonObject();
            json.addProperty("xp_multiplier", multiplier);
            Files.writeString(file(), GSON.toJson(json));
        } catch (IOException ignored) {
        }
    }
}
