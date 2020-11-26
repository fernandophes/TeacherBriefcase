package model.BO;

import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import model.VO.ProfessorVO;
import model.VO.ProvaVO;
import model.VO.QuestaoVO;

public interface DisciplinaInterBO extends BaseInterBO<DisciplinaVO> {

    public void adicionar(DisciplinaVO disciplina, AssuntoVO assunto);

    public void remover(DisciplinaVO disciplina, AssuntoVO assunto);

    public void adicionar(DisciplinaVO disciplina, ProfessorVO professor);

    public void remover(DisciplinaVO disciplina, ProfessorVO professor);

    public void adicionar(DisciplinaVO disciplina, QuestaoVO questao);

    public void remover(DisciplinaVO disciplina, QuestaoVO questao);

    public void adicionar(DisciplinaVO disciplina, ProvaVO prova);

    public void remover(DisciplinaVO disciplina, ProvaVO prova);

}