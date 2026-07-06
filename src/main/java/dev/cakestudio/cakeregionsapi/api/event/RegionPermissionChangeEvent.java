package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;
import dev.cakestudio.cakeregionsapi.api.value.RegionPermValue;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called when a player's permissions in a region are changed.
 */
@Getter
public class RegionPermissionChangeEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final IRegion region;
    private final OfflinePlayer target;
    private final RegionPermValue permission;
    private final boolean newState;

    @Setter private boolean cancelled;

    public RegionPermissionChangeEvent(@NonNull final IRegion region, @NonNull final OfflinePlayer target,
                                       @NonNull final RegionPermValue permission, final boolean newState) {
        this.region = region;
        this.target = target;
        this.permission = permission;
        this.newState = newState;
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
