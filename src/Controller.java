package src;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

/****************************************************************
 * Autor ............: Raman Melo Cavalcante
 * Matricula ........: 201820754
 * Inicio ...........: 05/10/2021
 * Ultima alteracao..: 15/10/2021
 * Nome .............: Controller.java
 * Funcao ...........: onde acontece modificacoes na interface grafica
 * ************************************************************** */

public class Controller implements Initializable {

    @FXML
    private ImageView cadeira01;
    
    @FXML
    private ImageView cadeira02;

    @FXML
    private ImageView cadeira04;

    @FXML
    private ImageView cadeira05;

    @FXML
    private ImageView cadeira03;

    @FXML
    private Button btnStart;

    @FXML
    private ImageView cadeiraBarber;
  
    @FXML
    private ImageView imgRunnig;

    @FXML
    private ImageView imgCabeleleila;

    @FXML
    private Slider sldSalao;

    @FXML
    private Slider sldBarbeiro;

    @FXML
    private Label lbl_barbeiro;

    @FXML
    private Label lbl_salao;

    @FXML
    private ImageView imgTv;

/*********************************************************
 * Nome.......: initialize
 * Funcao.....: seta os imageViews das cadeiras
 * Parametros.:
 * retorno....: 
 *********************************************************/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      //seta a imagem de cliente nas cadeiras
      cadeira01.setImage(Gallery.cliente);
      cadeira02.setImage(Gallery.cliente);
      cadeira03.setImage(Gallery.cliente);
      cadeira04.setImage(Gallery.cliente);
      cadeira05.setImage(Gallery.cliente);
      //seta a visibilidade dos ImageView das cadeiras como false
      cadeira01.setVisible(false);
      cadeira02.setVisible(false);
      cadeira03.setVisible(false);
      cadeira04.setVisible(false);
      cadeira05.setVisible(false);

    }

/*********************************************************
 * Nome.......: StartCabeleilaleila
 * Funcao.....: acao do botao btnStart da start nas 
 * threads Sala e barbeiro
 * Parametros.: event do tipo ActionEvent
 * retorno....: void
 *********************************************************/

    @FXML
    void StartCabeleilaleila(ActionEvent event) {
      imgTv.setImage(Gallery.blindado);//imagem do cabelereiro blindado eh setada no imageview da tv
      btnStart.setDisable(false);//deixa o botao desabilitado
      btnStart.setVisible(false);//seta a visibilidade como false
      Fila fila = new Fila(cadeira01,cadeira02,cadeira03,cadeira04,cadeira05, imgRunnig);//instanciando objeto do tipo Fila
      Salao threadSalao = new Salao(fila, sldSalao);//a thread do tipo Salao eh instaciada
      Barbeiro threadBarbeiro = new Barbeiro(fila, cadeiraBarber, imgCabeleleila,sldBarbeiro);//a thread do tipo Barbeiro
      //monitorando so sliders para alterar o valor da variavel
      sldBarbeiro.valueProperty().addListener( (obs, oldValue, newValue) -> threadBarbeiro.slideBarber = (int)(double)newValue);
      sldSalao.valueProperty().addListener( (obs, oldValue, newValue) ->  threadSalao.sldSalao = (int)(double)newValue);
      //as threads sao startadas
      threadBarbeiro.start();
      threadSalao.start();
    }

/*********************************************************
 * Nome.......: onMouse
 * Funcao.....: acao usada no slider 
 * Parametros.: event do tipo MouseEvent
 * retorno....: void
 *********************************************************/

    @FXML
    void onMouse(MouseEvent event) {
      if(event.getSource().equals(sldSalao)){//verifica se o evento esta acontecendo no slider sldSalao
        lbl_salao.setText(""+(int)Salao.sldSalao);//imprimi no Label o valor da variavel alterada
      }
      if(event.getSource().equals(sldBarbeiro)){//verifica se o evento esta acontecendo no slider sldBarbeiro
        if(sldBarbeiro.getValue()<20000) imgCabeleleila.setImage(Gallery.cut04);//dependendo do valor seta image
        if(sldBarbeiro.getValue()<15000) imgCabeleleila.setImage(Gallery.cut03);
        if(sldBarbeiro.getValue()<10000) imgCabeleleila.setImage(Gallery.cut02);
        if(sldBarbeiro.getValue()<5000) imgCabeleleila.setImage(Gallery.cut00);

        lbl_barbeiro.setText(""+(int)Barbeiro.slideBarber);//imprimi no Label o valor da variavel alterada
      }
      
    }
   
    public ImageView getCadeira01() {
      return cadeira01;
    }

    public void setCadeira01(ImageView cadeira01) {
      this.cadeira01 = cadeira01;
    }

    public ImageView getCadeira02() {
      return cadeira02;
    }

    public void setCadeira02(ImageView cadeira02) {
      this.cadeira02 = cadeira02;
    }

    public ImageView getCadeira04() {
      return cadeira04;
    }

    public void setCadeira04(ImageView cadeira04) {
      this.cadeira04 = cadeira04;
    }

    public ImageView getCadeira05() {
      return cadeira05;
    }

    public void setCadeira05(ImageView cadeira05) {
      this.cadeira05 = cadeira05;
    }

    public ImageView getCadeira03() {
      return cadeira03;
    }

    public void setCadeira03(ImageView cadeira03) {
      this.cadeira03 = cadeira03;
    }

    public ImageView getCadeiraBarber() {
      return cadeiraBarber;
    }

    public void setCadeiraBarber(ImageView cadeiraBarber) {
      this.cadeiraBarber = cadeiraBarber;
    }

    

}

