package src.model.DAO;

import java.sql.ResultSet;

import src.exception.AuthenticationException;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public interface ProvaInterDAO extends BaseInterDAO<ProvaVO> {
    
    public ResultSet buscar(DisciplinaVO disciplina) throws AuthenticationException;

    public void adicionar(ProvaVO prova, QuestaoVO questao);

    public void remover(ProvaVO prova, QuestaoVO questao);

}
