/* Essa clase realiza a inicialização da interface, assim como trata possíveis
* exceções que podem ocorrer durante o fluxo de execução do programa. */

package guiApp;

import lombok.extern.log4j.Log4j2;

import java.awt.HeadlessException;

@Log4j2
public class Main {
    public static void main(String[] args) {
        try {
            (new MainWindow()).initInterface();
        } catch (HeadlessException ex) {
           log.error("Exceção do tipo HeadLessException capturada: " + ex);
        } catch (Exception ex) {
            log.error("Exceção genérica capturada: " + ex.getMessage());
        }
    }
}
