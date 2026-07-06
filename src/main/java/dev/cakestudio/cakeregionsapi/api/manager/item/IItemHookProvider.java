package dev.cakestudio.cakeregionsapi.api.manager.item;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import lombok.NonNull;

/**
 * Interface representing a custom item hook provider.
 * Allows addons to integrate third-party item plugins into CakeRegions.
 */
public interface IItemHookProvider {

    /**
     * Retrieves an ItemStack by its unique identifier.
     *
     * @param id The custom item identifier (without the prefix, e.g., "ruby").
     * @return The {@link ItemStack} if found, otherwise null.
     */
    @Nullable
    ItemStack getItem(@NonNull String id);

    /**
     * Gets the unique identifier for a specific ItemStack if it belongs to this provider.
     *
     * @param item The item stack to check.
     * @return The identifier string (without prefix, e.g., "ruby"), or null if it doesn't belong to this provider.
     */
    @Nullable
    String getItemId(@NonNull ItemStack item);

    /**
     * Returns the unique prefix for this provider (e.g., "oraxen", "itemsadder").
     * Used to avoid identifier collisions.
     *
     * @return The prefix string.
     */
    @NonNull
    String getPrefix();

}
