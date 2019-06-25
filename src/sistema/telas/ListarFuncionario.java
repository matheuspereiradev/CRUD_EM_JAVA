package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sistema.entidades.*;
import sistema.principal.Conexao;
import sistema.principal.Navegador;

/**
 *
 * @author Matheus
 */
public class ListarFuncionario extends JPanel {

    private Funcionario funcionarioAtual;
    private JButton btPesquisar, btEditar, btExcluir;
    private JTextField tfNomeCargoPesquisar;
    private JLabel lbTitulo, lbNomeFuncionario;
    private JList<Funcionario> listFuncionarios;
    private DefaultListModel<Funcionario> modelListaFuncionario;

    public ListarFuncionario() {
        iniciarComponentes();
        iniciarEventos();
        pesquisarFuncionarios();

    }

    private void iniciarComponentes() {
        setLayout(null);

        btPesquisar = new JButton("Pesquisar");
        btEditar = new JButton("Editar");
        btEditar.setEnabled(false);
        btExcluir = new JButton("Excluir");
        btExcluir.setEnabled(false);
        tfNomeCargoPesquisar = new JTextField();
        lbTitulo = new JLabel("Listar funcionários", JLabel.CENTER);
        lbTitulo.setFont(new Font(lbTitulo.getFont().getName(), Font.PLAIN, 30));
        lbNomeFuncionario = new JLabel("Nome do funcionário");

        modelListaFuncionario = new DefaultListModel();
        listFuncionarios = new JList<Funcionario>();
        listFuncionarios.setModel(modelListaFuncionario);
        listFuncionarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        lbTitulo.setBounds(20, 20, 660, 40);
        lbNomeFuncionario.setBounds(100, 120, 400, 20);
        tfNomeCargoPesquisar.setBounds(100, 140, 400, 30);
        btPesquisar.setBounds(510, 140, 130, 30);
        listFuncionarios.setBounds(50, 200, 600, 300);
        btEditar.setBounds(655, 435, 100, 30);
        btExcluir.setBounds(655, 470, 100, 30);

        add(btPesquisar);
        add(btEditar);
        add(btExcluir);
        add(tfNomeCargoPesquisar);
        add(lbTitulo);
        add(lbNomeFuncionario);
        add(listFuncionarios);

        setVisible(true);

    }

    private void iniciarEventos() {

        listFuncionarios.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                funcionarioAtual = listFuncionarios.getSelectedValue();

                if (funcionarioAtual == null) {
                    btEditar.setEnabled(false);
                    btExcluir.setEnabled(false);
                } else {
                    btEditar.setEnabled(true);
                    btExcluir.setEnabled(true);
                }

            }
        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int conf = JOptionPane.showConfirmDialog(null, "deseja realmente excluir " + funcionarioAtual.getNome() + "?", "excluir", JOptionPane.YES_NO_OPTION);
                if (JOptionPane.YES_OPTION == conf) {
                    excluirFuncionario();
                    pesquisarFuncionarios();
                }

            }
        });
        btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navegador.editarFuncionario(funcionarioAtual);
            }
        });
    }

    private void pesquisarFuncionarios() {
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
            resultados = instrucaoSQL.executeQuery("SELECT cargos.id as 'idCargo', cargos.nome_cargo as 'nomeCargo',funcionario.id as 'id',"
                    + "funcionario.nome as 'nome',funcionario.sobrenome as 'sobrenome',funcionario.data_nascimento as 'data',funcionario.email as 'email',"
                    + "funcionario.cargo as 'idCargo',funcionario.salario as 'salario' FROM cargos,funcionario WHERE cargos.id=funcionario.cargo ORDER BY funcionario.nome ASC");

            modelListaFuncionario.clear();

            while (resultados.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultados.getInt("id"));
                funcionario.setNome(resultados.getString("nome"));
                funcionario.setSobrenome(resultados.getString("sobrenome"));
                funcionario.setData_nascimento(resultados.getString("data"));
                funcionario.setEmail(resultados.getString("email"));
                funcionario.setCargo(resultados.getInt("idCargo"));
                funcionario.setNomeCargo(resultados.getString("nomeCargo"));
                funcionario.setSalario(resultados.getFloat("salario"));

                modelListaFuncionario.addElement(funcionario);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao iniciar os funcionarios.");
            Logger.getLogger(ListarCargos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void excluirFuncionario() {
        // conexão
        Connection conexao;
        // instrucao SQL
        PreparedStatement instrucaoSQL;
        // resultados
        ResultSet resultados;

        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(Conexao.servidor, Conexao.usuario, Conexao.senha);

            // criando a instrução SQL
            String sql = "DELETE FROM funcionario";
            sql = sql + " WHERE id = ?";

            instrucaoSQL = conexao.prepareStatement(sql);
            instrucaoSQL.setInt(1, funcionarioAtual.getId());
            instrucaoSQL.executeUpdate();

            JOptionPane.showMessageDialog(null, "Funcionario apagado com sucesso");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao iniciar os funcionarios.");
            Logger.getLogger(ListarCargos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
