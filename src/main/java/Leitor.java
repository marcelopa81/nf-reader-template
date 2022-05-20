import java.io.File;
import java.util.List;

public interface Leitor<T> {

    List<T> leia(File file, Class<? extends T> tipo);
}
