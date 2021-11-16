package src;
import java.util.Random;

import javafx.scene.control.Slider;

/****************************************************************
 * Autor ............: Raman Melo Cavalcante
 * Matricula ........: 201820754
 * Inicio ...........: 05/10/2021
 * Ultima alteracao..: 16/10/2021
 * Nome..............: Salao.java
 * Funcao............: onde os clientes sao gerados e o metodo 
 * sentar eh chamado
 * **************************************************************/

public class Salao extends Thread{
  public static int ordem;
  int max = 30, min = 10;
  private Fila fila;
  public static int sldSalao = 10000;
  private Slider sld;
  Random tardis = new Random();
  
  public Salao(Fila fila, Slider sld){
    this.fila = fila;
    this.sld = sld;
  }

/*********************************************************
 * Nome.......: run
 * Funcao.....: a thread cria um novo cliente a cada while rodado
 * Parametros.: 
 * retorno....: void
 *********************************************************/

  public void run(){
    while(true){
      int time  = tardis.nextInt(((max - min) + 1) + min)*1000;
      try {
        Thread.sleep(tardis.nextInt(((5 - 1)+ 1) + 1)*1000);
      } catch (InterruptedException e1) { }
     
      try { 
        Cliente newClient = new Cliente(0,time,null);
        Thread.sleep(sldSalao);
        this.fila.sentar(newClient); 
        System.out.println("Um cliente chegou"+newClient.getCuttingTime());
      }catch (InterruptedException e){}

    }
  }

  public Slider getSld() {
    return sld;
  }

  public void setSld(Slider sld) {
    this.sld = sld;
  }
  
}
