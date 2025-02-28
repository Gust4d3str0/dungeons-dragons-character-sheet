package dungeons.atributosederivados;

public enum Pericia {
    ACROBACIA("Acrobacia", "destreza"),
    ADESTRAMENTO("Adestramento", "sabedoria"),
    ARCANISMO("Arcanismo", "inteligencia"),
    ATLETISMO("Atletismo", "forca"),
    ENGANACAO("Enganação", "carisma"),
    FURTIVIDADE("Furtividade", "destreza"),
    HISTORIA("História", "inteligencia"),
    INTUICAO("Intuição", "sabedoria"),
    INTIMIDACAO("Intimidação", "carisma"),
    INVESTIGACAO("Investigação", "inteligencia"),
    MEDICINA("Medicina", "sabedoria"),
    NATUREZA("Natureza", "inteligencia"),
    PERCEPCAO("Percepção", "sabedoria"),
    PERSUASAO("Persuasão", "carisma"),
    PRESTIDIGITACAO("Prestidigitação", "destreza"),
    RELIGIAO("Religião", "inteligencia"),
    SOBREVIVENCIA("Sobrevivência", "sabedoria");

    private final String nome;
    private final String atributoRelacionado;

    Pericia(String nome, String atributoRelacionado) {
        this.nome = nome;
        this.atributoRelacionado = atributoRelacionado;
    }

    public String getNome() {
        return nome;
    }

    public String getAtributoRelacionado() {
        return atributoRelacionado;
    }
}
