package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.exception.AuthenticationException;
import src.model.VO.QuestaoSubjetivaVO;

public class QuestaoSubjetivaDAO extends QuestaoDAO<QuestaoSubjetivaVO> implements QuestaoSubjetivaInterDAO {

    public final String tabela = "questao_subjetiva";

    @Override
    public void cadastrar(QuestaoSubjetivaVO vo) {
        String sql = "insert into " + tabela + " (questao, gabarito) values (?, ?)";
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
                try {
                    vo.setId(generatedKeys.getLong("id"));
                } catch (AuthenticationException e) {
                    throw new SQLException("O banco de dados retornou um id inválido do cadastro feito.");
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet listar() {
        String sql = "select * from " + tabela;
        Statement statement;
        ResultSet result = null;

        try {
            statement = getConnection().createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ResultSet buscar(QuestaoSubjetivaVO vo) {
        String sql = "select * from " + tabela + " where id = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            super.buscar(vo);
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getId());
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void atualizar(QuestaoSubjetivaVO vo) {
        String sql = "update " + tabela + " set gabarito = ? where id = ?";
        PreparedStatement statement;

        try {
            super.atualizar(vo);
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, vo.getGabarito());
            statement.setLong(2, vo.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar esta atualização.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void excluir(QuestaoSubjetivaVO vo) {
        String sql = "delete from " + tabela + " where id = ?";
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
            e.printStackTrace();
        }

    }

}
