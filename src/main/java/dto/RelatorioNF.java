package dto;

import com.opencsv.bean.CsvBindByName;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class RelatorioNF {

    @CsvBindByName
    private String destinatario;

    @CsvBindByName
    private BigDecimal valorTotal;

    public RelatorioNF() {
    }

    public RelatorioNF(String destinatario, BigDecimal valorTotal) {
        this.destinatario = destinatario;
        this.valorTotal = valorTotal;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RelatorioNF.class.getSimpleName() + "[", "]")
                .add("destinatario='" + destinatario + "'")
                .add("valorTotal=" + valorTotal)
                .toString();
    }
}
