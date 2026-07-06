package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import lombok.NonNull;

/**
 * Fired when a player toggles (enables or disables) an active region booster.
 */
@Getter
public class RegionBoosterToggleEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final IRegion region;
    private final Player player;
    private final String boosterId;
    private final boolean newEnabledState;

    @Setter
    private boolean cancelled = false;

    public RegionBoosterToggleEvent(@NonNull IRegion region, @NonNull Player player, @NonNull String boosterId, boolean newEnabledState) {
        this.region = region;
        this.player = player;
        this.boosterId = boosterId;
        this.newEnabledState = newEnabledState;
    }

    @Override
    public @NonNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
