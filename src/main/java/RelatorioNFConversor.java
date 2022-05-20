import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioNFConversor {

    public List<RelatorioNF> converte(Map<String, BigDecimal> totaisPorDestinatario) {

        return totaisPorDestinatario.entrySet()
                .stream()
                .map((entry) -> new RelatorioNF(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
