package unlar.edu.ar;

import unlar.edu.ar.service.BibliotecaService;
import unlar.edu.ar.ui.MenuUI;

public class Main {
    public static void main(String[] args) {
        // 1. Creamos el "cerebro" (el servicio)
        BibliotecaService service = new BibliotecaService();

        // 2. Creamos la "cara" (el menú) y le pasamos el cerebro
        MenuUI menu = new MenuUI(service);

        // 3. Arrancamos el programa
        menu.mostrarMenu();
    }
}