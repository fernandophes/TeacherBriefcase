package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.model.VO.QuestaoVO;

public class QuestaoAssuntoDAO extends BaseDAO implements QuestaoAssuntoInterDAO {

    public static final String tabela = "questao_assunto";

    @Override
    public void adicionar(QuestaoVO questao, String assunto) {
        String sql = "insert into " + tabela + "(questao, assunto) values (?, ?)";

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, questao.getId());
            statement.setString(2, assunto);

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível associar este assunto a esta questao.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet buscar(QuestaoVO questao) {
        String sql = "select * from " + tabela + " where questao = ?";
        ResultSet resultado = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, questao.getId());

            resultado = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public ResultSet buscar(String assunto) {
        String sql = "select * from " + tabela + " where assunto like ?";
        ResultSet resultado = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, "%" + assunto + "%");

            resultado = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public void remover(QuestaoVO questao, String assunto) {
        String sql = "delete from " + tabela + " where questao = ? and assunto = ?";

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, questao.getId());
            statement.setString(2, assunto);

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível remover esta associação.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
