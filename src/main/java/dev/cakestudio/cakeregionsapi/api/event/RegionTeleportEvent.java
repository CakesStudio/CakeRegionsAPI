package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called before a player is teleported to a region.
 */
@Getter
public class RegionTeleportEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final IRegion region;

    @Setter private boolean cancelled;

    public RegionTeleportEvent(@NonNull final Player player, @NonNull final IRegion region) {
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