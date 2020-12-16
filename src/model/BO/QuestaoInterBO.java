package src.model.BO;

import java.util.List;

import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public interface QuestaoInterBO extends BaseInterBO<QuestaoVO> {

    public List<QuestaoVO> buscar(DisciplinaVO disciplina);

    public List<QuestaoVO> buscar(String assunto);

    public List<QuestaoVO> buscar(int dificuldade);

    public List<QuestaoVO> buscar(DisciplinaVO disciplina, int dificuldade);

    public List<QuestaoVO> buscar(String assunto, int dificuldade);

    public void adicionar(QuestaoVO questao, String assunto);

    public void remover(QuestaoVO questao, String assunto);

    public void adicionar(QuestaoVO questao, ProvaVO prova);

    public void remover(QuestaoVO questao, ProvaVO prova);

    public void mudar(QuestaoVO questao, DisciplinaVO disciplina);
}
