package dev.cakestudio.cakeregionsapi.api.event.member;

import dev.cakestudio.cakeregionsapi.api.event.RegionMemberEvent;
import dev.cakestudio.cakeregionsapi.api.data.IRegion;
import lombok.NonNull;
import org.bukkit.OfflinePlayer;

public class RegionMemberRemoveEvent extends RegionMemberEvent {

    public RegionMemberRemoveEvent(@NonNull final IRegion region, @NonNull final OfflinePlayer member) {
        super(region, member);
    }

}