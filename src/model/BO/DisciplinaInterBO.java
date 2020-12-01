package src.model.BO;

import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public interface DisciplinaInterBO extends BaseInterBO<DisciplinaVO> {

    public void adicionar(DisciplinaVO disciplina, String assunto);

    public void remover(DisciplinaVO disciplina, String assunto);

    public void adicionar(DisciplinaVO disciplina, ProfessorVO professor);

    public void remover(DisciplinaVO disciplina, ProfessorVO professor);

    public void adicionar(DisciplinaVO disciplina, QuestaoVO questao);

    public void remover(DisciplinaVO disciplina, QuestaoVO questao);

    public void adicionar(DisciplinaVO disciplina, ProvaVO prova);

    public void remover(DisciplinaVO disciplina, ProvaVO prova);

}
