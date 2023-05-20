package aedagencia;

public class TadCandidato {
    
    private class Celula {
        Candidato cand;
        Celula prox;
        Celula ant;
        
        public Celula(Candidato cand) {
            this.cand = cand;
        }
    }
    
    private Celula head;
    private Celula tail;
    private long size;
    
    public TadCandidato() {
        head = new TadCandidato.Celula(null);
        tail = new TadCandidato.Celula(null);
        head.prox = tail;
        tail.ant = head;
    }
    
    //funcção add só será chamada quando o obj candidato estiver pronto
    //obj candidato será preparado em outro arquivo
    void add(Candidato cand) {
        TadCandidato.Celula novo = new TadCandidato.Celula(cand);
        novo.ant = tail.ant;
        novo.prox = tail;
        tail.ant = novo;
        novo.ant.prox = novo;

    }
}
