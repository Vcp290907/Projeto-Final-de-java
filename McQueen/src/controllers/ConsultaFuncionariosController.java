package controllers;

import java.awt.HeadlessException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.ModelsFuncionarios;
import repository.funcionariosRepository;
import views.ConsultaFuncionarios;

public class ConsultaFuncionariosController {
    private final ConsultaFuncionarios view;
    private final funcionariosRepository repositorio;
    private final JTable tabela;
    private final DefaultTableModel modeloTabela;

    public ConsultaFuncionariosController(ConsultaFuncionarios view, JTable tabela, DefaultTableModel modeloTabela) {
        this.view = view;
        this.repositorio = new funcionariosRepository();
        this.tabela = tabela;
        this.modeloTabela = modeloTabela;
    }

    public void inicializar() {
        System.out.println("Inicializando tela de Consulta de Funcionários através do controlador");
        view.iniciar();
        consultarTodosFuncionarios();
    }

    public void consultarTodosFuncionarios() {
        List<ModelsFuncionarios> funcionarios = repositorio.todosFuncionarios();
        atualizarTabela(funcionarios);
    }

    public void cadastrarFuncionario(ModelsFuncionarios novoFuncionario) {
        try {
            repositorio.cadastroFuncionario(novoFuncionario);
            JOptionPane.showMessageDialog(view, "Funcionário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(view, "Erro ao cadastrar funcionário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void consultarFuncionarios(String filtro, String valor) {
        List<ModelsFuncionarios> funcionarios = repositorio.buscarFuncionarios(filtro, valor);
        if (funcionarios.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nenhum funcionário encontrado com " + filtro + " = " + valor);
        } else {
            atualizarTabela(funcionarios);
        }
    }

    public boolean alterarFuncionario(ModelsFuncionarios funcionario) {
        try {
            repositorio.atualizarFuncionario(funcionario);
            JOptionPane.showMessageDialog(view, "Funcionário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            consultarTodosFuncionarios();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao atualizar funcionário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void deletarFuncionario(int codigo) {
        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Tem certeza que deseja deletar este funcionário?",
                "Confirmar deletação",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            repositorio.deletarFuncionario(codigo);
            JOptionPane.showMessageDialog(view, "Funcionário deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            atualizarTabela(repositorio.todosFuncionarios());
        }
    }

    private void atualizarTabela(List<ModelsFuncionarios> funcionarios) {
        modeloTabela.setRowCount(0); // Limpa a tabela
        for (ModelsFuncionarios funcionario : funcionarios) {
            modeloTabela.addRow(new Object[]{
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCPF(),
                funcionario.getCEP(),
                funcionario.getFuncao(),
                funcionario.getSalario(),
                funcionario.getStatus()
            });
        }
    }
}