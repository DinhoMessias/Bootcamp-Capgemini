package com.collections;

import java.util.*;
import java.util.function.Function;


/*Dadas as seguintes informações sobre minhas séries favoritas,
crie um conjunto e ordene este conjunto exibindo:
(nome - genero - tempo de episódio);
Série 1 = Nome: got, genero: fantasia, tempoEpisodio: 73
Série 2 = nome: dark, genero: drama, tempoEpisodio: 60
Série 3 = nome: simpsons genero: comédia, tempoEpisodio: 30
*/
public class ordenacaoSet {

    public static void main(String[] args) {
        Serie s1 = new Serie("got", "fantasia", 73);
        Serie s2 = new Serie("dark", "drama", 60);
        Serie s3 = new Serie("simpsons", "comedia", 30);

        System.out.println("--\tOrdem aleatória\t--");
        Set<Serie> series = new HashSet<>() {{
            addAll(Arrays.asList(s1, s2, s3));
        }};
        System.out.println(series);

        System.out.println("\n--\tOrdem inserção\t--");
        Set<Serie> series2 = new LinkedHashSet<>() {{
            addAll(Arrays.asList(s1, s2, s3));
        }};
        System.out.println(series2);

        System.out.println("\n--\tOrdem natural (TempoEpisodio)\t--");
        Set<Serie> series3 = new TreeSet<>(series2);
        System.out.println(series3);

        System.out.println("\n--\tOrdem Nome/Gênero/TempoEpisodio\t--");
        Set<Serie> series4 = new TreeSet<>(new ComparacaoSerie()) {{
            addAll(series3);
        }};
        System.out.println(series4);

        System.out.println("\n--\tOrdem gênero\t--");
        Set<Serie> series5 = new TreeSet<>(new Comparator<Serie>() {
            @Override
            public int compare(Serie s1, Serie s2) {
                return s1.getGenero().compareToIgnoreCase(s2.getGenero());
            }
        }) {{ addAll(series2); }};
        System.out.println(series5);

        System.out.println("\n--\tOrdem Tempo Episódio\t--");
        Set<Serie> series6 = new TreeSet<>(new Comparator<Serie>() {
            @Override
            public int compare(Serie s1, Serie s2) {
                return Integer.compare(s1.getTempoEpisodio(), s2.getTempoEpisodio());
            }
        }) {{ addAll(series2); }};
        System.out.println(series6);

        System.out.println("\n--\tOrdem Nome Episódio\t--");
        Set<Serie>series7 = new TreeSet<>(Comparator.comparing(new Function<Serie, String>() {
            @Override
            public String apply(Serie serie) {
                return serie.getNome();
            }
        })) {{ addAll(series); }};
        System.out.println(series7);
    }


}

class Serie implements Comparable<Serie> {
    private String nome;
    private String genero;
    private Integer tempoEpisodio;

    public Serie(String nome, String genero, Integer tempoEpisodio) {
        this.nome = nome;
        this.genero = genero;
        this.tempoEpisodio = tempoEpisodio;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public Integer getTempoEpisodio() {
        return tempoEpisodio;
    }

    @Override
    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", tempoEpisodio=" + tempoEpisodio +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return nome.equals(serie.nome) && genero.equals(serie.genero) && tempoEpisodio.equals(serie.tempoEpisodio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, genero, tempoEpisodio);
    }

    @Override
    public int compareTo(Serie serie) {
        return Integer.compare(this.tempoEpisodio, serie.tempoEpisodio);
    }
}

class ComparacaoSerie implements Comparator<Serie> {
    @Override
    public int compare(Serie s1, Serie s2) {
        int nome = s1.getNome().compareToIgnoreCase(s2.getNome());
        int genero = s1.getGenero().compareToIgnoreCase(s2.getGenero());
        if (nome != 0) {
            return nome;
        } else if (genero != 0) {
            return genero;
        }
        return Integer.compare(s1.getTempoEpisodio(), s2.getTempoEpisodio());
    }
}