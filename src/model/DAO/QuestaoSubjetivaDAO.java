package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.model.QuestaoDificuldade;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoSubjetivaVO;

public class QuestaoSubjetivaDAO extends BaseDAO implements QuestaoInterDAO<QuestaoSubjetivaVO> {

    public static final String tabela = "questao_subjetiva";

    private QuestaoDAO<QuestaoSubjetivaVO> questaoDAO = new QuestaoDAO<QuestaoSubjetivaVO>();

    @Override
    public void cadastrar(QuestaoSubjetivaVO questao) {
        String sql = "insert into " + tabela + " (id, gabarito) values (?, ?)";
        PreparedStatement statement;

        try {
            questaoDAO.cadastrar(questao);

            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, questao.getId());
            statement.setString(2, questao.getGabarito());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível completar este cadastro.");

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
    public ResultSet buscar(QuestaoSubjetivaVO questao) {
        String sql = "select * from questao join questao_subjetiva on (questao.id = questao_subjetiva.id) where questao.id = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            questaoDAO.buscar(questao);
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
        String sql = "select * from questao join " + tabela + " on (questao.id = " + tabela + ".id) where questao.disciplina = ?";

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
        String sql = "select * from questao join " + tabela + " on (questao.id = " + tabela + ".id) where questao.id in (select questao from questao_assunto where assunto like ?)";
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
    public ResultSet buscar(ProvaVO prova) {
        String sql = "select * from questao join " + tabela + " on (questao.id = " + tabela + ".id) where questao.id in (select questao from prova_questao where prova = ?)";
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
    public ResultSet buscarPorDificuldade(QuestaoSubjetivaVO questao) {
        String sql = "select * from questao join " + tabela + " on (questao.id = " + tabela + ".id) where questao.dificuldade = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            questaoDAO.buscar(questao);
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, questao.getDificuldade().getId());
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ResultSet buscarPorDificuldade(QuestaoSubjetivaVO questao, String assunto) {
        String sql = "select * from questao join " + tabela + " on (questao.id = " + tabela + ".id) where questao.id in (select questao from questao_assunto where assunto like ?) and dificuldade = ?";
        ResultSet resultado = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, "%" + assunto + "%");
            statement.setInt(2, questao.getDificuldade().getId());

            resultado = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public ResultSet buscarPorDificuldadeEDisciplina(QuestaoDificuldade dificuldade, DisciplinaVO disciplina) {
        String sql = "select * from questao join " + tabela + " on (questao.id = " + tabela + ".id) where questao.dificuldade = ? and questao.disciplina = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, dificuldade.getId());
            statement.setLong(2, disciplina.getId());
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void atualizar(QuestaoSubjetivaVO questao) {
        String sql = "update " + tabela + " set gabarito = ? where id = ?";
        PreparedStatement statement;

        try {
            questaoDAO.atualizar(questao);
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, questao.getGabarito());
            statement.setLong(2, questao.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar esta atualização.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void excluir(QuestaoSubjetivaVO questao) {
        String sql = "delete from " + tabela + " where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, questao.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Não foi possível realizar esta exclusão.");
            } else {
                questaoDAO.excluir(questao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void adicionar(QuestaoSubjetivaVO questao, String assunto) {
        questaoDAO.adicionar(questao, assunto);
    }

    @Override
    public void remover(QuestaoSubjetivaVO questao, String assunto) {
        questaoDAO.remover(questao, assunto);
    }

}
