package com.gg.literalurachallengejava;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.gg.literalurachallengejava.dto.ClassDTO;
import com.gg.literalurachallengejava.dto.LivroDTO;
import com.gg.literalurachallengejava.model.Autor;
import com.gg.literalurachallengejava.model.Livro;
import com.gg.literalurachallengejava.repository.AutorRepository;
import com.gg.literalurachallengejava.repository.LivroRepository;
import com.gg.literalurachallengejava.service.ApiClient;
import com.gg.literalurachallengejava.service.ApiConvert;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Main implements CommandLineRunner {

    private final Scanner LER = new Scanner(System.in);
    private final ApiClient CONSUMO_API = new ApiClient();
    private final ApiConvert CONVERTEDADOS = new ApiConvert();

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    private final String URL = "http://gutendex.com/books";

    public Main(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bem-vindo ao LiterAlura - Catálogo de Livros");
        System.out.println("-------------------------------------------");

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Buscar livro pelo título");
            System.out.println("2. Listar livros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. listar autores vivos em um determinado ano");
            System.out.println("5. Listar livros de um determinado idioma");
            System.out.println("0. Sair");


            int option = LER.nextInt();
            LER.nextLine(); // Limpar o buffer

            switch (option) {
                case 1:
                    consultarLivroPorTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmUmDeterminadoAno();
                    break;
                case 5:
                    listarLivrosEmUmDeterminadoIdioma();
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até logo!");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        }
    }

    private void consultarLivroPorTitulo() {
        ClassDTO dados = getDados();
        List<LivroDTO> livroDTOS = dados.livroDTO();

        if (!livroDTOS.isEmpty()) {
            Livro livro = new Livro(livroDTOS.get(0));
            Autor autor = autorRepository.buscarAutorPeloNome(livro.getAutor().getAutor());
            if (autor != null) {
                livro.setAutor(autor);
            }
            livroRepository.save(livro);
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado");
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        livros.forEach(System.out::println);

    }
    private void listarAutoresRegistrados(){
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);

    }
    private void  listarAutoresVivosEmUmDeterminadoAno(){
        try {
            List<Autor> autores = autorRepository.findAll();
            autores.forEach(System.out::println);

            System.out.println("Digite o ano: ");
            Integer ano = LER.nextInt();
            LER.nextLine();

            List<Autor> listaAutoresAno = autorRepository.procuraAutoresAno(ano);
            listaAutoresAno.forEach(System.out::println);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida, digite um ano válido.");;
            LER.nextLine();
        }

    }
    private void listarLivrosEmUmDeterminadoIdioma(){
        System.out.println("""
            Escolha um idioma para filtrar:
            en. Inglês
            pt. Português
            """);
        var idiomaEscolha = LER.nextLine();

        List<Livro> livrosIdioma = livroRepository.findByIdioma(idiomaEscolha);
        livrosIdioma.forEach(System.out::println);
    }

    private ClassDTO getDados() {
        System.out.println("Qual livro deseja pesquisar?");
        String nomeLivro = LER.nextLine();
        String json = CONSUMO_API.getData(URL + "/?search=" + nomeLivro.replace(" ", "+").toLowerCase());
        return CONVERTEDADOS.obterDados(json, ClassDTO.class);
    }
}
