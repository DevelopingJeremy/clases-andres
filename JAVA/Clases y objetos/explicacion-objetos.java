// SUPERCLASE / CLASE PADRE
class Animal {

    // ATRIBUTO / CAMPO
    protected String nombre;
    protected int edad;

    // CONSTRUCTOR
    Animal(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // MÉTODO
    void comer() {
        System.out.println(nombre + " está comiendo");
    }
}


// SUBCLASE / CLASE HIJA
class Perro extends Animal { // HERENCIA

    // ATRIBUTO PROPIO DE PERRO
    String raza;

    // CONSTRUCTOR
    Perro(String nombre, int edad, String raza) {

        // Llama al constructor de la clase PADRE
        super(nombre, edad); // Referencia

        // Atributo de ESTE objeto
        this.raza = raza;
    }

    // MÉTODO PROPIO DE PERRO
    void ladrar() {
        System.out.println(nombre + " dice: ¡Guau!");
    }
}


// CLASE PRINCIPAL
public class Main {

    public static void main(String[] args) {

        // CREAR UNA INSTANCIA / OBJETO
        Perro perro = new Perro("Firulais", 5, "Labrador");

        // MÉTODO HEREDADO DE ANIMAL
        perro.comer();

        // MÉTODO PROPIO DE PERRO
        perro.ladrar();

        // ACCEDER A ATRIBUTOS
        System.out.println(perro.nombre);
        System.out.println(perro.edad);
        System.out.println(perro.raza);
    }
}
