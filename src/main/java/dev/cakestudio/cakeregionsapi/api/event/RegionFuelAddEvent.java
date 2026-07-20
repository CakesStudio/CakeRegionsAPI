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

@Getter
public class RegionFuelAddEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final IRegion region;
    private final ItemStack fuelItem;
    @Setter private long fuelPoints;

    @Setter private boolean cancelled;

    public RegionFuelAddEvent(@NonNull final Player player, @NonNull final IRegion region,
                              @NonNull final ItemStack fuelItem, final long fuelPoints) {
        this.player = player;
        this.region = region;
        this.fuelItem = fuelItem;
        this.fuelPoints = fuelPoints;
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