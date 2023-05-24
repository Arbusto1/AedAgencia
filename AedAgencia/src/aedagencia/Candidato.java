package aedagencia;

public class Candidato {
    
    private long id;
    private String nome;
    private String estado;
    private char sexo;
    private char sexoInteresse;
    private TadGenerica<String> areaInteresse;
    private TadGenerica<Integer> candidatosPotenciais;
    private int qtdPotencialCandidato;
    private int grauInteresse;
    
    //Construtor de candidato
    Candidato(long id, String nome, String estado, char sexo, 
            char sexoInteresse) {
        
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.sexo = sexo;
        this.sexoInteresse = sexoInteresse;
        this.areaInteresse = new TadGenerica<String>();
        this.candidatosPotenciais = new TadGenerica<Integer>();
    }
    
    //cria candidato com as informações pegas em outro arquivo
    public Candidato criaCand(long id, String nome, String estado, char sexo, 
            char sexoInteresse, TadGenerica<String> areaInteresse) {
        
        Candidato cand = new Candidato(id, nome, estado, sexo, sexoInteresse);
        return cand;
    }
    
    public void setGrauInteresse(int grauInteresse) {
        this.grauInteresse = grauInteresse;
    }
    
    public String getEstado() {
        return this.estado;
    }
        
}
