package com.example.eurder.security;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public enum Role {
    CUSTOMER(newArrayList(Feature.ORDER)),
    ADMIN(newArrayList(Feature.ADD_ITEM));

    private List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
