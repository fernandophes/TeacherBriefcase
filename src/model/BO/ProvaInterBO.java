package src.model.BO;

import java.util.List;

import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public interface ProvaInterBO extends BaseInterBO<ProvaVO> {

    public List<ProvaVO> buscar(DisciplinaVO disciplina);

    public ProvaVO gerar(int quaisquer, int faceis, int medias, int dificeis);

    public void adicionar(ProvaVO prova, QuestaoVO questao);

    public void remover(ProvaVO prova, QuestaoVO questao);
}
