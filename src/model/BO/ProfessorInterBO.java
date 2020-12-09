package src.model.BO;

import src.exception.AuthenticationException;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;

public interface ProfessorInterBO extends BaseInterBO<ProfessorVO> {

    public ProfessorVO autenticar(ProfessorVO professor) throws AuthenticationException;

    @Override
    public ProfessorVO buscar(ProfessorVO professor) throws AuthenticationException;

    public ProfessorVO buscarPorEmail(ProfessorVO professor) throws AuthenticationException;

    public void adicionar(ProfessorVO professor, DisciplinaVO disciplina);

    public void remover(ProfessorVO professor, DisciplinaVO disciplina);
}
