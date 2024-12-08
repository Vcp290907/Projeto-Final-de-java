package views;

import controllers.FuncionarioController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Funcionarios {
    private FuncionarioController controller;

    public Funcionarios(FuncionarioController controller) {
        this.controller = controller;
    }

    public void iniciar() {
        System.out.println("Iniciando tela de Funcionários");
        JFrame frame = new JFrame("Funcionários");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel titulo = new JLabel("<html><div style='text-align: center; font-size:2em;'>Funcionários</div></html>", SwingConstants.CENTER);
        titulo.setBounds(0, 10, 400, 30);
        frame.add(titulo);

        JButton BTConsulatar = new JButton("Consultar");
        BTConsulatar.setBounds(80, 150, 110, 40);
        frame.add(BTConsulatar);

        JButton BTCadastrar = new JButton("Cadastrar");
        BTCadastrar.setBounds(210, 150, 110, 40);
        frame.add(BTCadastrar);

        JButton BTVoltar = new JButton("Voltar");
        BTVoltar.setBounds(150, 220, 100, 30);
        frame.add(BTVoltar);

        BTCadastrar.addActionListener(e -> {
            try {
                System.out.println("Botão Cadastrar clicado");
                controller.iniciarCadastro();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erro ao abrir a tela de cadastro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        BTConsulatar.addActionListener(e -> {
            try {
                System.out.println("Botão Consultar clicado");
                controller.iniciarConsulta();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erro ao abrir a tela de consulta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        BTVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Botão Voltar clicado");
                    frame.dispose();
                    new index().iniciar(); // Volta para a tela principal
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Erro ao voltar para a tela principal: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("Tela de Funcionários visível");
    }
}
