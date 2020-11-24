package model.BO;

import model.VO.AssuntoVO;
import model.VO.QuestaoVO;

public interface AssuntoInterBO extends BaseInterBO<AssuntoVO> {

    public void adicionar(AssuntoVO assunto, QuestaoVO questao);

    public void remover(AssuntoVO assunto, QuestaoVO questao);

}
