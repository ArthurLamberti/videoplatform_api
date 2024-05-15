package application;

import com.arthurlamberti.videoplataform.domain.castmember.CastMember;
import com.arthurlamberti.videoplataform.domain.castmember.CastMemberType;
import com.arthurlamberti.videoplataform.domain.category.Category;
import com.arthurlamberti.videoplataform.domain.genre.Genre;
import com.arthurlamberti.videoplataform.domain.video.Rating;
import com.arthurlamberti.videoplataform.domain.video.Resource;
import com.arthurlamberti.videoplataform.domain.video.Video;
import com.github.javafaker.Faker;
import io.vavr.API;

import java.time.Year;
import java.util.Arrays;
import java.util.Set;

import static io.vavr.API.*;

public final class Fixture {


    private static final Faker FAKER = new Faker();

    public static String name() {
        return FAKER.name().fullName();
    }

    public static Integer year() {
        return FAKER.random().nextInt(2000, 2024);
    }

    public static Double duration() {
        return (FAKER.random().nextInt(20, 1200)) / 10.0; //return a double between 2.0 and 120.0
    }


    public static boolean bool() {
        return FAKER.bool().bool();
    }

    public static String title() {
        return FAKER.harryPotter().book();
    }

    public static Video video() {
        return Video.newVideo(
                Fixture.title(),
                Videos.description(),
                Year.of(Fixture.year()),
                Fixture.duration(),
                Videos.rating(),
                Fixture.bool(),
                Fixture.bool(),
                Set.of(Categories.lessons().getId()),
                Set.of(Genres.tech().getId()),
                Set.of(CastMembers.johnDoe().getId())
        );
    }

    public static final class CastMembers {
        private static final CastMember JOHNDOE = CastMember.newMember("John Doe", CastMemberType.ACTOR);

        public static CastMemberType type() {
            return FAKER.options().option(CastMemberType.ACTOR, CastMemberType.DIRECTOR);
        }

        public static CastMember johnDoe() {
            return CastMember.with(JOHNDOE);
        }
    }

    public static final class Categories {
        private static final Category LESSONS = Category.newCategory("Lessons", "Some description", true);

        public static Category lessons() {
            return LESSONS.clone();
        }
    }

    public static final class Genres {
        private static final Genre TECH = Genre.newGenre("Technology", true);

        public static Genre tech() {
            return Genre.with(TECH);
        }
    }

    public static final class Videos {
        private static final Video SYSTEM_DESIGN = Video.newVideo(
                "System Design no Mercado Livre na prática",
                description(),
                Year.of(2022),
                Fixture.duration(),
                rating(),
                Fixture.bool(),
                Fixture.bool(),
                Set.of(Categories.lessons().getId()),
                Set.of(Genres.tech().getId()),
                Set.of(CastMembers.johnDoe().getId())
        );

        public static Video systemDesign() {
            return Video.with(SYSTEM_DESIGN);
        }
        public static Rating rating() {
            return FAKER.options().option(Rating.values());
        }

        public static String description(Integer... qtdCharacters) {
            return Faker.instance().lorem().characters(qtdCharacters.length > 0 ? qtdCharacters[0] : 300);
        }

        public static Resource resource(final Resource.Type type) {
            final String contentType = API.Match(type).of(
                    Case($(List(Resource.Type.VIDEO, Resource.Type.TRAILER)::contains), "video/mp4"),
                    Case($(), "image/jpg")
            );
            final byte[] content = FAKER.lorem().characters(10).getBytes();
            return Resource.with(content, contentType, type.name().toLowerCase(), type);
        }
    }
}
