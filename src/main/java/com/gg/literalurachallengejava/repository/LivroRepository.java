package com.gg.literalurachallengejava.repository;

import com.gg.literalurachallengejava.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.idioma ILIKE :idiomaEscolha")
    List<Livro> findByIdioma(String idiomaEscolha);
}
