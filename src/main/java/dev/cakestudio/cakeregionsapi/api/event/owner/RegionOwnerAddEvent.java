package dev.cakestudio.cakeregionsapi.api.event.owner;

import dev.cakestudio.cakeregionsapi.api.event.RegionMemberEvent;
import dev.cakestudio.cakeregionsapi.api.data.IRegion;
import lombok.NonNull;
import org.bukkit.OfflinePlayer;

public class RegionOwnerAddEvent extends RegionMemberEvent {

    public RegionOwnerAddEvent(@NonNull final IRegion region, @NonNull final OfflinePlayer owner) {
        super(region, owner);
    }

}