package src.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import src.exception.OperationException;
import src.model.DAO.ProvaDAO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public class ProvaBO implements ProvaInterBO {

    private ProvaDAO provaDAO = new ProvaDAO();

    @Override
    public void cadastrar(ProvaVO prova) throws OperationException {
        if (prova != null) {
            provaDAO.cadastrar(prova);
        } else
            throw new OperationException("A questão não pode ser nula.");
    }

    @Override
    public List<ProvaVO> listar() {

        List<ProvaVO> lista = new ArrayList<ProvaVO>();

        ResultSet consulta = provaDAO.listar();

        if (consulta != null)
            try {
                while (consulta.next()) {
                    ProvaVO atual = new ProvaVO();
                    atual.setId(consulta.getLong("id"));
                    atual = buscar(atual);
                    lista.add(atual);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        return lista;
    }

    @Override
    public ProvaVO buscar(ProvaVO prova) throws OperationException {

        if (prova != null) {
            ResultSet consulta = provaDAO.buscar(prova);

            if (consulta != null)
                try {
                    while (consulta.next()) {
                        prova.setTitulo(consulta.getString("titulo"));

                        Calendar criacao = Calendar.getInstance();
                        criacao.setTime(consulta.getDate("data_criacao"));
                        prova.setDataCriacao(criacao);

                        DisciplinaVO disciplina = new DisciplinaVO();
                        disciplina.setId(consulta.getLong("disciplina"));
                        DisciplinaBO disciplinaBO = new DisciplinaBO();
                        disciplina = disciplinaBO.buscar(disciplina);
                        prova.setDisciplina(disciplina);

                        QuestaoBO questaoBO = new QuestaoBO();
                        prova.setQuestoes(questaoBO.buscar(prova));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return prova;
    }

    @Override
    public List<ProvaVO> buscar(DisciplinaVO disciplina) {
        List<ProvaVO> lista = new ArrayList<ProvaVO>();

        ResultSet consulta = provaDAO.buscar(disciplina);

        if (consulta != null)
            try {
                while (consulta.next()) {
                    ProvaVO atual = new ProvaVO();
                    atual.setId(consulta.getLong("id"));
                    atual = buscar(atual);
                    lista.add(atual);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        return lista;
    }

    @Override
    public void atualizar(ProvaVO prova) throws OperationException {
        if (prova != null)
            provaDAO.atualizar(prova);
        else
            throw new OperationException("A prova não pode ser nula.");
    }

    @Override
    public void excluir(ProvaVO prova) throws OperationException {
        if (prova != null)
            provaDAO.excluir(prova);
        else
            throw new OperationException("A prova não pode ser nula.");
    }

    @Override
    public ProvaVO gerar(int quaisquer, int faceis, int medias, int dificeis) {
        // Gera uma prova com questões aleatórias ProvaVO resultado = new ProvaVO();

        // TODO DAO gerar

        return new ProvaVO();
    }

    @Override
    public void adicionar(ProvaVO prova, QuestaoVO questao) {

        List<QuestaoVO> lista = prova.getQuestoes();

        // Se esta questão não estiver na lista desta prova, poderá ou não ser
        // adicionada
        if (!lista.contains(questao))
            // Esta questão só será adicionada a esta prova se ambas pertencerem à mesma
            // disciplina
            if (prova.getDisciplina().equals(questao.getDisciplina())) {
                lista.add(questao);
                prova.setQuestoes(lista);

                provaDAO.remover(prova, questao);
            } else {
                System.out.println("A prova e a questão não pertencem à mesma disciplina");
            }
    }

    public void remover(ProvaVO prova, QuestaoVO questao) {

        List<QuestaoVO> lista = prova.getQuestoes();

        // Se esta questão estiver na lista desta prova, será removida
        if (lista.remove(questao)) {
            prova.setQuestoes(lista);

            provaDAO.remover(prova, questao);
        }
    }
}
