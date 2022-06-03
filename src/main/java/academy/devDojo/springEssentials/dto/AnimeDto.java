package academy.devDojo.springEssentials.dto;

import academy.devDojo.springEssentials.domain.Anime;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class AnimeDto {
    private Long id;
    private String name;

    public AnimeDto(){}
    public AnimeDto(Long id, String name){
        this.id = id;
        this.name = name;
    }
    public AnimeDto(Anime entity){
        this.id = entity.getId();
       this.name = entity.getName();
    }

}
