package aedagencia;

public class Candidato {
    
    private String nome;
    private String estado;
    private char sexo;
    private char sexoInteresse;
    private TadGenerica<String> areaInteresse;
    
    //Construtor de candidato
    Candidato(String nome, String estado, char sexo, 
            char sexoInteresse, TadGenerica<String> areaInteresse) {
        
        this.nome = nome;
        this.estado = estado;
        this.sexo = sexo;
        this.sexoInteresse = sexoInteresse;
        this.areaInteresse = areaInteresse;
    }
    
    //cria candidato com as informações pegas em outro arquivo
    public Candidato criaCand(String nome, String estado, char sexo, 
            char sexoInteresse, TadGenerica<String> areaInteresse) {
        
        Candidato cand = new Candidato(nome, estado, sexo, sexoInteresse, areaInteresse);
        return cand;
    }
}
