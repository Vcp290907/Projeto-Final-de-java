package controllers;

import views.Funcionarios;
import views.ConsultaFuncionarios;
import views.CadastroFuncionarios;

public class FuncionarioController {
    public void iniciar() {
        System.out.println("Iniciando tela de Funcionários através do controlador");
        Funcionarios view = new Funcionarios(this);
        view.iniciar();
    }

    public void iniciarConsulta() {
        System.out.println("Iniciando tela de Consulta de Funcionários");
        ConsultaFuncionarios view = new ConsultaFuncionarios();
        view.iniciar();
    }

    public void iniciarCadastro() {
        System.out.println("Iniciando tela de Cadastro de Funcionários");
        CadastroFuncionarios view = new CadastroFuncionarios();
        view.iniciar();
    }
}