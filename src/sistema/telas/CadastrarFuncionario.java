/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.telas;

import java.util.Calendar;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import sistema.entidades.Cargo;
import sistema.entidades.Funcionario;
import sistema.principal.Conexao;
import sistema.principal.Navegador;

/**
 *
 * @author Matheus
 */
public class CadastrarFuncionario extends JPanel {

    private JLabel lbNome, lbSobrenome, lbDataDeNascimento, lbEmail, lbCargo, lbSalario, lbTitulo;
    private JButton btSalvar, btCancelar;
    private JTextField tfNome, tfSobrenome, tfEmail;
    private JFormattedTextField tfDataDeNascimento, tfSalario;
    private JComboBox<Cargo> cbCargos;

    public CadastrarFuncionario() {
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
        tfNome = new JTextField();
        tfSobrenome = new JTextField();
        tfDataDeNascimento = new JFormattedTextField();
        tfEmail = new JTextField();
        DecimalFormat df = new DecimalFormat("###0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        tfSalario = new JFormattedTextField(df);
        tfSalario.setText("0.00");
        cbCargos = new JComboBox<Cargo>();

        try {
            MaskFormatter mask = new MaskFormatter("##/##/####");
            mask.install(tfDataDeNascimento);
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

        pesquisarCargos();

        setVisible(true);

    }

    private void iniciarEventos() {
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navegador.menu();
            }
        });
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(tfNome.getText());
                funcionario.setSobrenome(tfSobrenome.getText());
                funcionario.setEmail(tfEmail.getText());
                funcionario.setData_nascimento(tfDataDeNascimento.getText());
                Cargo cargoFunc = new Cargo();
                cargoFunc = (Cargo) cbCargos.getSelectedItem();
                funcionario.setCargo(cargoFunc.getId());
                funcionario.setSalario(Double.parseDouble(tfSalario.getText().replace(",", ".")));
                salvarFuncionario(funcionario);
            }
        });
    }

    private void pesquisarCargos() {
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

            cbCargos.removeAll();
            while (resultados.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(resultados.getInt("id"));
                cargo.setNome(resultados.getString("nome_cargo"));

                cbCargos.addItem(cargo);
            }
            cbCargos.updateUI();

            conexao.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar os Cargos.");
            Logger.getLogger(ListarCargos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void salvarFuncionario(Funcionario funcionario) {

        if (tfNome.getText().length() < 3) {
            JOptionPane.showMessageDialog(null, "O nome digitado é muito curto");
            return;
        }

        if (tfSobrenome.getText().length() < 3) {
            JOptionPane.showMessageDialog(null, "O sobrenome digitado é muito curto");
            return;
        }

        if (Double.parseDouble(tfSalario.getText().replace(",", ".")) < 100) {
            JOptionPane.showMessageDialog(null, "O salário digitado é muito baixo, verifique");
            return;
        }

        Boolean emailValido = false;
        String ePatern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern p = Pattern.compile(ePatern);
        Matcher m = p.matcher(tfEmail.getText());
        emailValido = m.matches();

        if (!emailValido) {
            JOptionPane.showMessageDialog(null, "Insira um email válido");
            return;
        }

        // conexão
        Connection conexao;

        //intruçao sql
        PreparedStatement instrucaoSQL;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(Conexao.servidor, Conexao.usuario, Conexao.senha);

            // criando a instrução SQL
            //instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String comando = "INSERT INTO funcionario (nome,sobrenome,data_nascimento,email,cargo,salario)";
            comando = comando + " VALUES (?,?,?,?,?,?)";
            instrucaoSQL = conexao.prepareStatement(comando);
            instrucaoSQL.setString(1, funcionario.getNome());
            instrucaoSQL.setString(2, funcionario.getSobrenome());
            instrucaoSQL.setString(3, funcionario.getData_nascimento());
            instrucaoSQL.setString(4, funcionario.getEmail());
            instrucaoSQL.setInt(5, funcionario.getCargo());
            instrucaoSQL.setDouble(6, funcionario.getSalario());
            instrucaoSQL.executeUpdate();

            JOptionPane.showMessageDialog(null, "Adicionado com sucesso");
            conexao.close();
            Navegador.menu();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao adicionar o funcionario.");
            Logger.getLogger(ListarCargos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
