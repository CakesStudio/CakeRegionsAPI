package dev.cakestudio.cakeregionsapi.api.manager.stats;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

/**
 * Manager for retrieving region and player statistics.
 */
public interface IStatsManager {

    /**
     * Get the count of raids registered for a player.
     *
     * @param playerId the UUID of the player
     * @return the number of raids
     */
    int getPlayerRaidCount(@NonNull UUID playerId);

    /**
     * Get the count of regions owned by a player.
     *
     * @param playerId the UUID of the player
     * @return the number of regions
     */
    int getPlayerRegionCount(@NonNull UUID playerId);

    /**
     * Get the list of regions with the highest durability.
     *
     * @param limit maximum number of regions to return
     * @return list of regions ordered by durability descending
     */
    @NonNull
    List<IRegion> getTopRegionsByDurability(int limit);

    /**
     * Get the list of regions with the most members.
     *
     * @param limit maximum number of regions to return
     * @return list of regions ordered by members count descending
     */
    @NonNull
    List<IRegion> getTopRegionsByMembers(int limit);
}
