package academy.devDojo.springEssentials.dto;

import academy.devDojo.springEssentials.domain.Anime;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class AnimeDto {
    private Long id;

    @NotEmpty(message = "The anime name canot be empty")
    @NotNull(message = "The anime name canot be null")
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
