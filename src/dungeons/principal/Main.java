package dungeons.principal;

import dungeons.atributosederivados.Pericia;
import dungeons.personagem.Personagem;

public class Main {
    public static void main(String[] args) {
        int dano = 2;
        Personagem p = new Personagem("Arthur", "Paladino", "Humano", 16, 14, 15, 10, 12, 13);

        // Adicionar perícias ao personagem
        p.adicionarProficiencia(Pericia.ATLETISMO);
        p.adicionarProficiencia(Pericia.PERSUASAO);
        p.adicionarProficiencia(Pericia.INTIMIDACAO);
        p.subirNivel();
        p.subirNivel();
        p.subirNivel();
        p.adicionarProficiencia(Pericia.ARCANISMO);
        p.tomarDano(dano);

        // Exibir personagem e perícias
        p.exibirPersonagem();
    }
}
