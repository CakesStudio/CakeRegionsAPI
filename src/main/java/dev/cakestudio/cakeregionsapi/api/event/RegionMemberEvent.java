package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public abstract class RegionMemberEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final OfflinePlayer member;
    private final IRegion region;

    @Setter private boolean cancelled;

    public RegionMemberEvent(@NonNull final IRegion region, @NonNull final OfflinePlayer member) {
        this.region = region;
        this.member = member;
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