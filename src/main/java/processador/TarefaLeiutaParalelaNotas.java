package processador;

import dto.NotaFiscalItem;
import io.LeitorCSV;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TarefaLeiutaParalelaNotas implements Runnable {

    private File arquivo;
    private Map<String, BigDecimal> totaisPorDestinatario;
    private BarraDeProgresso barraDeProgresso;
    private final LeitorCSV<NotaFiscalItem> leitor = new LeitorCSV<>();

    public TarefaLeiutaParalelaNotas(File arquivo, Map<String, BigDecimal> totaisPorDestinatario,
                                     BarraDeProgresso barraDeProgresso) {
        this.arquivo = arquivo;
        this.totaisPorDestinatario = totaisPorDestinatario;
        this.barraDeProgresso = barraDeProgresso;
    }

    @Override
    public void run() {
        checaSeEhCSV(arquivo);

        List<NotaFiscalItem> notaFiscalItems = leitor.leia(arquivo, NotaFiscalItem.class);

        agrupaTotal(notaFiscalItems, totaisPorDestinatario);

        barraDeProgresso.incrementa();
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
}
