package dev.cakestudio.cakeregionsapi.api.manager.notification;

import dev.cakestudio.cakeregionsapi.api.data.IRegion;

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

}
