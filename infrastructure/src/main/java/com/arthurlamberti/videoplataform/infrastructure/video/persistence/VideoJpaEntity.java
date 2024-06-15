package com.arthurlamberti.videoplataform.infrastructure.video.persistence;

import com.arthurlamberti.videoplataform.domain.castmember.CastMemberID;
import com.arthurlamberti.videoplataform.domain.category.CategoryID;
import com.arthurlamberti.videoplataform.domain.genre.GenreID;
import com.arthurlamberti.videoplataform.domain.video.Rating;
import com.arthurlamberti.videoplataform.domain.video.Video;
import com.arthurlamberti.videoplataform.domain.video.VideoID;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.Year;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Table(name = "videos")
@Entity(name = "Video")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class VideoJpaEntity {

    @Id
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 4000)
    private String description;

    @Column(name = "year_launched")
    private int yearLaunched;

    @Column(name = "opened")
    private boolean opened;

    @Column(name = "published")
    private boolean published;


    @Column(name = "rating")
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @Column(name = "duration", precision = 2)
    private double duration;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")

    private Instant createdAt;
    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "video_id")
    private AudioVideoMediaJpaEntity video;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "trailer_id")
    private AudioVideoMediaJpaEntity trailer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "banner_id")
    private ImageMediaJpaEntity banner;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "thumbnail_id")
    private ImageMediaJpaEntity thumbnail;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "thumbnail_half_id")
    private ImageMediaJpaEntity thumbnailHalf;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VideoCategoryJpaEntity> categories = new HashSet<>(1);


    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VideoGenreJpaEntity> genres = new HashSet<>(1);

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VideoCastMemberJpaEntity> castMembers = new HashSet<>(1);

    public static VideoJpaEntity from(final Video aVideo) {
        final var entity = new VideoJpaEntity(
                aVideo.getId().getValue(),
                aVideo.getTitle(),
                aVideo.getDescription(),
                aVideo.getLaunchedAt().getValue(),
                aVideo.isOpened(),
                aVideo.isPublished(),
                aVideo.getRating(),
                aVideo.getDuration(),
                aVideo.getCreatedAt(),
                aVideo.getUpdatedAt(),
                aVideo.getVideo().map(AudioVideoMediaJpaEntity::from).orElse(null),
                aVideo.getTrailer().map(AudioVideoMediaJpaEntity::from).orElse(null),
                aVideo.getBanner().map(ImageMediaJpaEntity::from).orElse(null),
                aVideo.getThumbnail().map(ImageMediaJpaEntity::from).orElse(null),
                aVideo.getThumbnailHalf().map(ImageMediaJpaEntity::from).orElse(null),
                new HashSet<>(1),
                new HashSet<>(1),
                new HashSet<>(1)
        );

        aVideo.getCategories().forEach(entity::addCategory);
        aVideo.getGenres().forEach(entity::addGenre);
        aVideo.getCastMembers().forEach(entity::addCastMember);
        return entity;
    }

    private void addCategory(final CategoryID categoryID) {
        this.categories.add(VideoCategoryJpaEntity.from(this, categoryID));
    }

    private void addGenre(final GenreID genreID) {
        this.genres.add(VideoGenreJpaEntity.from(this, genreID));
    }

    private void addCastMember(final CastMemberID genreID) {
        this.castMembers.add(VideoCastMemberJpaEntity.from(this, genreID));
    }

    public Video toAggregate() {
        return Video.with(
                VideoID.from(this.id),
                this.title,
                this.description,
                Year.of(this.yearLaunched),
                this.duration,
                this.rating,
                this.opened,
                this.published,
                this.createdAt,
                this.updatedAt,
                Optional.ofNullable(this.banner).map(ImageMediaJpaEntity::toDomain).orElse(null),
                Optional.ofNullable(this.thumbnail).map(ImageMediaJpaEntity::toDomain).orElse(null),
                Optional.ofNullable(this.thumbnailHalf).map(ImageMediaJpaEntity::toDomain).orElse(null),
                Optional.ofNullable(this.trailer).map(AudioVideoMediaJpaEntity::toDomain).orElse(null),
                Optional.ofNullable(this.video).map(AudioVideoMediaJpaEntity::toDomain).orElse(null),
                this.categories.stream().map(it -> CategoryID.from(it.getId().getCategoryId())).collect(Collectors.toSet()),
                this.genres.stream().map(it -> GenreID.from(it.getId().getGenreId())).collect(Collectors.toSet()),
                this.castMembers.stream().map(it -> CastMemberID.from(it.getId().getCastMemberId())).collect(Collectors.toSet())
        );
    }

}