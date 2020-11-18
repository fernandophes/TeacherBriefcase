package model.BO;

import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import model.VO.QuestaoVO;

public class QuestaoBO {
    public void cadastrar(QuestaoVO questao) {
        // cadastra uma nova questão

        // analisa
        // DAO
    }

    public QuestaoVO[] listar() {
        // lista todas as questoes

        QuestaoVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    public QuestaoVO buscar(QuestaoVO questao) {
        // busca uma questão

        QuestaoVO resultado = new QuestaoVO();
        // DAO
        // ajusta
        return resultado;
    }

    public QuestaoVO[] buscar(DisciplinaVO disciplina) {
        // busca todas as questoes desta disciplina

        QuestaoVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    public QuestaoVO[] buscar(AssuntoVO assunto) {
        // busca todas as questoes deste assunto

        QuestaoVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    public QuestaoVO[] buscar(int dificuldade) {
        // busca todas as questoes neste nível de dificuldade

        QuestaoVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    public QuestaoVO[] buscar(DisciplinaVO disciplina, int dificuldade) {
        // busca todas as questoes desta disciplina neste nível de dificuldade

        QuestaoVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    public QuestaoVO[] buscar(AssuntoVO assunto, int dificuldade) {
        // busca todas as questoes deste assunto neste nível de dificuldade

        QuestaoVO[] lista = {};
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
        // adiciona um assunto à questão
        AssuntoVO[] lista = questao.getAssuntos();

        // verifica se a questão possui um assunto de mesmo nome
        for (int i = 0; i < lista.length; i++)
            if (lista[i].getNome().equals(assunto.getNome()))
                return;

        // incrementa a lista
        lista[lista.length] = assunto;

        // atualiza a questão
        questao.setAssuntos(lista);

        // atualiza o assunto
        AssuntoBO assuntoBO = new AssuntoBO();
        assuntoBO.adicionar(assunto, questao);
        
        // analisa (o assunto pertence à disciplina correta?)
        // DAO
        // ajusta
    }

    public void remover(QuestaoVO questao, AssuntoVO assunto) {
        // remove o assunto da questão
        AssuntoVO[] lista = questao.getAssuntos();

        // verifica se a questão possui um assunto de mesmo nome
        for (int i = 0; i < lista.length; i++)
            if (lista[i].getNome().equals(assunto.getNome())) {
                // atualiza a questão
                    // CÓDIGO PARA REMOVER O ASSUNTO

                // atualiza o assunto
                AssuntoBO assuntoBO = new AssuntoBO();
                assuntoBO.remover(assunto, questao);

                return;
            }

        // analisa
        // DAO
        // ajusta
    }
}
