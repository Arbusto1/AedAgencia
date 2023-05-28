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
        size = 0;
    }

    void add(T item) {
        Celula novo = new Celula(item);
        novo.ant = tail.ant;
        novo.prox = tail;
        tail.ant = novo;
        novo.ant.prox = novo;
        size++;

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
                size--;
                return aux.item;
            }
        }

    }
    
    public T get(long i) {
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
    
    public long size() {
        return this.size;
    }

}
