package dev.cakestudio.cakeregionsapi.api.manager.action;

import lombok.NonNull;

import java.util.Set;

/**
 * Interface for managing custom action tags used in configuration files.
 * Allows addons to register their own action tags (e.g., [GIVE_REWARD], [DISCORD_WEBHOOK]).
 */
public interface IActionManager {

    /**
     * Registers a custom action tag.
     * After registration, the tag can be used in any action list in configuration files.
     *
     * @param tag      The action tag identifier (e.g., "GIVE_REWARD"). Case-insensitive.
     * @param executor The executor that handles the action.
     */
    void registerAction(@NonNull String tag, @NonNull ActionExecutor executor);

    /**
     * Unregisters a custom action tag.
     *
     * @param tag The action tag to unregister.
     */
    void unregisterAction(@NonNull String tag);

    /**
     * Gets all registered action tag names (both built-in and custom).
     *
     * @return An unmodifiable set of action tag names.
     */
    @NonNull Set<String> getRegisteredActions();

}
