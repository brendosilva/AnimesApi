package academy.devDojo.springEssentials.controller;


import academy.devDojo.springEssentials.domain.Anime;
import academy.devDojo.springEssentials.dto.AnimeDto;
import academy.devDojo.springEssentials.service.AnimeService;
import academy.devDojo.springEssentials.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Page<Anime>> list(Pageable pageable) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok().body(animeService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable Long id){
        return ResponseEntity.ok(animeService.findByid(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name){
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<AnimeDto> insert(@Valid @RequestBody  AnimeDto anime) {
        AnimeDto save = animeService.save(anime);
        return ResponseEntity.ok().body(save);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Anime> delete (@PathVariable final Long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AnimeDto> replace(@PathVariable final Long id, @RequestBody AnimeDto dto){
        AnimeDto anime = animeService.replace(id, dto);
        return ResponseEntity.ok().body(anime);
    }
}
