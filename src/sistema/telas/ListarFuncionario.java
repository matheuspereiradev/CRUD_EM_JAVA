/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.telas;

import java.awt.Font;
import javax.swing.*;
import sistema.entidades.*;

/**
 *
 * @author Matheus
 */
public class ListarFuncionario extends JPanel {

    private JButton btPesquisar, btEditar, btExcluir;
    private JTextField tfNomeCargoPesquisar;
    private JLabel lbTitulo, lbNomeFuncionario;
    private JList<Funcionario> listFuncionarios;
    private DefaultListModel<Funcionario> modelListaFuncionario = new DefaultListModel();
    private Funcionario funcionario;

    public ListarFuncionario() {
        iniciarComponentes();
        iniciarEventos();
    }

    public void iniciarComponentes() {
        setLayout(null);

        btPesquisar = new JButton("Pesquisar");
        btEditar = new JButton("Editar");
        btEditar.setEnabled(false);
        btExcluir = new JButton("Excluir");
        btExcluir.setEnabled(false);
        tfNomeCargoPesquisar = new JTextField();
        lbTitulo = new JLabel("Listar funcionários");
        lbTitulo.setFont(new Font(lbTitulo.getFont().getName(), Font.PLAIN, 30));
        lbNomeFuncionario = new JLabel("Nome do funcionário");
        listFuncionarios = new JList<Funcionario>();

        lbTitulo.setBounds(250, 20, 660, 40);
        lbNomeFuncionario.setBounds(100, 120, 400, 20);
        tfNomeCargoPesquisar.setBounds(100, 140, 400, 30);
        btPesquisar.setBounds(510, 140, 130, 30);
        listFuncionarios.setBounds(100, 200, 400, 300);
        btEditar.setBounds(510, 435, 100, 30);
        btExcluir.setBounds(510, 470, 100, 30);

        add(btPesquisar);
        add(btEditar);
        add(btExcluir);
        add(tfNomeCargoPesquisar);
        add(lbTitulo);
        add(lbNomeFuncionario);
        add(listFuncionarios);

        setVisible(true);

    }

    public void iniciarEventos() {

    }
}
