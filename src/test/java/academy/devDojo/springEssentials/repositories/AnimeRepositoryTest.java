package academy.devDojo.springEssentials.repositories;

import academy.devDojo.springEssentials.domain.Anime;

import academy.devDojo.springEssentials.util.AnimeCreators;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Test for anime repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    private String nameUpdate = "Overlod";

    @Test
    @DisplayName("save Persist Anime when successFull")
    void test_savePersistAnimeWhenSuccessFull(){
        var anime = AnimeCreators.createAnimeToBeSaved();
        var saveAnime = this.animeRepository.save(anime);

        Assertions.assertThat(saveAnime).isNotNull();
        Assertions.assertThat(saveAnime.getId()).isNotNull();
        Assertions.assertThat(saveAnime.getName()).isEqualTo(saveAnime.getName());

     }

    @Test
    @DisplayName("updates Persist Anime when successFull")
    void test_UpdatePersistAnimeWhenSuccessFull(){
        var anime = AnimeCreators.createAnimeToBeSaved();
        var saveAnime = this.animeRepository.save(anime);

        saveAnime.setName(nameUpdate);

        var animeUpdate = this.animeRepository.save(saveAnime);

        Assertions.assertThat(animeUpdate).isNotNull();
        Assertions.assertThat(animeUpdate.getId()).isNotNull();
        Assertions.assertThat(animeUpdate.getName()).isEqualTo(nameUpdate);

     }

     @Test
     @DisplayName("Delete should remove anime when success full")
     void test_delete(){
        var anime = AnimeCreators.createAnimeToBeSaved();
        var animeSaved = this.animeRepository.save(anime);
        this.animeRepository.delete(animeSaved);
        var animeOptional = this.animeRepository.findById(animeSaved.getId());
        Assertions.assertThat(animeOptional.isEmpty()).isTrue();
     }

     @Test
     @DisplayName("Find By Name Should return Anime when sucess full")
     void test_findByName(){
        var anime = AnimeCreators.createAnimeToBeSaved();
        var savedAnime = this.animeRepository.save(anime);
        var animes = this.animeRepository.findByName(savedAnime.getName());

        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(animes).contains(savedAnime);
     }

    @Test
    @DisplayName("save throw ConstraintViolationException when name is empty")
    void test_throwContraintViolationException(){
        var anime = new Anime();
       Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
               .isInstanceOf(ConstraintViolationException.class);
    }
}