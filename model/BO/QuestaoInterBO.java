package model.BO;

import model.VO.AssuntoVO;
import model.VO.ProvaVO;
import model.VO.QuestaoVO;

public interface QuestaoInterBO extends BaseInterBO<QuestaoVO> {

    public void adicionar(QuestaoVO questao, AssuntoVO assunto);

    public void remover(QuestaoVO questao, AssuntoVO assunto);

    public void adicionar(QuestaoVO questao, ProvaVO prova);

    public void remover(QuestaoVO questao, ProvaVO prova);
}
