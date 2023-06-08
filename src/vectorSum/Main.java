/*
 Enunciado
Dada la siguiente problemática: ¿puede un número X formarse usando la suma de 2 elementos
de un array?
Ejemplo 1
Input: nums = [1,4,3,9], requiredSum = 8
Output: False
Ejemplo 2
Input: nums = [1,2,4,4], requiredSum = 8
Output: True
Desarrolle (en pseudo código o su lenguaje de preferencia):
1. Un algoritmo que resuelva el problema asumiendo que la máquina en donde va a correrse
el programa tiene recursos infinitos, que el tiempo de ejecución no importa y que lo más
importante es realizar el desarrollo en el menor tiempo posible.
2. Un algoritmo que resuelva el problema asumiendo que los recursos son un bien preciado,
que el tiempo de ejecución si importa y que el tiempo de desarrollo no es importante.
*/

package vectorSum;

import java.util.Arrays;

public class Main {
	
	//1 Desarrollando sin importar recursos.
	public static Boolean CalcularSinImportarRecursos(int [] array, int valorBuscado) {
		
		//Ya que en este caso no nos importa la optimizacion del codigo, realizamos una busqueda secuencial ya que es facil de implementar
		//costo 6 lineas de codigo realizarla. 
		//El costo de ejecucion (Complejidad computacional) en este caso es O(N^2) ya que tenemos dos ciclos for añidados
		for(int i = 0 ; i < array.length ; i++) {
			
			for(int j = i + 1 ; j < array.length ; j++) {

				if(array[i] + array[j] == valorBuscado) {
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	//2 Desarrollando priorizando la optimizacion de recursos.
	public static boolean CalcularOptimizandoRecursos(int [] array, int valorBuscado) {
	
			//Ya que en este caso tenemos que preocuparnos por la utilizacion de recursos, vamos a implementar una busqueda logaritmica.
			//El costo de ejecucion (Complejidad computacional) en este caso es O(N x Log N). siendo mucho mas optimo que el caso anterior.
			
			for (int i = 0; i < array.length - 1; i++) {
				//enviamos a la funcion de busquedaLogaritmica un slice del array a partir del elemento i + 1 para buscar el numero deseado.
				//el array debe estar ordenado ya sea de menor a mayor o viceversa.
				Arrays.sort(array);
				int[] slicedArray = Arrays.copyOfRange(array, i + 1, array.length);
				
				//Calculamos el valor que necesitamos encontrar en la busquedaLogaritmica.
				int valorABuscar = valorBuscado - array[i];
				
				//Si en alguna de las iteraciones con la funcion nos devuelve 'true' indicando que encontro el valor deseado realizamos un return del mismo
				//finalizando la ejecucion del programa.
				if (BusquedaLogaritmica(slicedArray, valorABuscar) == true) {
					return true;
				}
			}
			//Si terminamos de recorrer todo el array y en ningun elemento la funcion 'BusquedaLogaritmica' nos devuelve true, quiere decir que no existe
			//ninguna combinacion posible que nos de el resultado que buscamos en el array. Por lo tanto devolvemos un false.
			return false;
			
		}
	
	
	public static boolean BusquedaLogaritmica(int [] array, int valorABuscar) {
			
			//Debugging Sysos
			/*
			System.out.println("New iteration");
			System.out.println("Valor a buscar = " +valorABuscar);
			*/
			
			//Inicializamos los indices superior, inferior
			int indiceInferior = 0;
			int indiceSuperior = array.length - 1;
			
			//En el caso que el indice inferior supere el valor del indice superior, signfica que el valor deseado no se encuentra en el array. y sera nuestro
			//punto de quiebre.
			while(indiceInferior <= indiceSuperior ) {
				int pibot = indiceInferior + (indiceSuperior - indiceInferior) / 2;
				
				 /*//Debugging Sysos
				System.out.println("pibot value = " +array[pibot]);
				System.out.println("pibot position = " +pibot);
				System.out.println(indiceInferior);
				System.out.println(indiceSuperior);
				*/
				
				//Si encontramos el valor esperado, retornamos un true
				if (array[pibot] == valorABuscar) {
					return true;
					
				}
				//si el valor del pibot actual es menor que el valor que estamos buscando establecemos el indice inferior a la posicion del pibot +1,
				//de esta forma eliminamos la mitad inferior del array.
				if (array[pibot] < valorABuscar) {
					indiceInferior = pibot + 1;
					
				}//si el valor del pibot actual es mayor que el valor que estamos buscando establecemos el indice Superior a la posicion del pibot -1,
				//de esta forma eliminamos la mitad superior del array.
				else {
					indiceSuperior = pibot - 1;
				}
			}
			//En caso que se cumpla la condicion del while donde ambos indices tendran el mismo valor, damos por hecho que el numero buscado no se encuentra
			//en el array y retornamos false.
			return false;
		}
	
	public static void main(String[] args ){

		//Casos de prueba.
		int numsEjemploUno [] = {1, 4 ,3 ,9};
		int numsEjemploDos [] = {1, 2 ,4 ,4};
		int numsEjemploTres [] = { 1999, 1430, 1233, 544, 604, 321, 435 , 25, 987, 654, 321, 123, 456,
				788, 621, 26, 333, 3211, 4512, 657, 659, 874, 6351 };
		int numsEjemploCuatro []  = {1,2};
		
		
		int requiredSumUno = 8;
		int requiredSumDos = 6;
		int requiredSumTres = 51;
		int requiredSumCuatro = 10863;
		
		System.out.println("********************************** Inicio Casos de Prueba Algoritmo Secuencial **********************************\n");
		
		System.out.println("RequiredSumUno(8) en numsEjemploUno ; Resultado esperado = false ; Resultado Obtenido = " +CalcularSinImportarRecursos(numsEjemploUno, requiredSumUno));
		System.out.println("RequiredSumUno(8) en numsEjemploDos ; Resultado esperado = true ; Resultado Obtenido = " +CalcularSinImportarRecursos(numsEjemploDos, requiredSumUno));
		System.out.println("RequiredSumUno(8) en numsEjemploTres ; Resultado esperado = false ; Resultado Obtenido = " +CalcularSinImportarRecursos(numsEjemploTres, requiredSumUno));
		System.out.println("RequiredSumUno(8) en numsEjemploCuatro ; Resultado esperado = false ; Resultado Obtenido = " +CalcularSinImportarRecursos(numsEjemploCuatro, requiredSumUno));
		
		System.out.println("RequiredSumDos(6) en numsEjemploUno ; Resultado esperado = false ; Resultado Obtenido = " +CalcularSinImportarRecursos(numsEjemploUno, requiredSumDos));
		System.out.println("RequiredSumDos(6) en numsEjemploDos ; Resultado esperado = true ; Resultado Obtenido = " +CalcularSinImportarRecursos(numsEjemploDos, requiredSumDos));
		System.out.println("RequiredSumDos(6) en numsEjemploTres ; Resultado esperado = false ; Resultado Obtenido = " +CalcularSinImportarRecursos(numsEjemploTres, requiredSumDos));
		System.out.println("RequiredSumDos(6) en numsEjemploCuatro ; Resultado esperado = false ; Resultado Obtenido = " +CalcularSinImportarRecursos(numsEjemploCuatro, requiredSumDos));
		
		System.out.println("\n********************************** Fin Casos de Prueba Algoritmo Secuencial **********************************\n");
		
		
		System.out.println("********************************** Inicio Casos de Prueba Algoritmo Logaritmico **********************************\n");
		
		System.out.println("RequiredSumTres(51) en numsEjemploUno ; Resultado esperado = false ; Resultado Obtenido = " +CalcularOptimizandoRecursos(numsEjemploUno, requiredSumTres));
		System.out.println("RequiredSumTres(51) en numsEjemploDos ; Resultado esperado = false ; Resultado Obtenido = " +CalcularOptimizandoRecursos(numsEjemploDos, requiredSumTres));
		System.out.println("RequiredSumTres(51) en numsEjemploTres ; Resultado esperado = true ; Resultado Obtenido = " +CalcularOptimizandoRecursos(numsEjemploTres, requiredSumTres));
		System.out.println("RequiredSumTres(51) en numsEjemploCuatro ; Resultado esperado = false ; Resultado Obtenido = " +CalcularOptimizandoRecursos(numsEjemploCuatro, requiredSumTres));
		
		System.out.println("RequiredSumCuatro(10863) en numsEjemploUno ; Resultado esperado = false ; Resultado Obtenido = " +CalcularOptimizandoRecursos(numsEjemploUno, requiredSumCuatro));
		System.out.println("RequiredSumCuatro(10863) en numsEjemploDos ; Resultado esperado = false ; Resultado Obtenido = " +CalcularOptimizandoRecursos(numsEjemploDos, requiredSumCuatro));
		System.out.println("RequiredSumCuatro(10863) en numsEjemploTres ; Resultado esperado = true ; Resultado Obtenido = " +CalcularOptimizandoRecursos(numsEjemploTres, requiredSumCuatro));
		System.out.println("RequiredSumCuatro(10863) en numsEjemploCuatro ; Resultado esperado = false ; Resultado Obtenido = " +CalcularOptimizandoRecursos(numsEjemploCuatro, requiredSumCuatro));
		
		System.out.println("\n********************************** Fin Casos de Prueba Algoritmo Logaritmico **********************************");
		
	}
	
}
