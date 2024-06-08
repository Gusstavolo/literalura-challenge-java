package com.gg.literalurachallengejava.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(@JsonAlias("id") Long id,
                       @JsonAlias("title") String titulo,
                       @JsonAlias("authors") List<AutorDTO> authors,
                       @JsonAlias("languages") List<String> idioma,
                       @JsonAlias("download_count") Double numeroDownload){
}