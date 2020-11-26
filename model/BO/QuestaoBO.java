package model.BO;

import java.util.ArrayList;
import java.util.List;

import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import model.VO.ProvaVO;
import model.VO.QuestaoSubjetivaVO;
import model.VO.QuestaoVO;

public class QuestaoBO {
    public void cadastrar(QuestaoVO questao) {
        // cadastra uma nova questão

        // analisa
        // DAO
    }

    public List<QuestaoVO> listar() {
        // lista todas as questoes

        List<QuestaoVO> lista = new ArrayList<QuestaoVO>();
        // DAO
        // ajusta
        return lista;
    }

    public QuestaoVO buscar(QuestaoVO questao) {
        return new QuestaoSubjetivaVO();
    }

    public List<QuestaoVO> buscar(DisciplinaVO disciplina) {
        // busca todas as questoes desta disciplina

        List<QuestaoVO> lista = new ArrayList<QuestaoVO>();
        // DAO
        // ajusta
        return lista;
    }

    public List<QuestaoVO> buscar(AssuntoVO assunto) {
        // busca todas as questoes deste assunto

        List<QuestaoVO> lista = new ArrayList<QuestaoVO>();
        // DAO
        // ajusta
        return lista;
    }

    public List<QuestaoVO> buscar(int dificuldade) {
        // busca todas as questoes neste nível de dificuldade

        List<QuestaoVO> lista = new ArrayList<QuestaoVO>();
        // DAO
        // ajusta
        return lista;
    }

    public List<QuestaoVO> buscar(DisciplinaVO disciplina, int dificuldade) {
        // busca todas as questoes desta disciplina neste nível de dificuldade

        List<QuestaoVO> lista = new ArrayList<QuestaoVO>();
        // DAO
        // ajusta
        return lista;
    }

    public List<QuestaoVO> buscar(AssuntoVO assunto, int dificuldade) {
        // busca todas as questoes deste assunto neste nível de dificuldade

        List<QuestaoVO> lista = new ArrayList<QuestaoVO>();
        // DAO
        // ajusta
        return lista;
    }

    public void editar(QuestaoVO questao) {
        // edita os dados de uma questão

        // analisa
        // DAO
        // ajusta
    }

    public void excluir(QuestaoVO questao) {
        // exclui uma questão

        // analisa
        // DAO
    }

    public void adicionar(QuestaoVO questao, AssuntoVO assunto) {

        List<AssuntoVO> lista = questao.getAssuntos();

        // Se este assunto não estiver na lista desta questão, poderá ou não ser
        // adicionado
        if (!lista.contains(assunto))
            // Este assunto só será adicionado a esta questão se ambos pertencerem à mesma
            // disciplina
            if (assunto.getDisciplina().equals(questao.getDisciplina())) {
                lista.add(assunto);
                questao.setAssuntos(lista);

                // atualiza o assunto
                AssuntoBO assuntoBO = new AssuntoBO();
                assuntoBO.adicionar(assunto, questao);

                // DAO
            }
    }

    public void remover(QuestaoVO questao, AssuntoVO assunto) {

        List<AssuntoVO> lista = questao.getAssuntos();

        // Se este assunto estiver na lista deste assunto, será removido
        if (lista.remove(assunto)) {
            questao.setAssuntos(lista);

            // atualiza o assunto
            AssuntoBO assuntoBO = new AssuntoBO();
            assuntoBO.remover(assunto, questao);

            // DAO
        }
    }

    public void adicionar(QuestaoVO questao, ProvaVO prova) {

        List<ProvaVO> lista = questao.getProvas();

        // Se esta prova não estiver na lista desta questão, poderá ou não ser adicionada
        if (!lista.contains(prova))
            // Esta prova só será adicionada a esta questão se ambas pertencerem à mesma disciplina
            if (questao.getDisciplina().equals(prova.getDisciplina())) {
                lista.add(prova);

                // Atualizar a prova
                ProvaBO provaBO = new ProvaBO();
                provaBO.adicionar(prova, questao);

                // DAO
            }
    }

    public void remover(QuestaoVO questao, ProvaVO prova) {

        List<ProvaVO> lista = questao.getProvas();

        // Se esta prova estiver na lista desta questão, será removida
        if (lista.remove(prova)) {
            questao.setProvas(lista);

            // Atualizar a prova
            ProvaBO provaBO = new ProvaBO();
            provaBO.remover(prova, questao);

            // DAO
        }
    }
}
