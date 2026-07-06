package dev.cakestudio.cakeregionsapi.api.manager.fuel;

import lombok.NonNull;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a fuel type that can be used to power regions.
 */
public interface IFuelType {

    /**
     * Gets the unique identifier for this fuel type.
     *
     * @return The fuel type ID.
     */
    @NonNull String getId();

    /**
     * Gets the amount of fuel points this type provides (in seconds).
     *
     * @return The fuel points value.
     */
    long getFuelPoints();

    /**
     * Checks if the given ItemStack matches this fuel type.
     *
     * @param item The item to check.
     * @return true if the item matches this fuel type.
     */
    boolean matches(@NonNull ItemStack item);

    /**
     * Gets the region type ID this fuel is restricted to, or null if it's universal.
     *
     * @return The region type ID, or null.
     */
    @Nullable String getRegionType();

}
