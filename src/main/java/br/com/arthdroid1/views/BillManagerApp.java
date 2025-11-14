package br.com.arthdroid1.views;

import br.com.arthdroid1.controllers.BillController;

public class BillManagerApp {
    
    public static void main(String[] args) {
        BillController controller = new BillController();

        controller.startMenu();
    }
}