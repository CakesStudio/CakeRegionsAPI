package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class RegionBoosterExpireEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final IRegion region;
    private final String boosterId;

    public RegionBoosterExpireEvent(@NonNull final IRegion region, @NonNull final String boosterId) {
        this.region = region;
        this.boosterId = boosterId;
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