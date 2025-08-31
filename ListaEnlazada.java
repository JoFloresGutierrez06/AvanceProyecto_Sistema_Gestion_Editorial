//==== Clase de Lista enlazada  ====//
public class ListaEnlazada<E> {

    //CLASE NODO
    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data) { this.data = data; } // Constructor de nodo
    }

    private Node<E> head; //Atributo/apuntador a la cabeza de la lista

    // MÉTODOS DE LA CLASE
    public void insert(E data) { // Agregar elemento al final de la pila
        Node<E> nuevo = new Node<>(data); // Crea un nodo y le pasa el dato
        if (head == null) { // Si no hay un elemento en la cabeza, agrega al nuevo dato
            head = nuevo;
        } else {
            Node<E> temp = head; // Guarda el nodo en una variable
            while (temp.next != null) {  // Recorre todos los elementos hasta llegar al último
                temp = temp.next;
            }
            temp.next = nuevo; // Actualiza la referencia al siguiente nodo
        }
        System.out.println("Registro exitoso!");
    }
    // Mostrar todos los elementos
    public void display() {
        Node<E> temp = head; // Guarda la cabeza en una variable
        if (temp == null) { // Mira si no hay elementos
            System.out.println("No se encuentran registros.");
        } else {
            while (temp != null) { // Recorre todos los elementos
                System.out.print(temp.data + " -> "); // Los muestra
                temp = temp.next;
            }
            System.out.println("null"); //Voy a dejar el null para que se vea que es una lista enlazada
        }
    }
    // Buscar elemento
    public boolean find(E dato) {
        Node<E> temp = head;
        while (temp != null) { //Repasa todos los elementos
            if (temp.data.equals(dato)) { // Si encuentra la coincidencia
                return true; // Elemento encontrado
            }
            temp = temp.next; //se mueve con la referencia
        }
        return false; // Elemento no encontrado
    }
    // Eliminar elemento
    public boolean delete(E dato) {
        if (head == null) return false; //Si no hay elementos regresa falso

        if (head.data.equals(dato)) { // Si el dato está en la cabeza
            head = head.next; // Mueve el apuntador al próximo nodo
            return true;
        }

        Node<E> temp = head; // Guarda la cabeza en una variable
        while (temp.next != null) { // recorre los elementos
            if (temp.next.data.equals(dato)) { // revisa la coincidencia
                temp.next = temp.next.next; // elimina el nodo
                return true;
            }
            temp = temp.next; // se desplaza
        }
        return false; // no encontrado
    }
}
