package dev.cakestudio.cakeregionsapi.api;

import com.tcoded.folialib.FoliaLib;

import dev.cakestudio.cakeregionsapi.api.manager.action.IActionManager;
import dev.cakestudio.cakeregionsapi.api.manager.effects.IEffectsManager;
import dev.cakestudio.cakeregionsapi.api.manager.fuel.IFuelManager;
import dev.cakestudio.cakeregionsapi.api.manager.item.IItemHookManager;
import dev.cakestudio.cakeregionsapi.api.manager.economy.IEconomyManager;
import dev.cakestudio.cakeregionsapi.api.manager.menu.IMenuManager;
import dev.cakestudio.cakeregionsapi.api.manager.notification.INotificationManager;
import dev.cakestudio.cakeregionsapi.api.manager.repair.IRepairManager;
import dev.cakestudio.cakeregionsapi.api.manager.stats.IStatsManager;
import dev.cakestudio.cakeregionsapi.api.manager.visualizer.IRegionVisualizer;
import dev.cakestudio.cakeregionsapi.api.data.ICuboid;
import dev.cakestudio.cakeregionsapi.api.data.IPlayerPermissions;
import dev.cakestudio.cakeregionsapi.api.data.IProtectionBlock;
import dev.cakestudio.cakeregionsapi.api.data.IRegion;
import dev.cakestudio.cakeregionsapi.api.value.RegionPermValue;

import lombok.NonNull;

import net.kyori.adventure.text.Component;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Main API interface for CakeRegionsPlus.
 * Provides methods for managing protection blocks, regions, players, holograms, commands...
 */
public interface IRegionsAPI {

    /**
     * Gets a region at the specified location.
     *
     * @param location The location to check. Must not be null.
     * @return An Optional containing the IRegion at the location, or Optional.empty() if no active region is found.
     */
    Optional<IRegion> getRegionAt(@NonNull final Location location);

    /**
     * Gets a region by its unique ID.
     *
     * @param id The region ID. Must not be null.
     * @return An Optional containing the IRegion if found, or Optional.empty().
     */
    Optional<IRegion> getRegionById(@NonNull final String id);

    /**
     * Gets all regions where the specified player is registered as an owner.
     *
     * @param player The player. Must not be null.
     * @return A list of regions owned by the player.
     */
    @NonNull List<IRegion> getRegionsByOwner(@NonNull final Player player);

    /**
     * Gets all regions where the specified player is registered as the creator.
     *
     * @param player The player. Must not be null.
     * @return A list of regions created by the player.
     */
    @NonNull List<IRegion> getRegionsByCreator(@NonNull final Player player);

    /**
     * Gets all regions where the specified player is either an owner or a member.
     *
     * @param player The player. Must not be null.
     * @return A list of all regions associated with the player.
     */
    @NonNull List<IRegion> getRegionsForPlayer(@NonNull final Player player);

    /**
     * Gets a collection of all active regions on the server.
     *
     * @return A collection of all regions.
     */
    @NonNull Collection<IRegion> getAllRegions();

    /**
     * Checks if the specified location is within the boundaries of any active region.
     *
     * @param location The location. Must not be null.
     * @return true if the location is protected by a region.
     */
    boolean isProtected(@NonNull final Location location);

    /**
     * Checks if the player can modify/build at the specified location.
     * Takes into account region protections, bypass permissions, and active status.
     *
     * @param player   The player performing the action. Must not be null.
     * @param location The location. Must not be null.
     * @return true if the player is allowed to build/modify blocks.
     */
    boolean canBuild(@NonNull final Player player, @NonNull final Location location);

    /**
     * Gets the maximum number of regions the player is allowed to create based on permissions.
     *
     * @param player The player. Must not be null.
     * @return The region limit.
     */
    int getPlayerRegionLimit(@NonNull final Player player);

    /**
     * Checks if the specified cuboid area overlaps with any existing region's cuboid.
     *
     * @param cuboid The area to check. Must not be null.
     * @return true if there is an overlap.
     */
    boolean isAreaOverlapping(@NonNull final ICuboid cuboid);

    /**
     * Checks if a player has a specific permission in a region.
     *
     * @param region     The region. Must not be null.
     * @param player     The player. Must not be null.
     * @param permission The permission value. Must not be null.
     * @return true if the player has the permission.
     */
    boolean hasRegionPermission(@NonNull final IRegion region, @NonNull final OfflinePlayer player, @NonNull final RegionPermValue permission);

    /**
     * Creates a new region at the specified location with default item characteristics.
     *
     * @param owner    The owner/creator of the region. Must not be null.
     * @param location The center block location. Must not be null.
     * @param type     The type of protection block. Must not be null.
     * @return An Optional containing the created IRegion, or Optional.empty() if placement failed (e.g., overlapping area).
     */
    Optional<IRegion> createRegion(@NonNull final Player owner, @NonNull final Location location, @NonNull final IProtectionBlock type);

