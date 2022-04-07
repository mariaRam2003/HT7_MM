/**
 * Hoja de Trabajo 7
 * @author Maria Marta Ramirez
 * Carne 21342
 * Algoritmos y Estructura de Datos
 * 
 * Clase Association<K,V>
 */

public class Association<V,K> {
    public V key;
    public K value;
    public Association(V KEY, K VALUE) {
        key = KEY;
        value = VALUE;
    }
    public V getKey() {
        return key;
    }
    public K getValue() {
        return value;
    }
    public void setKey(V key) {
        this.key = key; 
    }
    public void setValue(K value) {
        this.value = value;
    }

}