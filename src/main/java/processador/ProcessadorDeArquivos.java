package processador;

import dto.NotaFiscalItem;
import dto.RelatorioNF;
import io.EscritorCSV;
import io.LeitorCSV;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class ProcessadorDeArquivos {

    private final LeitorCSV<NotaFiscalItem> leitor = new LeitorCSV<>();
    private final EscritorCSV escritor = new EscritorCSV();
    private final RelatorioNFConversor conversor = new RelatorioNFConversor();

    public void processaArquivosDo(String diretorio) {

        Map<String, BigDecimal> totaisPorDestinatario = new HashMap<>();

        Set<File> arquivos = listFilesFrom(diretorio);

        BarraDeProgresso barraDeProgresso = new BarraDeProgresso(arquivos.size());

        for (File arquivo : arquivos) {

            checaSeEhCSV(arquivo);

            List<NotaFiscalItem> notaFiscalItems = leitor.leia(arquivo, NotaFiscalItem.class);

            agrupaTotal(notaFiscalItems, totaisPorDestinatario);

            barraDeProgresso.incrementa();
        }

        List<RelatorioNF> relatorioNFs = conversor.converte(totaisPorDestinatario);

        escritor.escreve(relatorioNFs, Path.of("src/main/resources/relatorio/relatorio.csv"));
    }

    private void agrupaTotal(List<NotaFiscalItem> notaFiscalItems, Map<String, BigDecimal> totaisPorDestinatario) {

        notaFiscalItems.forEach(nf -> {

            BigDecimal valorAnterior = totaisPorDestinatario.putIfAbsent(nf.getNomeDestinatario(), nf.getValorTotal());

            if (Objects.nonNull(valorAnterior)) {
                totaisPorDestinatario.put(nf.getNomeDestinatario(), valorAnterior.add(nf.getValorTotal()));
            }
        });
    }

    private void checaSeEhCSV(File arquivo) {

        var nomeDoArquivo = arquivo.getName();
        if (!nomeDoArquivo.endsWith(".csv")) {
            throw new IllegalArgumentException("Formato inválido do arquivo: " + nomeDoArquivo);
        }
    }


    private Set<File> listFilesFrom(String diretorio) {
        return Stream.of(requireNonNull(new File(diretorio).listFiles()))
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }
}
