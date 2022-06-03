package academy.devDojo.springEssentials.repositories;

import academy.devDojo.springEssentials.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
}
