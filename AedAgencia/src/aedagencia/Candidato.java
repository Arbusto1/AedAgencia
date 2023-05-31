package aedagencia;

import java.util.List;

public class Candidato {

    private long id;
    private String nome;
    private String estado;
    private String sexo;
    private TadGenerica<String> sexoInteresse;
    private TadGenerica<String> areaInteresse;
    private TadGenerica<Long> candidatosPotenciais;
    private int qtdPotencialCandidato;
    private int grauInteresse;

    //Construtor de candidato
    Candidato(long id, String nome, String estado, String sexo, String sexoInteresse, TadGenerica<String> areaInteresse ) {

        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.sexo = sexo;
        this.sexoInteresse = new TadGenerica<>();
        this.sexoInteresse.add(sexoInteresse);
        this.areaInteresse = areaInteresse;
        this.candidatosPotenciais = new TadGenerica<>();
        this.grauInteresse = 2;
    }

    //cria candidato com as informações pegas em outro arquivo
    public Candidato criaCand(long id, String nome, String estado, String sexo,
            String sexoInteresse, TadGenerica<String> areaInteresse) {

        Candidato cand = new Candidato(id, nome, estado, sexo,sexoInteresse, areaInteresse);
        return cand;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setGrauInteresse(int grauInteresse) {
        this.grauInteresse = grauInteresse;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getSexo() {
        return this.sexo;
    }
    
    public int getGrauInteresse() {
        return this.grauInteresse;
    }
    
    public TadGenerica<Long> getCandidatosPotenciais() {
        return this.candidatosPotenciais;
    }
     public  void setCandidatosPotenciais(TadGenerica tad) {
        this.candidatosPotenciais = tad;
    }
    public long getId() {
        return this.id;
    }

    //passagem por cópia(sem quebra de encapsulamento)
    public TadGenerica<String> getAreaInteresse() {
        TadGenerica<String> aux = new TadGenerica<String>();
        for (long i = 0; i < this.areaInteresse.size(); i++) {
            aux.add(this.areaInteresse.get(i));
        }
        return aux;
    }
    
    public TadGenerica<String> getSexoInteresse() {
        TadGenerica<String> aux = new TadGenerica<>();
        for (long i = 0; i < this.sexoInteresse.size(); i++) {
            aux.add(this.sexoInteresse.get(i));
        }
        return aux;
    }
    
}
