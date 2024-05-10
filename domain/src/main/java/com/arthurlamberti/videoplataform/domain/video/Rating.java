package com.arthurlamberti.videoplataform.domain.video;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public enum Rating {
    ER("ER"),
    L("L"),
    AGR_10("10"),
    AGR_12("12"),
    AGR_14("14"),
    AGR_16("16"),
    AGR_18("18");

    private final String name;

    Rating(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Optional<Rating> of(final String label) {
        return Arrays.stream(Rating.values())
                .filter(it -> it.name.equalsIgnoreCase(label))
                .findFirst();
    }
}
