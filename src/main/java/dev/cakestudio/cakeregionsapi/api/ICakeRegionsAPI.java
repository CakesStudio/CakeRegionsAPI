package dev.cakestudio.cakeregionsapi.api;

import dev.cakestudio.cakeregionsapi.api.data.IProtectionBlock;
import dev.cakestudio.cakeregionsapi.api.data.IRegion;

import lombok.NonNull;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ICakeRegionsAPI {
    Optional<IRegion> getRegionAt(@NonNull final Location location);
    Optional<IRegion> getRegionById(@NonNull final String id);
    @NonNull List<IRegion> getRegionsForPlayer(@NonNull final Player player);
    @NonNull Collection<IRegion> getAllRegions();
    boolean isProtected(@NonNull final Location location);
    boolean canBuild(@NonNull final Player player, @NonNull final Location location);
    Optional<IProtectionBlock> getProtectionBlock(@NonNull final String typeId);
    @NonNull Collection<IProtectionBlock> getProtectionBlockTypes();
}