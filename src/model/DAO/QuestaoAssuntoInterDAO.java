package src.model.DAO;

import java.sql.ResultSet;

import src.model.VO.QuestaoVO;

public interface QuestaoAssuntoInterDAO {
    
    public void adicionar(QuestaoVO questao, String assunto);

    // Buscar assuntos da questao
    public ResultSet buscar(QuestaoVO questao);

    // Buscar questoes desse assunto
    public ResultSet buscar(String assunto);

    public void remover(QuestaoVO questao, String assunto);
}
