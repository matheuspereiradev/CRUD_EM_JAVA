/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.principal;

import javax.swing.*;

/**
 *
 * @author Matheus
 */
public class Sistema {

    public static JPanel painel;
    public static JFrame frame;

    public static void main(String[] args) {
        iniciarComponentes();
    }

    public static void iniciarComponentes() {

        frame = new JFrame("SISTEMA");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }

}
