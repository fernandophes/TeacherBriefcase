package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.model.VO.DisciplinaVO;
import src.model.VO.QuestaoVO;

public class AssuntoDAO extends BaseDAO implements AssuntoInterDAO {

    public static final String tabela = "assunto";

    @Override
    public void cadastrar(DisciplinaVO disciplina, String assunto) {
        String sql = "insert into " + tabela + "(disciplina, nome) values (?, ?)";

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, disciplina.getId());
            statement.setString(2, assunto);

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar este cadastro.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet listar() {
        String sql = "select * from " + tabela;
        ResultSet resultado = null;

        try {
            Statement statement = getConnection().createStatement();
            resultado = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public ResultSet buscar(DisciplinaVO disciplina, String assunto) {
        String sql = "select * from " + tabela + " where disciplina = ? and nome = ?";
        ResultSet resultado = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, disciplina.getId());
            statement.setString(2, assunto);

            resultado = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public ResultSet buscar(DisciplinaVO disciplina) {
        String sql = "select * from " + tabela + " where disciplina = ?";
        ResultSet resultado = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, disciplina.getId());

            resultado = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
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
    public void atualizar(DisciplinaVO disciplina, String assunto, String novo) {
        String sql = "update " + tabela + " set nome = ? where disciplina = ? and nome = ?";

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, assunto);

            if (statement.executeUpdate() == 0)
				throw new SQLException("Não foi possível realizar esta atualização.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(DisciplinaVO disciplina, String assunto) {
        String sql = "delete from " + tabela + " where disciplina = ? and nome = ?";
		PreparedStatement statement;

		try {
			statement = getConnection().prepareStatement(sql);
			statement.setLong(1, disciplina.getId());
            statement.setString(2, assunto);

			if (statement.executeUpdate() == 0)
				throw new SQLException("Não foi possível realizar esta exclusão.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }

}
