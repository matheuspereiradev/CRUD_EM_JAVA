/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.telas;

import javax.swing.*;
import java.awt.*;
import sistema.entidades.Cargo;

/**
 *
 * @author Matheus
 */
public class ListarCargos extends JPanel {

    Cargo cargoAtual;
    JLabel lb_titulo, lb_cargo;
    JTextField tf_campo_cargo;
    JButton bt_editar, bt_excluir, bt_pesquisar;
    DefaultListModel<Cargo> listar_cargos_model = new DefaultListModel();
    JList<Cargo> listaCargos;

    public ListarCargos() {
        criarComponentes();
        criarEventos();
    }

    private void criarEventos() {

    }

    private void criarComponentes() {
        setLayout(null);

        lb_titulo = new JLabel("Consulta cargos", JLabel.CENTER);
        lb_titulo.setFont(new Font(lb_titulo.getFont().getName(), Font.PLAIN, 30));
        lb_cargo = new JLabel("Nome do cargo", JLabel.LEFT);
        tf_campo_cargo = new JTextField();
        bt_pesquisar = new JButton("Pesquisar");
        bt_excluir = new JButton("Excluir");
        bt_excluir.setEnabled(false);
        bt_editar = new JButton("Editar");
        bt_editar.setEnabled(false);

        listar_cargos_model = new DefaultListModel();
        listaCargos = new JList();
        listaCargos.setModel(listar_cargos_model);
        listaCargos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        lb_titulo.setBounds(20, 20, 660, 40);
        lb_cargo.setBounds(150, 120, 400, 20);
        tf_campo_cargo.setBounds(150, 140, 400, 20);
        bt_pesquisar.setBounds(560, 140, 130, 40);
        listaCargos.setBounds(150, 200, 400, 240);
        bt_editar.setBounds(560, 360, 120, 40);
        bt_excluir.setBounds(560, 400, 130, 40);

        add(lb_titulo);
        add(lb_cargo);
        add(tf_campo_cargo);
        add(bt_pesquisar);
        add(listaCargos);
        add(bt_editar);
        add(bt_excluir);

        setVisible(true);
    }

}
