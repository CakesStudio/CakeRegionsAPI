package dev.cakestudio.cakeregionsapi.api.manager.action;

import lombok.NonNull;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.Nullable;

/**
 * Functional interface for executing custom action tags in configuration files.
 * Used with {@link IActionManager#registerAction(String, ActionExecutor)}.
 */
@FunctionalInterface
public interface ActionExecutor {

    /**
     * Executes the action for the given player.
     *
     * @param player   The player the action is executed for.
     * @param location The location context (may be null for non-location actions).
     * @param args     The arguments passed after the action tag (e.g., "[MY_TAG] these are args").
     */
    void execute(@NonNull Player player, @Nullable Location location, @NonNull String args);

}
