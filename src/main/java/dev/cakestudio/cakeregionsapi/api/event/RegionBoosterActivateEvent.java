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
public class RegionBoosterActivateEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final IRegion region;
    private final Player player;
    private final String boosterId;
    @Setter private long durationSeconds;

    @Setter private boolean cancelled;

    public RegionBoosterActivateEvent(@NonNull final IRegion region, final Player player, @NonNull final String boosterId, final long durationSeconds) {
        this.region = region;
        this.player = player;
        this.boosterId = boosterId;
        this.durationSeconds = durationSeconds;
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