package com.gg.literalurachallengejava.model;

import com.gg.literalurachallengejava.dto.AutorDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String autor;

    private Integer anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    // Default constructor (required by JPA/Hibernate)
    public Autor() {
    }

    // Constructor to initialize with AutorDTO
    public Autor(AutorDTO autorDTO) {
        this.autor = autorDTO.autor();
        this.anoNascimento = autorDTO.anoNascimento();
        this.anoFalecimento = autorDTO.anoFalecimento();
    }

    // Constructor with parameters for convenience
    public Autor(String autor, Integer anoNascimento, Integer anoFalecimento) {
        this.autor = autor;
        this.anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;
    }

    // Getters and Setters
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        String livrosStr = livros.stream()
                .map(Livro::getTitulo)
                .collect(Collectors.joining(", "));
        return
                ", autor='" + autor + '\'' +
                ", anoNascimento=" + anoNascimento +
                ", anoFalecimento=" + anoFalecimento +
                ", livros=" + livrosStr
               ;
    }
}
