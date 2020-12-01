package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import src.model.VO.ProfessorVO;

public class ProfessorDAO extends BaseDAO<ProfessorVO> {

    @Override
    public void cadastrar(ProfessorVO vo) {
        conn = getConnection();
        String sql = "insert into professor (nome, email, senha, data_criacao) values (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, vo.getNome());
        statement.setString(2, vo.getEmail());
        statement.setString(3, vo.getSenha());
        statement.setString(4, vo.getDataCriacao());
    }

    @Override
    public ResultSet listar() {
        // TODO Auto-generated method stub
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
