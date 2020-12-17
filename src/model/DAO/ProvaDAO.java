package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public class ProvaDAO extends BaseDAO implements ProvaInterDAO {

    public static final String tabela = "prova";
    private ProvaQuestaoDAO provaQuestaoDAO = new ProvaQuestaoDAO();

    @Override
    public void cadastrar(ProvaVO prova) {
        String sql = "insert into " + tabela + " (disciplina, titulo, data_criacao) values (?, ?, ?)";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, prova.getDisciplina().getId());
            statement.setString(2, prova.getTitulo());
            statement.setTimestamp(3, new Timestamp(prova.getDataCriacao().getTimeInMillis()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0)
                throw new SQLException("Não foi possível realizar este cadastro.");

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next())
                prova.setId(generatedKeys.getLong("id"));

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
    public ResultSet buscar(ProvaVO prova) {
        String sql = "select * from " + tabela + " where id = ?";
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
    public ResultSet buscar(DisciplinaVO disciplina) {
        String sql = "select * from " + tabela + " where disciplina = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, disciplina.getId());
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void atualizar(ProvaVO prova) {
        String sql = "update " + tabela + " set titulo = ? where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, prova.getTitulo());
            statement.setLong(2, prova.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar esta atualização.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(ProvaVO prova) {
        String sql = "delete from " + tabela + " where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, prova.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar esta exclusão.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(ProvaVO prova, QuestaoVO questao) {
        provaQuestaoDAO.adicionar(prova, questao);
    }

    @Override
    public void remover(ProvaVO prova, QuestaoVO questao) {
        provaQuestaoDAO.remover(prova, questao);
    }
    
}