    /**
     * Creates a new region at the specified location, parsing custom item metadata (e.g., custom durability) if present.
     *
     * @param owner      The owner/creator of the region. Must not be null.
     * @param location   The center block location. Must not be null.
     * @param type       The type of protection block. Must not be null.
     * @param placedItem The item stack that was placed to create the region. Must not be null.
     * @return An Optional containing the created IRegion, or Optional.empty() if placement failed.
     */
    Optional<IRegion> createRegion(@NonNull final Player owner, @NonNull final Location location, @NonNull final IProtectionBlock type, @NonNull final ItemStack placedItem);

    /**
     * Safely deletes a region, closes associated menus, triggers deletion actions, and removes holograms.
     *
     * @param region The region to delete. Must not be null.
     */
    void deleteRegion(@NonNull final IRegion region);

    /**
     * Asynchronously deletes a region and breaks the physical block at its center.
     *
     * @param regionId The ID of the region. Must not be null.
     * @param dropItem true if the protection block item should be dropped.
     * @return A CompletableFuture returning true if successful.
     */
    CompletableFuture<Boolean> deleteRegionAndBlockAsync(@NonNull final String regionId, final boolean dropItem);

    /**
     * Teleports a player to the center of the specified region.
     *
     * @param player The player to teleport. Must not be null.
     * @param region The destination region. Must not be null.
     * @return A CompletableFuture returning true if teleportation succeeded.
     */
    CompletableFuture<Boolean> teleportToRegion(@NonNull final Player player, @NonNull final IRegion region);

    /**
     * Retrieves the permissions wrapper for the specified player in the region.
     *
     * @param region The region. Must not be null.
     * @param player The player. Must not be null.
     * @return The player permissions.
     */
    @NotNull IPlayerPermissions getPlayerPermissions(@NonNull final IRegion region, @NonNull final OfflinePlayer player);

    /**
     * Adds a member to the region.
     *
     * @param region The region. Must not be null.
     * @param target The target player to add. Must not be null.
     * @param bypass true to bypass limit checks (e.g., max members).
     * @return true if the member was added successfully.
     */
    boolean addMember(@NonNull final IRegion region, @NonNull final OfflinePlayer target, final boolean bypass);

    /**
     * Removes a member from the region.
     *
     * @param region The region. Must not be null.
     * @param target The target player to remove. Must not be null.
     * @return true if the member was removed successfully.
     */
    boolean removeMember(@NonNull final IRegion region, @NonNull final OfflinePlayer target);

    /**
     * Adds an owner (co-owner) to the region.
     *
     * @param region The region. Must not be null.
     * @param target The target player to promote. Must not be null.
     * @param bypass true to bypass limit checks.
     * @return true if the owner was added successfully.
     */
    boolean addOwner(@NonNull final IRegion region, @NonNull final OfflinePlayer target, final boolean bypass);

    /**
     * Removes an owner from the region's owners list.
     *
     * @param region The region. Must not be null.
     * @param target The target player to demote. Must not be null.
     * @return true if the owner was removed successfully.
     */
    boolean removeOwner(@NonNull final IRegion region, @NonNull final OfflinePlayer target);

    /**
     * Gets a list of all offline players registered as members in the region.
     *
     * @param region The region. Must not be null.
     * @return A list of members.
     */
    @NotNull List<OfflinePlayer> getRegionMembers(@NonNull final IRegion region);

    /**
     * Gets a list of all offline players registered as owners in the region.
     *
     * @param region The region. Must not be null.
     * @return A list of owners.
     */
    @NotNull List<OfflinePlayer> getRegionOwners(@NonNull final IRegion region);

    /**
     * Gets the unique ID of the creator of the region.
     *
     * @param region The region. Must not be null.
     * @return The creator's UUID.
     */
    @NotNull UUID getRegionCreator(@NonNull final IRegion region);

    /**
     * Gets the protection block settings template by its type ID.
     *
     * @param typeId The block type ID (e.g. "diamond"). Must not be null.
     * @return An Optional containing the protection block template.
     */
    Optional<IProtectionBlock> getProtectionBlock(@NonNull final String typeId);

    /**
     * Gets all registered protection block templates.
     *
     * @return A collection of all protection block templates.
     */
    @NonNull Collection<IProtectionBlock> getProtectionBlockTypes();

    /**
     * Toggles the permission to create regions for a player.
     *
     * @param player The player. Must not be null.
     * @return true if region creation is now enabled, false if disabled.
     */
    boolean toggleRegionCreation(@NonNull final Player player);

    /**
     * Checks if region creation is currently disabled for a player.
     *
     * @param player The player. Must not be null.
     * @return true if creation is disabled.
     */
    boolean isCreationDisabled(@NonNull final Player player);

