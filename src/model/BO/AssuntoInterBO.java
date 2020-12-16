package src.model.BO;

import java.util.List;

import src.exception.OperationException;
import src.model.VO.DisciplinaVO;
import src.model.VO.QuestaoVO;

public interface AssuntoInterBO {
    
    public void cadastrar(DisciplinaVO disciplina, String assunto) throws OperationException;
    
    public List<List<String>> listar();

    public String buscar(DisciplinaVO disciplina, String assunto) throws OperationException;

    public List<String> buscar(DisciplinaVO disciplina) throws OperationException;

    public List<String> buscar(QuestaoVO questao) throws OperationException;

    public void atualizar(DisciplinaVO disciplina, String assunto, String novo) throws OperationException;

    public void excluir(DisciplinaVO disciplina, String assunto) throws OperationException;
    
}
