package academy.devDojo.springEssentials.controller;

import academy.devDojo.springEssentials.domain.Anime;
import academy.devDojo.springEssentials.dto.AnimeDto;
import academy.devDojo.springEssentials.service.AnimeService;
import academy.devDojo.springEssentials.util.AnimeCreators;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks
    private AnimeController animeController;

    @Mock
    private AnimeService animeServiceMock;

    private String expectName = "Naruto";

    @BeforeEach
    void setUp(){
       var animePage = new PageImpl<>(List.of(AnimeCreators.createValidAnime()));
        BDDMockito.when(animeServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(animePage);

        BDDMockito.when(animeServiceMock.findByid(ArgumentMatchers.any()))
                .thenReturn(AnimeCreators.createValidAnime());

        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.any()))
                .thenReturn(List.of(AnimeCreators.createValidAnime()));

        BDDMockito.when(animeServiceMock.save(ArgumentMatchers.any(AnimeDto.class)))
                .thenReturn(AnimeCreators.createDto());

        BDDMockito.doNothing().when(animeServiceMock).delete(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName("Return list of animes of inside page object success full")
    void list_returnListOfAnimesInsidePageObject_whenSuccessFull(){
        var anime = AnimeCreators.createValidAnime().getName();
        var animePage = animeController.list(null).getBody();
        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectName);
    }

    @Test
    @DisplayName("Find by id Return anime ")
    void return_findById(){
        var expectedId = AnimeCreators.createValidAnime().getId();
        var anime = this.animeController.findById(1L).getBody();
        Assertions.assertThat(anime).isNotNull();

        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Find by name should return list of anime by name")
    void returnAnimeFindByName(){
        var expectedName = AnimeCreators.createValidAnime().getName();
        var animes = animeController.findByName("anime").getBody();

       Assertions.assertThat(animes)
               .isNotNull()
               .isNotEmpty()
               .hasSize(1);

       Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName should return an empty list of anime when anime is not found")
    void test_notFoundAnime(){
        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.any()))
                .thenReturn(Collections.emptyList());


        var animes = animeController.findByName("anime").getBody();

       Assertions.assertThat(animes)
               .isNotNull()
               .isEmpty();

    }

//    @Test
//    @DisplayName("save return anime dto when success ful")
//    void test_save(){
//        var expectedId = AnimeCreators.createValidAnime().getId();
//        var anime = animeController.insert(AnimeCreators.createDto()).getBody();
//        Assertions.assertThat(anime).isNotNull();
//        Assertions.assertThat(anime).isEqualTo(AnimeCreators.createValidAnime());
//
//    }

    void delete_deleteAnime_whenSuccessfull(){
        Assertions.assertThatCode(() -> animeController.delete(1L))
                .doesNotThrowAnyException();


        Assertions.assertThat(animeController.delete(1L)).isNotNull();
        Assertions.assertThat(animeController.delete(1L).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);


    }




}