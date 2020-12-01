package src.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import src.model.VO.ProfessorVO;

public class ProfessorDAO extends BaseDAO<ProfessorVO> {

    @Override
    public void cadastrar(ProfessorVO vo) {
        Connection conn = getConnection();
        String sql = "insert into professor (nome, email, senha, data_criacao) values (?, ?, ?, ?)";
        PreparedStatement statement;

        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, vo.getNome());
            statement.setString(2, vo.getEmail());
            statement.setString(3, vo.getSenha());
            statement.setTimestamp(4, new Timestamp(vo.getDataCriacao().getTimeInMillis()));
            statement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet listar() {
        Connection conn = getConnection();
        String sql = "select * from professor";
        PreparedStatement statement;

        try {
            statement = conn.prepareStatement(sql);
            statement.execute();
            return statement.getResultSet();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ResultSet buscar(ProfessorVO vo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void editar(ProfessorVO vo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void excluir(ProfessorVO vo) {
        // TODO Auto-generated method stub

    }

}
