package src.model.DAO;

import java.sql.ResultSet;

import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public interface QuestaoInterDAO<QuestaoDerivadaVO extends QuestaoVO> extends BaseInterDAO<QuestaoDerivadaVO> {
    
    public ResultSet buscar(DisciplinaVO disciplina);
    
    public ResultSet buscar(ProvaVO prova);
    
    public ResultSet buscar(String assunto);
    
    public ResultSet buscarPorDificuldade(QuestaoDerivadaVO questao);
    
    public ResultSet buscarPorDificuldade(QuestaoDerivadaVO questao, String assunto);
    
    public ResultSet buscarPorDificuldadeEDisciplina(QuestaoDerivadaVO questao);

}
