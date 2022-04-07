/**
 * Hoja de Trabajo 7
 * @author Maria Marta Ramirez
 * Carne 21342
 * Algoritmos y Estructura de Datos
 * 
 * Clase Main
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        GenericTree Frances = new GenericTree();
        GenericTree Ingles = new GenericTree();
        boolean translate = false;
        int mode = -1; // 0 = ingles, 1 = frances, -1 = no definido

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el archivo con los datos");
            String directory = scanner.nextLine();
            //String directory = "C:\\Users\\maria\\Documents\\UVG\\Algoritmos y Estructuras de Datos\\HT7_MM\\diccionario.txt";
            BufferedReader reader = new BufferedReader(new FileReader(directory));
            String line;
            while ((line = reader.readLine()) != null){
                line = line.replace(",", " ");
                String[] parts = line.split("\\s");
                Ingles.insert(parts[0].strip().toLowerCase(), parts[1].toLowerCase());
                Frances.insert(parts[2].strip().toLowerCase(), parts[1].toLowerCase());
            }
            translate = true;
        }catch (Exception e) {
            System.out.println("ERROR, intente de nuevo");
            System.exit(1);
        }
        
        if(translate){
        Scanner op = new Scanner(System.in);
        while (true) {
            System.out.println("1. TRADUCCION");
            System.out.println("2. EDITAR LA TRADUCCION");
            System.out.println("3. BORRAR PALABRA DEL DICCIONARIO"); 
            System.out.println("4. EXIT");
            System.out.println("5. MOSTRAR EL DICCIONARIO");
            System.out.println("6. TRADUCIR UNA PALABRA");
            try {
                switch(op.nextInt()){
                    case 1 ->{
                        System.out.println("Porfavor ingrese la direccion del archivo");
                        Scanner scanner = new Scanner(System.in);
                        try{
                            String file = scanner.nextLine();
                            BufferedReader reader = new BufferedReader(new FileReader(file));
                            String line;
                            ArrayList<String> notfoundTokens = new ArrayList<String>();
                            while ((line = reader.readLine()) != null){
                                line = removePunctuations(line);
                                String[] tokens = line.split(" ");
                                ArrayList<String> tokensList = new ArrayList<String>();
                                for(String word: tokens){
                                    if(Ingles.contains(word.toLowerCase())){
                                        mode = 0;
                                    }
                                    if(Frances.contains(word.toLowerCase())){
                                        mode = 1;
                                    }
                                }
                                switch(mode){
                                    case 0 -> {
                                        for(String word: tokens){
                                            if(Ingles.contains(word.toLowerCase())){
                                                tokensList.add((String) Ingles.getValue(word.toLowerCase()));
                                            }else{
                                                notfoundTokens.add(word);
                                                tokensList.add("*" + word + "*");
                                            }
                                        }
                                        String transaltion = "";
                                        for(String word : tokensList){
                                            transaltion = transaltion + " " + word;
                                        }
                                        System.out.println(transaltion);
                                    }
                                    case 1 -> {
                                        for(String word: tokens){
                                            if(Frances.contains(word.toLowerCase())){
                                                tokensList.add((String) Frances.getValue(word.toLowerCase()));
                                            }else{
                                                notfoundTokens.add(word);
                                                tokensList.add("*" + word + "*");
                                            }
                                        }
                                        String transaltion = "";
                                        for(String word : tokensList){
                                            transaltion = transaltion + " " + word;
                                        }
                                        System.out.println(transaltion);

                                    }
                                    case -1 ->{
                                        System.out.println("Idioma invalido");
                                        break;
                                    }
                                }
                            }
                            for(String newword : notfoundTokens){
                                System.out.println("Agregar significado para la palabra: " + newword);
                                String meaning =  scanner.nextLine();
                                switch(mode){
                                    case 0->{
                                        Ingles.insert(newword.toLowerCase(), meaning.toLowerCase());
                                    }
                                    case 1->{
                                        Frances.insert(newword.toLowerCase(), meaning.toLowerCase());
                                    }
                                }
                            }
                        }
                        catch(FileNotFoundException e){
                            System.out.println("ARCHIVO NO ENCONTRADO");
                            scanner.next();
                        }
                        catch (Exception e){
                            System.out.println("ERROR INESPERADO INTENTE DE NUEVO");
                            scanner.next();
                        }
                        
                    }
                    case 2 ->{
                        System.out.println("Porfavor seleccione el idioma que desea editar:");
                        System.out.println("1. Ingles");
                        System.out.println("2. Frances");
                        try { 
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Ingrese la palabra");
                            String word = scanner.nextLine();
                            switch(op.nextInt()){
                                
                                case 1 ->{
                                    
                                    if(Ingles.contains(word.toLowerCase())){
                                        System.out.println("Ingrese el nuevo significado:");
                                        String newmeanings = scanner.nextLine();
                                        Ingles.delete(word.toLowerCase());
                                        Ingles.insert(word.toLowerCase(), newmeanings.toLowerCase());
                                        
                                    }else{
                                        System.out.println("PALABRA NO ENCONTRADA, INTENTE DE NUEVO");
                                    }
                                }
                                case 2 ->{
                                    
                                    if(Frances.contains(word.toLowerCase())){
                                        System.out.println("Ingrese el nuevo significado:");
                                        String newmeanings = scanner.nextLine();
                                        Frances.delete(word.toLowerCase());
                                        Frances.insert(word.toLowerCase(), newmeanings.toLowerCase());
                                    }else{
                                        System.out.println("PALABRA NO ENCONTRADA, INTENTE DE NUEVO");
                                    }
                                }
                            }

                        }catch(InputMismatchException ie){
                            System.out.println("ENTRADA INVALIDA INTENTE DE NUEVO");
                            op.next();
                        }
                    }
                    case 3 ->{
                        System.out.println("Porfavor ingrese la palabra que desea eliminar:");
                        Scanner scanner = new Scanner(System.in);
                        String word = scanner.nextLine();
                            if(Ingles.contains(word.toLowerCase())){
                                Ingles.delete(word.toLowerCase());
                                System.out.println("Palabra eliminada con exito");
                            }else if(Frances.contains(word.toLowerCase())){
                                Frances.delete(word.toLowerCase());
                                System.out.println("Palabra eliminada con exito");
                            }else{
                                System.out.println("PALABRA NO ENCONTRADA");
                            }
                    }
                    case 4->{
                        System.exit(0);
                    }
                    case 5->{
                        System.out.println("Diccionario en Ingles");
                        Ingles.print();
                        System.out.println("\n Diccionario en Frances");
                        Frances.print();
                        System.out.println();
                    }
                    case 6 ->{
                        System.out.println("Porfavor ingrese la palabra");
                        Scanner scanner = new Scanner(System.in);
                        String word = scanner.nextLine();
                        if(Ingles.contains(word.toLowerCase())){
                            System.out.println(Ingles.getValue(word.toLowerCase()));
                        }
                        if(Frances.contains(word.toLowerCase())){
                            System.out.println(Frances.getValue(word.toLowerCase()));
                        }

                    }
                }
            } catch (InputMismatchException e){
                System.out.println("ENTRADA INVALIDA");
                op.next();
            }
            
            
        }
    } 
}

/**
 * @param source
 * @return
 */
public static String removePunctuations(String source) {
    return source.replaceAll("\\p{Punct}", "");
    }
}