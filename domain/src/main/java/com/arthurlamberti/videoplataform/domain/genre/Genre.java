package com.arthurlamberti.videoplataform.domain.genre;

import com.arthurlamberti.videoplataform.domain.AggregateRoot;
import com.arthurlamberti.videoplataform.domain.category.CategoryID;
import com.arthurlamberti.videoplataform.domain.exception.NotificationException;
import com.arthurlamberti.videoplataform.domain.utils.InstantUtils;
import com.arthurlamberti.videoplataform.domain.validation.ValidationHandler;
import com.arthurlamberti.videoplataform.domain.validation.handler.Notification;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
public class Genre extends AggregateRoot<GenreID> {

    private String name;
    private boolean active;
    private List<CategoryID> categories;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Genre(
            final GenreID genreID,
            final String aName,
            final boolean isActive,
            final List<CategoryID> categories,
            final Instant aCreatedAt,
            final Instant aUpdatedAt,
            final Instant aDeletedAt
    ) {
        super(genreID);
        this.name = aName;
        this.active = isActive;
        this.categories = categories;
        this.createdAt = aCreatedAt;
        this.updatedAt = aUpdatedAt;
        this.deletedAt = aDeletedAt;
        selfValidate();
    }

    public static Genre newGenre(final String aName, final Boolean isActive) {
        final var anId = GenreID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = isActive ? null : now;
        return new Genre(anId, aName, isActive, new ArrayList<>(), now, now, deletedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new GenreValidator(this, handler).validate();
    }

    public Genre deactivate() {
        if (isNull(this.deletedAt)) {
            this.deletedAt = InstantUtils.now();
        }
        this.active = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Genre activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Genre update(final String aName, final boolean isActive, final List<CategoryID> categories) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }
        this.name = aName;
        this.categories = new ArrayList<>(isNull(categories) ? Collections.emptyList() : categories);
        this.updatedAt = InstantUtils.now();
        selfValidate();
        return this;
    }

    public Genre addCategory(final CategoryID aCategory) {
        if (isNull(aCategory)) {
            return this;
        }
        this.categories.add(aCategory);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Genre removeCategory(final CategoryID aCategoryID) {
        if (isNull(aCategoryID)) {
            return this;
        }
        this.categories.remove(aCategoryID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Genre addCategories(final List<CategoryID> categories) {
        if (isNull(categories) || categories.isEmpty()) {
            return this;
        }
        this.categories.addAll(categories);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError())
            throw new NotificationException("Failed to create a Genre", notification);
    }
}
