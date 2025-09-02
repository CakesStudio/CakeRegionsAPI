package dev.cakestudio.cakeregionsapi.api.data;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IProtectionBlock {
    String getTypeId();
    Material getMaterial();
    int getRadius();
    int getMaxMembers();
    String getItemName();
    List<String> getLore();
    boolean isPreventMetaModification();
    boolean isGlow();
    boolean isFuelSystemEnabled();
    Set<String> getAccessWorlds();

    IHologramSettings getHologram();
    IDurabilitySettings getDurability();
    IPlacementRules getPlacementRules();
    ICraftData getCraftData();
    IInactiveBreakSettings getInactiveBreak();
    IRegionActions getActions();

    interface IHologramSettings {
        boolean isEnabled();
        double getOffsetY();
        int getViewDistance();
        String getHoloName();
        List<String> getLines();
    }

    interface IDurabilitySettings {
        boolean isEnabled();
        int getInitial();
        int getMax();
        String getDisableValue();
        Map<EntityType, Integer> getExplosionDamage();
    }

    interface IHeightLimit {
        int getMin();
        int getMax();
    }

    interface INearbyBlocks {
        int getRadius();
        Set<Material> getMaterials();
    }

    interface IPlacementRules {
        Set<String> getAllowedBiomes();
        Set<String> getForbiddenBiomes();
        INearbyBlocks getRequire();
        INearbyBlocks getForbid();
    }

    interface ICraftData {
        boolean isEnabled();
        List<String> getShape();
        Map<String, String> getIngredients();
    }

    interface IInactiveBreakSettings {
        boolean isEnabled();
        boolean isDropItem();
    }

    interface IRegionActions {
        Actions getCreate();
        Actions getBreakByPlayer();
        Actions getBreakByDurability();
        Actions getBreakByExplosion();
        Actions getDurabilityDamage();
        Actions getDurabilityRepair();
    }

}