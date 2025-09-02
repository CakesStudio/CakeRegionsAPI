package dev.cakestudio.cakeregionsapi.api.data;

import lombok.NonNull;

import java.util.List;

public interface IActions {

    @NonNull List<String> getActionStrings();

}