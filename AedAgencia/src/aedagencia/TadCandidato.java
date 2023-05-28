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
    public void add(Candidato cand) {
        TadCandidato.Celula novo = new TadCandidato.Celula(cand);
        novo.ant = tail.ant;
        novo.prox = tail;
        tail.ant = novo;
        novo.ant.prox = novo;

    }

    public Candidato get(long i) {
        if (head == tail) {
            throw new IllegalArgumentException("A lista está vazia");
        } else {
            Celula aux = head.prox;
            long j = 0;

            while (aux.prox != null && j < i) {
                aux = aux.prox;
                j++;
            }

            if (aux == tail) {
                throw new IllegalArgumentException("Indice inválido, favor "
                        + "inserir índice dentro do tamanho da lista(" + size
                        + ")");
            } else {
                return aux.cand;
            }
        }
    }

    public boolean reside(Candidato u, String sigla) {
        if (u.getEstado().equals(sigla)) {
            return true;
        }
        return false;
    }

    public boolean inserePotencialCompanheiro(Candidato u, Candidato v) {
        if (verificaPotencialCandidato(u, v)) {
            u.getCandidatosPotenciais().add(v.getId());
            return true;
        }
        return false;
    }

    private boolean verificaPotencialCandidato(Candidato u, Candidato v) {
        if (u.getEstado().equals(v.getEstado()) && verificaSexoInteresse(u, v) && verificaAreaInteresse(u, v)) {
            return true;
        }
        return false;
    }

    private boolean verificaSexoInteresse(Candidato u, Candidato v) {
        for (long i = 0; i < u.getSexoInteresse().size(); i++) {
            if (u.getSexoInteresse().get(i).equals(v.getSexo())) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaAreaInteresse(Candidato u, Candidato v) {
        int cont = 0;

        for (long i = 0; i < u.getAreaInteresse().size(); i++) {
            for (long j = 0; j < v.getAreaInteresse().size(); j++) {

                if ((u.getAreaInteresse().get(i).equals("ESPORTES") && v.getAreaInteresse().get(j).equals("ESPORTES"))
                        || (u.getAreaInteresse().get(i).equals("MUSICA") && v.getAreaInteresse().get(j).equals("MUSICA"))
                        || (u.getAreaInteresse().get(i).equals("CINEMA") && v.getAreaInteresse().get(j).equals("CINEMA"))) {

                    cont++;
                } else if ((u.getAreaInteresse().get(i).equals("ARTES") && v.getAreaInteresse().get(j).equals("ARTES"))
                        || (u.getAreaInteresse().get(i).equals("ANIMAIS") && v.getAreaInteresse().get(j).equals("ANIMAIS"))
                        || (u.getAreaInteresse().get(i).equals("GASTRONOMIA") && v.getAreaInteresse().get(j).equals("GASTRONOMIA"))) {

                    cont += 2;
                } else if (u.getAreaInteresse().get(i).equals(v.getAreaInteresse().get(j))) {
                    cont += 3;
                }
            }
        }

        if (cont == u.getGrauInteresse()) {
            return true;
        }
        return false;
    }

    public long numerosCompanheiros(Candidato u) {
        return u.getCandidatosPotenciais().size();
    }

    public TadGenerica<Long> listaCompanheiros(Candidato u) {
        TadGenerica<Long> aux = new TadGenerica<>();
        for (long i = 0; i < u.getCandidatosPotenciais().size(); i++) {
            aux.add(u.getCandidatosPotenciais().get(i));
        }
        return aux;
    }
    
    public boolean temInteresse(Candidato u, long id){
        for (long i = 0; i < u.getCandidatosPotenciais().size(); i++) {
            if (u.getCandidatosPotenciais().get(i).equals(id)) {
                return true;
            }
        }
        return false;
    }
}
