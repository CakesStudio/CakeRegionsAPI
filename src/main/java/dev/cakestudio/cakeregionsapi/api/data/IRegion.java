package dev.cakestudio.cakeregionsapi.api.data;

import org.bukkit.Location;

import lombok.NonNull;

import java.util.Set;
import java.util.UUID;

public interface IRegion {
    @NonNull String getId();
    @NonNull Location getCenter();
    @NonNull UUID getCreator();
    @NonNull Set<UUID> getOwners();
    @NonNull Set<UUID> getMembers();
    @NonNull Set<UUID> getFullMembers();
    boolean isActive();
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

    @NonNull IProtectionBlock getProtectionBlockType();
    @NonNull ICuboid getCuboid();
    @NonNull IPlayerPermissions getPermissions(@NonNull final UUID uuid);
    void setPermissions(@NonNull final UUID uuid, @NonNull final IPlayerPermissions permissions);
}