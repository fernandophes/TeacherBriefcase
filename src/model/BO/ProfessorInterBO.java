package src.model.BO;

import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;

public interface ProfessorInterBO extends BaseInterBO<ProfessorVO> {

    public boolean autenticar(ProfessorVO professor);

    public void adicionar(ProfessorVO professor, DisciplinaVO disciplina);

    public void remover(ProfessorVO professor, DisciplinaVO disciplina);
}
