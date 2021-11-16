package src;

import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

/****************************************************************
 * Autor ............: Raman Melo Cavalcante
 * Matricula ........: 201820754
 * Inicio ...........: 05/10/2021
 * Ultima alteracao..: 15/10/2021
 * Nome .............: Barbeiro.java
 * Funcao ...........: uma classe thread que cria objetos clientes para ser 
 * inseridos na lista de espera
 * ************************************************************** */
public class Barbeiro extends Thread{
    private boolean ocupado;
    private Fila fila;
    Cliente retorno;
    ImageView imgCadeiraCorte, imgCabeleilaleila;
    Controller objControle = new Controller();
    public static int slideBarber = 10000;
    private Slider sld;
  
/*********************************************************
 * Nome.......: Barbeiro
 * Funcao.....: Construtor da classe Barbeiro
 * Parametros.: fila do tipo fila, imgCadeiraCorte ImageView da 
 * cadeira de corte, imgCabeleilaleila Imageview de gif do cabeleireiro
 * sld Slider utilizado para alterar a velocidade
 * retorno....:
 *********************************************************/

    public Barbeiro(Fila fila, ImageView imgCadeiraCorte, ImageView imgCabeleilaleila, Slider sld){
      this.fila = fila;
      this.imgCadeiraCorte = imgCadeiraCorte;
      this.imgCabeleilaleila = imgCabeleilaleila;
      this.sld = sld;
    }

/*********************************************************
 * Nome.......: run
 * Funcao.....: executar a funcao da thread
 * Parametros.: 
 * retorno....: void
 *********************************************************/

    public void run(){
      while(true){//enquanto for true o laco vai repetir a thread
        //caso o valor de ordem seja 0 e o valor da variavel seja false
        if(Fila.ordem == 0 && ocupado == false){
          try {
            dormir();} catch (InterruptedException e) {}//chamando o metodo dormir
        }else{
          try {
            //atribui o retorno do metodo levantar na variavel retorno
            retorno = this.fila.levantar();
            cutHair(retorno);//chama o metodo cutHair com a variavel retorno como parametro
          }catch (InterruptedException e){}
        }
      }
    }
    
/*********************************************************
 * Nome.......: cutHair
 * Funcao.....: setar imagem quando estiver cortando cabelo
 * Parametros.: objeto do tipo cliente
 * retorno....: void
 *********************************************************/

    public void cutHair(Cliente retorno) throws InterruptedException{
      ocupado = true;//muda o valor da variavel ocupado pra para true
      Platform.runLater(new Runnable() {
        @Override public void run() {
      imgCadeiraCorte.setVisible(true);//seta visibilidade no ImageView
      imgCabeleilaleila.setVisible(true);//seta visibilidade no ImageView
      imgCabeleilaleila.setImage(Gallery.cut01);
      imgCadeiraCorte.setImage(Gallery.cliente);
      }});
      System.out.println("cortando o cabelo time"+retorno.getCuttingTime()+" -- numchegada"+retorno.getNumChegada());
      Thread.sleep(1000);
      Platform.runLater(() -> imgCadeiraCorte.setImage(Gallery.cutting));
      Thread.sleep(slideBarber);
      System.out.println("cabei de cortar");
      ocupado = false;
      Platform.runLater(new Runnable() {
        @Override public void run() { 
          imgCadeiraCorte.setVisible(false);
          imgCabeleilaleila.setVisible(false);
      }});
    }

/*********************************************************
 * Nome.......: dormir
 * Funcao.....: setar imagem quando estiver dormindo
 * Parametros.: 
 * retorno....: void
 *********************************************************/
    public void dormir() throws InterruptedException{
      Platform.runLater(new Runnable(){
        @Override public void run(){
          imgCadeiraCorte.setImage(Gallery.dormindo);//seta imagem de dormindo no ImageView
          imgCadeiraCorte.setVisible(true);//seta true no ImageView
        }
      });
      ocupado = false;//muda o valor da variavel ocupado para false;
      // enquanto o estado da variavel for false, e o valor da variavel for 0
      // o laco vai ficar em sleep
      while(Fila.ordem == 0 && ocupado == false){
        Thread.sleep(1000);
      }
    }

  public Slider getSld() {
    return sld;
  }

  public void setSld(Slider sld) {
    this.sld.setValue( sld.getValue());
  }
  

  
}
