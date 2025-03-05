package dungeons.personagem;

import dungeons.atributosederivados.Atributos;
import dungeons.atributosederivados.Pericia;
import dungeons.atributosederivados.Pericias;
import dungeons.armaseequipamentos.Armas; // Importação da classe Armas
import java.util.HashMap;
import java.util.Map;

public class Personagem {
    private String nome;
    private String classe;
    private String raca;
    private String subRaca;
    private int nivel;
    private int vida;
    private int classeDeArmadura;
    private Atributos atributos;
    private Pericias pericias;
    private int bonusProficiencia;
    private int dadoDeVida;
    private Armas armaEquipada; // Novo campo para a arma equipada

    public Personagem(String nome, String classe, String raca, String subRaca, int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma, String[] atributosEscolhidos) {
        this.nome = nome;
        this.classe = classe;
        this.raca = raca;
        this.subRaca = subRaca != null ? subRaca : "";
        this.nivel = 1;
        this.atributos = new Atributos(forca, destreza, constituicao, inteligencia, sabedoria, carisma);
        aplicarModificadoresDeRaca(atributosEscolhidos);
        this.pericias = new Pericias(atributos);
        this.bonusProficiencia = 2;
        this.dadoDeVida = definirDadoDeVida(classe);
        int modificadorConstituicao = (atributos.getConstituicao() - 10) / 2;
        this.vida = dadoDeVida + Math.max(modificadorConstituicao, 0);
        this.classeDeArmadura = calcularCA();
        this.armaEquipada = null; // Inicializa sem arma equipada
    }

    private void aplicarModificadoresDeRaca(String[] atributosEscolhidos) {
        Map<String, Integer> modificadores = new HashMap<>();
        switch (raca.toUpperCase()) {
            case "HUMANO":
                modificadores.put("forca", 1);
                modificadores.put("destreza", 1);
                modificadores.put("constituicao", 1);
                modificadores.put("inteligencia", 1);
                modificadores.put("sabedoria", 1);
                modificadores.put("carisma", 1);
                break;
            case "ELFO":
                modificadores.put("destreza", 2);
                if (subRaca.equalsIgnoreCase("ALTO")) {
                    modificadores.put("inteligencia", 1);
                } else if (subRaca.equalsIgnoreCase("FLORESTA")) {
                    modificadores.put("sabedoria", 1);
                } else if (subRaca.equalsIgnoreCase("DROW")) {
                    modificadores.put("carisma", 1);
                }
                break;
            case "ANAO":
                modificadores.put("constituicao", 2);
                if (subRaca.equalsIgnoreCase("COLINA")) {
                    modificadores.put("sabedoria", 1);
                } else if (subRaca.equalsIgnoreCase("MONTANHA")) {
                    modificadores.put("forca", 2);
                }
                break;
            case "HALFLING":
                modificadores.put("destreza", 2);
                if (subRaca.equalsIgnoreCase("PES_LEVES")) {
                    modificadores.put("carisma", 1);
                } else if (subRaca.equalsIgnoreCase("ROBUSTO")) {
                    modificadores.put("constituicao", 1);
                }
                break;
            case "DRACONATO":
                modificadores.put("forca", 2);
                modificadores.put("carisma", 1);
                break;
            case "GNOMO":
                modificadores.put("inteligencia", 2);
                if (subRaca.equalsIgnoreCase("FLORESTA")) {
                    modificadores.put("destreza", 1);
                } else if (subRaca.equalsIgnoreCase("ROCHAS")) {
                    modificadores.put("constituicao", 1);
                }
                break;
            case "MEIO_ELFO":
                modificadores.put("carisma", 2);
                if (atributosEscolhidos != null && atributosEscolhidos.length == 2) {
                    for (String atributo : atributosEscolhidos) {
                        modificadores.put(atributo.toLowerCase(), modificadores.getOrDefault(atributo.toLowerCase(), 0) + 1);
                    }
                }
                break;
            case "MEIO_ORC":
                modificadores.put("forca", 2);
                modificadores.put("constituicao", 1);
                break;
            case "TIEFLING":
                modificadores.put("carisma", 2);
                modificadores.put("inteligencia", 1);
                break;
            case "AASIMAR":
                modificadores.put("carisma", 2);
                modificadores.put("sabedoria", 1);
                break;
            case "GOLIATH":
                modificadores.put("forca", 2);
                modificadores.put("constituicao", 1);
                break;
            case "TABAXI":
                modificadores.put("destreza", 2);
                modificadores.put("carisma", 1);
                break;
            case "TRITAO":
                modificadores.put("forca", 1);
                modificadores.put("constituicao", 1);
                modificadores.put("carisma", 1);
                break;
            case "KENKU":
                modificadores.put("destreza", 2);
                modificadores.put("sabedoria", 1);
                break;
            case "FIRBOLG":
                modificadores.put("sabedoria", 2);
                modificadores.put("forca", 1);
                break;
            case "HOBGOBLIN":
                modificadores.put("constituicao", 2);
                modificadores.put("inteligencia", 1);
                break;
            case "BUGBEAR":
                modificadores.put("forca", 2);
                modificadores.put("destreza", 1);
                break;
            case "KOBOLD":
                modificadores.put("destreza", 2);
                modificadores.put("forca", -2);
                break;
            case "YUAN_TI":
                modificadores.put("carisma", 2);
                modificadores.put("inteligencia", 1);
                break;
            default:
                System.out.println("Raça não reconhecida: " + raca);
                break;
        }

        atributos.setForca(atributos.getForca() + modificadores.getOrDefault("forca", 0));
        atributos.setDestreza(atributos.getDestreza() + modificadores.getOrDefault("destreza", 0));
        atributos.setConstituicao(atributos.getConstituicao() + modificadores.getOrDefault("constituicao", 0));
        atributos.setInteligencia(atributos.getInteligencia() + modificadores.getOrDefault("inteligencia", 0));
        atributos.setSabedoria(atributos.getSabedoria() + modificadores.getOrDefault("sabedoria", 0));
        atributos.setCarisma(atributos.getCarisma() + modificadores.getOrDefault("carisma", 0));
    }

