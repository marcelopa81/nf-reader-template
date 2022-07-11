package processador;

import io.EnviaEmail;

public class TratadorDeExcecaoHandler implements Thread.UncaughtExceptionHandler {
    private final EnviaEmail emailErro = new EnviaEmail();
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Ocorreu uma excecão da Therad: " + t.getName() + "," + "erro: " +
                e.getMessage());
        emailErro.enviaEmail();

    }
}
