package model.BO;

import model.VO.DisciplinaVO;
import model.VO.ProfessorVO;

public interface ProfessorInterBO extends BaseInterBO<ProfessorVO> {

    public boolean autenticar(ProfessorVO professor);

    public void adicionar(ProfessorVO professor, DisciplinaVO disciplina);

    public void remover(ProfessorVO professor, DisciplinaVO disciplina);
}
