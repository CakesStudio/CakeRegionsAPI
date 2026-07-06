package dev.cakestudio.cakeregionsapi.api.manager.repair;

import lombok.NonNull;

import org.bukkit.inventory.ItemStack;

/**
 * Represents a repair item that can be used to restore region durability.
 */
public interface IRepairItem {

    /**
     * Gets the unique identifier for this repair item.
     *
     * @return The repair item ID.
     */
    @NonNull String getId();

    /**
     * Gets the number of durability points this item restores.
     *
     * @return The repair points value.
     */
    int getRepairPoints();

    /**
     * Checks if the given ItemStack matches this repair item.
     *
     * @param item The item to check.
     * @return true if the item matches this repair item.
     */
    boolean matches(@NonNull ItemStack item);

}
