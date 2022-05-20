import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class ProcessadorDeArquivos {

    private final LeitorCSV<NotaFiscalItem> leitor = new LeitorCSV<>();

    public void processaArquivosDo(String diretorio) {

        Map<String, BigDecimal> totaisPorDestinatario = new HashMap<>();

        Set<File> arquivos = listFilesFrom(diretorio);

        BarraDeProgresso barraDeProgresso = new BarraDeProgresso(arquivos.size());

        for (File arquivo : arquivos) {

            checaSeEhCSV(arquivo);

            List<NotaFiscalItem> notaFiscalItems = leitor.leia(arquivo, NotaFiscalItem.class);

            totaisPorDestinatario.putAll(agrupaTotal(notaFiscalItems));

            barraDeProgresso.incrementa();
        }


        System.out.println(totaisPorDestinatario);
    }

    private Map<String, BigDecimal> agrupaTotal(List<NotaFiscalItem> notaFiscalItems) {
        Map<String, BigDecimal> totalPorDestinatario = new HashMap<>();

        notaFiscalItems.forEach(nf -> {

            BigDecimal valorAnterior = totalPorDestinatario.putIfAbsent(nf.getNomeDestinatario(), nf.getValorTotal());

            if (Objects.nonNull(valorAnterior)) {
                totalPorDestinatario.put(nf.getNomeDestinatario(), valorAnterior.add(nf.getValorTotal()));
            }
        });

        return totalPorDestinatario;
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
