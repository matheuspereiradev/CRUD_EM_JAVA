/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sistema.principal.Navegador;

/**
 *
 * @author Matheus
 */
public class Menu extends JPanel {

    JLabel lb_escolha;

    JButton bt_cad_cargo, bt_lista_cargos, bt_cad_func, bt_list_func;

    public Menu() {
        iniciarComponentes();
        iniciarEventos();
        Navegador.habilitaMenu();
    }

    private void iniciarComponentes() {

        setLayout(null);

        lb_escolha = new JLabel("Escolha no menu superior a opção", JLabel.CENTER);
        bt_cad_cargo = new JButton("Cadastrar novo cargo");
        bt_lista_cargos = new JButton("Lista de cargos");
        bt_cad_func = new JButton("Adicionar novo funcionario");
        bt_list_func = new JButton("Listar funcionários");

        lb_escolha.setBounds(100, 30, 500, 50);
        bt_cad_cargo.setBounds(100, 100, 200, 100);
        bt_lista_cargos.setBounds(100, 210, 200, 100);
        bt_cad_func.setBounds(315, 100, 200, 100);
        bt_list_func.setBounds(315, 210, 200, 100);

        add(lb_escolha);
        add(bt_cad_cargo);
        add(bt_lista_cargos);
        add(bt_cad_func);
        add(bt_list_func);

        setVisible(true);

    }

    private void iniciarEventos() {
        bt_cad_cargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navegador.cadastrarCargo();
            }
        });
        bt_lista_cargos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navegador.listarCargos();
            }
        });
        bt_cad_func.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navegador.cadastrarFuncionario();
            }
        });
        bt_list_func.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navegador.listarFuncionario();
            }
        });
    }
}
