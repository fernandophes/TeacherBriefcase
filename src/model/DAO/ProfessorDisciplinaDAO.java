package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;

public class ProfessorDisciplinaDAO extends BaseDAO implements ProfessorDisciplinaInterDAO {

    public final String tabela = "professor_disciplina";

    @Override
    public void adicionar(ProfessorVO professor, DisciplinaVO disciplina) {
        String sql = "insert into " + tabela + "(professor, disciplina) values (?, ?)";

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, professor.getId());
            statement.setLong(2, disciplina.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível associar esta disciplina a este professor.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet buscar(ProfessorVO professor) {
        String sql = "select from " + tabela + " where professor = ?";
        ResultSet resultado = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, professor.getId());

            resultado = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public ResultSet buscar(DisciplinaVO disciplina) {
        String sql = "select from " + tabela + " where disciplina = ?";
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
    public void remover(ProfessorVO professor, DisciplinaVO disciplina) {
        String sql = "delete from " + tabela + " where professor = ? and disciplina = ?";

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setLong(1, professor.getId());
            statement.setLong(2, disciplina.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível remover esta associação.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
