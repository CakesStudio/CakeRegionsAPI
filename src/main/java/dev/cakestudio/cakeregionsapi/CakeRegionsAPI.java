package dev.cakestudio.cakeregionsapi;

import dev.cakestudio.cakeregionsapi.api.IRegionsAPI;

import lombok.NonNull;

/**
 * Main API entry point for CakeRegions.
 * This class provides access to the API implementation and handles its registration.
 */
public final class CakeRegionsAPI {

    private static IRegionsAPI implementation;

    /**
     * Registers the API implementation.
     * Internal use only (called by the main plugin).
     *
     * @param api The implementation instance.
     */
    public static void registerImplementation(@NonNull final IRegionsAPI api) {
        if (implementation != null) return;
        implementation = api;
    }

    /**
     * Unregisters the API implementation.
     * Internal use only (called by the main plugin).
     */
    public static void unregisterImplementation() {
        implementation = null;
    }

    /**
     * Gets the current CakeRegions API instance.
     *
     * @return The active {@link IRegionsAPI}.
     * @throws IllegalStateException if the plugin is not loaded yet.
     */
    public static IRegionsAPI getApi() {
        if (implementation == null) {
            throw new IllegalStateException("CakeRegions is not loaded.");
        }
        return implementation;
    }

}