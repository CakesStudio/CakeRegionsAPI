package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;

import lombok.Getter;
import lombok.NonNull;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;

/**
 * Called when a raid on a region is detected (first damage from a non-member).
 * This event is informational and not cancellable.
 */
@Getter
public class RegionRaidStartEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final IRegion region;
    @Nullable private final Player raider;
    @Nullable private final Entity damageSource;

    public RegionRaidStartEvent(@NonNull final IRegion region, @Nullable final Player raider,
                                @Nullable final Entity damageSource) {
        this.region = region;
        this.raider = raider;
        this.damageSource = damageSource;
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
