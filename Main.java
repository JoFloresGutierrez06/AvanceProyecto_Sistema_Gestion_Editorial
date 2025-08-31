// ===== Clase principal - Sistema de Gestión Editorial =====//
import java.util.*; // Importamos librerías para usar el scanner

public class Main {

    // MÉTODOS ESTÁTICOS DEL MONTÍCULO
    public static <E> void swap(E[] arreglo, int a, int b) { //Intercambiar elementos en el arreglo
        E temp = arreglo[a];   // Guardar la referencia al objeto en una variable temporal
        arreglo[a] = arreglo[b];   //Mover al padre al lugar del objeto
        arreglo[b] = temp;     //Mover al objeto al lugar del padre
    }
    public static int getIndex(Objeto objeto, Objeto[] arreglo) { //Obtener un index del arreglo
        for (int i = 1; i < arreglo.length; i++) {
            if (arreglo[i].equals(objeto)) {     //Comparar si es el mismo objeto en memoria con ==, sino reemplazar con .equals()
                return i; // devuelve el índice
            }
        }
        return -1; //Dará error porque -1 está fuera del rango
    }
    public static int getSize(Objeto[] arreglo) { //Obtener el num. de elementos del arreglo
        int tamano = 0;
        for (Objeto elemento: arreglo) { // Recorre todos los elementos del arreglo
            if (elemento != null) { //Si no está vacío el espacio lo cuentas
                tamano += 1; // Contador que cuenta los elementos reales
            } 
        }
        return tamano;
    }
    public static void enqueue(Objeto objeto, Objeto[] arreglo) { //Agregar elemento al arreglo

        if (getSize(arreglo) == 0) {    //Si la lista está vacía agregarlo en la posición 1 
            arreglo[1] = objeto;    //Agregarlo al arreglo
            objeto.setHijoIzquierdo(2); //2 - 2*n
            objeto.setHijoDerecho(3); //3 - 2*n +1
            objeto.setPadre(0);//No tiene padre aún

        } else {    //No es el primer elemento
            
            arreglo[getSize(arreglo) + 1] = objeto; //Agregarlo primeramente
            //Asignar a sus hijos
            objeto.setHijoIzquierdo(getIndex(objeto, arreglo)*2); //2*n
            objeto.setHijoDerecho((getIndex(objeto, arreglo)*2) +1); //2*n +1
            objeto.setPadre(getIndex(objeto, arreglo)/2);  //Asignarle padre

            while(true) {
                if (objeto.getPrioridad() < arreglo[objeto.getPadre()].getPrioridad()) { //Si es menor la prioridad del nuevo
                    int index_inicial_objeto = getIndex(objeto, arreglo);   //Guarda la posición inicial del objeto antes de cambiar
                    swap(arreglo, getIndex(objeto, arreglo), objeto.getPadre()); //Cambia lugar con el padre en el arreglo
                    
                    //Si quedó en la posición 1 del arreglo no se modifica su padre
                    if (getIndex(objeto, arreglo) == 1) {
                    } else {    //Si no es el 1, reasignarle padre
                        objeto.setPadre(getIndex(objeto, arreglo)/2);  
                    }

                    //Actualiza a sus hijos
                    objeto.setHijoIzquierdo(getIndex(objeto, arreglo)*2); //2*n
                    objeto.setHijoDerecho((getIndex(objeto, arreglo)*2) +1); //2*n +1

                    //Actualizar datos del padre anterior, que se movió al lugar inicial del objeto
                    arreglo[index_inicial_objeto].setPadre(index_inicial_objeto/2); //actualizarle el padre al padre
                    arreglo[index_inicial_objeto].setHijoIzquierdo(index_inicial_objeto*2); //Actualizar hijo izquierdo del padre
                    arreglo[index_inicial_objeto].setHijoDerecho((index_inicial_objeto*2) +1); //Actualizar hijo derecho del padre
                
                } else { //Si el objeto no es menor que su padre no se mueve
                    break;
                }
                
            }     
        }
        System.out.println("Registro exitoso!");
    }
    public static void dequeue(Objeto[] arreglo) { //Eliminar el primer elemento del arreglo
        
        if (getSize(arreglo) == 0) { //El arreglo está vacío
            System.out.println("No hay eventos registrados.");
        } else { 
            Objeto eliminado = arreglo[1]; //Guardar en una variable el objeto eliminado
            //Recorrer los elementos un lugar en el arreglo
            for (int i = 1; i < getSize(arreglo); i++) { //De 1 a 'tamaño del arreglo' -1
                arreglo[i] = arreglo[i+1];
            }
            arreglo[getSize(arreglo)] = null; //Se actualiza el último espacio para que sea null
            System.out.println("Evento completado: " + eliminado.getDato());
        }
        
    }
    public static void front(Objeto[] arreglo) { //Mostrar el primer elemento

        if (getSize(arreglo)== 0) { //Si el arreglo está vacío
            System.out.println("No hay eventos registrados."); 
        } else {
            System.out.println("Evento: " + arreglo[1].getDato() + " Prioridad: " + arreglo[1].getPrioridad());
        }
    }
    public static void display(Objeto[] arreglo) {
        if (getSize(arreglo) == 0) { //Si el arreglo está vacío
            System.out.println("Aún no hay eventos.");
        } else { //Recorrer cada elemento
            System.out.println(" <= Actual | En espera =>");
            for (Objeto elemento: arreglo) {
                if (elemento != null) { //Si no está vacío el espacio lo muestra
                    System.out.print(elemento.getDato() + "(" + elemento.getPrioridad() + ") "); //Muestra el dato y su prioridad entre paréntesis
                } 
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in); // Creamos el scanner para leer las entradas
        Objeto[] monticulo = new Objeto[100]; //Declarar montículo. Le ponemos tamaño grande
        Pila pila = new Pila(100); // Creamos una pila
        ListaEnlazada<String> listaEnlazada = new ListaEnlazada<>(); // Creamos una lista enlazada
        boolean ciclo = true;

        // MENÚ PRINCIPAL
        while(ciclo) {
            System.out.println("\n ==== SISTEMA DE GESTIÓN EDITORIAL ====");
            System.out.print("1) Agendar evento " +         // Montículo
                            "\n2) Historial de eventos " +  // Pila
                            "\n3) Propuestas de proyecto" + // Lista enlazada
                            "\n4) Salir \n=> ");
            int seleccion = input.nextInt();
            input.nextLine(); // limpiar buffer

            switch(seleccion) { //MENÚS SENCUNDARIOS
            
                case 1: //MONTÍCULO
                    System.out.println("\n === 'AGENDAR EVENTO' ===");
                    System.out.print("1) Agregar evento a la cola " +           // enqueue
                                    "\n2) Mostrar evento actual " +             // front
                                    "\n3) Completar evento actual" +            // dequeue
                                    "\n4) Mostrar todos los eventos en cola" +  // display
                                    "\n5) Volver \n=> ");
                    seleccion = input.nextInt();
                    input.nextLine(); // limpiar buffer

                    switch(seleccion) {
                        case 1: // AGREGAR EVENTO (ENQUEUE)
                            System.out.println("Nombre evento: ");
                            String dato = input.nextLine();
                            System.out.println("Prioridad del evento (1 en delante): ");
                            int prioridad = input.nextInt();
                            input.nextLine(); // Limpiar buffer
                            enqueue(new Objeto(dato, prioridad), monticulo); //Crea el objeto, añade los datos y lo agrega al arreglo
                            break;
                        case 2: // MOSTRAR EVENTO ACTUAL (FRONT)
                            front(monticulo);
                            break;
                        case 3: // COMPLETAR EVENTO ACTUAL (DEQUEUE)
                            dequeue(monticulo);
                            break;
                        case 4: // MOSTRAR TODOS LOS EVENTOS EN COLA (DISPLAY)
                            display(monticulo);
                            break;
                        case 5: // VOLVER
                            break;
                        default: // VALIDACIÓN DE DATO
                            System.out.println("Opción inválida.");
                    }    
                    break;

                case 2: // PILA
                    System.out.println("\n === 'HISTORIAL DE EVENTOS' ===");
                    System.out.print("1) Agregar evento al historial " +        // push
                                    "\n2) Mostrar evento más reciente " +       // peek
                                    "\n3) Borrar evento más reciente" +         // pop
                                    "\n4) Mostrar historial de eventos" +       // display
                                    "\n5) Volver \n=> ");
                    seleccion = input.nextInt();
                    input.nextLine(); // limpiar buffer

                    switch(seleccion) {
                        case 1: // AREGAR EVENTO AL HISTORIAL (PUSH)
                            System.out.print("Ingrese el nombre del evento => ");
                            String dato = input.nextLine();
                            pila.push(dato);
                            break;
                        case 2: // MOSTRAR EVENTO MÁS RECIENTE (PEEK)
                            System.out.println("Evento más reciente: " + pila.peek());
                            break;
                        case 3: // BORRAR EVENTO MÁS RECIENTE (POP)
                            System.out.println(pila.pop());
                            break;
                        case 4: // MOSTRAR HISTORIAL DE EVENTOS (DISPLAY)
                            System.out.println("Historial de eventos:");
                            pila.display();
                            System.out.println();
                            break;
                        case 5: // VOLVER
                            break;
                        default: // VALIDAR DATO
                            System.out.println("Opción inválida.");
                    }    
                    break;

                case 3: // LISTA ENLAZADA
                    System.out.println("\n === 'PROPUESTAS DE PROYECTO' ===");
                    System.out.print("1) Agregar propuesta " +                  // insert
                                    "\n2) Buscar propuesta " +                  // find
                                    "\n3) Borrar propuesta" +                   // delete
                                    "\n4) Mostrar propuestas" +                 // display
                                    "\n5) Volver \n=> ");
                    seleccion = input.nextInt();
                    input.nextLine(); // limpiar buffer

                    switch(seleccion) {
                        case 1: // AGREGAR PROPUESTA (insert)
                            System.out.print("Nombre de la propuesta => ");
                            listaEnlazada.insert(input.nextLine());
                            break;
                        case 2: // BUSCAR PROPUESTA (find)
                            System.out.print("Nombre de la propuesta a buscar: ");
                            String dato = input.nextLine();

                            if (listaEnlazada.find(dato)) { // Si está el dato es true
                                System.out.println("Propuesta existente. ");
                            } else {
                                System.out.println("No se encuentra la propuesta. ");
                            }
                            break;
                        case 3: // BORRAR PROPUESTA (delete)
                            System.out.print("Propuesta a eliminar: ");
                            String datoEliminar = input.nextLine();

                            if(listaEnlazada.delete(datoEliminar)) { // Sí encontró el dato y lo elimina
                                System.out.println("Propuesta eliminada.");
                            } else {
                                System.out.println("Propuesta no encontrada."); // No encontró el dato
                            } 
                            break;
                        case 4: // MOSTRAR PROPUESTAS (DISPLAY)
                            System.out.println("Propuestas:");
                            listaEnlazada.display();
                            break;
                        case 5: // VOLVER
                            break;
                        default: // VALIDACIÓN DE DATO
                            System.out.println("Opción inválida.");
                    }
                    break;
                case 4: // SALIR
                    System.out.println("Saliendo... Hasta pronto!");
                    ciclo = false;
                    break;
                default: // VALIDAR DATO
                    System.out.println("Opción no válida.");
            }
        }
    }
}