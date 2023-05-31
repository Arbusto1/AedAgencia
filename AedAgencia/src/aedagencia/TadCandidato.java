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

    public long getSize() {
        return this.size;
    }

    //funcção add só será chamada quando o obj candidato estiver pronto
    //obj candidato será preparado em outro arquivo
    public void add(Candidato cand) {
        Celula novo = new TadCandidato.Celula(cand);
        novo.ant = tail.ant;
        novo.prox = tail;
        tail.ant = novo;
        novo.ant.prox = novo;
        size++;

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

    public static TadGenerica<String> nCompanheiros(TadCandidato tadCandidato, Candidato cand) {
        TadGenerica<String> aux = new TadGenerica();
        boolean potencial = false;
        for (int i = 0; i < tadCandidato.getSize(); i++) {
            Candidato cand2 = tadCandidato.get(i);
            potencial = tadCandidato.verificaPotencialCandidato(cand, cand2);
            if (potencial) {
                aux.add(cand2.getNome());
            }
        }
        return aux;
    }

    public TadGenerica<String> listar_mesmo_estado(String sigla) {
        TadGenerica<String> nomes = new TadGenerica();

        if (head.prox == tail) {
            throw new IllegalArgumentException("Lista Vazia");
        } else {
            Celula aux = head.prox;

            // procurar a celula
            while ((aux != tail)) {
                if (aux.cand.getEstado().equals(sigla)) {
                    nomes.add(aux.cand.getNome());
                }
                aux = aux.prox; // aux++
            }
        }
                return nomes;

    }

    public static TadGenerica<String> nCandidatosIntesseArea(String area, TadCandidato tadCandidato) {
        TadGenerica<String> nomes = new TadGenerica();
        for (int i = 0; i < tadCandidato.getSize(); i++) {
            Candidato candArea = tadCandidato.get(i);
            for (int j = 0; j < candArea.getAreaInteresse().size(); j++) {
                if (candArea.getAreaInteresse().get(j).equals(area)) {
                    nomes.add(candArea.getNome());
                }
            }
        }
        return nomes;
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

    public boolean verificaPotencialCandidato(Candidato u, Candidato v) {

        if (u.getEstado().equals(v.getEstado()) && verificaSexoInteresse(u, v) && verificaAreaInteresse(u, v)) {
            return true;
        }
        return false;
    }

    private boolean verificaSexoInteresse(Candidato u, Candidato v) {
        for (long i = 0; i < u.getSexoInteresse().size(); i++) {
            if (u.getSexoInteresse().get(i).equals(v.getSexo()) || u.getSexoInteresse().get(i).equals("i")) {
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

        if (cont >= u.getGrauInteresse()) {
            return true;
        }
        return false;
    }

    public long numerosCompanheiros(Candidato u) {
        return u.getCandidatosPotenciais().size();
    }

    public static TadGenerica<Long> listaCompanheiros(Candidato u) {
        TadGenerica<Long> aux = new TadGenerica<>();
        for (long i = 0; i < u.getCandidatosPotenciais().size(); i++) {
            aux.add(u.getCandidatosPotenciais().get(i));
        }
        return aux;
    }

    public boolean temInteresse(Candidato u, long id) {
        for (long i = 0; i < u.getCandidatosPotenciais().size(); i++) {
            if (u.getCandidatosPotenciais().get(i).equals(id)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("");
        Celula aux = head.prox;

        while (aux != tail) {

            sb.append(aux.cand.getId());
            sb.append("  -  ");
            sb.append(aux.cand.getNome());
            sb.append("\n");
            aux = aux.prox;
        }
        sb.append("");
        return sb.toString();

    }
}
