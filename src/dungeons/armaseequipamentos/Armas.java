package dungeons.armaseequipamentos;

import dungeons.atributosederivados.Atributos;

public class Armas {
    private String nomeDaArma;
    private String tipoArma; // Ex.: "corpo a corpo", "à distância"
    private int quantidadeDados; // Quantidade de dados (ex.: 1 para 1d8, 2 para 2d6)
    private int tipoDado; // Tipo do dado (ex.: 4 para d4, 6 para d6, 8 para d8, etc.)
    private String tipoDano; // Ex.: "corte", "perfuração", "contusão"
    private String atributoRelacionado; // "forca", "destreza", etc.
    private boolean usaProficiencia; // Se a arma usa o bônus de proficiência

    public Armas(String nomeDaArma, String tipoArma, int quantidadeDados, int tipoDado, String tipoDano, String atributoRelacionado, boolean usaProficiencia) {
        this.nomeDaArma = nomeDaArma;
        this.tipoArma = tipoArma;
        this.quantidadeDados = quantidadeDados;
        this.tipoDado = tipoDado;
        this.tipoDano = tipoDano;
        this.atributoRelacionado = atributoRelacionado.toLowerCase();
        this.usaProficiencia = usaProficiencia;
    }

    // Método para calcular o modificador de ataque com base nos atributos e proficiência
    public int calcularAtaque(Atributos atributos, int bonusProficiencia) {
        int modificadorAtributo = switch (atributoRelacionado) {
            case "forca" -> (atributos.getForca() - 10) / 2;
            case "destreza" -> (atributos.getDestreza() - 10) / 2;
            case "inteligencia" -> (atributos.getInteligencia() - 10) / 2;
            case "sabedoria" -> (atributos.getSabedoria() - 10) / 2;
            case "carisma" -> (atributos.getCarisma() - 10) / 2;
            default -> 0;
        };

        return modificadorAtributo + (usaProficiencia ? bonusProficiencia : 0);
    }

    // Método para simular um ataque
    public void realizarAtaque(Atributos atributos, int bonusProficiencia) {
        int ataque = calcularAtaque(atributos, bonusProficiencia);

        // Simula o dado de ataque (1d20 + modificador)
        int rolagemD20 = (int) (Math.random() * 20) + 1; // Rolagem de 1 a 20
        int totalAtaque = rolagemD20 + ataque;

        // Calcula o dano rolando os dados especificados
        int totalDano = 0;
        for (int i = 0; i < quantidadeDados; i++) {
            totalDano += (int) (Math.random() * tipoDado) + 1; // Rola cada dado (ex.: 1 a 8 para d8)
        }

        // Adiciona o modificador do atributo relacionado ao dano
        int modificadorDano = switch (atributoRelacionado) {
            case "forca" -> (atributos.getForca() - 10) / 2;
            case "destreza" -> (atributos.getDestreza() - 10) / 2;
            default -> 0;
        };
        totalDano += modificadorDano;

        // Exibe o resultado
        System.out.println("Ataque com " + nomeDaArma + ":");
        System.out.println("Rolagem de ataque (1d20 + " + ataque + "): " + rolagemD20 + " + " + ataque + " = " + totalAtaque);
        System.out.println("Dano (" + quantidadeDados + "d" + tipoDado + " + " + modificadorDano + "): " + totalDano + " (" + tipoDano + ")");
    }

    // Getters
    public String getNomeDaArma() {
        return nomeDaArma;
    }

    public String getTipoArma() {
        return tipoArma;
    }

    public int getQuantidadeDados() {
        return quantidadeDados;
    }

    public int getTipoDado() {
        return tipoDado;
    }

    public String getAtributoRelacionado() {
        return atributoRelacionado;
    }

}
