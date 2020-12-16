package src.model.BO;

import java.util.List;

import src.exception.AuthenticationException;
import src.exception.OperationException;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public interface DisciplinaInterBO extends BaseInterBO<DisciplinaVO> {

    public List<DisciplinaVO> buscar(ProfessorVO professor) throws AuthenticationException, OperationException;    

    public DisciplinaVO buscarPorCodigo(DisciplinaVO disciplina);

    public List<DisciplinaVO> buscarPorNome(DisciplinaVO disciplina);

    public void adicionar(DisciplinaVO disciplina, String assunto) throws OperationException;

    public void remover(DisciplinaVO disciplina, String assunto) throws OperationException;

    public void adicionar(DisciplinaVO disciplina, ProfessorVO professor);

    public void remover(DisciplinaVO disciplina, ProfessorVO professor);

    public void adicionar(DisciplinaVO disciplina, QuestaoVO questao);

    public void remover(DisciplinaVO disciplina, QuestaoVO questao);

    public void adicionar(DisciplinaVO disciplina, ProvaVO prova);

    public void remover(DisciplinaVO disciplina, ProvaVO prova);

}
