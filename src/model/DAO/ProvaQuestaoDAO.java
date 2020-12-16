package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public class ProvaQuestaoDAO extends BaseDAO implements ProvaQuestaoInterDAO {

    public static final String tabela = "prova_questao";

    @Override
    public void adicionar(ProvaVO prova, QuestaoVO questao) {
        String sql = "insert into " + tabela + "(prova, questao) values (?, ?)";

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, prova.getId());
            statement.setLong(2, questao.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível fazer esta associação.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet buscar(ProvaVO prova) {
        String sql = "select * from " + tabela + " where prova = ?";
        ResultSet resultado = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, prova.getId());

            resultado = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public void remover(ProvaVO prova, QuestaoVO questao) {
        String sql = "delete from " + tabela + " where prova = ? and questao = ?";

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, prova.getId());
            statement.setLong(2, questao.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível remover esta associação.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
