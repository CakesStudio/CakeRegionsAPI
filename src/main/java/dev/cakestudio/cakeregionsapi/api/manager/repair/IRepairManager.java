package dev.cakestudio.cakeregionsapi.api.manager.repair;

import lombok.NonNull;

import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Optional;

/**
 * Interface for managing repair items used to restore region durability.
 * Allows addons to register custom repair items or query existing ones.
 */
public interface IRepairManager {

    /**
     * Gets all registered repair items.
     *
     * @return An unmodifiable collection of all repair items.
     */
    @NonNull Collection<IRepairItem> getRepairItems();

    /**
     * Finds a repair item that matches the given ItemStack.
     *
     * @param item The item to check.
     * @return An Optional containing the matching repair item, or empty if no match.
     */
    Optional<IRepairItem> matchRepairItem(@NonNull ItemStack item);

    /**
     * Registers a custom repair item.
     * After registration, this repair item will be recognized by the plugin.
     *
     * @param repairItem The repair item to register.
     */
    void registerRepairItem(@NonNull IRepairItem repairItem);

    /**
     * Unregisters a repair item by its ID.
     *
     * @param id The repair item ID to unregister.
     */
    void unregisterRepairItem(@NonNull String id);

}
