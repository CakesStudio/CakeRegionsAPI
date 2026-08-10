package dev.cakestudio.cakeregionsapi.api.manager.notification;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;
import dev.cakestudio.cakeregionsapi.api.value.RaidTrigger;

import org.bukkit.entity.Entity;

import org.jetbrains.annotations.Nullable;

import lombok.NonNull;

/**
 * Interface for sending notifications through the plugin's built-in notification system.
 * Notifications can be delivered via in-game chat, bossbar, actionbar, Telegram, and Discord
 * depending on the server configuration and player settings.
 */
public interface INotificationManager {

    /**
     * Sends a notification to all members of the region using the default notification channel.
     *
     * @param region  The region whose members will be notified.
     * @param message The message content (supports MiniMessage format).
     */
    void sendNotification(@NonNull IRegion region, @NonNull String message);

    /**
     * Sends a raid notification to members of a region and executes member actions.
     *
     * @param region   The region being raided.
     * @param attacker The entity attacking the region (can be null).
     * @param trigger  The trigger type for the raid.
     */
    void sendRaidNotification(@NonNull IRegion region, @Nullable Entity attacker, @NonNull RaidTrigger trigger);

}