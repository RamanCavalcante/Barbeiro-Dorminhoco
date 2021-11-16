package src;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

/****************************************************************
 * Autor ............: Raman Melo Cavalcante
 * Matricula ........: 201820754
 * Inicio ...........: 05/10/2021
 * Ultima alteracao..: 16/10/2021
 * Nome .............: Fila.java
 * Funcao ...........: onde os objetos do tipo cliente sao
 * guardados e controlados pelo semaforo
 * *************************************************************/

public class Fila {
 
  Controller objControle = new Controller();
  public static int ordem = 0;
  private Semaphore barbeiro;
  private Semaphore clientes;
  private Semaphore semMutex;
  ArrayList<Cliente> queue = new ArrayList<>();
  ImageView imgCadeira01,imgCadeira02,imgCadeira03,imgCadeira04,imgCadeira05,imgRunning;
  ImageView BKimg = new ImageView();


  public Fila(ImageView imgCadeira01, ImageView imgCadeira02, ImageView imgCadeira03, ImageView imgCadeira04, ImageView imgCadeira05, ImageView imgRunning ){
    this.barbeiro = new Semaphore(1);
    this.clientes = new Semaphore(0);
    this.semMutex = new Semaphore(1);
    this.imgCadeira01 = imgCadeira01;
    this.imgCadeira02 = imgCadeira02;
    this.imgCadeira03 = imgCadeira03;
    this.imgCadeira04 = imgCadeira04;
    this.imgCadeira05 = imgCadeira05;
    this.imgRunning = imgRunning;
  }


/*********************************************************
 * Nome.......: sentar
 * Funcao.....: controlado pelos semaforos os objetos do tipo
 * cliente sao adicionados na lista
 * Parametros.: 
 * retorno....: void
 *********************************************************/

  public void sentar(Cliente client) throws InterruptedException{
    semMutex.acquire();
    if(ordem<5){//verifica se o valor de orde eh menor que 5
      ordem++;//acrescenta mais um no valor da variavel ordem
      System.out.println("teste"+ordem);
      clientes.release();
      semMutex.release();
    
      Cliente cliente = client; 
      cliente.setNumChegada(ordem);//seta a variavel ordem como numero de chegada do objeto
      Platform.runLater(new Runnable() {
        @Override public void run() {
      //dependendo do valor de ordem o switch define em qual ImageView a imagem vai ser setada
      switch (ordem) {
        case 1:
            imgCadeira01.setVisible(true);
            BKimg = imgCadeira01;  
          break;
        case 2:
            imgCadeira02.setVisible(true);
            BKimg = imgCadeira02;
          break;
        case 3:
            imgCadeira03.setVisible(true);
            BKimg = imgCadeira03;
          break;
        case 4:
            imgCadeira04.setVisible(true);
            BKimg = imgCadeira04;
          break;
        case 5:
            imgCadeira05.setVisible(true);
            BKimg = imgCadeira05;
          break;
        default:
          break;
      }
    }});
      cliente.setImgCadeira(BKimg);//guarda o ImageView escolhido 
      queue.add(cliente);//o objeto eh adicionado ao ArrayList
      System.out.println("um cliente sentou "+cliente.getNumChegada()+" --  time "+client.getCuttingTime());
    }else{//caso o valor de ordem seja maior maior ou igual a 5 
      semMutex.release();
      embora();//o metodo embora foi chamado 
    } 
  }
/*********************************************************
 * Nome.......: levantar
 * Funcao.....: acha o cliente compativel com a ordem
 * Parametros.: 
 * retorno....: objeto do tipo cliente
 *********************************************************/
  public Cliente levantar() throws InterruptedException{ 
    Cliente client = findClient(ordem);
    barbeiro.acquire();
   // clientes.acquire();
    semMutex.acquire();
    
    Platform.runLater(new Runnable() {
      @Override public void run() {
        System.out.println("ordem"+ordem);
        switch (ordem) {
      case 0:
          imgCadeira01.setVisible(false);
        break;
      case 1:
         imgCadeira02.setVisible(false);
        break;
      case 2:
         imgCadeira03.setVisible(false);
        break;
      case 3:
         imgCadeira04.setVisible(false);
        break;
      case 4:
         imgCadeira05.setVisible(false);
        break;
      default:
        break;
    }
  }});
  ordem--;
    queue.remove(client);
    barbeiro.release();
    semMutex.release(); 
    return client;   
  }
/*********************************************************
 * Nome.......: embora
 * Funcao.....: seta imagem no ImageView e o faz movimentar no eixo x
 * Parametros.: 
 * retorno....: void
 *********************************************************/
  public void embora() throws InterruptedException{
    int i = 0;//cria uma variavel e atribui 0 a ela 
    System.out.println("to indo embora nao tem lugar");
    imgRunning.setVisible(true);//setava a visibilidade como true ao ImageView
    imgRunning.setImage(Gallery.running);//seta o gif no ImageView
    while(i<200){
      //faz o ImageView acrescentar mais 10 no eixo x enquanto estiver dentro do while
      Platform.runLater(() -> imgRunning.setLayoutX(imgRunning.getLayoutX() + 10));
      Thread.sleep(100);
      i++;
    }
    imgRunning.setLayoutX(-42);//reseta a posicao do eixo x
    imgRunning.setVisible(false);//seta a visibilidade do ImageView
  }
  
/*********************************************************
 * Nome.......: findClient
 * Funcao.....: procura o clinte que for compativel com o numero de chegada
 * Parametros.: numChegada do tipo int
 * retorno....: Cliente
 *********************************************************/

  public Cliente findClient(int numChegada){
    Cliente retorno = new Cliente();//instancia um objeto do tipo cliente
    for(int i = 0; i<queue.size(); i++){//percorre o ArrayList
      if(queue.get(i).getNumChegada()==numChegada){//compara o getNumChegada com recebido no parametro
        retorno =  queue.get(i);//atribui o objeto ao retorno
        break;
      }
    }
    return retorno;//retorno o objeto
  }
}
