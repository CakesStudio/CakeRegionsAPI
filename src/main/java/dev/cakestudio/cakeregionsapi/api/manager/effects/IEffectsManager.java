package dev.cakestudio.cakeregionsapi.api.manager.effects;

import lombok.NonNull;

import org.bukkit.Location;

import java.util.Collection;

/**
 * Interface for playing particle effects defined in the effects configuration.
 * Allows addons to trigger existing effects or check available effects.
 */
public interface IEffectsManager {

    /**
     * Plays a registered effect at the specified location.
     *
     * @param effectId The effect identifier from the effects configuration.
     * @param location The location to play the effect at.
     */
    void playEffect(@NonNull String effectId, @NonNull Location location);

    /**
     * Stops a specific effect at the specified location.
     *
     * @param effectId The effect identifier.
     * @param location The location where the effect is playing.
     */
    void stopEffect(@NonNull String effectId, @NonNull Location location);

    /**
     * Stops all currently running effects.
     */
    void stopAllEffects();

    /**
     * Gets a collection of all registered effect identifiers.
     *
     * @return An unmodifiable collection of effect IDs.
     */
    @NonNull Collection<String> getRegisteredEffects();

}
