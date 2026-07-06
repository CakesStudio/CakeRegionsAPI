package dev.cakestudio.cakeregionsapi.api.manager.economy;

import org.bukkit.OfflinePlayer;

import lombok.NonNull;

/**
 * Interface for economy management, abstracting the underlying provider (Vault, BVault, etc.).
 */
public interface IEconomyManager {

    /**
     * Gets the current balance of a player in the default economy.
     *
     * @param player The player.
     * @return The balance amount.
     */
    double getBalance(@NonNull OfflinePlayer player);

    /**
     * Gets the current balance of a player in a specific economy provider.
     *
     * @param player           The player.
     * @param economyProvider  The specific economy provider name (e.g. Vault, XP, PlayerPoints).
     * @return The balance amount.
     */
    double getBalance(@NonNull OfflinePlayer player, String economyProvider);

    /**
     * Checks if a player has at least a specific amount of money in the default economy.
     *
     * @param player The player.
     * @param amount The amount to check.
     * @return true if the balance is sufficient.
     */
    boolean has(@NonNull OfflinePlayer player, double amount);

    /**
     * Checks if a player has at least a specific amount of money in a specific economy provider.
     *
     * @param player           The player.
     * @param amount           The amount to check.
     * @param economyProvider  The specific economy provider name.
     * @return true if the balance is sufficient.
     */
    boolean has(@NonNull OfflinePlayer player, double amount, String economyProvider);

    /**
     * Adds money to a player's balance in the default economy.
     *
     * @param player The player.
     * @param amount The amount to add.
     */
    void deposit(@NonNull OfflinePlayer player, double amount);

    /**
     * Adds money to a player's balance in a specific economy provider.
     *
     * @param player           The player.
     * @param amount           The amount to add.
     * @param economyProvider  The specific economy provider name.
     */
    void deposit(@NonNull OfflinePlayer player, double amount, String economyProvider);

    /**
     * Removes money from a player's balance in the default economy.
     *
     * @param player The player.
     * @param amount The amount to withdraw.
     * @return true if the transaction was successful.
     */
    boolean withdraw(@NonNull OfflinePlayer player, double amount);

    /**
     * Removes money from a player's balance in a specific economy provider.
     *
     * @param player           The player.
     * @param amount           The amount to withdraw.
     * @param economyProvider  The specific economy provider name.
     * @return true if the transaction was successful.
     */
    boolean withdraw(@NonNull OfflinePlayer player, double amount, String economyProvider);

    /**
     * Formats a monetary amount into a human-readable string based on plugin settings.
     *
     * @param amount The amount to format.
     * @return The formatted currency string.
     */
    @NonNull
    String format(double amount);

    /**
     * Formats a monetary amount into a human-readable string based on a specific economy provider.
     *
     * @param amount           The amount to format.
     * @param economyProvider  The specific economy provider name.
     * @return The formatted currency string.
     */
    @NonNull
    String format(double amount, String economyProvider);

    /**
     * Registers a new custom economy provider to be used by the plugin.
     * After registration, this provider can be selected in the main config or used programmatically.
     *
     * @param provider The provider implementation.
     */
    void registerProvider(IEconomyProvider provider);

    /**
     * Gets the active economy provider name.
     *
     * @return The active economy name.
     */
    @NonNull
    String getActiveEconomyName();

}
