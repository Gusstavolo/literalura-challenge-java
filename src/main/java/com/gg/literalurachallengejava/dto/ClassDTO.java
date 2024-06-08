package com.gg.literalurachallengejava.dto;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ClassDTO(@JsonAlias("results") List<LivroDTO> livroDTO)  {


}
