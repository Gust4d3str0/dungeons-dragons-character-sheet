package dungeons.atributosederivados;

import dungeons.personagem.Personagem;

import java.util.HashMap;
import java.util.Map;

public class Atributos{
    private int forca;
    private int destreza;
    private int constituicao;
    private int inteligencia;
    private int sabedoria;
    private int carisma;

    public Atributos(int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma) {
        this.forca = forca;
        this.destreza = destreza;
        this.constituicao = constituicao;
        this.inteligencia = inteligencia;
        this.sabedoria = sabedoria;
        this.carisma = carisma;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getConstituicao() {
        return constituicao;
    }

    public void setConstituicao(int constituicao) {
        this.constituicao = constituicao;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getSabedoria() {
        return sabedoria;
    }

    public void setSabedoria(int sabedoria) {
        this.sabedoria = sabedoria;
    }

    public int getCarisma() {
        return carisma;
    }

    public void setCarisma(int carisma) {
        this.carisma = carisma;
    }


    public Map<String, Integer> aplicarModificadores(){
        Map<String, Integer> modificadores = new HashMap<>();
        modificadores.put("forca", calculaModificador(forca));
        modificadores.put("destreza", calculaModificador(destreza));
        modificadores.put("constituicao", calculaModificador(constituicao));
        modificadores.put("inteligencia", calculaModificador(inteligencia));
        modificadores.put("sabedoria", calculaModificador(sabedoria));
        modificadores.put("carisma", calculaModificador(carisma));
        return modificadores;
    }

    public int calculaModificador(int modificador) {
        return (modificador - 10)/2;

    }

    public void exibirAtributos() {
        System.out.println("==========Atributos==========");
        System.out.println("| Força: " + forca + " (Mod: " + calculaModificador(forca) + ")");
        System.out.println("| Destreza: " + destreza + " (Mod: " + calculaModificador(destreza) + ")");
        System.out.println("| Constituição: " + constituicao + " (Mod: " + calculaModificador(constituicao) + ")");
        System.out.println("| Inteligência: " + inteligencia + " (Mod: " + calculaModificador(inteligencia) + ")");
        System.out.println("| Sabedoria: " + sabedoria + " (Mod: " + calculaModificador(sabedoria) + ")");
        System.out.println("| Carisma: " + carisma + " (Mod: " + calculaModificador(carisma) + ")");
    }

}
