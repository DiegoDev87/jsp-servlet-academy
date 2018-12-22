package dev.webacademy.aluno;

import dev.webacademy.bancodados.GerenciadorConexao;
import dev.webacademy.bancodados.GerenciadorTransacao;
import dev.webacademy.bancodados.IDAO;
import dev.webacademy.bancodados.UtilsDB;
import dev.webacademy.endereco.EnderecoDAO;
import dev.webacademy.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AlunoDAO implements IDAO<Aluno> {

    private Connection conexao;

    public AlunoDAO(){}
    
    public AlunoDAO(Connection connection) {
        this.conexao = connection;
    }

    @Override
    public boolean inserir(Aluno aluno) throws SQLException {
        boolean isSalvo = false;
        if (aluno != null) {
            StringBuilder sql = new StringBuilder();
            EnderecoDAO enderecoDAO = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                conexao = GerenciadorConexao.getConnection();
                if (this.conexao != null && !this.conexao.isClosed()) {
                    sql.append("INSERT INTO aluno(nome,email,tipo_pessoa,tel_fixo,tel_celular,data_nascimento,cpf,rg) ");
                    sql.append("VALUES(?,?,?,?,?,?,?,?)");
                    ps = this.conexao.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1, aluno.getNome());
                    ps.setString(2, aluno.getEmail());
                    ps.setInt(3, aluno.getTipoPessoa().getValor());
                    ps.setString(4, aluno.getTelFixo());
                    ps.setString(5, aluno.getTelCelular());
                    ps.setDate(6, UtilsDB.converteData(aluno.getDataNascimento()));
                    ps.setString(7, aluno.getCpf());
                    ps.setString(8, aluno.getRg());
                    if (ps.executeUpdate() == 1) {
                        rs = ps.getGeneratedKeys();
                        int idAluno = 0;
                        if (rs.next()) {
                            idAluno = rs.getInt("id");
                        }
                        if (idAluno > 0) {
                            aluno.setId(idAluno);
                            if (aluno.getEndereco() != null) {
                                enderecoDAO = new EnderecoDAO(this.conexao);
                                enderecoDAO.inserir(aluno.getEndereco());
                            }
                            isSalvo = true;
                        }
                    }
                }
            } catch (SQLException ex) {
                throw ex;
            } finally {
                Utils.limparSB(sql);
                enderecoDAO = null;
                UtilsDB.fechar(ps, rs);
            }
        }
        return isSalvo;
    }

    @Override
    public boolean atualizar(Aluno t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Aluno buscarPorID(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Aluno> todos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
