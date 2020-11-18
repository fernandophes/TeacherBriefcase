package model.BO;

import model.VO.AssuntoVO;
import model.VO.QuestaoVO;

public class AssuntoBO {
    public void cadastrar(AssuntoVO assunto) {
        // cadastra um novo assunto no BD

        // analisa
        // DAO
    }

    public AssuntoVO[] listar() {
        // lista todos os assuntos

        AssuntoVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    public AssuntoVO buscar(AssuntoVO assunto) {
        // busca um assunto

        AssuntoVO resultado = new AssuntoVO();
        // DAO
        // ajusta
        return resultado;
    }

    public void editar(AssuntoVO assunto) {
        // edita os dados de um assunto

        // analisa
        // DAO
        // ajusta
    }

    public void excluir(AssuntoVO assunto) {
        // exclui um assunto

        // analisa
        // DAO
    }

    public void adicionar(AssuntoVO assunto, QuestaoVO questao) {
        // adiciona uma questão ao acervo deste assunto

        QuestaoVO[] lista = assunto.getQuestoes();

        // verifica se já existe uma questão de mesmo enunciado neste assunto
        for (int i = 0; i < lista.length; i++)
            // Por enquanto, o enunciado está sendo "a chave", sei que isso não é o ideal,
            // mas por ora é o que temos
            if (lista[i].getEnunciado().equals(questao.getEnunciado()))
                return;

        // incrementa a lista
        lista[lista.length] = questao;

        // atualiza o assunto
        assunto.setQuestoes(lista);

        // atualiza a questão
        QuestaoBO questaoBO = new QuestaoBO();
        questaoBO.adicionar(questao, assunto);
    }

    public void remover(AssuntoVO assunto, QuestaoVO questao) {
        // remove uma questão do acervo deste assunto

        QuestaoVO[] lista = assunto.getQuestoes();

        // verifica se realmente existe uma questão de mesmo enunciado neste assunto
        for (int i = 0; i < lista.length; i++)
            // Por enquanto, o enunciado está sendo "a chave", sei que isso não é o ideal,
            // mas por ora é o que temos
            if (lista[i].getEnunciado().equals(questao.getEnunciado())) {
                // atualiza o assunto
                    // CÓDIGO QUE REMOVE A QUESTÃO DO ACERVO DO ASSUNTO

                // atualiza a questão
                QuestaoBO questaoBO = new QuestaoBO();
                questaoBO.remover(questao, assunto);

                return;
                }
    }
}
