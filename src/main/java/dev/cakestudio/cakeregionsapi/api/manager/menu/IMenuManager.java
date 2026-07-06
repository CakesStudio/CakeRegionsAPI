package dev.cakestudio.cakeregionsapi.api.manager.menu;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;

import lombok.NonNull;

import org.bukkit.entity.Player;

/**
 * Interface for programmatically opening built-in plugin menus.
 */
public interface IMenuManager {

    /**
     * Opens the region info menu for the player.
     *
     * @param player The player to open the menu for.
     * @param region The region to display info for.
     */
    void openRegionInfo(@NonNull Player player, @NonNull IRegion region);

    /**
     * Opens the fuel menu for the player.
     *
     * @param player The player to open the menu for.
     * @param region The region to display fuel info for.
     */
    void openFuelMenu(@NonNull Player player, @NonNull IRegion region);

    /**
     * Opens the members menu for the player.
     *
     * @param player The player to open the menu for.
     * @param region The region to display members for.
     */
    void openMembersMenu(@NonNull Player player, @NonNull IRegion region);

    /**
     * Closes all open menus associated with a specific region.
     *
     * @param region The region to close menus for.
     */
    void closeMenusForRegion(@NonNull IRegion region);

    /**
     * Forces a refresh of all open menus associated with a specific region.
     *
     * @param region The region to update menus for.
     */
    void updateMenusForRegion(@NonNull IRegion region);

}
