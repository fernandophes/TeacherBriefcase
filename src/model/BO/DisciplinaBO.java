package src.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import src.exception.OperationException;
import src.model.DAO.DisciplinaDAO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;
import src.model.VO.QuestaoVO;

public class DisciplinaBO extends BaseBO<DisciplinaVO> implements DisciplinaInterBO {

    private static DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private static QuestaoBO questaoBO = new QuestaoBO();
    private static AssuntoBO assuntoBO = new AssuntoBO();

    @Override
    public void cadastrar(DisciplinaVO disciplina) throws OperationException {
        disciplinaDAO.cadastrar(disciplina);
    }

    @Override
    public List<DisciplinaVO> listar() throws OperationException {

        List<DisciplinaVO> lista = new ArrayList<DisciplinaVO>();

        ResultSet consulta = disciplinaDAO.listar();

        try {
            if (consulta != null)
                while (consulta.next()) {
                    DisciplinaVO atual = new DisciplinaVO();
                    atual.setId(consulta.getLong("id"));
                    atual = buscar(atual);
                    lista.add(atual);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public DisciplinaVO buscar(DisciplinaVO disciplina) {

        ResultSet consulta = disciplinaDAO.buscar(disciplina);

        try {
            if (consulta != null)
                while (consulta.next()) {
                    disciplina.setCodigo(consulta.getString("codigo"));
                    disciplina.setNome(consulta.getString("nome"));
                    Calendar criacao = Calendar.getInstance();
                    criacao.setTime(consulta.getDate("data_criacao"));
                    disciplina.setDataCriacao(criacao);

                    disciplina.setAssuntos(assuntoBO.buscar(disciplina));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (OperationException e) {
            e.printStackTrace();
        }

        return disciplina;
    }

    @Override
    public List<DisciplinaVO> buscar(ProfessorVO professor) {

        List<DisciplinaVO> lista = new ArrayList<DisciplinaVO>();

        ResultSet consulta = disciplinaDAO.buscar(professor);

        try {
            if (consulta != null)
                while (consulta.next()) {
                    DisciplinaVO atual = new DisciplinaVO();
                    atual.setId(consulta.getLong("id"));
                    atual = buscar(atual);
                    lista.add(atual);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (OperationException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public DisciplinaVO buscarPorCodigo(DisciplinaVO disciplina) {

        ResultSet consulta = disciplinaDAO.buscarPorCodigo(disciplina);

        try {
            if (consulta != null)
                while (consulta.next()) {
                    disciplina.setId(consulta.getLong("id"));
                    disciplina = buscar(disciplina);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (OperationException e) {
            e.printStackTrace();
        }

        return disciplina;
    }

    @Override
    public List<DisciplinaVO> buscarPorNome(DisciplinaVO disciplina) {

        List<DisciplinaVO> lista = new ArrayList<DisciplinaVO>();

        ResultSet consulta = disciplinaDAO.buscarPorNome(disciplina);

        try {
            if (consulta != null)
                while (consulta.next()) {
                    DisciplinaVO atual = new DisciplinaVO();
                    atual.setId(consulta.getLong("id"));
                    atual = buscar(atual);
                    lista.add(atual);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (OperationException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void atualizar(DisciplinaVO disciplina) throws OperationException {

        if (disciplina != null)
            disciplinaDAO.atualizar(disciplina);
        else
            throw new OperationException("A disciplina fornecida não pode ser nula.");

    }

    @Override
    public void excluir(DisciplinaVO disciplina) throws OperationException {

        if (disciplina != null)
            disciplinaDAO.excluir(disciplina);
        else
            throw new OperationException("A disciplina fornecida não pode ser nula.");

    }

    @Override
    public void adicionar(DisciplinaVO disciplina, String assunto) throws OperationException {

        List<String> lista = disciplina.getAssuntos();

        // Se este assunto não estiver na lista desta disciplina, ele será adicionado
        if (!lista.contains(assunto)) {
            lista.add(assunto);
            disciplina.setAssuntos(lista);

            // Adiciona ao Banco de Dados
            assuntoBO.cadastrar(disciplina, assunto);

        }
    }

    @Override
    public void remover(DisciplinaVO disciplina, String assunto) throws OperationException {

        List<String> lista = disciplina.getAssuntos();

        // Se este assunto estiver na lista desta disciplina, ele será removido
        if (lista.remove(assunto)) {
            disciplina.setAssuntos(lista);

            // Atualizar as questões (remover este assunto da lista de cada uma)
            List<QuestaoVO> questoes = questaoBO.buscar(disciplina);

            Iterator<QuestaoVO> questoesIt = questoes.iterator();
            while (questoesIt.hasNext()) {
                questaoBO.remover(questoesIt.next(), assunto);
            }

            // Remove do Banco de Dados
            assuntoBO.excluir(disciplina, assunto);

        }
    }

    @Override
    public void adicionar(DisciplinaVO disciplina, QuestaoVO questao) throws OperationException {

        List<QuestaoVO> lista = questaoBO.buscar(disciplina);

        // Se esta questão não estiver na lista desta disciplina, será adicionada
        if (!questaoBO.contem(lista, questao))
            questaoBO.atualizar(questao, disciplina);

    }

}
