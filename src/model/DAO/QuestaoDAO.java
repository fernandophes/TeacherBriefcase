package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import src.exception.OperationException;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public class QuestaoDAO<QuestaoDerivadaVO extends QuestaoVO> extends BaseDAO implements QuestaoInterDAO<QuestaoDerivadaVO> {

    public static final String tabela = "questao";

    @Override
    public void cadastrar(QuestaoDerivadaVO questao) {
        String sql = "insert into " + tabela + " (disciplina, enunciado, dificuldade, data_criacao) values (?, ?, ?, ?)";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, questao.getDisciplina().getId());
            statement.setString(2, questao.getEnunciado());
            statement.setInt(3, questao.getDificuldade());
            statement.setTimestamp(4, new Timestamp(questao.getDataCriacao().getTimeInMillis()));

            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next())
                try {
                    questao.setId(generatedKeys.getLong("id"));
                } catch (OperationException e) {
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
    public ResultSet buscar(QuestaoDerivadaVO questao) {
        String sql = "select * from " + tabela + " where id = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, questao.getId());
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
    public ResultSet buscar(ProvaVO prova) {
        String sql = "select * from questao where id in (select questao from prova_questao where prova = ?)";
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
    public ResultSet buscar(String assunto) {
        String sql = "select * from questao where id in (select questao from questao_assunto where assunto like ?)";
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
    public ResultSet buscarPorDificuldade(QuestaoDerivadaVO questao) {
        String sql = "select * from " + tabela + " where dificuldade = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, questao.getDificuldade());
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ResultSet buscarPorDificuldade(QuestaoDerivadaVO questao, String assunto) {
        String sql = "select * from questao where id in (select questao from questao_assunto where assunto like ?) and dificuldade = ?";
        ResultSet resultado = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, "%" + assunto + "%");
            statement.setInt(2, questao.getDificuldade());

            resultado = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public ResultSet buscarPorDificuldadeEDisciplina(QuestaoDerivadaVO questao) {
        String sql = "select * from " + tabela + " where dificuldade = ? and disciplina = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
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
    public void atualizar(QuestaoDerivadaVO questao) {
        String sql = "update " + tabela + " set enunciado = ?, dificuldade = ? where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, questao.getEnunciado());
            statement.setInt(2, questao.getDificuldade());
            statement.setLong(3, questao.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar esta atualização.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void excluir(QuestaoDerivadaVO questao) {
        String sql = "delete from " + tabela + " where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, questao.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar esta exclusão.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void adicionar(QuestaoDerivadaVO questao, String assunto) {
        QuestaoAssuntoDAO questaoAssuntoDAO = new QuestaoAssuntoDAO();
        questaoAssuntoDAO.adicionar(questao, assunto);
    }

    @Override
    public void remover(QuestaoDerivadaVO questao, String assunto) {
        QuestaoAssuntoDAO questaoAssuntoDAO = new QuestaoAssuntoDAO();
        questaoAssuntoDAO.remover(questao, assunto);
    }

}
