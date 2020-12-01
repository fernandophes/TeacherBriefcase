package src.model.DAO;

import java.sql.ResultSet;

public interface BaseInterDAO<VO> {
    public void cadastrar(VO vo);

    public ResultSet listar();

    public ResultSet buscar(VO vo);

    public void editar(VO vo);

    public void excluir(VO vo);
}
