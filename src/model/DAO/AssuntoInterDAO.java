package src.model.DAO;

import java.sql.ResultSet;

import src.exception.AuthenticationException;
import src.model.VO.DisciplinaVO;
import src.model.VO.QuestaoVO;

public interface AssuntoInterDAO {
    
    public void cadastrar(DisciplinaVO disciplina, String assunto) throws AuthenticationException;
    
    public ResultSet listar();

    public ResultSet buscar(DisciplinaVO disciplina, String assunto);

    public ResultSet buscar(DisciplinaVO disciplina);

    public ResultSet buscar(QuestaoVO questao);

    public void atualizar(DisciplinaVO disciplina, String assunto, String novo);

    public void excluir(DisciplinaVO disciplina, String assunto);
    
}
