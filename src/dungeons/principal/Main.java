package dungeons.principal;

import dungeons.armaseequipamentos.Armas;
import dungeons.atributosederivados.Pericia;
import dungeons.personagem.Personagem;

public class Main {
    public static void main(String[] args) {
        int dano = 2;
        Personagem p = new Personagem("Arthur", "Paladino", "HUMANO", "",16, 14, 15, 10, 12, 13, null);

        // Adicionar perícias ao personagem
        p.adicionarProficiencia(Pericia.ATLETISMO);
        p.adicionarProficiencia(Pericia.PERSUASAO);
        p.adicionarProficiencia(Pericia.INTIMIDACAO);
        p.subirNivel();
        p.subirNivel();
        p.subirNivel();
        p.adicionarProficiencia(Pericia.ARCANISMO);
        p.tomarDano(dano);

        Personagem meioElfo = new Personagem("Tanis", "Ladino", "MEIO_ELFO", "", 10, 14, 12, 10, 12, 10, new String[]{"forca", "destreza"});
        meioElfo.subirNivel();
        meioElfo.adicionarProficiencia(Pericia.ARCANISMO);


        Personagem humano = new Personagem("Aragorn", "Guerreiro", "HUMANO", "", 10, 10, 10, 10, 10, 10, null);
        humano.subirNivel();
        humano.adicionarProficiencia(Pericia.INTIMIDACAO);

        Personagem guerreiro = new Personagem("Aragorn", "Guerreiro", "HUMANO", "", 15, 12, 14, 10, 13, 8, null);
        guerreiro.subirNivel();
        guerreiro.adicionarProficiencia(Pericia.INTIMIDACAO);

        Armas espadaLonga = new Armas("Espada Longa", "corpo a corpo", 1, 8, "corte", "forca", true);
        Armas arco = new Armas("Arco", "media", 2, 4, "perfurante", "destreza", false);
        Armas katana = new Armas("Kakatana", "corpo a corpo", 1, 10, "corte", "forca", true);

        guerreiro.equiparArma(espadaLonga);
        meioElfo.equiparArma(arco);
        p.equiparArma(katana);

        p.exibirPersonagem();
        System.out.println("\n--------------------------------\n");
        humano.exibirPersonagem();
        System.out.println("\n--------------------------------\n");
        meioElfo.exibirPersonagem();
        System.out.println("\n--------------------------------\n");
        guerreiro.exibirPersonagem();
        System.out.println("\n--------------------------------\n");
        System.out.println("\n////////////////////////////////////////////////////\n");

        guerreiro.atacar();
        System.out.println("\n////////////////////////////////////////////////////\n");
        meioElfo.atacar();
        System.out.println("\n////////////////////////////////////////////////////\n");
        p.atacar();

    }
}
