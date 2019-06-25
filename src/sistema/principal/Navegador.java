/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import sistema.entidades.*;
import sistema.telas.*;

/**
 *
 * @author Matheus
 */
public class Navegador {

    public static boolean menuConstruido, menuHabilitado;
    private static JMenuBar barraMenu;
    private static JMenu menuArquivos, menuFuncionarios, menuCargos, menuRelatorios;
    private static JMenuItem miSair, miHome, miCadastrarFuncionario, miListarFuncionarios,
            miCadastrarCargo, miListarCargos, miRelarioDeCargos, miRelatorioDeSalarios;

    public static void login() {
        Sistema.painel = new Login();
        Sistema.frame.setTitle("Login");
        atualizarTela();
    }

    public static void menu() {
        Sistema.painel = new Menu();
        Sistema.frame.setTitle("Menu");
        atualizarTela();
    }

    public static void cadastrarCargo() {
        Sistema.painel = new CadastrarCargo();
        Sistema.frame.setTitle("Cadastrar Cargo");
        atualizarTela();
    }

    public static void listarCargos() {
        Sistema.painel = new ListarCargos();
        Sistema.frame.setTitle("Listar Cargos");
        atualizarTela();
    }

    public static void editarCargo(Cargo cargo) {
        Sistema.painel = new EditarCargo(cargo);
        Sistema.frame.setTitle("Editar cargo");
        atualizarTela();
    }

    public static void cadastrarFuncionario() {
        Sistema.painel = new CadastrarFuncionario();
        Sistema.frame.setTitle("Cadastrar funcionario");
        atualizarTela();
    }

    public static void listarFuncionario() {
        Sistema.painel = new ListarFuncionario();
        Sistema.frame.setTitle("Listar funcionarios");
        atualizarTela();
    }

    public static void editarFuncionario(Funcionario funcionario) {
        Sistema.painel = new EditarFuncionario(funcionario);
        Sistema.frame.setTitle("Editar funcionario");
        atualizarTela();
    }

    private static void atualizarTela() {
        Sistema.frame.getContentPane().removeAll();
        Sistema.painel.setVisible(true);
        Sistema.frame.add(Sistema.painel);
        Sistema.frame.setVisible(true);
    }

    private static void construirMenu() {
        if (!menuConstruido) {
            menuConstruido = true;

            barraMenu = new JMenuBar();

            // menu Arquivo
            menuArquivos = new JMenu("Arquivo");
            barraMenu.add(menuArquivos);
            miHome = new JMenuItem("HOME");
            menuArquivos.add(miHome);
            miSair = new JMenuItem("Sair");
            menuArquivos.add(miSair);

            // menu Funcionários
            menuFuncionarios = new JMenu("Funcionários");
            barraMenu.add(menuFuncionarios);
            miCadastrarFuncionario = new JMenuItem("Cadastrar");
            menuFuncionarios.add(miCadastrarFuncionario);
            miListarFuncionarios = new JMenuItem("Consultar");
            menuFuncionarios.add(miListarFuncionarios);

            // menu Cargos
            menuCargos = new JMenu("Cargos");
            barraMenu.add(menuCargos);
            miCadastrarCargo = new JMenuItem("Cadastrar");
            menuCargos.add(miCadastrarCargo);
            miListarCargos = new JMenuItem("Consultar");
            menuCargos.add(miListarCargos);

            // menu Relatórios
            menuRelatorios = new JMenu("Relatórios");
            barraMenu.add(menuRelatorios);
            miRelarioDeCargos = new JMenuItem("Funcionários por cargos");
            menuRelatorios.add(miRelarioDeCargos);
            miRelatorioDeSalarios = new JMenuItem("Salários dos funcionários");
            menuRelatorios.add(miRelatorioDeSalarios);

            criarEventosMenu();

        }
    }

    public static void habilitaMenu() {
        if (!menuConstruido) {
            construirMenu();
        }
        if (!menuHabilitado) {
            menuHabilitado = true;
            Sistema.frame.setJMenuBar(barraMenu);
        }
    }

    public static void desabilitaMenu() {
        if (menuHabilitado) {
            menuHabilitado = false;
            Sistema.frame.setJMenuBar(null);
        }
    }

    private static void criarEventosMenu() {
        miSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Funcionario
        miCadastrarFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario();
            }
        });
        miListarFuncionarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarFuncionario();
            }
        });

        // Cargos
        miCadastrarCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCargo();
            }
        });
        miListarCargos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCargos();
            }
        });

        miRelarioDeCargos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        miRelatorioDeSalarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        miHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu();
            }
        });
    }

}
