package dev.cakestudio.cakeregionsapi.api.data;

import dev.cakestudio.cakeregionsapi.api.value.RegionPermValue;

import lombok.NonNull;

import java.util.Set;

public interface IPlayerPermissions {
    boolean hasPermission(@NonNull final RegionPermValue permission);
    @NonNull Set<RegionPermValue> getPermissions();

    void addPermission(@NonNull final RegionPermValue permission);
    void removePermission(@NonNull final RegionPermValue permission);
    void togglePermission(@NonNull final RegionPermValue permission);
}