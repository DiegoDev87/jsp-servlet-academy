package dev.webacademy.bancodados;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    boolean atualizar(T t) throws SQLException;

    T buscarPorID(int id) throws SQLException;

    boolean deletar(int id) throws SQLException;

    boolean inserir(T t) throws SQLException;

    List<T> todos() throws SQLException;

}
