package model.BO;

import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import model.VO.QuestaoVO;

public class QuestaoBO {
    void cadastrar(QuestaoVO questao) {
        // cadastra uma nova questão

        // analisa
        // DAO
    }

    QuestaoVO[] listar() {
        // lista todas as questoes

        QuestaoVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    QuestaoVO buscar(QuestaoVO questao) {
        // busca uma questão

        QuestaoVO resultado = new QuestaoVO();
        // DAO
        // ajusta
        return resultado;
    }

    QuestaoVO[] buscar(DisciplinaVO disciplina) {
        // busca todas as questoes desta disciplina

        QuestaoVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    QuestaoVO[] buscar(AssuntoVO assunto) {
        // busca todas as questoes deste assunto

        QuestaoVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    QuestaoVO[] buscar(int dificuldade) {
        // busca todas as questoes neste nível de dificuldade

        QuestaoVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    QuestaoVO[] buscar(DisciplinaVO disciplina, int dificuldade) {
        // busca todas as questoes desta disciplina neste nível de dificuldade

        QuestaoVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    QuestaoVO[] buscar(AssuntoVO assunto, int dificuldade) {
        // busca todas as questoes deste assunto neste nível de dificuldade

        QuestaoVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    void editar(QuestaoVO questao) {
        // edita os dados de uma questão

        // analisa
        // DAO
        // ajusta
    }

    void excluir(QuestaoVO questao) {
        // exclui uma questão

        // analisa
        // DAO
    }

    void adicionar(QuestaoVO questao, AssuntoVO assunto) {
        // adiciona um assunto à questão

        // analisa (o assunto pertence à disciplina correta?)
        // DAO
        // ajusta
    }

    void remover(QuestaoVO questao, AssuntoVO assunto) {
        // remove o assunto da questão
        
        // analisa
        // DAO
        // ajusta
    }
}
