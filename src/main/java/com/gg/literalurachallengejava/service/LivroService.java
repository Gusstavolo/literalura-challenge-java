package com.gg.literalurachallengejava.service;

import com.gg.literalurachallengejava.model.Livro;
import com.gg.literalurachallengejava.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public List<Livro> listarPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }

    public List<Livro> listarPorDownloads(Double numeroDownloads) {
        return livroRepository.findByNumeroDownloadsGreaterThan(numeroDownloads);
    }
}
