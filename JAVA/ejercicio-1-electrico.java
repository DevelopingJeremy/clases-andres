/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;
import java.util.Scanner;
/**
 *
 * @author aagui
 */
public class JavaApplication1 {
    
    static Scanner lectura = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Definir Variables
        String nuevoConductor = "N";
        int conductoresAnalizados = 0;
        double energiaTotal = 0;
        double potenciaPromedio = 0;
        
        String nombreMayorPotencia = "";
        double mayorPotencia = 0;
        String nombreMayorEnergia = "";
        double mayorEnergia = 0;
        

        do{
            
            String nombreOCodigo = "";
            double voltajeAplicado = 0;
            double resistenciaConductor = 0;
            double temperaturaInicial = 0;
            double temperaturaEstimada = 0;
            double coeficienteTermico = 0;
            double tiempoFuncionamiento = 0;
        
            // Pedir Datos al Usuario
            System.out.println("Ingrese Nombre o codigo del conductor");
            nombreOCodigo  = lectura.nextLine();

            System.out.println("Ingrese Voltaje aplicado");
            voltajeAplicado  = ValidarMayorQueCero();

            System.out.println("Ingrese Resistencia inicial del conductor");
            resistenciaConductor  = ValidarMayorQueCero();

            System.out.println("Ingrese Temperatura Inicial");
            temperaturaInicial  = lectura.nextDouble();

            System.out.println("Ingrese Temperatura final estimada");
            temperaturaEstimada  = lectura.nextDouble();

            System.out.println("Ingrese Coeficiente térmico del  material");
            coeficienteTermico  = ValidacionPositivo();

            System.out.println("Ingrese Tiempo de funcionamiento en horas");
            tiempoFuncionamiento  = ValidarMayorQueCero();
            
            lectura.nextLine();

            // Llama al metodo de reistencia final
            double resistenciaFinal = CalcularResistenciaFinal(resistenciaConductor,coeficienteTermico,temperaturaEstimada,temperaturaInicial);
            
            if (resistenciaFinal <= 0) {
                System.out.println("La resistencia final es invalida.");
                continue;
            }

            //LLama al metodo  de Corriente
            double corriente = CalcularCorriente(voltajeAplicado,resistenciaFinal);

            //LLama al  metodo de Potencia disipada
            double potenciaDisipada = CalcularPotenciaDisipada(corriente,resistenciaFinal);

            //Llama al metodo de Energia Consumida
            double energiaConsumida = CalcularEnergiaConsumida(potenciaDisipada,tiempoFuncionamiento);

            //LLama al metodo Clasificacion Conductor
            String clasificacion = ClasificarConductor(potenciaDisipada);

            // Resultados del conductor 
            System.out.println("----- Resultados -----");
            System.out.println("Resistencia Final: " + resistenciaFinal + " ohm");
            System.out.println("Corriente " + corriente + " A");
            System.out.println("Potencia " + potenciaDisipada + " W" );
            System.out.println("Energia " + energiaConsumida + " Wh");
            System.out.println("Clasificacion "+ clasificacion);
            
            // Calcular para reporte final
            conductoresAnalizados++;
            energiaTotal += energiaConsumida;
            potenciaPromedio += potenciaDisipada;
            
            if (potenciaDisipada > mayorPotencia) {
                nombreMayorPotencia = nombreOCodigo;
                mayorPotencia = potenciaDisipada;
            }
            
            if (energiaConsumida > mayorEnergia) {
                nombreMayorEnergia = nombreOCodigo;
                mayorEnergia = energiaConsumida;
            }
            
            //Analizar otro conductor 
            System.out.println("Desea analizar otro conductor? (S/N): ");
            nuevoConductor = lectura.nextLine();
        } while (nuevoConductor.equalsIgnoreCase("S"));
        
        // Reporte final
        System.out.println("----- Reporte Final -----");
        System.out.println("Conductores Analizados: " + conductoresAnalizados);
        System.out.println("Energia Total: " + energiaTotal + " Wh");
        System.out.println("Potencia promedio: " + (potenciaPromedio / conductoresAnalizados) + " W" );
        System.out.println("Mayor potencia: " + nombreMayorPotencia + " ("+mayorPotencia+" W)");
        System.out.println("Mayor energia: " + nombreMayorEnergia + " ("+mayorEnergia+" W)");
    }
    
    //Metodos de validación
    public static double ValidarMayorQueCero () {
        double valor;
        
        do {
            valor = lectura.nextDouble();
            if (valor <= 0) {
                System.out.println("El valor debe ser mayor que 0");
                
            }
        } while (valor <= 0);
        return valor;
    }
    
    //Metodo validacion Positivo 
        public static double ValidacionPositivo () {
        double valor;
        
        do {
            valor = lectura.nextDouble();
            if (valor < 0) {
                System.out.println("El valor debe ser positivo");
                
            }
        } while (valor < 0);
        return valor;
    }
    
    
    
    // Metodo para calcular la resistencia final
    public static double CalcularResistenciaFinal (double Ro,double Cx,double Tf,double Ti){
        return Ro * (1 + Cx * (Tf - Ti));
    }
    
    //Metodo para calcular la corriente 
    public static double CalcularCorriente (double V,double Rf){
        return V/Rf;
    }
        
    //Metodo para calcular la  Potencia Disipada
    public static double CalcularPotenciaDisipada (double I,double Rf){
        return Math.pow(I,2)* Rf;
    }
    
    //Metodo para calcular la Energia Consumida
    public static double CalcularEnergiaConsumida (double P,double t){
        return P * t;
    }
            
    //Metodo  
    public static String ClasificarConductor (double P){
       String clasificacion = "";
       if(P < 100){
           clasificacion = "Funcionamiento Seguro";     
       } else if (P >=100 & P <= 500) {
           clasificacion = "Calentamiento Moderado";              
       } else if (P > 500) {
           clasificacion = "Riesgo de Sobre Calentamiento";        
       }
       
           
      return clasificacion;
    }
    
}