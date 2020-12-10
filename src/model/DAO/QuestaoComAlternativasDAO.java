package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import src.exception.AuthenticationException;
import src.model.VO.AlternativaVO;
import src.model.VO.QuestaoComAlternativasVO;

public class QuestaoComAlternativasDAO extends QuestaoDAO<QuestaoComAlternativasVO>
        implements QuestaoComAlternativasInterDAO {

    @Override
    public void cadastrar(QuestaoComAlternativasVO vo) {
        String sql = "insert into questao_com_alternativas (questao) values (?)";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getIdQuestao());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar o cadastro.");

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
        String sql = "select * from questao_com_alternativas";
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
    public ResultSet buscar(QuestaoComAlternativasVO vo) {
        String sql = "select * from questao_com_alternativas where id = ?";
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
    public ResultSet buscarPorDificuldade(QuestaoComAlternativasVO vo) {
        String sql = "select * from questao_com_alternativas where dificuldade = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            super.buscar(vo);
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, vo.getDificuldade());
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void editar(QuestaoComAlternativasVO vo) {
        // A tabela em si só possui duas chaves que não devem ser alteradas.
        super.editar(vo);

    }

    @Override
    public void excluir(QuestaoComAlternativasVO vo) {
        // Primeiro, excluir as alternativas
        AlternativaDAO alternativaDAO = new AlternativaDAO();
        List<AlternativaVO> alternativas = vo.getAlternativas();
        while (alternativas.iterator().hasNext()) {
            alternativaDAO.excluir(alternativas.iterator().next());
        }

        // E então excluir a questão
        String sql = "delete from questao_com_alternativa where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar esta exclusão.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
