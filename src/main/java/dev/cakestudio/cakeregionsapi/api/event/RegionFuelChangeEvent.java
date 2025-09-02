package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class RegionFuelChangeEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final long oldFuel;
    private final long newFuel;

    @Setter private IRegion region;
    @Setter private boolean cancelled;

    public RegionFuelChangeEvent(@NonNull final IRegion region, final long oldFuel, final long newFuel) {
        this.region = region;
        this.oldFuel = oldFuel;
        this.newFuel = newFuel;
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