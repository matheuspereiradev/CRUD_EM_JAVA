/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import sistema.entidades.Cargo;
import sistema.principal.Conexao;
import sistema.principal.Navegador;

/**
 *
 * @author Matheus
 */
public class CadastrarCargo extends JPanel {

    JLabel jl_msg;
    JTextField tf_nome_cargo;
    JButton bt_adicionar;

    public CadastrarCargo() {
        iniciarComponentes();
        criarEventos();
    }

    private void iniciarComponentes() {
        setLayout(null);

        jl_msg = new JLabel("Nome do cargo");
        tf_nome_cargo = new JTextField();
        bt_adicionar = new JButton("Adicionar");

        jl_msg.setBounds(150, 100, 300, 20);
        tf_nome_cargo.setBounds(150, 120, 400, 30);
        bt_adicionar.setBounds(250, 250, 200, 40);

        add(jl_msg);
        add(tf_nome_cargo);
        add(bt_adicionar);

        setVisible(true);

    }

    private void criarEventos() {
        bt_adicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cargo novoCargo = new Cargo();
                novoCargo.setNome(tf_nome_cargo.getText());

                sqlInserirCargo(novoCargo);
            }

        });
    }

    private void sqlInserirCargo(Cargo ncargo) {
        if (tf_nome_cargo.getText().length() < 2) {
            JOptionPane.showMessageDialog(null, "Nome digitado é muito curto");
        }

        //conecção
        Connection conexao;
        //insrução sql
        Statement instrucaoSQL;

        ResultSet resultado;

        try {
            //conectando ao banco de dados
            conexao = DriverManager.getConnection(Conexao.servidor, Conexao.usuario, Conexao.senha);
            //criando a conexão
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            instrucaoSQL.executeUpdate("INSERT INTO cargos (nome_cargo) VALUES ('" + ncargo.getNome() + "');");

            JOptionPane.showMessageDialog(null, "Sucesso ao incluir cargo");

            Navegador.menu();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO AO ADICIONAR");
            Logger.getLogger(CadastrarCargo.class.getName()).log(Level.SEVERE, null, erro);
        }

    }

}
