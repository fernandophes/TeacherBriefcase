package model.BO;

import model.VO.ProvaVO;
import model.VO.QuestaoVO;

public class ProvaBO {
    void cadastrar(ProvaVO prova) {
        // cadastra uma prova no BD

        // analisa
        // DAO
    }

    ProvaVO[] listar() {
        // lista todas as provas

        ProvaVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    ProvaVO buscar(ProvaVO prova) {
        // busca uma prova

        ProvaVO resultado = new ProvaVO();
        // DAO
        // ajusta
        return resultado;
    }

    void editar(ProvaVO prova) {
        // edita os dados da prova

        // analisa
        // DAO
    }

    void excluir(ProvaVO prova) {
        // exclui a prova do BD

        // analisa
        // DAO
    }

    void adicionar(ProvaVO prova, QuestaoVO questao) {
        // adiciona a questão à prova

        // analisa
        // DAO
        // ajusta
    }

    void remover(ProvaVO prova, QuestaoVO questao) {
        // remove a questão da prova

        // analisa
        // DAO
        // ajusta
    }
}
