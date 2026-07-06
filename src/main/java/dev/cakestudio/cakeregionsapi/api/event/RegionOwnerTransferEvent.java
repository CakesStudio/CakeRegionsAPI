package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

/**
 * Called when the creator (owner) of a region is being transferred to another player.
 */
@Getter
public class RegionOwnerTransferEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final IRegion region;
    private final UUID previousCreator;
    private final OfflinePlayer newCreator;

    @Setter private boolean cancelled;

    public RegionOwnerTransferEvent(@NonNull final IRegion region, @NonNull final UUID previousCreator,
                                    @NonNull final OfflinePlayer newCreator) {
        this.region = region;
        this.previousCreator = previousCreator;
        this.newCreator = newCreator;
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
