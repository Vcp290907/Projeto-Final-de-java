package repository;

import config.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.ModelsFuncionarios;

public class funcionariosRepository {

    public void cadastroFuncionario(ModelsFuncionarios funcionarios) {
        String SQL = "INSERT INTO funcionarios (nome, CPF, CEP, funcao, salario, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
                PreparedStatement aaa = conn.prepareStatement(SQL)) {
            aaa.setString(1, funcionarios.getNome());
            aaa.setString(2, funcionarios.getCPF());
            aaa.setString(3, funcionarios.getCEP());
            aaa.setString(4, funcionarios.getFuncao());
            aaa.setInt(5, funcionarios.getSalario());
            aaa.setString(6, funcionarios.getStatus());

            int mudanca = aaa.executeUpdate();
            if (mudanca > 0) {
                System.out.println("Funcionário adicionado com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar Funcionário");
        }
    }

    public List<ModelsFuncionarios> todosFuncionarios() {
        List<ModelsFuncionarios> func = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try (Connection conn = DbConnection.getConnection();
                Statement bbb = conn.createStatement();
                ResultSet rs = bbb.executeQuery(sql)) {

            while (rs.next()) {
                ModelsFuncionarios todosFunc = new ModelsFuncionarios(
                        rs.getString("nome"),
                        rs.getString("CPF"), 
                        rs.getString("CEP"),
                        rs.getString("funcao"), 
                        rs.getInt("salario"),
                        rs.getString("status")
                ); 
                func.add(todosFunc); 
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter funcionários.");
        }

        return func; 
    }

    public ModelsFuncionarios obterFuncionarioPorCodigo(int codigo) {
        String sql = "SELECT * FROM funcionarios WHERE id = ?";
        ModelsFuncionarios funcionario = null;

        try (Connection conn = DbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = new ModelsFuncionarios(
                        rs.getString("nome"),
                        rs.getString("CPF"),
                        rs.getString("CEP"),
                        rs.getString("funcao"),
                        rs.getInt("salario"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter funcionário por id.");
        }

        return funcionario;
    }

    public void atualizarFuncionario(ModelsFuncionarios funcionario) {
        String sql = "UPDATE funcionarios SET nome = ?, CPF = ?, CEP = ?, funcao = ?, salario = ?, status = ? WHERE id = ?";
    
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCPF());
            stmt.setString(3, funcionario.getCEP());
            stmt.setString(4, funcionario.getFuncao());
            stmt.setInt(5, funcionario.getSalario());
            stmt.setString(6, funcionario.getStatus());
            stmt.setInt(7, funcionario.getId());
    
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Funcionário atualizado com sucesso!");
            } else {
                System.out.println("Funcionário não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário.");
        }
    }

    public void deletarFuncionario(int codigo) {
        String sql = "DELETE FROM funcionarios WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Funcionário deletado com sucesso!");
            } else {
                System.out.println("Funcionário não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar funcionário.");
        }
    }

    public List<ModelsFuncionarios> buscarFuncionarios(String filtro, String valor) {
        List<ModelsFuncionarios> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios WHERE " + filtro + " LIKE ?";
    
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, "%" + valor + "%");
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                ModelsFuncionarios funcionario = new ModelsFuncionarios(
                        rs.getInt("id"), // Recupera o campo id
                        rs.getString("nome"),
                        rs.getString("CPF"),
                        rs.getString("CEP"),
                        rs.getString("funcao"),
                        rs.getInt("salario"),
                        rs.getString("status")
                );
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionários.");
            e.printStackTrace();
        }
    
        return funcionarios;
    }
}