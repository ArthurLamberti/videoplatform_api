package com.arthurlamberti.videoplataform.infrastructure.video.persistence;

import com.arthurlamberti.videoplataform.domain.castmember.CastMemberID;
import com.arthurlamberti.videoplataform.domain.category.CategoryID;
import com.arthurlamberti.videoplataform.domain.genre.GenreID;
import com.arthurlamberti.videoplataform.domain.video.VideoPreview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.UUID;

public interface VideoRepository extends JpaRepository<VideoJpaEntity, String> {

    @Query("""
            select distinct new com.fullcycle.admin.catalogo.domain.video.VideoPreview(
                v.id as id,
                v.title as title,
                v.description as description,
                v.createdAt as createdAt,
                v.updatedAt as updatedAt
            )
            from Video v
                left join v.castMembers members
                left join v.categories categories
                left join v.genres genres
            where
                ( :terms is null or UPPER(v.title) like :terms )
            and
                ( :castMembers is null or members.id.castMemberId in :castMembers )
            and
                ( :categories is null or categories.id.categoryId in :categories )
            and
                ( :genres is null or genres.id.genreId in :genres )
            """)
    Page<VideoPreview> findAll(
            @Param("terms") final String terms,
            @Param("castMembers") final Set<String> castMembers,
            @Param("categories") final Set<String> categories,
            @Param("genres") final Set<String> genres,
            @Param("page") final PageRequest page);
}
