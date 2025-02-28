package dungeons.personagem;

import dungeons.atributosederivados.Atributos;
import dungeons.atributosederivados.Pericia;
import dungeons.atributosederivados.Pericias;

public class Personagem {
    private String nome;
    private String classe;
    private String raca;
    private int nivel;
    private int vida;
    private int classeDeArmadura;
    private Atributos atributos;
    private Pericias pericias;
    private int bonusProficiencia;
    private int dadoDeVida;
    //...


    public Personagem(String nome, String classe, String raca, int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma) {

        this.nome = nome;
        this.classe = classe;
        this.raca = raca;
        this.nivel = 1;
        this.atributos = new Atributos(forca, destreza, constituicao, inteligencia, sabedoria, carisma);
        this.pericias = new Pericias(atributos);
        this.bonusProficiencia = 2;
        this.dadoDeVida = definirDadoDeVida(classe);
        int modificadorConstituicao = (atributos.getConstituicao() - 10) / 2;
        this.vida = dadoDeVida + Math.max(modificadorConstituicao, 0);
        this.classeDeArmadura = calcularCA();
    }

    private int definirDadoDeVida(String classe) {
        return switch (classe.toLowerCase()) {
            case "guerreiro", "bárbaro" -> 10;
            case "mago", "feiticeiro" -> 6;
            case "ladino", "monge" -> 8;
            case "paladino", "ranger" -> 10;
            default -> 8; // Valor padrão
        };
    }

    private int calcularCA() {
        // Armadura base sem armadura = 10 + mod de Destreza
        int modificadorDestreza = (atributos.getDestreza() - 10) / 2;
        return 10 + Math.max(modificadorDestreza, 0);
    }

    public void subirNivel() {
        this.nivel++;
        if (nivel == 5 || nivel == 9 || nivel == 13 || nivel == 17) {
            bonusProficiencia++;
        }

        int modificadorDeConstituicao = (atributos.getConstituicao() - 10) / 2;
        int vidaGanha = dadoDeVida + Math.max(modificadorDeConstituicao, 0);
        this.vida += vidaGanha;
    }

    public void aplicarModificadores(Atributos modificadores) {
        this.atributos.setForca(atributos.getForca() + modificadores.getForca());
        this.atributos.setDestreza(atributos.getDestreza() + modificadores.getDestreza());
        this.atributos.setConstituicao(atributos.getConstituicao() + modificadores.getConstituicao());
        this.atributos.setInteligencia(atributos.getInteligencia() + modificadores.getInteligencia());
        this.atributos.setSabedoria(atributos.getSabedoria() + modificadores.getSabedoria());
        this.atributos.setCarisma(atributos.getCarisma() + modificadores.getCarisma());
    }

    public int tomarDano(int dano) {
        if (dano < 0) {
            System.out.println("Dano inválido! O dano não pode ser negativo.");
            return this.vida; // Mantém a vida inalterada.
        }

        this.vida -= dano;

        if (this.vida < 0) {
            this.vida = 0;
            System.out.println("Personagem morrendo!");
        }

        return this.vida;
    }

    public void adicionarProficiencia(Pericia pericia) {
        pericias.adicionarProficiencia(pericia);
    }

    public void exibirPersonagem(){
        System.out.println("============= Personagem ============");
        System.out.println("Nome: " + nome);
        System.out.println("Classe: " + classe);
        System.out.println("Raca: " + raca);
        System.out.println("Nivel: " + nivel);
        System.out.println("Vida: " + vida);
        System.out.println("ClasseDeArmadura: " + classeDeArmadura);
        atributos.exibirAtributos();
        pericias.exibirPericias(bonusProficiencia);
    }


    public Atributos getAtributos() {
        return atributos;
    }

}
