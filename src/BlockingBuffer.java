import java.util.concurrent.ArrayBlockingQueue;
/*
 * BUFFER BLOQUEANTE
 *  garantiza la entrega correcta de valores y elimina la necesidad de sincronización explícita.
 *  
 *   # Ventajas de utilizar un buffer bloqueante
- No se requiere sincronización explícita: El buffer bloqueante se encarga de la 
   sincronización entre los hilos productor y consumidor.
   
- No se pierden valores: El buffer bloqueante garantiza que los valores producidos por
   el hilo productor se entreguen al hilo consumidor en el orden correcto.


 * 
 */
public class BlockingBuffer implements Buffer {
	/*
	 * ArrayBlockingQueue
	 * Es una implementación de cola de bloqueo que utiliza un arreglo para almacenar los elementos.
	 */
	private final ArrayBlockingQueue<Integer> buffer;// Este es el buffer bloqueante 
	
	//constructor
	public BlockingBuffer() {
		buffer = new ArrayBlockingQueue<Integer>(1);//Este constructor crea una nueva instancia de BlockingBuffer
		                                             //e inicializa el buffer bloqueante con una capacidad de 1 elemento.

	}


	@Override
	public void blockingPut(int value) throws InterruptedException {
		//blockingPut(int value)*	Este método se utiliza para escribir un valor en el buffer bloqueante.
		
		buffer.put(value); //Se coloca el valor en el buffer bloqueante. 
		                   //Si el buffer está lleno, el hilo productor se bloqueará hasta que se libere espacio en el buffer.
		
		
		
		//Se imprime un mensaje en la consola que indica que el productor ha escrito un valor en el buffer y muestra el número de celdas ocupadas en el buffer
		System.out.printf("%s%2d\t%s%d%n", "Producer writes ", value,
				"Buffer cells occupied: ", buffer.size());

	}

	@Override
	public int blockingGet() throws InterruptedException {
		//*blockingGet()*Este método se utiliza para leer un valor del buffer bloqueante.

		
		//Se elimina un valor del buffer bloqueante. Si el buffer está vacío, el hilo consumidor se bloqueará hasta que se agregue un valor al buffer.
		int readValue = buffer.take(); // remove value from buffer
		
		System.out.printf("%s %2d\t%s%d%n", "Consumer reads ",
				readValue, "Buffer cells occupied: ", buffer.size());// Se imprime un mensaje en la consola que indica que el consumidor ha leído un valor del buffer y muestra el número de celdas ocupadas en el buffer.

		return  readValue;//Se devuelve el valor leído del buffer.

	}

}

