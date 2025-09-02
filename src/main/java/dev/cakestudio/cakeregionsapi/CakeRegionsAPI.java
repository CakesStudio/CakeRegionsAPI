package dev.cakestudio.cakeregionsapi;

import dev.cakestudio.cakeregionsapi.api.ICakeRegionsAPI;

import lombok.NonNull;

import org.bukkit.plugin.java.JavaPlugin;

public final class CakeRegionsAPI extends JavaPlugin {

    private static ICakeRegionsAPI implementation;

    @Override
    public void onEnable() {

    }

    public static void registerImplementation(@NonNull final ICakeRegionsAPI api) {
        if (implementation != null) return;

        implementation = api;
    }

    public static ICakeRegionsAPI getApi() {
        if (implementation == null) {
            throw new IllegalStateException("CakeRegions is not loaded.");
        }

        return implementation;
    }

}