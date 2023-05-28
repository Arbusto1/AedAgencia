package aedagencia;

public class Candidato {

    private long id;
    private String nome;
    private String estado;
    private char sexo;
    private TadGenerica<Character> sexoInteresse;
    private TadGenerica<String> areaInteresse;
    private TadGenerica<Long> candidatosPotenciais;
    private int qtdPotencialCandidato;
    private int grauInteresse;

    //Construtor de candidato
    Candidato(long id, String nome, String estado, char sexo) {

        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.sexo = sexo;
        this.sexoInteresse = new TadGenerica<>();
        this.areaInteresse = new TadGenerica<>();
        this.candidatosPotenciais = new TadGenerica<>();
    }

    //cria candidato com as informações pegas em outro arquivo
    public Candidato criaCand(long id, String nome, String estado, char sexo,
            char sexoInteresse, TadGenerica<String> areaInteresse) {

        Candidato cand = new Candidato(id, nome, estado, sexo);
        return cand;
    }

    public void setGrauInteresse(int grauInteresse) {
        this.grauInteresse = grauInteresse;
    }

    public String getEstado() {
        return this.estado;
    }

    public char getSexo() {
        return this.sexo;
    }
    
    public int getGrauInteresse() {
        return this.grauInteresse;
    }
    
    public TadGenerica<Long> getCandidatosPotenciais() {
        return this.candidatosPotenciais;
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

    public TadGenerica<Character> getSexoInteresse() {
        TadGenerica<Character> aux = new TadGenerica<>();
        for (long i = 0; i < this.sexoInteresse.size(); i++) {
            aux.add(this.sexoInteresse.get(i));
        }
        return aux;
    }
    
}
