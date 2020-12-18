package src.model;

public enum QuestaoDificuldade {

    FACIL(0, "Fácil"), MEDIA(1, "Média"), DIFICIL(2, "Difícil");

    private int id;
    private String descricao;

    private QuestaoDificuldade(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static QuestaoDificuldade buscar(int id) {
        switch (id) {
            case 0:
                return QuestaoDificuldade.FACIL;

            case 1:
                return QuestaoDificuldade.MEDIA;

            case 2:
                return QuestaoDificuldade.DIFICIL;

            default:
                return null;
        }
    }

}
