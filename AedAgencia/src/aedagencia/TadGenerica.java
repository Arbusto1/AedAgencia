package aedagencia;

public class TadGenerica<T> {

    private class Celula {

        T item;
        Celula prox;
        Celula ant;

        public Celula(T item) {
            this.item = item;
        }
    }

    private Celula head;
    private Celula tail;
    private long size;

    public TadGenerica() {
        head = new Celula(null);
        tail = new Celula(null);
        head.prox = tail;
        tail.ant = head;
    }

    void add(T item) {
        Celula novo = new Celula(item);
        novo.ant = tail.ant;
        novo.prox = tail;
        tail.ant = novo;
        novo.ant.prox = novo;

    }

    public T excluir(T item) {
        if (head.prox == tail) {
            throw new IllegalArgumentException("Lista Vazia");
        } else {
            Celula aux = head.prox;
            while (aux != tail && !aux.item.equals(item)) {
                aux = aux.prox; // aux++
            }
            if (aux == tail) {
                throw new IllegalArgumentException("Este item não existe");
            } else {
                aux.ant.prox = aux.prox;
                aux.prox.ant = aux.ant;
                return aux.item;
            }
        }

    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        Celula aux = head.prox;

        while (aux != tail) {
            sb.append(aux.item);
            sb.append(" ");
            aux = aux.prox;
        }
        sb.append("]");
        return sb.toString();

    }

}
