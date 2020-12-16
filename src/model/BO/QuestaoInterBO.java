package src.model.BO;

import java.util.List;

import src.exception.OperationException;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public interface QuestaoInterBO<QuestaoDerivadaVO extends QuestaoVO> extends BaseInterBO<QuestaoDerivadaVO> {

    public List<QuestaoDerivadaVO> buscar(DisciplinaVO disciplina) throws OperationException;

    public List<QuestaoDerivadaVO> buscar(String assunto) throws OperationException;
    
    public List<QuestaoDerivadaVO> buscar(ProvaVO prova) throws OperationException;

    public List<QuestaoDerivadaVO> buscarPorDificuldade(QuestaoDerivadaVO questao) throws OperationException;

    public List<QuestaoDerivadaVO> buscarPorDificuldade(QuestaoDerivadaVO questao, String assunto) throws OperationException;

    public List<QuestaoDerivadaVO> buscarPorDificuldadeEDisciplina(QuestaoDerivadaVO questao) throws OperationException;

    public void adicionar(QuestaoDerivadaVO questao, String assunto);

    public void remover(QuestaoDerivadaVO questao, String assunto);

    public void atualizar(QuestaoDerivadaVO questao, DisciplinaVO disciplina);
}
