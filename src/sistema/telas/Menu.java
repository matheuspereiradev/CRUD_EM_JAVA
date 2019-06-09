/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.telas;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Matheus
 */
public class Menu extends JPanel {

    JLabel lb_escolha;

    public Menu() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        setLayout(null);

        lb_escolha = new JLabel("Escolha no menu superior a opção", JLabel.CENTER);

        lb_escolha.setBounds(100, 250, 500, 50);

        add(lb_escolha);

        setVisible(true);

    }
}
