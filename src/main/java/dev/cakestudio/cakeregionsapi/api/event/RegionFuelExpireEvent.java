package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;

import lombok.Getter;
import lombok.NonNull;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called when a region's fuel has expired(reached zero).
 * This event is not cancellable - the fuel has already run out.
 */
@Getter
public class RegionFuelExpireEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final IRegion region;

    public RegionFuelExpireEvent(@NonNull final IRegion region) {
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