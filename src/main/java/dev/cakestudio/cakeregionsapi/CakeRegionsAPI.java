package dev.cakestudio.cakeregionsapi;

import dev.cakestudio.cakeregionsapi.api.IRegionsAPI;

import lombok.NonNull;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CakeRegionsAPI extends JavaPlugin {

    private static IRegionsAPI implementation;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("CakeRegionsAPI successfully enabled");
    }

    public static void registerImplementation(@NonNull final IRegionsAPI api) {
        if (implementation != null) return;

        implementation = api;
    }

    public static IRegionsAPI getApi() {
        if (implementation == null) {
            throw new IllegalStateException("CakeRegions is not loaded.");
        }

        return implementation;
    }

}