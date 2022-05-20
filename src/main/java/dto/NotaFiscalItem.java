package dto;

import com.opencsv.bean.CsvBindByName;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class NotaFiscalItem {

    @CsvBindByName(column = "CHAVE DE ACESSO")
    private String chaveDeAcesso;

    @CsvBindByName(column = "MODELO")
    private String modelo;

    @CsvBindByName(column = "SÉRIE")
    private String serie;

    @CsvBindByName(column = "NÚMERO")
    private String numero;

    @CsvBindByName(column = "NATUREZA DA OPERAÇÃO")
    private String naturezaDaOperacao;

    @CsvBindByName(column = "DATA EMISSÃO")
    private String dataEmissao;

    @CsvBindByName(column = "CPF/CNPJ Emitente")
    private String cpfCnpjEmitente;

    @CsvBindByName(column = "RAZÃO SOCIAL EMITENTE")
    private String razaoSocialEmitente;

    @CsvBindByName(column = "INSCRIÇÃO ESTADUAL EMITENTE")
    private String inscricaoEstadualEmitente;

    @CsvBindByName(column = "UF EMITENTE")
    private String ufEmitente;

    @CsvBindByName(column = "MUNICÍPIO EMITENTE")
    private String municipioEmitente;

    @CsvBindByName(column = "CNPJ DESTINATÁRIO")
    private String cnpjDestinatario;

    @CsvBindByName(column = "NOME DESTINATÁRIO")
    private String nomeDestinatario;

    @CsvBindByName(column = "UF DESTINATÁRIO")
    private String ufDestinatario;

    @CsvBindByName(column = "INDICADOR IE DESTINATÁRIO")
    private String indicadorIeDestinatario;

    @CsvBindByName(column = "DESTINO DA OPERAÇÃO")
    private String destinoDaOperacao;

    @CsvBindByName(column = "CONSUMIDOR FINAL")
    private String consumidorFinal;

    @CsvBindByName(column = "PRESENÇA DO COMPRADOR")
    private String presencaDoComprador;

    @CsvBindByName(column = "NÚMERO PRODUTO")
    private String numeroProduto;

    @CsvBindByName(column = "DESCRIÇÃO DO PRODUTO/SERVIÇO")
    private String descricaoDoProdutoServico;

    @CsvBindByName(column = "CÓDIGO NCM/SH")
    private String codigoNcmSh;

    @CsvBindByName(column = "NCM/SH (TIPO DE PRODUTO)")
    private String ncmShTipoDeProduto;

    @CsvBindByName(column = "CFOP")
    private String cfop;

    @CsvBindByName(column = "QUANTIDADE")
    private String quantidade;

    @CsvBindByName(column = "UNIDADE")
    private String unidade;

    @CsvBindByName(column = "VALOR UNITÁRIO", locale = "pt-BR")
    private BigDecimal valorUnitario;

    @CsvBindByName(column = "VALOR TOTAL", locale = "pt-BR")
    private BigDecimal valorTotal;

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NotaFiscalItem.class.getSimpleName() + "[", "]")
                .add("chaveDeAcesso='" + chaveDeAcesso + "'")
                .add("valorUnitario=" + valorUnitario)
                .add("valorTotal=" + valorTotal)
                .toString();
    }
}
