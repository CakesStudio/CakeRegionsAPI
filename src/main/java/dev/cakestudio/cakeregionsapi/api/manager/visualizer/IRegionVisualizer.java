package dev.cakestudio.cakeregionsapi.api.manager.visualizer;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;
import lombok.NonNull;
import org.bukkit.entity.Player;

/**
 * Manager for visualizing region boundaries to players.
 */
public interface IRegionVisualizer {

    /**
     * Show boundaries of a region to a player.
     *
     * @param player the player to show boundaries to
     * @param region the region to show
     */
    void showBoundary(@NonNull Player player, @NonNull IRegion region);

    /**
     * Hide boundaries of a region from a player.
     *
     * @param player the player to hide boundaries from
     */
    void hideBoundary(@NonNull Player player);

    /**
     * Toggle the boundary visualization for a player.
     *
     * @param player the player to toggle visualization for
     * @param region the region to toggle
     * @return true if visualization was enabled, false if disabled
     */
    boolean toggleBoundary(@NonNull Player player, @NonNull IRegion region);
}
