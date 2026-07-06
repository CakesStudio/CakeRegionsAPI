package dev.cakestudio.cakeregionsapi.api.event.owner;

import dev.cakestudio.cakeregionsapi.api.event.RegionMemberEvent;
import dev.cakestudio.cakeregionsapi.api.data.IRegion;
import lombok.NonNull;
import org.bukkit.OfflinePlayer;

public class RegionOwnerRemoveEvent extends RegionMemberEvent {

    public RegionOwnerRemoveEvent(@NonNull final IRegion region, @NonNull final OfflinePlayer owner) {
        super(region, owner);
    }

}