    private int definirDadoDeVida(String classe) {
        return switch (classe.toLowerCase()) {
            case "guerreiro", "bárbaro" -> 10;
            case "mago", "feiticeiro" -> 6;
            case "ladino", "monge" -> 8;
            case "paladino", "ranger" -> 10;
            default -> 8;
        };
    }

    private int calcularCA() {
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
            return this.vida;
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

    public void exibirPersonagem() {
        System.out.println("============= Personagem ============");
        System.out.println("Nome: " + nome);
        System.out.println("Classe: " + classe);
        System.out.println("Raça: " + raca);
        System.out.println("Sub-Raça: " + subRaca);
        System.out.println("Nível: " + nivel);
        System.out.println("Vida: " + vida);
        System.out.println("Classe de Armadura: " + classeDeArmadura);
        System.out.println("Arma Equipada: " + (armaEquipada != null ? armaEquipada.getNomeDaArma() : "Nenhuma"));
        atributos.exibirAtributos();
        pericias.exibirPericias(bonusProficiencia);
    }

    // Novo método para equipar uma arma
    public void equiparArma(Armas arma) {
        this.armaEquipada = arma;
        System.out.println(nome + " equipou " + arma.getNomeDaArma() + ".");
    }

    // Novo método para realizar um ataque com a arma equipada
    public void atacar() {
        if (armaEquipada != null) {
            armaEquipada.realizarAtaque(atributos, bonusProficiencia);
        } else {
            System.out.println(nome + " não tem uma arma equipada para atacar!");
        }
    }

    public Atributos getAtributos() {
        return atributos;
    }
}