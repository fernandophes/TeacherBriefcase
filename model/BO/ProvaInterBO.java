package model.BO;

import model.VO.ProvaVO;
import model.VO.QuestaoVO;

public interface ProvaInterBO extends BaseInterBO<ProvaVO> {

    public void adicionar(ProvaVO prova, QuestaoVO questao);

    public void remover(ProvaVO prova, QuestaoVO questao);
}
