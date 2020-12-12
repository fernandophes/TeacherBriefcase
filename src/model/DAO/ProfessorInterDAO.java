package src.model.DAO;

import java.sql.ResultSet;

import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;

public interface ProfessorInterDAO extends BaseInterDAO<ProfessorVO> {

    public ResultSet buscarPorEmail(ProfessorVO vo);

    public ResultSet buscar(DisciplinaVO disciplina);

    public void adicionar(ProfessorVO professor, DisciplinaVO disciplina);

    public void remover(ProfessorVO professor, DisciplinaVO disciplina);

}
