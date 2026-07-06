package dev.cakestudio.regionsaddon;

import dev.cakestudio.cakeregionsapi.addon.AbstractAddon;
import dev.cakestudio.cakeregionsapi.api.IRegionsAPI;
import dev.cakestudio.cakeregionsapi.api.manager.item.IItemHookProvider;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

@Getter
public final class RegionsAddon extends AbstractAddon {

    private IItemHookProvider myItemProvider;

    @Override
    protected void onEnable() {
        saveDefaultConfig();

        IRegionsAPI regionsAPI = getApi();
        
        getLogger().info("RegionsAddon v" + getDescription().version() + " enabled!");

        registerListener(new RegionsListener(this));

        // Register custom item hook provider
        myItemProvider = new IItemHookProvider() {
            @Override
            public @Nullable ItemStack getItem(@NonNull String id) {
                if (id.equalsIgnoreCase("heavy_core")) {
                    ItemStack core = new ItemStack(Material.NETHERITE_BLOCK);
                    ItemMeta meta = core.getItemMeta();
                    if (meta != null) {
                        meta.setDisplayName("Heavy Protection Core");
                        meta.getPersistentDataContainer().set(
                                new NamespacedKey("regionsaddon", "item_type"),
                                PersistentDataType.STRING,
                                "heavy_core");
                        core.setItemMeta(meta);
                    }
                    return core;
                }
                return null;
            }

            @Override
            public @Nullable String getItemId(@NonNull ItemStack item) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null && meta.getPersistentDataContainer().has(
                        new NamespacedKey("regionsaddon", "item_type"),
                        PersistentDataType.STRING)) {
                    String type = meta.getPersistentDataContainer().get(
                            new NamespacedKey("regionsaddon", "item_type"),
                            PersistentDataType.STRING);
                    if ("heavy_core".equals(type)) {
                        return "heavy_core";
                    }
                }
                return null;
            }

            @Override
            public @NonNull String getPrefix() {
                return "mycustomitems";
            }
        };

        regionsAPI.getItemHookManager().registerProvider(myItemProvider);
    }

    @Override
    protected void onDisable() {
        getLogger().info("RegionsAddon disabled. Cleaning up...");
        IRegionsAPI regionsAPI = getApi();
        if (regionsAPI != null && myItemProvider != null) {
            regionsAPI.getItemHookManager().unregisterProvider(myItemProvider);
        }
    }

    public String getMessage(String path) {
        return getConfig().getString("messages." + path, "Message not found: " + path);
    }

}
