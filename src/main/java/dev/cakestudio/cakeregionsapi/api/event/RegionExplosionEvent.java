package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.List;

@Getter
public class RegionExplosionEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final IRegion region;
    private final Entity explosionSource;

    @Setter private List<Block> affectedBlocks;
    @Setter private boolean cancelled;

    public RegionExplosionEvent(@NonNull final IRegion region, final Entity explosionSource, @NonNull final List<Block> affectedBlocks) {
        this.region = region;
        this.explosionSource = explosionSource;
        this.affectedBlocks = affectedBlocks;
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