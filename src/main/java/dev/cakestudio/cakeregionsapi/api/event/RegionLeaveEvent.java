package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;

import lombok.Getter;
import lombok.NonNull;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called when a player leaves the boundaries of a region.
 */
@Getter
public class RegionLeaveEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final IRegion region;

    public RegionLeaveEvent(@NonNull final Player player, @NonNull final IRegion region) {
        this.player = player;
        this.region = region;
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
