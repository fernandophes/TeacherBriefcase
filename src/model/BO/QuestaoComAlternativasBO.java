package src.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.exception.OperationException;
import src.model.DAO.QuestaoComAlternativasDAO;
import src.model.VO.AlternativaVO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoComAlternativasVO;
import src.model.VO.QuestaoVO;

public class QuestaoComAlternativasBO implements QuestaoInterBO<QuestaoComAlternativasVO> {

    private QuestaoBO questaoBO = new QuestaoBO();
    private QuestaoComAlternativasDAO questaoComAlternativasDAO = new QuestaoComAlternativasDAO();

    public void adicionar(QuestaoComAlternativasVO questao, AlternativaVO alternativa) throws OperationException {
        // adiciona uma alternativa à questão

        List<AlternativaVO> lista = questao.getAlternativas();

        // Se esta alternativa não estiver na lista desta questão, será adicionada
        if (!lista.contains(alternativa)) {
            lista.add(alternativa);
            questao.setAlternativas(lista);

            AlternativaBO alternativaBO = new AlternativaBO();
            alternativaBO.cadastrar(alternativa, questao);
        }
    }

    public void remover(QuestaoComAlternativasVO questao, AlternativaVO alternativa) throws OperationException {
        // remove a alternativa da questão

        List<AlternativaVO> lista = questao.getAlternativas();

        // Se esta alternativa estiver na lista desta questão, será removida
        if (lista.remove(alternativa)) {
            questao.setAlternativas(lista);

            // atualiza a alternativa (exclui, pois ela depende do vínculo com a questão)
            AlternativaBO alternativaBO = new AlternativaBO();
            alternativaBO.excluir(alternativa);
        }
    }

    @Override
    public void cadastrar(QuestaoComAlternativasVO questao) throws OperationException {
        if (questao != null) {
            questaoBO.cadastrar(questao);
            questaoComAlternativasDAO.cadastrar(questao);
        } else
            throw new OperationException("A questão não pode ser nula.");
    }

