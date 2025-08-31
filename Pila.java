public class Pila {
    // ATRIBUTOS
    private String[] data;  // Para guardar el arreglo de Strings
    private int top;        // Indica el lugar donde está el último elemento
    private int capacity;   // Indica la capacidad del arreglo

    // CONSTRUCTOR
    public Pila(int capacity) { 
        this.capacity = capacity;
        data = new String[capacity]; // Crear arreglo con la capacidad asignada
        top = -1; // Pila vacía
    }

    //MÉTODOS
    public boolean isEmpty() { // Ver si está vacía la pila
        return top == -1; // -1 es el valor para la pila vacía
    }
    public boolean isFull() { // Ver si ya se llenó la pila
        return top == capacity -1; //Si el último elemento es igual a la capacidad -1
    }
    public void push(String x) { // Agregar elemento a la pila
        if (isFull()) { // Si está lleno no agrega nada porque da error
            System.out.println("Límite alcanzado. No se pueden agregar más elementos. " + x);
            return;
        }
        data[++top] = x; // Lo agrega al próximo espacio disponible
        System.out.println("Registro exitoso!");
    }
    public String pop() { // Eliminar el último elemento
        if (isEmpty()) { // Si está vacío no puede eliminar nada
            return "Historial vacío."; //indicador de error
        }
        return "Eliminado: " + data[top--]; //Muestra el dato eliminado y le quita un espacio a la pila
    }
    public String peek() { // Muestra el último elemento ingresado
        if (isEmpty()) {
            return "Historial vacío.";
        }
        return data[top]; //Devuelve el último elemento ingresado
    }
    public void display() { // Muestra todos los elementos de la pila del más reciente al más antiguo
        if (isEmpty()) { // Si está vacío no puede mostrar nada
            System.out.println("Historial vacío.");
        } else {
            System.out.println("<- Reciente | Antiguo -> "); // Muestra de top a fondo de la pila
            for(int i = top; i >= 0; i--) { // Recorre todos los elementos de la pila
                System.out.print(data[i] + " - "); // Muestra los elementos
            }
            System.out.println();
        }
    }
}
