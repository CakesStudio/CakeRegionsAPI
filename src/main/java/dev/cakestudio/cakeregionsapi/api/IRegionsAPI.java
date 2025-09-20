package dev.cakestudio.cakeregionsapi.api;

import dev.cakestudio.cakeregionsapi.api.data.ICuboid;
import dev.cakestudio.cakeregionsapi.api.data.IPlayerPermissions;
import dev.cakestudio.cakeregionsapi.api.data.IProtectionBlock;
import dev.cakestudio.cakeregionsapi.api.data.IRegion;
import dev.cakestudio.cakeregionsapi.api.value.RegionPermValue;

import lombok.NonNull;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IRegionsAPI {

    Optional<IRegion> getRegionAt(@NonNull final Location location);
    Optional<IRegion> getRegionById(@NonNull final String id);
    @NonNull List<IRegion> getRegionsByOwner(@NonNull final Player player);
    @NonNull List<IRegion> getRegionsByCreator(@NonNull final Player player);
    @NonNull List<IRegion> getRegionsForPlayer(@NonNull final Player player);
    @NonNull Collection<IRegion> getAllRegions();

    boolean isProtected(@NonNull final Location location);
    boolean canBuild(@NonNull final Player player, @NonNull final Location location);
    int getPlayerRegionLimit(@NonNull final Player player);
    boolean isAreaOverlapping(@NonNull final ICuboid cuboid);
    boolean hasRegionPermission(@NonNull final IRegion region, @NonNull final OfflinePlayer player, @NonNull final RegionPermValue permission);

    Optional<IRegion> createRegion(@NonNull final Player owner, @NonNull final Location location, @NonNull final IProtectionBlock type);
    Optional<IRegion> createRegion(@NonNull final Player owner, @NonNull final Location location, @NonNull final IProtectionBlock type, @NonNull final ItemStack placedItem);
    void deleteRegion(@NonNull final IRegion region);
    CompletableFuture<Boolean> deleteRegionAndBlockAsync(@NonNull final String regionId, final boolean dropItem);
    CompletableFuture<Boolean> teleportToRegion(@NonNull final Player player, @NonNull final IRegion region);

    @NotNull IPlayerPermissions getPlayerPermissions(@NonNull final IRegion region, @NonNull final OfflinePlayer player);
    boolean addMember(@NonNull final IRegion region, @NonNull final OfflinePlayer target, final boolean bypass);
    boolean removeMember(@NonNull final IRegion region, @NonNull final OfflinePlayer target);
    boolean addOwner(@NonNull final IRegion region, @NonNull final OfflinePlayer target, final boolean bypass);
    boolean removeOwner(@NonNull final IRegion region, @NonNull final OfflinePlayer target);
    @NotNull List<OfflinePlayer> getRegionMembers(@NonNull final IRegion region);
    @NotNull List<OfflinePlayer> getRegionOwners(@NonNull final IRegion region);
    @NotNull UUID getRegionCreator(@NonNull final IRegion region);

    Optional<IProtectionBlock> getProtectionBlock(@NonNull final String typeId);
    @NonNull Collection<IProtectionBlock> getProtectionBlockTypes();

    boolean toggleRegionCreation(@NonNull final Player player);
    boolean isCreationDisabled(@NonNull final Player player);
    void setCreationDisabled(@NonNull final Player player, final boolean disabled);

    boolean isHologramEnabled();
    void createHologram(@NonNull final IRegion region);
    void deleteHologram(@NonNull final IRegion region);
    void updateHologram(@NonNull final IRegion region);
    void recreateAllHolograms();
    void updateAllHolograms();
    boolean hasHologram(@NonNull final IRegion region);

}