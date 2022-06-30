package processador;

import dto.RelatorioNF;
import io.EscritorCSV;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class ProcessadorDeArquivos {
    private final EscritorCSV escritor = new EscritorCSV();
    private final RelatorioNFConversor conversor = new RelatorioNFConversor();
    private final List<Future<Map<String, BigDecimal>>> futures = new ArrayList<>();

    public void processaArquivosDo(String diretorio) throws ExecutionException, InterruptedException, TimeoutException {

        Map<String, BigDecimal> totaisPorDestinatario = new ConcurrentHashMap<>();

        Set<File> arquivos = listFilesFrom(diretorio);

        BarraDeProgresso barraDeProgresso = new BarraDeProgresso(arquivos.size());

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        for (File arquivo : arquivos) {
            TarefaLeiutaParalelaNotas tarefaLeiutaParalelaNotas = new TarefaLeiutaParalelaNotas(arquivo, totaisPorDestinatario,
                    barraDeProgresso);
            Future<Map<String, BigDecimal>> futureTotal = threadPool.submit(tarefaLeiutaParalelaNotas);
            futures.add(futureTotal);
        }

        for (Future<Map<String, BigDecimal>> future : futures) {
            Map<String, BigDecimal> futuro = future.get();
            totaisPorDestinatario.putAll(futuro);
        }

        threadPool.shutdown();

        List<RelatorioNF> relatorioNFs = conversor.converte(totaisPorDestinatario);

        escritor.escreve(relatorioNFs, Path.of("src/main/resources/relatorio/relatorio.csv"));
    }




    private Set<File> listFilesFrom(String diretorio) {
        return Stream.of(requireNonNull(new File(diretorio).listFiles()))
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }
}
