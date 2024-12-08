package views;

import controllers.ConsultaFuncionariosController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.ModelsFuncionarios;

public class ConsultaFuncionarios extends JFrame {
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public void iniciar() {
        // Criando o JFrame da tela de consulta
        JFrame frame = new JFrame("Consulta de Funcionários");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);

        // Título da página
        JLabel titulo = new JLabel("<html><div style='text-align: center; font-size:2em;'>Consulta de Funcionários</div></html>", SwingConstants.CENTER);
        titulo.setBounds(0, 10, 600, 30);
        frame.add(titulo);

        // Tabela para exibir os dados
        String[] colunas = {"ID", "Nome", "CPF", "CEP", "Função", "Salário", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(30, 50, 540, 150);
        frame.add(scrollPane);

        // Campo para filtro
        JLabel LFiltro = new JLabel("Pesquisar por:");
        LFiltro.setBounds(30, 220, 100, 20);
        frame.add(LFiltro);

        JComboBox<String> comboFiltro = new JComboBox<>(new String[]{"Nome", "CPF", "CEP", "Função"});
        comboFiltro.setBounds(120, 220, 100, 20);
        frame.add(comboFiltro);

        JTextField campoFiltro = new JTextField();
        campoFiltro.setBounds(230, 220, 150, 20);
        frame.add(campoFiltro);

        JButton BTConsulta = new JButton("Consultar");
        BTConsulta.setBounds(400, 220, 100, 20);
        frame.add(BTConsulta);

        // Botões Alterar e Excluir
        JButton BTAlterar = new JButton("Alterar");
        BTAlterar.setBounds(150, 300, 100, 30);
        frame.add(BTAlterar);

        JButton BTExcluir = new JButton("Excluir");
        BTExcluir.setBounds(300, 300, 100, 30);
        frame.add(BTExcluir);

        // Botão Voltar
        JButton BTVoltar = new JButton("Voltar");
        BTVoltar.setBounds(450, 300, 100, 30);
        frame.add(BTVoltar);

        // Inicializa o controlador
        ConsultaFuncionariosController controller = new ConsultaFuncionariosController(this, tabela, modeloTabela);

        // Eventos dos botões
        BTConsulta.addActionListener(e -> {
            String filtro = (String) comboFiltro.getSelectedItem();
            String valor = campoFiltro.getText();
            controller.consultarFuncionarios(filtro, valor);
        });

        BTAlterar.addActionListener(e -> {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) modeloTabela.getValueAt(selectedRow, 0); // Supondo que a primeira coluna seja o ID
                String nome = (String) modeloTabela.getValueAt(selectedRow, 1);
                String cpf = (String) modeloTabela.getValueAt(selectedRow, 2);
                String cep = (String) modeloTabela.getValueAt(selectedRow, 3);
                String funcao = (String) modeloTabela.getValueAt(selectedRow, 4);
                int salario = (int) modeloTabela.getValueAt(selectedRow, 5);
                String status = (String) modeloTabela.getValueAt(selectedRow, 6);
        
                ModelsFuncionarios funcionario = new ModelsFuncionarios(id, nome, cpf, cep, funcao, salario, status);

                new AlterarFuncionario(funcionario);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um funcionário para alterar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        BTExcluir.addActionListener(e -> {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) modeloTabela.getValueAt(selectedRow, 0); // Supondo que a primeira coluna seja o ID
                controller.deletarFuncionario(id);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um funcionário para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        BTVoltar.addActionListener(e -> {
            frame.dispose(); // Fecha a janela atual
        });

        frame.setVisible(true);
    }
}
