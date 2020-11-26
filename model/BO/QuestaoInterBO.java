package model.BO;

import model.VO.ProvaVO;
import model.VO.QuestaoVO;

public interface QuestaoInterBO extends BaseInterBO<QuestaoVO> {

    public void adicionar(QuestaoVO questao, String assunto);

    public void remover(QuestaoVO questao, String assunto);

    public void adicionar(QuestaoVO questao, ProvaVO prova);

    public void remover(QuestaoVO questao, ProvaVO prova);
}
