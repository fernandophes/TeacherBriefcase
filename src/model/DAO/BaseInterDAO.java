package src.model.DAO;

import java.sql.ResultSet;

public interface BaseInterDAO<VO> {
    public void cadastrar(VO vo) throws Exception;

    public ResultSet listar();

    public ResultSet buscar(VO vo);

    public void atualizar(VO vo);

    public void excluir(VO vo);
}
