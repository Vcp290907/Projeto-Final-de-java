package views;

import controllers.ConsultaFuncionariosController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.ModelsFuncionarios;

public class AlterarFuncionario extends JFrame {
    private ModelsFuncionarios funcionario;
    private final ConsultaFuncionariosController controller;

    public AlterarFuncionario(ModelsFuncionarios funcionario) {
        this.funcionario = funcionario;
        this.controller = new ConsultaFuncionariosController(new ConsultaFuncionarios(), new JTable(), new DefaultTableModel());
        setTitle("Alterar Funcionário");
        iniciar();
    }

    private void iniciar() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 450); // Aumentei um pouco o tamanho para acomodar os radio buttons
        setLayout(null);

        // Título
        JLabel titulo = new JLabel("Alterar Funcionário", SwingConstants.CENTER); 
        titulo.setBounds(0, 10, 400, 30);
        add(titulo);

        // Nome
        JLabel LNome = new JLabel("Nome:");
        LNome.setBounds(20, 50, 100, 25); // Ajuste de posição e tamanho
        add(LNome);

        JTextField campoNome = new JTextField(funcionario.getNome());
        campoNome.setBounds(120, 50, 200, 25);
        add(campoNome);

        // CPF
        JLabel LCPF = new JLabel("CPF:");
        LCPF.setBounds(20, 90, 100, 25); // Ajuste de posição e tamanho
        add(LCPF);

        JTextField campoCPF = new JTextField(funcionario.getCPF());
        campoCPF.setBounds(120, 90, 200, 25);
        add(campoCPF);

        // CEP
        JLabel LCEP = new JLabel("CEP:");
        LCEP.setBounds(20, 130, 100, 25); // Ajuste de posição e tamanho
        add(LCEP);

        JTextField campoCEP = new JTextField(funcionario.getCEP());
        campoCEP.setBounds(120, 130, 200, 25);
        add(campoCEP);

        // Função
        JLabel LFuncao = new JLabel("Função:");
        LFuncao.setBounds(20, 170, 100, 25); // Ajuste de posição e tamanho
        add(LFuncao);

        JTextField campoFuncao = new JTextField(funcionario.getFuncao());
        campoFuncao.setBounds(120, 170, 200, 25);
        add(campoFuncao);

        // Salário (radio buttons)
        JLabel LSalario = new JLabel("Salário:");
        LSalario.setBounds(20, 210, 100, 25);
        add(LSalario);

        JRadioButton salario1 = new JRadioButton("1500");
        salario1.setBounds(120, 210, 60, 25);
        JRadioButton salario2 = new JRadioButton("2000");
        salario2.setBounds(190, 210, 60, 25);
        JRadioButton salario3 = new JRadioButton("2500");
        salario3.setBounds(260, 210, 60, 25);
        JRadioButton salario4 = new JRadioButton("3000");
        salario4.setBounds(330, 210, 60, 25);

        // Selecionar o radio button correspondente ao salário atual do funcionário
        switch (funcionario.getSalario()) {
            case 1500:
                salario1.setSelected(true);
                break;
            case 2000:
                salario2.setSelected(true);
                break;
            case 2500:
                salario3.setSelected(true);
                break;
            case 3000:
                salario4.setSelected(true);
                break;
        }

        ButtonGroup salarioGroup = new ButtonGroup();
        salarioGroup.add(salario1);
        salarioGroup.add(salario2);
        salarioGroup.add(salario3);
        salarioGroup.add(salario4);

        add(salario1);
        add(salario2);
        add(salario3);
        add(salario4);

        // Status (combo box)
        JLabel LStatus = new JLabel("Status:");
        LStatus.setBounds(20, 250, 100, 25);
        add(LStatus);

        String[] statuses = {"Ativado", "Desativado"};
        JComboBox<String> statusComboBox = new JComboBox<>(statuses);
        statusComboBox.setBounds(120, 250, 200, 25);

        // Selecionar o item do combo box correspondente ao status atual do funcionário
        statusComboBox.setSelectedItem(funcionario.getStatus());

        add(statusComboBox);

        // Botões
        JButton BTSalvar = new JButton("Salvar");
        BTSalvar.setBounds(80, 300, 100, 30);
        add(BTSalvar);

        BTSalvar.addActionListener(e -> {
            // Obter o salário a partir dos radio buttons selecionados
            int salario = 0;
            if (salario1.isSelected()) {
                salario = 1500;
            } else if (salario2.isSelected()) {
                salario = 2000;
            } else if (salario3.isSelected()) {
                salario = 2500;
            } else if (salario4.isSelected()) {
                salario = 3000;
            }

            // Atualiza o objeto funcionario com os novos valores
            funcionario.setNome(campoNome.getText());
            funcionario.setCPF(campoCPF.getText());
            funcionario.setCEP(campoCEP.getText());
            funcionario.setFuncao(campoFuncao.getText());
            funcionario.setSalario(salario);
            funcionario.setStatus((String) statusComboBox.getSelectedItem());

            // Chame o método do controller para salvar as alterações
            boolean sucesso = controller.alterarFuncionario(funcionario);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Funcionário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Fecha a janela atual
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton BTVoltar = new JButton("Voltar");
        BTVoltar.setBounds(200, 300, 100, 30);
        add(BTVoltar);

        BTVoltar.addActionListener(e -> {
            dispose(); // Fecha a janela atual
        });
    }
}