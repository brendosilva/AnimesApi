package academy.devDojo.springEssentials.util;

import academy.devDojo.springEssentials.domain.Anime;
import academy.devDojo.springEssentials.dto.AnimeDto;

public class AnimeCreators {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("Naruto")
                .build();
    }

    public static Anime createValidAnime(){
        return Anime.builder()
                .name("Naruto")
                .id(1L)
                .build();
    }

    public static Anime createValidUpdateAnime(){
        return Anime.builder()
                .name("Dragon Ball Z")
                .id(1L)
                .build();
    }

    public static AnimeDto createDto(){
        return AnimeDto.builder()
                .name(createAnimeToBeSaved().getName())
                .build();
    }
}
