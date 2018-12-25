package dev.webacademy.aluno;

import dev.webacademy.bancodados.GerenciadorConexao;
import dev.webacademy.bancodados.GerenciadorTransacao;
import dev.webacademy.bancodados.UtilsDB;
import java.sql.Connection;
import java.sql.SQLException;

public class AlunoServico {
    
    public boolean salvar(Aluno aluno) throws SQLException {
        boolean isSalvo = false;
        if (aluno != null) {
            Connection connection = GerenciadorConexao.getConnection();
            if (connection != null) {
                AlunoDAO alunoDAO = new AlunoDAO(connection);
                try {
                    GerenciadorTransacao.iniciar(connection);
                    if (aluno.getId() == 0) {
                        if (alunoDAO.inserir(aluno)) {
                            isSalvo = true;
                        }
                    } else if (aluno.getId() > 0) {
                        if (alunoDAO.atualizar(aluno)) {
                            isSalvo = true;
                        }
                    }
                    GerenciadorTransacao.finalizar(connection);
                } catch (SQLException ex) {
                    GerenciadorTransacao.cancelar(connection);
                    throw ex;
                } finally {
                    alunoDAO = null;
                    UtilsDB.fecharConexao(connection);
                }
            }
        }
        return isSalvo;
    }
    
}
