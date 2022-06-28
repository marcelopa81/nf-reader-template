package processador;

import dto.RelatorioNF;
import io.EscritorCSV;


import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class ProcessadorDeArquivos {


    private final EscritorCSV escritor = new EscritorCSV();
    private final RelatorioNFConversor conversor = new RelatorioNFConversor();

    public void processaArquivosDo(String diretorio) {

        Map<String, BigDecimal> totaisPorDestinatario = new HashMap<>();

        Set<File> arquivos = listFilesFrom(diretorio);

        BarraDeProgresso barraDeProgresso = new BarraDeProgresso(arquivos.size());

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        for (File arquivo : arquivos) {
            threadPool.execute(new TarefaLeiutaParalelaNotas(arquivo, totaisPorDestinatario,
                    barraDeProgresso));
//            Thread thread = new Thread(new TarefaLeiutaParalelaNotas(arquivo, totaisPorDestinatario,
//                    barraDeProgresso));
//            thread.start();
           // System.out.println(thread.getName());
        }

        List<RelatorioNF> relatorioNFs = conversor.converte(totaisPorDestinatario);

        escritor.escreve(relatorioNFs, Path.of("src/main/resources/relatorio/relatorio.csv"));
    }




    private Set<File> listFilesFrom(String diretorio) {
        return Stream.of(requireNonNull(new File(diretorio).listFiles()))
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }
}
