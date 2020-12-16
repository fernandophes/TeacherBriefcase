package src.model.DAO;

import java.sql.ResultSet;

public interface CleanBaseInterDAO<VO> {

    public ResultSet listar();

    public ResultSet buscar(VO vo);

    public void atualizar(VO vo);

    public void excluir(VO vo);
    
}
