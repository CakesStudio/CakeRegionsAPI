package dev.cakestudio.cakeregionsapi.api.value;

import lombok.Getter;
import lombok.NonNull;

@Getter
public enum RegionPermValue {
    ADD_FUEL("add-fuel"),
    ADD_MEMBER("add-member"),
    REMOVE_MEMBER("remove-member"),
    ADD_OWNER("add-owner"),
    REMOVE_OWNER("remove-owner"),
    MODIFY_PERMISSIONS("modify-permissions");

    private final String key;

    RegionPermValue(@NonNull final String key) {
        this.key = key;
    }

}