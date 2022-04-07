/**
 * Hoja de Trabajo 7
 * @author Maria Marta Ramirez
 * Carne 21342
 * Algoritmos y Estructura de Datos
 * 
 * Test de la clase GenericTree (Arbol Binario de Busqueda)
 */

/**
 * 
 * */


//import org.junit.Test;
 //import static org.junit.Assert.assertEquals;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;


public class TestBTree{
    @Test
    public void searchTest(){
        GenericTree Ingles = new GenericTree();

        try{

            //String directory = scanner.nextLine();
            String directory = "C:\\Users\\maria\\Documents\\UVG\\Algoritmos y Estructuras de Datos\\HT7_MM\\diccionario.txt";
            BufferedReader reader = new BufferedReader( new FileReader(directory) );
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.replace(",", " ");
                String[] parts = line.split("\\s");
                Ingles.insert( parts[0].strip().toLowerCase(), parts[1].toLowerCase() );

            }

        }
        catch (Exception e) {
            System.out.println( "ERROR INESPERADO INTENTE DE NUEVO" );
            System.exit(1);
        }

        boolean result1 = Ingles.contains("and"); //No esta
        assertEquals(result1, false);

        boolean result2 = Ingles.contains("house"); //Si esta
        assertEquals(result2, true);

        boolean result3 = Ingles.contains("town"); //Si esta
        assertEquals(result3, true);
    }

    private void assertEquals(boolean result1, boolean b) {
    }
}