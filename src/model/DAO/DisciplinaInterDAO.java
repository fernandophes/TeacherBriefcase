package src.model.DAO;

import java.sql.ResultSet;

import src.model.VO.DisciplinaVO;

public interface DisciplinaInterDAO extends BaseInterDAO<DisciplinaVO> {
    
    public ResultSet buscarPorCodigo(DisciplinaVO vo);
    public ResultSet buscarPorNome(DisciplinaVO vo);

}
