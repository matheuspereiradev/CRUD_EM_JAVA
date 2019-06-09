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

}
