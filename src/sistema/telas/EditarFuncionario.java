/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.telas;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import sistema.entidades.*;

/**
 *
 * @author Matheus
 */
public class EditarFuncionario extends JPanel {

    private JLabel lbNome, lbSobrenome, lbDataDeNascimento, lbEmail, lbCargo, lbSalario, lbTitulo;
    private JButton btSalvar, btCancelar;
    private JTextField tfNome, tfSobrenome, tfEmail;
    private JFormattedTextField tfDataDeNascimento, tfSalario;
    private JComboBox<Cargo> cbCargos;
    Funcionario funcionario;

    public EditarFuncionario(Funcionario f) {
        funcionario = f;
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes() {
        setLayout(null);
        lbNome = new JLabel("Nome");
        lbSobrenome = new JLabel("Sobrenome");
        lbDataDeNascimento = new JLabel("Data de nascimento");
        lbEmail = new JLabel("E-mail");
        lbCargo = new JLabel("Cargo");
        lbSalario = new JLabel("Salario");
        lbTitulo = new JLabel("Cadastrar funcionário");
        lbTitulo.setFont(new Font(lbTitulo.getFont().getName(), Font.BOLD, 38));
        btSalvar = new JButton("Salvar");
        btCancelar = new JButton("Cancelar");
        tfNome = new JTextField(funcionario.getNome());
        tfSobrenome = new JTextField(funcionario.getSobrenome());
        tfDataDeNascimento = new JFormattedTextField();

        tfEmail = new JTextField(funcionario.getEmail());
        DecimalFormat df = new DecimalFormat("###0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        tfSalario = new JFormattedTextField(df);
        tfSalario.setText(String.valueOf(funcionario.getSalario()));
        cbCargos = new JComboBox<Cargo>();

        try {
            MaskFormatter mask = new MaskFormatter("##/##/####");
            mask.install(tfDataDeNascimento);
            tfDataDeNascimento.setText(funcionario.getData_nascimento());
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }

        lbTitulo.setBounds(200, 25, 500, 40);
        lbNome.setBounds(120, 80, 40, 20);
        tfNome.setBounds(120, 100, 500, 25);
        lbSobrenome.setBounds(120, 124, 80, 20);
        tfSobrenome.setBounds(120, 145, 500, 25);
        lbDataDeNascimento.setBounds(120, 175, 150, 20);
        tfDataDeNascimento.setBounds(120, 195, 500, 25);
        lbEmail.setBounds(120, 220, 90, 20);
        tfEmail.setBounds(120, 240, 500, 25);
        lbCargo.setBounds(120, 265, 90, 20);
        cbCargos.setBounds(120, 285, 500, 25);
        lbSalario.setBounds(120, 310, 90, 20);
        tfSalario.setBounds(120, 330, 500, 25);
        btSalvar.setBounds(120, 400, 245, 40);
        btCancelar.setBounds(375, 400, 245, 40);

        add(lbNome);
        add(lbSobrenome);
        add(lbDataDeNascimento);
        add(lbEmail);
        add(lbCargo);
        add(lbSalario);
        add(lbTitulo);
        add(btSalvar);
        add(btCancelar);
        add(tfNome);
        add(tfSobrenome);
        add(tfDataDeNascimento);
        add(tfEmail);
        add(tfSalario);
        add(cbCargos);

        setVisible(true);
    }

    private void iniciarEventos() {

    }
}
