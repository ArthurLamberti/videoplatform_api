package com.arthurlamberti.videoplataform.domain.genre;

import com.arthurlamberti.videoplataform.domain.AggregateRoot;
import com.arthurlamberti.videoplataform.domain.category.Category;
import com.arthurlamberti.videoplataform.domain.category.CategoryID;
import com.arthurlamberti.videoplataform.domain.validation.ValidationHandler;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
public class Genre extends AggregateRoot<GenreID> {

    private String name;
    private boolean isActive;
    private List<CategoryID> categories;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Genre(
            final GenreID genreID
    ) {
        super(genreID);
    }

    public static Genre newGenre(String expectedName, Boolean expectedIsActive) {
        throw new RuntimeException();
    }

    @Override
    public void validate(ValidationHandler handler) {

    }

    public void deactivate() {
    }

    public void activate() {
    }
    
    public void update(String expectedName, boolean expectedIsActive, List<CategoryID> expectedCategories) {
        
    }

    public void addCategory(CategoryID seriesID) {
    }

    public void removeCategory(CategoryID seriesID) {
    }

    public void addCategories(List<CategoryID> expectedCategories) {
    }
}