    /**
     * Explicitly sets whether a player can create regions.
     *
     * @param player   The player. Must not be null.
     * @param disabled true to disable creation, false to enable.
     */
    void setCreationDisabled(@NonNull final Player player, final boolean disabled);

    /**
     * Checks if hologram managers are enabled globally (e.g., DecentHolograms or FancyHolograms is active).
     *
     * @return true if holograms are supported and initialized.
     */
    boolean isHologramEnabled();

    /**
     * Creates a hologram for the specified region using the active engine.
     *
     * @param region The region. Must not be null.
     */
    void createHologram(@NonNull final IRegion region);

    /**
     * Deletes the hologram associated with the specified region.
     *
     * @param region The region. Must not be null.
     */
    void deleteHologram(@NonNull final IRegion region);

    /**
     * Updates the text and visibility of a region's hologram.
     *
     * @param region The region. Must not be null.
     */
    void updateHologram(@NonNull final IRegion region);

    /**
     * Recreates all holograms for all regions.
     */
    void recreateAllHolograms();

    /**
     * Updates text lines and visibility of all holograms on the server.
     */
    void updateAllHolograms();

    /**
     * Checks if a hologram exists for the specified region.
     *
     * @param region The region. Must not be null.
     * @return true if a hologram is present.
     */
    boolean hasHologram(@NonNull final IRegion region);

    /**
     * Gets the JavaPlugin instance of the core plugin.
     *
     * @return The JavaPlugin instance.
     */
    JavaPlugin getPlugin();

    /**
     * Gets the FoliaLib instance used for scheduling and task execution.
     *
     * @return The FoliaLib instance.
     */
    FoliaLib getFoliaLib();

    /**
     * Registers a new command dynamically in the server command map.
     *
     * @param command The command instance to register. Must not be null.
     */
    void registerCommand(@NonNull Object command);

    /**
     * Unregisters a command from the server command map.
     *
     * @param command The command instance to unregister. Must not be null.
     */
    void unregisterCommand(@NonNull Object command);

    /**
     * Gets the data folder of the core plugin.
     *
     * @return The plugin data folder.
     */
    File getDataFolder();

    /**
     * Gets the economy manager interface.
     *
     * @return The IEconomyManager.
     */
    @NotNull IEconomyManager getEconomyManager();

    /**
     * Gets the custom item hook manager interface.
     *
     * @return The IItemHookManager.
     */
    @NotNull IItemHookManager getItemHookManager();

    /**
     * Gets the fuel manager interface for managing fuel types.
     *
     * @return The IFuelManager.
     */
    @NotNull IFuelManager getFuelManager();

    /**
     * Gets the repair manager interface for managing repair items.
     *
     * @return The IRepairManager.
     */
    @NotNull IRepairManager getRepairManager();

    /**
     * Gets the action manager interface for registering custom action tags.
     *
     * @return The IActionManager.
     */
    @NotNull IActionManager getActionManager();

    /**
     * Gets the notification manager interface.
     *
     * @return The INotificationManager.
     */
    @NotNull INotificationManager getNotificationManager();

    /**
     * Gets the effects manager interface.
     *
     * @return The IEffectsManager.
     */
    @NotNull IEffectsManager getEffectsManager();

    /**
     * Gets the menu manager interface for programmatically opening menus.
     *
     * @return The IMenuManager.
     */
    @NotNull IMenuManager getMenuManager();

    /**
     * Gets the stats manager for region and player statistics.
     *
     * @return The IStatsManager.
     */
    @NotNull IStatsManager getStatsManager();

    /**
     * Gets the region visualizer interface.
     *
     * @return The IRegionVisualizer.
     */
    @NotNull IRegionVisualizer getRegionVisualizer();

    /**
     * Checks if the specified player has the fuel boss bar enabled.
     *
     * @param player The player. Must not be null.
     * @return true if enabled.
     */
    boolean isPlayerFuelBarEnabled(@NonNull Player player);

    /**
     * Sets whether the specified player has the fuel boss bar enabled.
     *
     * @param player  The player. Must not be null.
     * @param enabled true to enable.
     */
    void setPlayerFuelBar(@NonNull Player player, boolean enabled);

    /**
     * Parses a text string with MiniMessage and legacy color codes (&amp;) into a Component.
     *
     * @param text The raw text string.
     * @return The parsed Adventure Component.
     */
    @NotNull Component parseColors(@NonNull String text);

    /**
     * Converts a text string with MiniMessage/hex colors into a legacy-formatted string (§ codes).
     *
     * @param text The raw text string.
     * @return The legacy-formatted string.
     */
    @NotNull String toLegacy(@NonNull String text);

    /**
     * Strips all color and formatting codes from a text string.
     *
     * @param text The raw text string.
     * @return The plain text without any formatting.
     */
    @NotNull String stripColors(@NonNull String text);

}