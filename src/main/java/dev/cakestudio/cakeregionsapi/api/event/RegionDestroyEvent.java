package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;

@Getter
public class RegionDestroyEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final IRegion region;
    private final DestroyCause cause;
    @Nullable private final Player destroyer;

    @Setter private boolean cancelled;
    @Setter private boolean dropItem = true;

    public RegionDestroyEvent(final @NonNull IRegion region, final @NonNull DestroyCause cause, final @Nullable Player destroyer) {
        this.region = region;
        this.cause = cause;
        this.destroyer = destroyer;
    }

    @NonNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public enum DestroyCause {
        PLAYER_BREAK,
        EXPLOSION
    }

}