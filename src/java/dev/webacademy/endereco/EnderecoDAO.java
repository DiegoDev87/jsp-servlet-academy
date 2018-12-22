package dev.webacademy.endereco;
import dev.webacademy.aluno.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnderecoDAO {

    private Connection conexao = null;

    public EnderecoDAO(Connection connection) {
        this.conexao = connection;
    }

    public boolean inserir(Endereco endereco) throws SQLException {
        boolean isSalvo = false;
        PreparedStatement ps = null;
        String fkPessoa = "";
        String nomeTabela = "";
        StringBuilder sql = new StringBuilder();
        try {
            if (this.conexao != null && !this.conexao.isClosed()) {
                if (endereco.getPessoa() != null) {
                    if (endereco.getPessoa() instanceof Aluno) {
                        nomeTabela += "endereco_aluno";
                        fkPessoa += "id_professor";
                    } /*else if (endereco.getPessoa() instanceof Professor) {
                        nomeTabela += "endereco_professor";
                        fkPessoa += "id_aluno";
                    }*/
                    sql.append("INSERT INTO ").append(nomeTabela).append("(");
                    sql.append(fkPessoa).append(",cep,logradouro,bairro,cidade,uf,complemento");
                    sql.append(") ");
                    sql.append("VALUES(?,?,?,?,?,?,?);");
                    ps = this.conexao.prepareStatement(sql.toString());
                    ps.setInt(1, endereco.getPessoa().getId());
                    ps.setString(2, endereco.getCep());
                    ps.setString(3, endereco.getLogradouro());
                    ps.setString(4, endereco.getBairro());
                    ps.setString(5, endereco.getCidade());
                    ps.setString(6, endereco.getUf());
                    ps.setString(7, endereco.getComplemento());
                    if (ps.executeUpdate() == 1) {
                        isSalvo = true;
                    }
                }
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            fkPessoa = null;
            nomeTabela = null;
            sql = null;
            ps = null;
        }
        return isSalvo;
    }
}
