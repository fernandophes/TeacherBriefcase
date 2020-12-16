package src.model.DAO;

import java.sql.ResultSet;

import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public interface ProvaQuestaoInterDAO {
    
    public void adicionar(ProvaVO prova, QuestaoVO questao);

    public ResultSet buscar(ProvaVO prova);

    public void remover(ProvaVO prova, QuestaoVO questao);
    
}
