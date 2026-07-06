package dev.cakestudio.cakeregionsapi.api.data;

import lombok.NonNull;

import org.bukkit.Location;
import org.bukkit.World;

public interface ICuboid {
    @NonNull World getWorld();
    int getMinX();
    int getMinY();
    int getMinZ();
    int getMaxX();
    int getMaxY();
    int getMaxZ();
    boolean contains(@NonNull final Location loc);
    boolean overlaps(@NonNull final ICuboid other);
}