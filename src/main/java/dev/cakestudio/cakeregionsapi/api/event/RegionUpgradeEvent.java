package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
/**
 * Called when a region is upgraded.
 */
public class RegionUpgradeEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final IRegion region;
    private final Player player;
    private final String upgradeId;
    private final int oldLevel;
    private final int newLevel;

    @Setter private boolean cancelled;

    public RegionUpgradeEvent(@NonNull final IRegion region, final Player player, @NonNull final String upgradeId, final int oldLevel, final int newLevel) {
        this.region = region;
        this.player = player;
        this.upgradeId = upgradeId;
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }

    @NonNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}