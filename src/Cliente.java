package src;


import javafx.scene.image.ImageView;
/****************************************************************
 * Autor ............: Raman Melo Cavalcante
 * Matricula ........: 201820754
 * Inicio ...........: 05/10/2021
 * Ultima alteracao..: 15/10/2021
 * Nome .............: Cliente.java
 * Funcao ...........: Classe do tipo cliente 
 * ***************************************************************/
public class Cliente {
  private int numChegada;
  private int cuttingTime;
  private ImageView imgCadeira;

  public Cliente(){};

/*********************************************************
 * Nome.......: Cliente
 * Funcao.....: contrutor da classe Cliente 
 * Parametros.: numChegada do tipo int, cuttingTime do tipo int
 * imgCadeira do tipo ImageView
 * retorno....: 
 *********************************************************/

  public Cliente(int numChegada, int cuttingTime, ImageView imgCadeira) {
    this.numChegada = numChegada;
    this.cuttingTime = cuttingTime;
    this.imgCadeira = imgCadeira;
  }
  
  public ImageView getImgCadeira() {
    return imgCadeira;
  }
  public void setImgCadeira(ImageView imgCadeira) {
    this.imgCadeira = imgCadeira;
  }
  public int getNumChegada() {
    return numChegada;
  }
  public void setNumChegada(int numChegada) {
    this.numChegada = numChegada;
  }
  public int getCuttingTime() {
    return cuttingTime;
  }
  public void setCuttingTime(int cuttingTime) {
    this.cuttingTime = cuttingTime;
  }
  
}
