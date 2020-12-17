package src.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.exception.OperationException;
import src.model.DAO.QuestaoSubjetivaDAO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoSubjetivaVO;
import src.model.VO.QuestaoVO;

public class QuestaoSubjetivaBO extends BaseBO<QuestaoSubjetivaVO> implements QuestaoInterBO<QuestaoSubjetivaVO> {

    private static QuestaoBO questaoBO = new QuestaoBO();
    private static QuestaoSubjetivaDAO questaoSubjetivaDAO = new QuestaoSubjetivaDAO();

    @Override
    public void cadastrar(QuestaoSubjetivaVO questao) throws OperationException {
        if (questao != null) {
            questaoSubjetivaDAO.cadastrar(questao);
        } else
            throw new OperationException("A questão não pode ser nula.");
    }

    @Override
    public List<QuestaoSubjetivaVO> listar() throws OperationException {

        List<QuestaoSubjetivaVO> lista = new ArrayList<QuestaoSubjetivaVO>();

        ResultSet consulta = questaoSubjetivaDAO.listar();

        if (consulta != null)
            try {
                while (consulta.next()) {
                    QuestaoSubjetivaVO atual = new QuestaoSubjetivaVO();
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
    public QuestaoSubjetivaVO buscar(QuestaoSubjetivaVO questao) throws OperationException {
        if (questao != null) {
            ResultSet consulta = questaoSubjetivaDAO.buscar(questao);

            try {
                if (consulta != null)
                    if (consulta.next()) {
                        QuestaoVO raiz = questaoBO.buscarRaiz(questao);
                        questao.setAssuntos(raiz.getAssuntos());
                        questao.setDataCriacao(raiz.getDataCriacao());
                        questao.setDificuldade(raiz.getDificuldade());
                        questao.setDisciplina(raiz.getDisciplina());
                        questao.setEnunciado(raiz.getEnunciado());

                        questao.setGabarito(consulta.getString("gabarito"));
                    }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            throw new OperationException("A questão não pode ser nula.");

        return questao;
    }

    @Override
    public List<QuestaoSubjetivaVO> buscar(DisciplinaVO disciplina) throws OperationException {
        // busca todas as questoes desta disciplina

        List<QuestaoSubjetivaVO> lista = new ArrayList<QuestaoSubjetivaVO>();

        if (disciplina != null) {
            ResultSet consulta = questaoSubjetivaDAO.buscar(disciplina);

            if (consulta != null)
                try {
                    while (consulta.next()) {
                        QuestaoSubjetivaVO atual = new QuestaoSubjetivaVO();
                        atual.setId(consulta.getLong("id"));
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
    public List<QuestaoSubjetivaVO> buscar(ProvaVO prova) throws OperationException {

        List<QuestaoSubjetivaVO> lista = new ArrayList<QuestaoSubjetivaVO>();

        if (prova != null) {
            ResultSet consulta = questaoSubjetivaDAO.buscar(prova);

            if (consulta != null)
                try {
                    while (consulta.next()) {
                        QuestaoSubjetivaVO atual = new QuestaoSubjetivaVO();
                        atual.setId(consulta.getLong("id"));
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
    public List<QuestaoSubjetivaVO> buscar(String assunto) throws OperationException {
        // busca todas as questoes deste assunto

        List<QuestaoSubjetivaVO> lista = new ArrayList<QuestaoSubjetivaVO>();

        if (assunto != null && !assunto.isEmpty()) {
            ResultSet consulta = questaoSubjetivaDAO.buscar(assunto);

            if (consulta != null)
                try {
                    while (consulta.next()) {
                        QuestaoSubjetivaVO atual = new QuestaoSubjetivaVO();
                        atual.setId(consulta.getLong("id"));
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
    public List<QuestaoSubjetivaVO> buscarPorDificuldade(QuestaoSubjetivaVO questao) throws OperationException {
        // busca todas as questoes neste nível de dificuldade

        List<QuestaoSubjetivaVO> lista = new ArrayList<QuestaoSubjetivaVO>();
        ResultSet consulta = questaoSubjetivaDAO.buscarPorDificuldade(questao);

        if (consulta != null)
            try {
                while (consulta.next()) {
                    QuestaoSubjetivaVO atual = new QuestaoSubjetivaVO();
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
    public List<QuestaoSubjetivaVO> buscarPorDificuldadeEDisciplina(QuestaoSubjetivaVO questao) throws OperationException {
        // busca todas as questoes desta disciplina neste nível de dificuldade

        List<QuestaoSubjetivaVO> lista = new ArrayList<QuestaoSubjetivaVO>();

        if (questao != null && questao.getDisciplina() != null) {
            ResultSet consulta = questaoSubjetivaDAO.buscarPorDificuldadeEDisciplina(questao);

            if (consulta != null)
                try {
                    while (consulta.next()) {
                        QuestaoSubjetivaVO atual = new QuestaoSubjetivaVO();
                        atual.setId(consulta.getLong("id"));
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
    public List<QuestaoSubjetivaVO> buscarPorDificuldade(QuestaoSubjetivaVO questao, String assunto)
            throws OperationException {
        // busca todas as questoes deste assunto neste nível de dificuldade

        List<QuestaoSubjetivaVO> lista = new ArrayList<QuestaoSubjetivaVO>();

        if (assunto != null && !assunto.isEmpty()) {
            ResultSet consulta = questaoSubjetivaDAO.buscarPorDificuldade(questao, assunto);

            if (consulta != null)
                try {
                    while (consulta.next()) {
                        QuestaoSubjetivaVO atual = new QuestaoSubjetivaVO();
                        atual.setId(consulta.getLong("id"));
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
    public void atualizar(QuestaoSubjetivaVO questao) throws OperationException {
        if (questao != null) {
            questaoSubjetivaDAO.atualizar(questao);
        } else
            throw new OperationException("A questão não pode ser nula.");
    }

    @Override
    public void excluir(QuestaoSubjetivaVO questao) throws OperationException {
        if (questao != null) {
            questaoSubjetivaDAO.excluir(questao);
        } else
            throw new OperationException("A questão não pode ser nula.");
    }

    @Override
    public void adicionar(QuestaoSubjetivaVO questao, String assunto) {
        questaoBO.adicionar(questao, assunto);
    }

    @Override
    public void remover(QuestaoSubjetivaVO questao, String assunto) {
        questaoBO.remover(questao, assunto);
    }

    @Override
    public void atualizar(QuestaoSubjetivaVO questao, DisciplinaVO disciplina) {
        questaoBO.atualizar(questao, disciplina);
    }

}
