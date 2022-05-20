public class BarraDeProgresso {

    private final int TAMANHO_MAXIMO_DA_BARRA = 100;
    private final int totalDeArquivos;

    private int arquivosProcessados = 0;

    public BarraDeProgresso(int totalDeArquivos) {
        this.totalDeArquivos = totalDeArquivos;
    }


    public void incrementa() {
        if (arquivosProcessados < totalDeArquivos) {
            arquivosProcessados++;
        }

        vizualizar();
    }

    public void vizualizar() {

        double coeficienteProcessado = (double) arquivosProcessados / totalDeArquivos;

        double porcentagem = coeficienteProcessado * 100;

        int tamanhoAtual = (int) (TAMANHO_MAXIMO_DA_BARRA * coeficienteProcessado);

        StringBuilder barra = new StringBuilder();
        for (int i = 0; i < TAMANHO_MAXIMO_DA_BARRA; i++) {

            if (tamanhoAtual > i) {
                barra.append("#");
            } else {
                barra.append(".");
            }
        }

        barra.append(" - ")
                .append(porcentagem)
                .append("%");

        System.out.println(barra);
    }
}
