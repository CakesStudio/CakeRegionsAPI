package dev.cakestudio.cakeregionsapi.api.event;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player repairs a region using a repair item.
 */
@Getter
public class RegionRepairEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final IRegion region;
    private final ItemStack repairItem;
    @Setter private int repairPoints;

    @Setter private boolean cancelled;

    public RegionRepairEvent(@NonNull final Player player, @NonNull final IRegion region,
                             @NonNull final ItemStack repairItem, final int repairPoints) {
        this.player = player;
        this.region = region;
        this.repairItem = repairItem;
        this.repairPoints = repairPoints;
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
