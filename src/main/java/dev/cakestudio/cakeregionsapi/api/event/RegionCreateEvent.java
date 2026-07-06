package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IProtectionBlock;
import dev.cakestudio.cakeregionsapi.api.data.IRegion;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class RegionCreateEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final IProtectionBlock IProtectionBlockType;
    private final IRegion region;

    @Setter private boolean cancelled;

    public RegionCreateEvent(@NonNull final Player player, @NonNull final IRegion region, @NonNull final IProtectionBlock IProtectionBlockType) {
        this.player = player;
        this.region = region;
        this.IProtectionBlockType = IProtectionBlockType;
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