package processador;

import io.EnviaEmail;

public class TratadorDeExcecaoHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {

        EnviaEmail enviaEmail = new EnviaEmail();

        System.out.println("Ocorreu uma excecão da Therad: " + t.getName() + "," + "erro: " + e.getMessage());

    }
}
