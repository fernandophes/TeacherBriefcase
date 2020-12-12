package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import src.exception.AuthenticationException;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;

public class ProfessorDAO extends BaseDAO implements ProfessorInterDAO {

    // TODO criar constante tabela em todos os DAO

    ProfessorDisciplinaDAO professorDisciplinaDAO = new ProfessorDisciplinaDAO();

    @Override
    public void cadastrar(ProfessorVO vo) {
        String sql = "insert into professor (nome, email, senha, data_criacao) values (?, ?, ?, ?)";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, vo.getNome());
            statement.setString(2, vo.getEmail());
            statement.setString(3, vo.getSenha());
            statement.setTimestamp(4, new Timestamp(vo.getDataCriacao().getTimeInMillis()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0)
                throw new SQLException("Não foi possível realizar este cadastro.");

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next())
                try {
                    vo.setId(generatedKeys.getLong("id"));
                } catch (AuthenticationException e) {
                    throw new SQLException("O banco de dados forneceu um id inválido");
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet listar() {
        String sql = "select * from professor";
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
    public ResultSet buscar(ProfessorVO vo) {
        String sql = "select * from professor where id = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
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
        ResultSet busca = professorDisciplinaDAO.buscar(disciplina);
        String lista = "";

        String sql = "select * from professor where id in (?)";
        ResultSet resultado = null;

        try {
            while (busca.next())
                lista = String.join(" ,", lista, Long.toString(busca.getLong("id")));

            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, lista);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public ResultSet buscarPorEmail(ProfessorVO vo) {
        String sql = "select * from professor where email = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, vo.getEmail());
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void atualizar(ProfessorVO vo) {
        String sql = "update professor set nome = ?, email = ?, senha = ? where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, vo.getNome());
            statement.setString(2, vo.getEmail());
            statement.setString(3, vo.getSenha());
            statement.setLong(4, vo.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar esta atualização.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(ProfessorVO vo) {
        String sql = "delete from professor where id = ?";
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

    @Override
    public void adicionar(ProfessorVO professor, DisciplinaVO disciplina) {
        professorDisciplinaDAO.adicionar(professor, disciplina);
    }

    @Override
    public void remover(ProfessorVO professor, DisciplinaVO disciplina) {
        professorDisciplinaDAO.remover(professor, disciplina);
    }

}
