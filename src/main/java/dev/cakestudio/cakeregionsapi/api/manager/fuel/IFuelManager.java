package dev.cakestudio.cakeregionsapi.api.manager.fuel;

import lombok.NonNull;

import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Optional;

/**
 * Interface for managing fuel types used to power regions.
 * Allows addons to register custom fuel types or query existing ones.
 */
public interface IFuelManager {

    /**
     * Gets all registered fuel types.
     *
     * @return An unmodifiable collection of all fuel types.
     */
    @NonNull Collection<IFuelType> getFuelTypes();

    /**
     * Gets a fuel type by its unique identifier.
     *
     * @param id The fuel type ID.
     * @return An Optional containing the fuel type if found.
     */
    Optional<IFuelType> getFuelType(@NonNull String id);

    /**
     * Finds a fuel type that matches the given ItemStack.
     *
     * @param item The item to check.
     * @return An Optional containing the matching fuel type, or empty if no match.
     */
    Optional<IFuelType> matchFuel(@NonNull ItemStack item);

    /**
     * Finds a fuel type that matches the given ItemStack for a specific region type.
     *
     * @param item       The item to check.
     * @param regionType The region type ID to filter fuel types by.
     * @return An Optional containing the matching fuel type, or empty if no match.
     */
    Optional<IFuelType> matchFuel(@NonNull ItemStack item, @NonNull String regionType);

    /**
     * Registers a custom fuel type.
     * After registration, this fuel type will be recognized by the plugin.
     *
     * @param fuelType The fuel type to register.
     */
    void registerFuelType(@NonNull IFuelType fuelType);

    /**
     * Unregisters a fuel type by its ID.
     *
     * @param id The fuel type ID to unregister.
     */
    void unregisterFuelType(@NonNull String id);

}
