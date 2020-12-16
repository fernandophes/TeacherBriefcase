package src.model.BO;

import java.util.List;

import src.exception.AuthenticationException;
import src.exception.OperationException;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;

public interface ProfessorInterBO extends BaseInterBO<ProfessorVO> {

    public ProfessorVO autenticar(ProfessorVO professor) throws AuthenticationException;

    public ProfessorVO buscarPorEmail(ProfessorVO professor) throws AuthenticationException;

    public List<ProfessorVO> buscar(DisciplinaVO disciplina) throws AuthenticationException, OperationException;

    public void adicionar(ProfessorVO professor, DisciplinaVO disciplina);

    public void remover(ProfessorVO professor, DisciplinaVO disciplina);
}
