package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import src.model.VO.ProvaVO;

public class ProvaDAO extends BaseDAO implements ProvaInterDAO {

    @Override
    public void cadastrar(ProvaVO vo) {
        String sql = "insert into prova (disciplina, titulo, data_criacao) values (?, ?, ?)";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getDisciplina().getId());
            statement.setString(2, vo.getTitulo());
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
        String sql = "select * from prova";
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
    public ResultSet buscar(ProvaVO vo) {
        String sql = "select * from prova where id = ?";
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
    public void atualizar(ProvaVO vo) {
        String sql = "update prova set titulo = ? where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, vo.getTitulo());
            statement.setLong(2, vo.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar esta atualização.");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(ProvaVO vo) {
        String sql = "delete from prova where id = ?";
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
