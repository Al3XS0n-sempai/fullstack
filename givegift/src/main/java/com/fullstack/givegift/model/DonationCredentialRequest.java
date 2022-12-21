package com.fullstack.givegift.model;

import lombok.Getter;
import lombok.Setter;

public class DonationCredentialRequest {
    @Getter @Setter public Long amount;
    @Getter @Setter public Long placementId;
}
