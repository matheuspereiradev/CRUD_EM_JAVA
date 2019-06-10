/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.principal;

import sistema.entidades.*;
import sistema.telas.*;

/**
 *
 * @author Matheus
 */
public class Navegador {

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

    private static void atualizarTela() {
        Sistema.frame.getContentPane().removeAll();
        Sistema.painel.setVisible(true);
        Sistema.frame.add(Sistema.painel);
        Sistema.frame.setVisible(true);
    }

}
