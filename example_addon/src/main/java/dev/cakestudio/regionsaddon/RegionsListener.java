package dev.cakestudio.regionsaddon;

import dev.cakestudio.cakeregionsapi.api.event.RegionCreateEvent;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class RegionsListener implements Listener {

    private final RegionsAddon addon;

    @EventHandler
    public void onRegionCreate(RegionCreateEvent event) {
        if (!addon.getConfig().getBoolean("enabled", true)) return;

        Player creator = event.getOwner();
        String regionId = event.getRegion().getId();
        Location loc = event.getRegion().getCenter();

        String message = addon.getMessage("broadcast-create")
                .replace("{player}", creator.getName())
                .replace("{region}", regionId)
                .replace("{x}", String.valueOf(loc.getBlockX()))
                .replace("{y}", String.valueOf(loc.getBlockY()))
                .replace("{z}", String.valueOf(loc.getBlockZ()));

        // Broadcast with colors
        Bukkit.broadcastMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
    }

}
