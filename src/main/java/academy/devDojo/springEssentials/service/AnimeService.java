package academy.devDojo.springEssentials.service;

import academy.devDojo.springEssentials.domain.Anime;
import academy.devDojo.springEssentials.dto.AnimeDto;
import academy.devDojo.springEssentials.mapper.AnimeMapper;
import academy.devDojo.springEssentials.repositories.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public Anime findByid(Long id) {

       var anime = animeRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));

        return anime;
    }

    public AnimeDto save(AnimeDto animeDto) {

        Anime anime = AnimeMapper.INSTANCE.toAnime(animeDto);
        animeRepository.save(anime);

        return new AnimeDto(anime);
    }

    public void delete(Long id) {
        animeRepository.delete(findByid(id));
    }

    public AnimeDto replace(Long id, AnimeDto dto) {
        var animeExists = animeRepository.getOne(id);
        //animeExists.setName(dto.getName());
        //animeRepository.save(animeExists);
        var anime = AnimeMapper.INSTANCE.toAnime(dto);
        animeExists.setName(anime.getName());
        animeRepository.save(animeExists);

        return new AnimeDto(animeExists);
    }
}
