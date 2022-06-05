package me.ez.cobblegen.Common.Enums;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum LavaOrWater implements StringRepresentable {

    HAS_LAVA("has_lava"),
    HAS_WATER("has_water"),
    HAS_BOTH("has_both"),
    NO_BOTH("no_both");

    private final String name;

    LavaOrWater(String floor) {
        this.name = floor;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }
}
