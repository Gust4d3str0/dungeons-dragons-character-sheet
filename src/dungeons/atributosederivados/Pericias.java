package dungeons.atributosederivados;

import java.util.HashMap;
import java.util.Map;

public class Pericias {
    private Map<Pericia, Boolean> proficiencias; // Quais perícias o personagem tem proficiência
    private Atributos atributos;

    public Pericias(Atributos atributos) {
        this.atributos = atributos;
        this.proficiencias = new HashMap<>();

        // Inicializa todas as perícias como "sem proficiência"
        for (Pericia pericia : Pericia.values()) {
            proficiencias.put(pericia, false);
        }
    }

    public void adicionarProficiencia(Pericia pericia) {
        proficiencias.put(pericia, true);
    }

    public int calcularBonus(Pericia pericia, int bonusProficiencia) {
        int modificador = getModificador(pericia.getAtributoRelacionado());

        // Se o personagem for proficiente, soma o bônus de proficiência
        if (proficiencias.get(pericia)) {
            return modificador + bonusProficiencia;
        }
        return modificador;
    }

    private int getModificador(String atributo) {
        return switch (atributo) {
            case "forca" -> atributos.calculaModificador(atributos.getForca());
            case "destreza" -> atributos.calculaModificador(atributos.getDestreza());
            case "constituicao" -> atributos.calculaModificador(atributos.getConstituicao());
            case "inteligencia" -> atributos.calculaModificador(atributos.getInteligencia());
            case "sabedoria" -> atributos.calculaModificador(atributos.getSabedoria());
            case "carisma" -> atributos.calculaModificador(atributos.getCarisma());
            default -> 0;
        };
    }

    public void exibirPericias(int bonusProficiencia) {
        System.out.println("========== Perícias ==========");
        for (Pericia pericia : Pericia.values()) {
            int bonus = calcularBonus(pericia, bonusProficiencia);
            String proficiente = proficiencias.get(pericia) ? "(Proficiente)" : "";
            System.out.println(pericia.getNome() + ": " + bonus + " " + proficiente);
        }
    }
}
