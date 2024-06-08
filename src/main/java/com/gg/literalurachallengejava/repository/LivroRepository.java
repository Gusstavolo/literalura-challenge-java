package com.gg.literalurachallengejava.repository;

import com.gg.literalurachallengejava.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(String idioma);
    List<Livro> findByNumeroDownloadsGreaterThan(Double numeroDownloads);
}
