package src.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import src.exception.OperationException;
import src.model.DAO.QuestaoDAO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoComAlternativasVO;
import src.model.VO.QuestaoSubjetivaVO;
import src.model.VO.QuestaoVO;

public class QuestaoBO extends BaseBO<QuestaoVO> implements QuestaoInterBO<QuestaoVO> {

    private static QuestaoDAO<QuestaoVO> questaoDAO = new QuestaoDAO<QuestaoVO>();
    private static QuestaoSubjetivaBO questaoSubjetivaBO = new QuestaoSubjetivaBO();
    private static QuestaoComAlternativasBO questaoComAlternativasBO = new QuestaoComAlternativasBO();

    @Override
    public void cadastrar(QuestaoVO questao) throws OperationException {
        if (questao != null)
            questaoDAO.cadastrar(questao);
        else
            throw new OperationException("A questão não pode ser nula.");
    }

    @Override
    public List<QuestaoVO> listar() throws OperationException {

        List<QuestaoVO> lista = new ArrayList<QuestaoVO>();

        // Apenas junta os dois tipos de questão
        lista.addAll(questaoSubjetivaBO.listar());
        lista.addAll(questaoComAlternativasBO.listar());

        return lista;
    }

    public QuestaoVO buscarRaiz(QuestaoVO questao) throws OperationException {
        if (questao != null) {
            ResultSet consulta = questaoDAO.buscar(questao);

            try {
                if (consulta != null)
                    while (consulta.next()) {
                        questao.setEnunciado(consulta.getString("enunciado"));
                        questao.setDificuldade(consulta.getInt("dificuldade"));
                        Calendar criacao = Calendar.getInstance();
                        criacao.setTime(consulta.getDate("data_criacao"));
                        questao.setDataCriacao(criacao);

                        AssuntoBO assuntoBO = new AssuntoBO();
                        questao.setAssuntos(assuntoBO.buscar(questao));

                        DisciplinaVO disciplina = new DisciplinaVO();
                        disciplina.setId(consulta.getLong("disciplina"));
                        DisciplinaBO disciplinaBO = new DisciplinaBO();
                        disciplina = disciplinaBO.buscar(disciplina);
                        questao.setDisciplina(disciplina);
                    }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (OperationException e) {
                e.printStackTrace();
            }
        } else
            throw new OperationException("A questão não pode ser nula.");

        return questao;
    }

    @Override
    public QuestaoVO buscar(QuestaoVO questao) throws OperationException {

        if (questao instanceof QuestaoSubjetivaVO) {
            questao = questaoSubjetivaBO.buscar((QuestaoSubjetivaVO) questao);
        } else if (questao instanceof QuestaoComAlternativasVO) {
            questao = questaoComAlternativasBO.buscar((QuestaoComAlternativasVO) questao);
        } else {
            questao = buscarRaiz(questao);
        }

        return questao;
    }

    @Override
    public List<QuestaoVO> buscar(DisciplinaVO disciplina) throws OperationException {
        // busca todas as questoes desta disciplina

        List<QuestaoVO> lista = new ArrayList<QuestaoVO>();

        if (disciplina != null) {
            lista.addAll(questaoSubjetivaBO.buscar(disciplina));
            lista.addAll(questaoComAlternativasBO.buscar(disciplina));
        } else
            throw new OperationException("A disciplina fornecida não pode ser nula.");

        return lista;
    }

    @Override
    public List<QuestaoVO> buscar(ProvaVO prova) throws OperationException {
        // busca todas as questoes desta disciplina

        List<QuestaoVO> lista = new ArrayList<QuestaoVO>();

        if (prova != null) {
            lista.addAll(questaoSubjetivaBO.buscar(prova));
            lista.addAll(questaoComAlternativasBO.buscar(prova));
        } else
            throw new OperationException("A disciplina fornecida não pode ser nula.");

        return lista;
    }

    @Override
    public List<QuestaoVO> buscar(String assunto) throws OperationException {
        // busca todas as questoes deste assunto

        List<QuestaoVO> lista = new ArrayList<QuestaoVO>();

        if (assunto != null && !assunto.isEmpty()) {
            lista.addAll(questaoSubjetivaBO.buscar(assunto));
            lista.addAll(questaoComAlternativasBO.buscar(assunto));
        } else
            throw new OperationException("O assunto fornecido não pode ser nulo ou vazio.");

        return lista;
    }

    @Override
    public List<QuestaoVO> buscarPorDificuldade(QuestaoVO questao) throws OperationException {
        // busca todas as questoes neste nível de dificuldade

        List<QuestaoVO> lista = new ArrayList<QuestaoVO>();
        lista.addAll(questaoSubjetivaBO.buscarPorDificuldade((QuestaoSubjetivaVO)questao));
        lista.addAll(questaoComAlternativasBO.buscarPorDificuldade((QuestaoComAlternativasVO)questao));

        return lista;
    }

    @Override
    public List<QuestaoVO> buscarPorDificuldade(QuestaoVO questao, String assunto) throws OperationException {
        // busca todas as questoes deste assunto neste nível de dificuldade

        List<QuestaoVO> lista = new ArrayList<QuestaoVO>();

        if (assunto != null && !assunto.isEmpty()) {
            lista.addAll(questaoSubjetivaBO.buscarPorDificuldade((QuestaoSubjetivaVO)questao, assunto));
            lista.addAll(questaoComAlternativasBO.buscarPorDificuldade((QuestaoComAlternativasVO)questao, assunto));
        } else
            throw new OperationException("O assunto fornecido não pode ser nulo ou vazio.");

        return lista;
    }

    @Override
    public List<QuestaoVO> buscarPorDificuldadeEDisciplina(QuestaoVO questao) throws OperationException {
        // busca todas as questoes desta disciplina neste nível de dificuldade

        List<QuestaoVO> lista = new ArrayList<QuestaoVO>();

        if (questao != null && questao.getDisciplina() != null) {
            lista.addAll(questaoSubjetivaBO.buscarPorDificuldadeEDisciplina((QuestaoSubjetivaVO)questao));
            lista.addAll(questaoComAlternativasBO.buscarPorDificuldadeEDisciplina((QuestaoComAlternativasVO)questao));
        } else
            throw new OperationException("A disciplina fornecida não pode ser nula.");

        return lista;
    }

    @Override
    public void atualizar(QuestaoVO questao) throws OperationException {
        if (questao != null) {
            if (questao instanceof QuestaoSubjetivaVO)
                questaoSubjetivaBO.atualizar((QuestaoSubjetivaVO) questao);
            else if (questao instanceof QuestaoComAlternativasVO)
                questaoComAlternativasBO.atualizar((QuestaoComAlternativasVO) questao);
            else
                questaoDAO.atualizar(questao);
        } else
            throw new OperationException("A questão não pode ser nula.");
    }

    @Override
    public void excluir(QuestaoVO questao) throws OperationException {
        if (questao != null) {
            if (questao instanceof QuestaoSubjetivaVO)
                questaoSubjetivaBO.excluir((QuestaoSubjetivaVO) questao);
            else if (questao instanceof QuestaoComAlternativasVO)
                questaoComAlternativasBO.excluir((QuestaoComAlternativasVO) questao);
            else
                questaoDAO.excluir(questao);
        } else
            throw new OperationException("A questão não pode ser nula.");
    }

    @Override
    public void adicionar(QuestaoVO questao, String assunto) {

        List<String> lista = questao.getAssuntos();

        // Se este assunto não estiver na lista desta questão, poderá ou não ser
        // adicionado
        if (!lista.contains(assunto))
            // Este assunto só será adicionado a esta questão se ambos pertencerem à mesma
            // disciplina
            if (questao.getDisciplina().getAssuntos().contains(assunto)) {
                lista.add(assunto);
                questao.setAssuntos(lista);

                questaoDAO.adicionar(questao, assunto);
            }
    }

    @Override
    public void remover(QuestaoVO questao, String assunto) {

        List<String> lista = questao.getAssuntos();

        // Se este assunto estiver na lista deste assunto, será removido
        if (lista.remove(assunto)) {
            questao.setAssuntos(lista);

            questaoDAO.remover(questao, assunto);
        }
    }

    @Override
    public void atualizar(QuestaoVO questao, DisciplinaVO disciplina) {

        questao.setDisciplina(disciplina);
        questaoDAO.atualizar(questao);

    }
}
