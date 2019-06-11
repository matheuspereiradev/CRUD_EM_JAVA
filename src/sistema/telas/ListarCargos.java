/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sistema.entidades.Cargo;
import sistema.principal.Conexao;
import sistema.principal.Navegador;

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
        iniciarLista();
    }

    private void criarComponentes() {
        setLayout(null);

        lb_titulo = new JLabel("Consultar cargos", JLabel.CENTER);
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
        lb_cargo.setBounds(100, 120, 400, 20);
        tf_campo_cargo.setBounds(100, 140, 400, 30);
        bt_pesquisar.setBounds(510, 140, 130, 30);
        listaCargos.setBounds(100, 200, 400, 300);
        bt_editar.setBounds(510, 435, 100, 30);
        bt_excluir.setBounds(510, 470, 100, 30);

        add(lb_titulo);
        add(lb_cargo);
        add(tf_campo_cargo);
        add(bt_pesquisar);
        add(listaCargos);
        add(bt_editar);
        add(bt_excluir);

        setVisible(true);
    }

    private void iniciarLista() {
        // conexão
        Connection conexao;
        // instrucao SQL
        Statement instrucaoSQL;
        // resultados
        ResultSet resultados;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(Conexao.servidor, Conexao.usuario, Conexao.senha);

            // criando a instrução SQL
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = instrucaoSQL.executeQuery("SELECT * FROM cargos ORDER BY nome_cargo ASC");

            listar_cargos_model.clear();

            while (resultados.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(resultados.getInt("id"));
                cargo.setNome(resultados.getString("nome_cargo"));

                listar_cargos_model.addElement(cargo);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao iniciar os cargos.");
            Logger.getLogger(ListarCargos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void criarEventos() {
        bt_pesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlPesquisarCargo(tf_campo_cargo.getText());
            }
        });
        bt_editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navegador.editarCargo(cargoAtual);
            }
        });
        bt_excluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlExcluirCargo();
            }
        });
        listaCargos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                cargoAtual = listaCargos.getSelectedValue();
                if (cargoAtual == null) {
                    bt_editar.setEnabled(false);
                    bt_excluir.setEnabled(false);
                } else {
                    bt_editar.setEnabled(true);
                    bt_excluir.setEnabled(true);
                }
            }
        });

    }

    private void sqlPesquisarCargo(String nome) {
        // conexão
        Connection conexao;
        // instrucao SQL
        Statement instrucaoSQL;
        // resultados
        ResultSet resultados;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(Conexao.servidor, Conexao.usuario, Conexao.senha);

            // criando a instrução SQL
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = instrucaoSQL.executeQuery("SELECT * FROM cargos WHERE nome_cargo like '%" + nome + "%' ORDER BY nome_cargo ASC");

            listar_cargos_model.clear();

            while (resultados.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(resultados.getInt("id"));
                cargo.setNome(resultados.getString("nome_cargo"));

                listar_cargos_model.addElement(cargo);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar os Cargos.");
            Logger.getLogger(ListarCargos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sqlExcluirCargo() {

        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o cargo de " + cargoAtual.getNome() + "?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            // conexão
            Connection conexao;
            // instrucao SQL
            Statement instrucaoSQL;
            // resultados
            ResultSet resultados;

            try {
                // conectando ao banco de dados
                conexao = DriverManager.getConnection(Conexao.servidor, Conexao.usuario, Conexao.senha);

                instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                instrucaoSQL.executeUpdate("DELETE cargos WHERE id=" + cargoAtual.getId() + "");

                JOptionPane.showMessageDialog(null, "Cargo deletado com sucesso");
                listar_cargos_model.clear();
                iniciarLista();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao deletar cargo");
                Logger.getLogger(ListarCargos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
