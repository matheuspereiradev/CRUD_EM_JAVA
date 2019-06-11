/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.telas;

import javax.swing.*;
import javax.swing.JTextField;
import sistema.entidades.Cargo;

/**
 *
 * @author Matheus
 */
public class CadastrarFuncionario extends JPanel {

    private JLabel lbNome, lbSobrenome, lbDataDeNascimento, lbEmail, lbCargo, lbSalario, lbTitulo;
    private JButton btSalvar;
    private JTextField tfNome, tfSobrenome, tfDataDeNascimento, tfEmail, tfSalario;
    private JComboBox<Cargo> cbCargos;

    public CadastrarFuncionario() {
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes() {

    }

    private void iniciarEventos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
