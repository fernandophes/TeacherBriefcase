package src.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import src.exception.AuthenticationException;
import src.exception.OperationException;
import src.model.DAO.DisciplinaDAO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public class DisciplinaBO implements DisciplinaInterBO {

    public DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    @Override
    public void cadastrar(DisciplinaVO disciplina) {
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

                    AssuntoBO assuntoBO = new AssuntoBO();
                    disciplina.setAssuntos(assuntoBO.buscar(disciplina));

                    ProfessorBO professorBO = new ProfessorBO();
                    disciplina.setProfessores(professorBO.buscar(disciplina));

                    QuestaoBO questaoBO = new QuestaoBO();
                    disciplina.setQuestoes(questaoBO.buscar(disciplina));

                    ProvaBO provaBO = new ProvaBO();
                    disciplina.setProvas(provaBO.buscar(disciplina));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (OperationException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
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
            AssuntoBO assuntoBO = new AssuntoBO();
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
            List<QuestaoVO> questoes = disciplina.getQuestoes();

            Iterator<QuestaoVO> questoesIt = questoes.iterator();
            while (questoesIt.hasNext()) {
                QuestaoBO questaoBO = new QuestaoBO();
                questaoBO.remover(questoesIt.next(), assunto);
            }

            // Remove do Banco de Dados
            AssuntoBO assuntoBO = new AssuntoBO();
            assuntoBO.excluir(disciplina, assunto);

        }
    }

    @Override
    public void adicionar(DisciplinaVO disciplina, ProfessorVO professor) {

        List<ProfessorVO> lista = disciplina.getProfessores();

        // Se este professor não estiver na lista desta disciplina, será adicionado
        if (!lista.contains(professor)) {
            lista.add(professor);
            disciplina.setProfessores(lista);

            // atualiza o professor (o método seguinte já se encarrega do BD)
            ProfessorBO professorBO = new ProfessorBO();
            professorBO.adicionar(professor, disciplina);

        }
    }

    @Override
    public void remover(DisciplinaVO disciplina, ProfessorVO professor) {

        List<ProfessorVO> lista = disciplina.getProfessores();

        // Se este professor estiver na lista desta disciplina, será removido
        if (lista.remove(professor)) {
            disciplina.setProfessores(lista);

            // atualiza o professor (o método seguinte já se encarrega do BD)
            ProfessorBO professorBO = new ProfessorBO();
            professorBO.remover(professor, disciplina);

        }
    }

    @Override
    public void adicionar(DisciplinaVO disciplina, QuestaoVO questao) {

        List<QuestaoVO> lista = disciplina.getQuestoes();

        // Se esta questão não estiver na lista desta disciplina, será adicionada
        if (!lista.contains(questao)) {
            lista.add(questao);
            disciplina.setQuestoes(lista);

            // Atualiza a questão
            QuestaoBO questaoBO = new QuestaoBO();
            questaoBO.atualizar(questao, disciplina);
        }

    }

    @Override
    public void remover(DisciplinaVO disciplina, QuestaoVO questao) {

        List<QuestaoVO> lista = disciplina.getQuestoes();

        // Se esta questão estiver na lista desta disciplina, será removida
        // Não mexe com o BD, pois será chamada apenas por QuestaoBO.atualizar()
        if (lista.remove(questao))
            disciplina.setQuestoes(lista);
    }

    @Override
    public void adicionar(DisciplinaVO disciplina, ProvaVO prova) {

        List<ProvaVO> lista = disciplina.getProvas();

        // Se esta prova não estiver na lista desta disciplina, será adicionada
        if (!lista.contains(prova)) {
            lista.add(prova);
            disciplina.setProvas(lista);
        }
    }

    @Override
    public void remover(DisciplinaVO disciplina, ProvaVO prova) {

        List<ProvaVO> lista = disciplina.getProvas();

        // Se esta prova estiver na lista desta disciplina, será removida
        // Não mexe com o BD, pois será chamada apenas por ProvaBO.atualizar()
        if (lista.remove(prova))
            disciplina.setProvas(lista);
    }
}
