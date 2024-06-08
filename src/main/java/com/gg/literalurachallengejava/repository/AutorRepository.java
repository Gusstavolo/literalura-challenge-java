package com.gg.literalurachallengejava.repository;

import com.gg.literalurachallengejava.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Livro l JOIN l.autor a WHERE a.autor = :autor")
    Autor buscarAutorPeloNome(String autor);

    @Query("SELECT a FROM Livro b JOIN b.autor a WHERE a.anoNascimento <= :ano and a.anoFalecimento >= :ano")
    List<Autor> procuraAutoresAno(Integer ano);

    @Query("SELECT a FROM Autor a WHERE a.autor ILIKE %:nomeAutor%")
    List<Autor> procuraPorNomeAutor(String nomeAutor);
}
