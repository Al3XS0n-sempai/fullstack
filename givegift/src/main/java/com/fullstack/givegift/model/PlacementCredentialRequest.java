package com.fullstack.givegift.model;

import lombok.Getter;
import lombok.Setter;

public class PlacementCredentialRequest {
    @Getter @Setter
    public String name;

    @Getter @Setter
    public String description;

    @Getter @Setter
    public Long targetSum;
}
