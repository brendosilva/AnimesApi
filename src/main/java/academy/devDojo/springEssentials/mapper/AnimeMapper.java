package academy.devDojo.springEssentials.mapper;


import academy.devDojo.springEssentials.domain.Anime;
import academy.devDojo.springEssentials.dto.AnimeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    public abstract Anime toAnime(AnimeDto animeDto);
}
