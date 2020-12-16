package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import src.model.VO.AlternativaVO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoComAlternativasVO;

public class QuestaoComAlternativasDAO extends BaseDAO implements QuestaoComAlternativasInterDAO {

    public static final String tabela = "questao_com_alternativas";
    private QuestaoDAO<QuestaoComAlternativasVO> questaoDAO = new QuestaoDAO<QuestaoComAlternativasVO>();

    @Override
    public void cadastrar(QuestaoComAlternativasVO vo) {
        String sql = "insert into " + tabela + " (id) values (?)";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar o cadastro.");

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
    public ResultSet buscar(QuestaoComAlternativasVO vo) {
        String sql = "select * from " + tabela + " where id = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            questaoDAO.buscar(vo);
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getId());
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ResultSet buscar(DisciplinaVO disciplina) {
        String sql = "select * from questao right join " + tabela + " on (questao.id = " + tabela + ".id) where questao.disciplina = ?";

        PreparedStatement statement;
        ResultSet result = null;

        try {
            questaoDAO.buscar(disciplina);
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, disciplina.getId());
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ResultSet buscar(String assunto) {
        // TODO _ Assunto _
        return null;
    }

    @Override
    public ResultSet buscar(ProvaVO prova) {
        String sql = "select * from questao right join " + tabela + " on (questao.id = " + tabela + ".id) where questao.id in (select questao from prova_questao where prova = ?)";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, prova.getId());
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ResultSet buscarPorDificuldade(QuestaoComAlternativasVO questao, String assunto) {
        // TODO _ Assunto _
        return null;
    }

    @Override
    public ResultSet buscarPorDificuldade(QuestaoComAlternativasVO vo) {
        String sql = "select * from " + tabela + " where dificuldade = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            questaoDAO.buscar(vo);
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, vo.getDificuldade());
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ResultSet buscarPorDificuldadeEDisciplina(QuestaoComAlternativasVO questao) {
        String sql = "select * from questao right join " + tabela + " on (questao.id = " + tabela + ".id) where questao.dificuldade = ? and questao.disciplina = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            questaoDAO.buscar(questao);
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, questao.getDificuldade());
            statement.setLong(2, questao.getDisciplina().getId());
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void atualizar(QuestaoComAlternativasVO vo) {
        // A tabela em si só possui duas chaves que não devem ser alteradas.
        questaoDAO.atualizar(vo);

    }

    @Override
    public void excluir(QuestaoComAlternativasVO vo) {
        // Primeiro, excluir as alternativas
        AlternativaDAO alternativaDAO = new AlternativaDAO();
        List<AlternativaVO> alternativas = vo.getAlternativas();
        while (alternativas.iterator().hasNext())
            alternativaDAO.excluir(alternativas.iterator().next());

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
