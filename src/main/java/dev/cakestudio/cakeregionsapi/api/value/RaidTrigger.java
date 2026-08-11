package dev.cakestudio.cakeregionsapi.api.value;

import lombok.Getter;
import lombok.NonNull;

@Getter
public enum RaidTrigger {
    BLOCK_BREAK("block-break"),
    TNT_EXPLOSION("tnt-explosion"),
    REGION_ENTER("region-enter"),
    TURRET_TARGET("turret-target");

    private final String key;

    RaidTrigger(@NonNull final String key) {
        this.key = key;
    }
}