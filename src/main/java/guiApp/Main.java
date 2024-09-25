/* Essa clase realiza a inicialização da interface, assim como trata possíveis
* exceções que podem ocorrer durante o fluxo de execução do programa. */

package guiApp;

import java.awt.HeadlessException;

public class Main {
    public static void main(String[] args) {
        try {
            (new MainWindow()).initInterface();
        } catch (HeadlessException ex) {
            System.out.println("Exceção do tipo HeadLessException capturada: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exceção genérica capturada: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
