package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import src.exception.OperationException;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;

public class DisciplinaDAO extends BaseDAO implements DisciplinaInterDAO {

	public static final String tabela = "disciplina";

	@Override
	public void cadastrar(DisciplinaVO disciplina) throws OperationException {
		String sql = "insert into " + tabela + " (codigo, nome, data_criacao) values (?, ?, ?)";
		PreparedStatement statement;

		try {
			statement = getConnection().prepareStatement(sql);
			statement.setString(1, disciplina.getCodigo());
			statement.setString(2, disciplina.getNome());
			statement.setTimestamp(3, new Timestamp(disciplina.getDataCriacao().getTimeInMillis()));

			if (statement.executeUpdate() == 0)
				throw new SQLException("Não foi possível realizar este cadastro.");

			ResultSet generatedKeys = statement.getGeneratedKeys();

			if (generatedKeys.next())
				try {
					disciplina.setId(generatedKeys.getLong("id"));
				} catch (OperationException e) {
					throw new SQLException("O banco de dados forneceu um id inválido");
				}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new OperationException("Não foi possível realizar este cadastro.");
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
	public ResultSet buscar(DisciplinaVO disciplina) {
		String sql = "select * from " + tabela + " where id = ?";
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
	public ResultSet buscar(ProfessorVO professor) {
		String sql = "select * from disciplina join professor_disciplina on (disciplina.id = professor_disciplina.disciplina) where professor_disciplina.professor = ?";
		PreparedStatement statement;
		ResultSet result = null;

		try {
			statement = getConnection().prepareStatement(sql);
			statement.setLong(1, professor.getId());
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ResultSet buscarPorCodigo(DisciplinaVO disciplina) {
		String sql = "select * from " + tabela + " where codigo = ?";
		PreparedStatement statement;
		ResultSet result = null;

		try {
			statement = getConnection().prepareStatement(sql);
			statement.setString(1, disciplina.getCodigo());
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ResultSet buscarPorNome(DisciplinaVO disciplina) {
		String sql = "select * from " + tabela + " where nome like ?";
		PreparedStatement statement;
		ResultSet result = null;

		try {
			statement = getConnection().prepareStatement(sql);
			statement.setString(1, "%"+ disciplina.getNome() + "%");
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void atualizar(DisciplinaVO disciplina) {
		String sql = "update " + tabela + " set codigo = ?, nome = ?, data_criacao = ? where id = ?";
		PreparedStatement statement;

		try {
			statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, disciplina.getCodigo());
			statement.setString(2, disciplina.getNome());
			statement.setTimestamp(3, new Timestamp(disciplina.getDataCriacao().getTimeInMillis()));
			statement.setLong(4, disciplina.getId());

			if (statement.executeUpdate() == 0)
				throw new SQLException("Não foi possível realizar esta atualização.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(DisciplinaVO disciplina) {
		String sql = "delete from " + tabela + " where id = ?";
		PreparedStatement statement;

		try {
			statement = getConnection().prepareStatement(sql);
			statement.setLong(1, disciplina.getId());

			if (statement.executeUpdate() == 0)
				throw new SQLException("Não foi possível realizar esta exclusão.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
