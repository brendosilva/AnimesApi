package academy.devDojo.springEssentials.service;

import academy.devDojo.springEssentials.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AnimeService {

    private static List<Anime> animes;
    static {
        animes = new ArrayList<>(List.of(new Anime(1L,"Naruto"), new Anime(2L, "Dragon Ball Z")));
    }
    public List<Anime> listAll() {
        return this.animes;
    }

    public Anime findByid(Long id) {
        return animes.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }

    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(3, 1000));
        animes.add(anime);
        return anime;
    }

    public void delete(Long id) {
        animes.remove(findByid(id));
    }

    public Anime replace(Long id, Anime anime) {
        var find = findByid(id);
        find.setName(anime.getName());
        return find;
    }
}
