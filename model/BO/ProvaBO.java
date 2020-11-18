package model.BO;

import model.VO.ProvaVO;
import model.VO.QuestaoVO;

public class ProvaBO {
    public void cadastrar(ProvaVO prova) {
        // cadastra uma prova no BD

        // analisa
        // DAO
    }

    public ProvaVO[] listar() {
        // lista todas as provas

        ProvaVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    public ProvaVO buscar(ProvaVO prova) {
        // busca uma prova

        ProvaVO resultado = new ProvaVO();
        // DAO
        // ajusta
        return resultado;
    }

    public void editar(ProvaVO prova) {
        // edita os dados da prova

        // analisa
        // DAO
    }

    public void excluir(ProvaVO prova) {
        // exclui a prova do BD

        // analisa
        // DAO
    }

    public void adicionar(ProvaVO prova, QuestaoVO questao) {
        // adiciona a questão à prova

        // analisa
        // DAO
        // ajusta
    }

    public void remover(ProvaVO prova, QuestaoVO questao) {
        // remove a questão da prova

        // analisa
        // DAO
        // ajusta
    }
}
