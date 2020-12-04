package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.model.VO.QuestaoSubjetivaVO;

public class QuestaoSubjetivaDAO extends QuestaoDAO<QuestaoSubjetivaVO> implements QuestaoSubjetivaInterDAO {

    @Override
    public void cadastrar(QuestaoSubjetivaVO vo) {
        String sql = "insert into questao_subjetiva (questao, gabarito) values (?, ?)";
        PreparedStatement statement;

        try {
            super.cadastrar(vo);

            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getIdQuestao());
            statement.setString(2, vo.getGabarito());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível completar este cadastro.");

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next())
                vo.setId(generatedKeys.getLong("id"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet listar() {
        String sql = "select * from questao_subjetiva";
        Statement statement;
        ResultSet result = null;

        try {
            statement = getConnection().createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ResultSet buscar(QuestaoSubjetivaVO vo) {
        String sql = "select * from questao_subjetiva where id = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            super.buscar(vo);
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getId());
            result = statement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void editar(QuestaoSubjetivaVO vo) {
        String sql = "update questao_subjetiva set gabarito = ? where id = ?";
        PreparedStatement statement;

        try {
            super.editar(vo);
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, vo.getGabarito());
            statement.setLong(2, vo.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar esta atualização.");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void excluir(QuestaoSubjetivaVO vo) {
        String sql = "delete from questao_subjetiva where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Não foi possível realizar esta exclusão.");
            } else {
                super.excluir(vo);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
