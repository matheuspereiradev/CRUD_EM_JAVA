/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.telas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import sistema.principal.Navegador;

/**
 *
 * @author Matheus
 */
public class Login extends JPanel {

    JLabel lb_login, lb_senha;
    JButton bt_entrar, bt_fechar;
    JTextField tf_login;
    JPasswordField pf_senha;

    public Login() {
        criarComponentes();
        criarEventos();
    }

    private void criarComponentes() {
        setLayout(null);

        JLabel jl_nome_empresa = new JLabel("SA COMPANY");
        jl_nome_empresa.setFont(new Font(jl_nome_empresa.getFont().getName(), Font.BOLD, 55));

        lb_login = new JLabel("Login");
        lb_senha = new JLabel("Senha");
        bt_entrar = new JButton("Entrar");
        bt_fechar = new JButton("Fechar");
        tf_login = new JTextField();
        pf_senha = new JPasswordField();

        jl_nome_empresa.setBounds(170, 100, 660, 50);
        lb_login.setBounds(250, 200, 200, 20);
        tf_login.setBounds(250, 220, 200, 30);
        lb_senha.setBounds(250, 250, 200, 20);
        pf_senha.setBounds(250, 270, 200, 30);
        bt_entrar.setBounds(240, 350, 100, 40);
        bt_fechar.setBounds(360, 350, 100, 40);

        add(jl_nome_empresa);
        add(lb_login);
        add(lb_senha);
        add(bt_entrar);
        add(bt_fechar);
        add(tf_login);
        add(pf_senha);

        setVisible(true);
    }

    private void criarEventos() {
        bt_entrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tf_login.getText().equals("adm") || pf_senha.getPassword().equals("adm")) {
                    Navegador.menu();
                } else {

                    JOptionPane.showMessageDialog(null, "Login ou senha inválidos");
                }
            }
        });
        bt_fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