    @Override
    public List<QuestaoComAlternativasVO> listar() throws OperationException {

        List<QuestaoComAlternativasVO> lista = new ArrayList<QuestaoComAlternativasVO>();

        ResultSet consulta = questaoComAlternativasDAO.listar();

        if (consulta != null)
            try {
                while (consulta.next()) {
                    QuestaoComAlternativasVO atual = new QuestaoComAlternativasVO();
                    atual = buscar(atual);
                    lista.add(atual);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return lista;
    }

    @Override
    public QuestaoComAlternativasVO buscar(QuestaoComAlternativasVO questao) throws OperationException {
        if (questao != null) {
            ResultSet consulta = questaoComAlternativasDAO.buscar(questao);

            try {
                if (consulta != null)
                    if (consulta.next()) {
                        QuestaoVO raiz = questaoBO.buscar(questao);
                        questao.setAssuntos(raiz.getAssuntos());
                        questao.setDataCriacao(raiz.getDataCriacao());
                        questao.setDificuldade(raiz.getDificuldade());
                        questao.setDisciplina(raiz.getDisciplina());
                        questao.setEnunciado(raiz.getEnunciado());

                        AlternativaBO alternativaBO = new AlternativaBO();
                        questao.setAlternativas(alternativaBO.buscar(questao));
                    }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            throw new OperationException("A questão não pode ser nula.");

        return questao;
    }

    @Override
    public List<QuestaoComAlternativasVO> buscar(DisciplinaVO disciplina) throws OperationException {
        // busca todas as questoes desta disciplina

        List<QuestaoComAlternativasVO> lista = new ArrayList<QuestaoComAlternativasVO>();

        if (disciplina != null) {
            ResultSet consulta = questaoComAlternativasDAO.buscar(disciplina);

            if (consulta != null)
                try {
                    while (consulta.next()) {
                        QuestaoComAlternativasVO atual = new QuestaoComAlternativasVO();
                        atual = buscar(atual);
                        lista.add(atual);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        } else
            throw new OperationException("A disciplina fornecida não pode ser nula.");

        return lista;
    }

    @Override
    public List<QuestaoComAlternativasVO> buscar(ProvaVO prova) throws OperationException {

        List<QuestaoComAlternativasVO> lista = new ArrayList<QuestaoComAlternativasVO>();

        if (prova != null) {
            ResultSet consulta = questaoComAlternativasDAO.buscar(prova);

            if (consulta != null)
                try {
                    while (consulta.next()) {
                        QuestaoComAlternativasVO atual = new QuestaoComAlternativasVO();
                        atual = buscar(atual);
                        lista.add(atual);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        } else
            throw new OperationException("A disciplina fornecida não pode ser nula.");

        return lista;
    }

    @Override
    public List<QuestaoComAlternativasVO> buscar(String assunto) throws OperationException {
        // busca todas as questoes deste assunto

        List<QuestaoComAlternativasVO> lista = new ArrayList<QuestaoComAlternativasVO>();

        if (assunto != null && !assunto.isEmpty()) {
            ResultSet consulta = questaoComAlternativasDAO.buscar(assunto);

            if (consulta != null)
                try {
                    while (consulta.next()) {
                        QuestaoComAlternativasVO atual = new QuestaoComAlternativasVO();
                        atual = buscar(atual);
                        lista.add(atual);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        } else
            throw new OperationException("O assunto fornecido não pode ser nulo ou vazio.");

        return lista;
    }

    @Override
    public List<QuestaoComAlternativasVO> buscarPorDificuldade(QuestaoComAlternativasVO questao)
            throws OperationException {
        // busca todas as questoes neste nível de dificuldade

        List<QuestaoComAlternativasVO> lista = new ArrayList<QuestaoComAlternativasVO>();
        ResultSet consulta = questaoComAlternativasDAO.buscarPorDificuldade(questao);

        if (consulta != null)
            try {
                while (consulta.next()) {
                    QuestaoComAlternativasVO atual = new QuestaoComAlternativasVO();
                    atual = buscar(atual);
                    lista.add(atual);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return lista;
    }

    @Override
    public List<QuestaoComAlternativasVO> buscarPorDificuldadeEDisciplina(QuestaoComAlternativasVO questao)
            throws OperationException {
        // busca todas as questoes desta disciplina neste nível de dificuldade

        List<QuestaoComAlternativasVO> lista = new ArrayList<QuestaoComAlternativasVO>();

        if (questao != null && questao.getDisciplina() != null) {
            ResultSet consulta = questaoComAlternativasDAO.buscarPorDificuldadeEDisciplina(questao);

            if (consulta != null)
                try {
                    while (consulta.next()) {
                        QuestaoComAlternativasVO atual = new QuestaoComAlternativasVO();
                        atual = buscar(atual);
                        lista.add(atual);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        } else
            throw new OperationException("A questão e a disciplina fornecidas não podem ser nulas.");

        return lista;
    }

    @Override
    public List<QuestaoComAlternativasVO> buscarPorDificuldade(QuestaoComAlternativasVO questao, String assunto)
            throws OperationException {
        // busca todas as questoes deste assunto neste nível de dificuldade

        List<QuestaoComAlternativasVO> lista = new ArrayList<QuestaoComAlternativasVO>();

        if (assunto != null && !assunto.isEmpty()) {
            ResultSet consulta = questaoComAlternativasDAO.buscarPorDificuldade(questao, assunto);

            if (consulta != null)
                try {
                    while (consulta.next()) {
                        QuestaoComAlternativasVO atual = new QuestaoComAlternativasVO();
                        atual = buscar(atual);
                        lista.add(atual);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        } else
            throw new OperationException("O assunto fornecido não pode ser nulo ou vazio.");

        return lista;
    }

    @Override
    public void atualizar(QuestaoComAlternativasVO questao) throws OperationException {
        if (questao != null) {
            questaoComAlternativasDAO.atualizar(questao);
        } else
            throw new OperationException("A questão não pode ser nula.");
    }

    @Override
    public void excluir(QuestaoComAlternativasVO questao) throws OperationException {
        if (questao != null) {
            questaoComAlternativasDAO.excluir(questao);
        } else
            throw new OperationException("A questão não pode ser nula.");
    }

    @Override
    public void adicionar(QuestaoComAlternativasVO questao, String assunto) {
        questaoBO.adicionar(questao, assunto);
    }

    @Override
    public void remover(QuestaoComAlternativasVO questao, String assunto) {
        questaoBO.remover(questao, assunto);
    }

    @Override
    public void atualizar(QuestaoComAlternativasVO questao, DisciplinaVO disciplina) {
        questaoBO.atualizar(questao, disciplina);
    }

}
