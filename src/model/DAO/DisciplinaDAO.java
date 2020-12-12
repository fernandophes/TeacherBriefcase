package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import src.exception.AuthenticationException;
import src.model.VO.DisciplinaVO;

public class DisciplinaDAO extends BaseDAO implements DisciplinaInterDAO {

	@Override
	public void cadastrar(DisciplinaVO vo) throws AuthenticationException {
		String sql = "insert into disciplina (codigo, nome, data_criacao) values (?, ?, ?)";
		PreparedStatement statement;

		try {
			statement = getConnection().prepareStatement(sql);
			statement.setString(1, vo.getCodigo());
			statement.setString(2, vo.getNome());
			statement.setTimestamp(3, new Timestamp(vo.getDataCriacao().getTimeInMillis()));

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0)
				throw new SQLException("Não foi possível realizar este cadastro.");

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
		String sql = "select * from disciplina";
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
	public ResultSet buscar(DisciplinaVO vo) {
		String sql = "select * from disciplina where id = ?";
		PreparedStatement statement;
		ResultSet result = null;

		try {
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
	public ResultSet buscarPorCodigo(DisciplinaVO vo) {
		String sql = "select * from disciplina where codigo = ?";
		PreparedStatement statement;
		ResultSet result = null;

		try {
			statement = getConnection().prepareStatement(sql);
			statement.setString(1, vo.getCodigo());
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ResultSet buscarPorNome(DisciplinaVO vo) {
		String sql = "select * from disciplina where nome = ?";
		PreparedStatement statement;
		ResultSet result = null;

		try {
			statement = getConnection().prepareStatement(sql);
			statement.setString(1, vo.getNome());
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void atualizar(DisciplinaVO vo) {
		String sql = "update disciplina set codigo = ?, nome = ?, data_criacao = ?, where id = ?";
		PreparedStatement statement;

		try {
			statement = getConnection().prepareStatement(sql);
			statement.setString(1, vo.getCodigo());
			statement.setString(2, vo.getNome());
			statement.setTimestamp(3, new Timestamp(vo.getDataCriacao().getTimeInMillis()));
			statement.setLong(4, vo.getId());

			if (statement.executeUpdate() == 0)
				throw new SQLException("Não foi possível realizar esta atualização.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(DisciplinaVO vo) {
		String sql = "delete from disciplina where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar esta exclusão.");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
