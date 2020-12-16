package src.model.DAO;

import java.sql.ResultSet;

import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;

public interface DisciplinaInterDAO extends BaseInterDAO<DisciplinaVO> {
    
    public ResultSet buscar(ProfessorVO professor);

    public ResultSet buscarPorCodigo(DisciplinaVO disciplina);

    public ResultSet buscarPorNome(DisciplinaVO disciplina);

}
