//==== Clase de Cola Priorizada ====//
public class Objeto<E> {

    // ATRIBUTOS
    private E dato;
    private int prioridad;
    private int padre, hijoIzquierdo, hijoDerecho;

    public Objeto(E dato, int prioridad) { // Constructor normal
        this.dato = dato;
        this.prioridad = prioridad;
    }
    public Objeto(Objeto<E> objeto) { // Constructor copia
        this.dato = objeto.getDato();
        this.prioridad = objeto.getPrioridad();
        this.padre = objeto.getPadre();
        this.hijoIzquierdo = objeto.getHijoIzquierdo();
        this.hijoDerecho = objeto.getHijoDerecha();
    }
    //Getters
    public E getDato() { return this.dato; } 
    public int getPrioridad() { return this.prioridad; } 
    public int getPadre() {return this.padre;}
    public int getHijoIzquierdo() {return this.hijoIzquierdo;}
    public int getHijoDerecha() {return this.hijoDerecho;}
    //Setters
    public void setDato(E dato) { this.dato = dato; } 
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; } 
    public void setPadre(int padre) { this.padre = padre; } 
    public void setHijoIzquierdo(int hijo) { this.hijoIzquierdo = hijo; } 
    public void setHijoDerecho(int hijo) { this.hijoDerecho = hijo; } 
}
