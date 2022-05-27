package academy.devDojo.springEssentials.controller;


import academy.devDojo.springEssentials.domain.Anime;
import academy.devDojo.springEssentials.service.AnimeService;
import academy.devDojo.springEssentials.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<Anime>> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok().body(animeService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable Long id){
        return ResponseEntity.ok(animeService.findByid(id));
    }

    @PostMapping
    public ResponseEntity<Anime> insert(@RequestBody Anime anime) {
        Anime save = animeService.save(anime);
        return ResponseEntity.ok().body(save);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Anime> delete (@PathVariable final Long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Anime> replace(@PathVariable final Long id, @RequestBody Anime anime){
        var update = animeService.replace(id, anime);
        return ResponseEntity.ok().body(update);
    }
}
