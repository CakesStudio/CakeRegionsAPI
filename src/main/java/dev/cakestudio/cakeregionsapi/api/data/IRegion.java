package dev.cakestudio.cakeregionsapi.api.data;

import org.bukkit.Location;

import lombok.NonNull;

import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface IRegion {
    @NonNull
    String getId();

    @NonNull
    Location getCenter();

    @NonNull
    UUID getCreator();

    @NonNull
    Set<UUID> getOwners();

    @NonNull
    Set<UUID> getMembers();

    @NonNull
    Set<UUID> getFullMembers();

    @NonNull
    String getFormattedCreationDate();

    boolean isActive();

    long getCreationDate();

    long getFuelExpiryDate();

    int getDurability();

    void repair(int amount);

    void damage(int amount);

    void setDurability(int amount);

    void addFuel(long seconds);

    void setFuel(long seconds);

    void removeFuel(long seconds);

    boolean isCreator(@NonNull final UUID uuid);

    boolean isOwner(@NonNull final UUID uuid);

    boolean isMember(@NonNull final UUID uuid);

    boolean isFullMember(@NonNull final UUID uuid);

    boolean canBuild(@NonNull final UUID uuid);

    @NonNull
    IProtectionBlock getProtectionBlockType();

    @NonNull
    ICuboid getCuboid();

    @NonNull
    IPlayerPermissions getPermissions(@NonNull final UUID uuid);

    void setPermissions(@NonNull final UUID uuid, @NonNull final IPlayerPermissions permissions);

    // ========================
    // New methods
    // ========================

    /**
     * Gets the remaining fuel time in seconds.
     * Returns 0 if the fuel system is disabled or fuel has expired.
     *
     * @return The remaining fuel time in seconds.
     */
    long getRemainingFuel();

    /**
     * Gets the age of the region in seconds since creation.
     *
     * @return The age in seconds.
     */
    long getAge();

    /**
     * Gets whether the auto-add feature is enabled for this region.
     * When enabled, nearby players are automatically added as members.
     *
     * @return true if auto-add is enabled.
     */
    boolean isAutoAdd();

    /**
     * Sets the auto-add feature state for this region.
     *
     * @param autoAdd true to enable auto-add.
     */
    void setAutoAdd(boolean autoAdd);

    /**
     * Gets whether the fuel bar is enabled for this region.
     *
     * @return true if the fuel bar is enabled.
     */
    boolean isFuelBar();

    /**
     * Sets the fuel bar state for this region.
     *
     * @param fuelBar true to enable the fuel bar.
     */
    void setFuelBar(boolean fuelBar);

    /**
     * Gets the role assigned to a specific member.
     *
     * @param uuid The member's UUID.
     * @return The role name, or null if no role is assigned.
     */
    @Nullable
    String getRole(@NonNull final UUID uuid);

    /**
     * Sets a role for a specific member.
     *
     * @param uuid The member's UUID.
     * @param role The role name, or null to remove the role.
     */
    void setRole(@NonNull final UUID uuid, @Nullable final String role);

    /**
     * Gets all members with their assigned roles.
     *
     * @return An unmodifiable map of UUID to role name.
     */
    @NonNull
    Map<UUID, String> getMembersWithRoles();

    // ========================
    // Metadata / Tags
    // ========================

    /**
     * Sets a custom metadata tag on this region.
     * Tags are persisted and can be used by addons to store arbitrary data.
     *
     * @param key   The tag key (e.g., "myaddon:level").
     * @param value The tag value.
     */
    void setTag(@NonNull String key, @NonNull String value);

    /**
     * Gets a custom metadata tag value.
     *
     * @param key The tag key.
     * @return The value, or null if the tag doesn't exist.
     */
    @Nullable
    String getTag(@NonNull String key);

    /**
     * Removes a custom metadata tag.
     *
     * @param key The tag key to remove.
     */
    void removeTag(@NonNull String key);

    /**
     * Checks if a custom metadata tag exists.
     *
     * @param key The tag key.
     * @return true if the tag exists.
     */
    boolean hasTag(@NonNull String key);

    /**
     * Gets all custom metadata tags.
     *
     * @return An unmodifiable map of all tags.
     */
    @NonNull
    Map<String, String> getTags();

    // ========================
    // Upgrades & Boosters
    // ========================

    int getRadiusUpgradeLevel();
    void setRadiusUpgradeLevel(int level);
    int getRadiusUpgradeBonus();

    int getDurabilityUpgradeLevel();
    void setDurabilityUpgradeLevel(int level);
    int getDurabilityUpgradeBonus();

    int getMembersUpgradeLevel();
    void setMembersUpgradeLevel(int level);
    int getMembersUpgradeBonus();

    int getStorageUpgradeLevel();
    void setStorageUpgradeLevel(int level);

    String getStorageContents();
    void setStorageContents(String base64);

    @Nullable
    org.bukkit.inventory.Inventory getStorageInventory();

    @Nullable
    Map<String, Long> getBoostersMap();
    boolean hasActiveBooster(@NonNull String boosterId);
    void activateBooster(@NonNull String boosterId, long durationSeconds);
    void deactivateBooster(@NonNull String boosterId);
    long getBoosterRemainingTime(@NonNull String boosterId);
    boolean isBoosterEnabled(@NonNull String boosterId);
    void setBoosterEnabled(@NonNull String boosterId, boolean enabled);
    boolean isBoosterPermanent(@NonNull String boosterId);
    void activatePermanentBooster(@NonNull String boosterId);
}