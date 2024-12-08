package views;

import controllers.FuncionarioController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class index {
    public void iniciar() {
        System.out.println("Iniciando tela principal");
        JFrame frame = new JFrame("MC QUEEN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel titulo = new JLabel("MC QUEEN", SwingConstants.CENTER);
        JLabel cabecalho = new JLabel("<html><div style='text-align: center;'>Seja bem-vindo à fábrica<br>Escolha a opção</div></html>", SwingConstants.CENTER);
        JButton BTFuncionarios = new JButton("Funcionários");
        JButton BTOrdem = new JButton("Ord. de Serviço");

        titulo.setBounds(0, 10, 400, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(titulo);

        cabecalho.setBounds(50, 50, 300, 50);
        cabecalho.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(cabecalho);

        BTFuncionarios.setBounds(20, 150, 150, 40);
        frame.add(BTFuncionarios);

        BTOrdem.setBounds(210, 150, 150, 40);
        frame.add(BTOrdem);

        BTFuncionarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botão Funcionários clicado");
                frame.dispose(); // Fecha a janela atual
                new FuncionarioController().iniciar(); // Abre a nova tela
            }
        });

        BTOrdem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botão Ordem de Serviço clicado");
                frame.dispose(); // Fecha a janela atual
                new ordem(); // Abre a nova tela
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("Tela principal visível");
    }
}