package Vista;

import modelo.IMC;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;


public class CalcularIMC extends Application {

	//Preparemos los textos
	private TextField pesoText;
	private TextField alturaText;
	private Label IMCText;
	private Label textoInfo;
	//Creamos un objeto de la clase IMC
	private IMC imc = new IMC();
	
	StringConverter<Number> converter = new NumberStringConverter();
	SimpleStringProperty resultado = new SimpleStringProperty();
	
	/*//Nos muestra la info según el índice de nuesto objeto imc
	private SimpleStringProperty darMiInfo() {
		double indice = imc.getIndice().get();
		String texto = "";
		
		if(indice < 18.5) {
			texto = "Bajo peso";
		}else if(18.5 <= indice && indice < 25){
			texto = "Peso normal";
		}else if(25 <= indice && indice < 30) {
			texto = "Sobrepeso";
		}else {
			texto = "Obesidad";
		}
		resultado.set(texto);
		return resultado;
		
	}
	*/
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		//Texto para peso
		pesoText = new TextField();
		pesoText.setPrefColumnCount(4);
		pesoText.textProperty().bindBidirectional(imc.getPeso(), converter);
		
		//Texto para altura
		alturaText = new TextField();
		alturaText.setPrefColumnCount(4);
		alturaText.textProperty().bindBidirectional(imc.getAlturaProperty(), converter);
		
		//Label para visualizar texto
		IMCText = new Label();
		IMCText.textProperty().bind(imc.getIndice().asString());
		
		
		textoInfo = new Label("Aqui debe haber texto o algo");
		imc.getIndice().addListener(listener->{		
			double indice = imc.getIndice().get();
			String texto = "";
			
			if(indice < 18.5) {
				texto = "Bajo peso";
			}else if(18.5 <= indice && indice < 25){
				texto = "Peso normal";
			}else if(25 <= indice && indice < 30) {
				texto = "Sobrepeso";
			}else {
				texto = "Obesidad";
			}
			textoInfo.setText(texto);
			
										});
		//Creamos la línea de altura
		HBox alturaBox = new HBox();
		Label textoLabel = new Label("Altura: ");
		Label textoLabel2 = new Label("cm");
		alturaBox.setAlignment(Pos.BASELINE_CENTER);
		alturaBox.setSpacing(5);
		alturaBox.getChildren().addAll(textoLabel, alturaText, textoLabel2);
		
		//Creamos la línea de peso
		HBox pesoBox = new HBox();
		Label textoLabel3 = new Label("/ Peso: ");
		Label textoLabel4 = new Label("kg");
		alturaBox.setAlignment(Pos.BASELINE_CENTER);
		alturaBox.setSpacing(5);
		alturaBox.getChildren().addAll(textoLabel3, pesoText, textoLabel4);
		
		//Aqui saldra el valor del IMC
		HBox imcBox = new HBox();
		imcBox.setAlignment(Pos.BASELINE_CENTER);
		imcBox.setSpacing(5);
		imcBox.getChildren().addAll(IMCText);
		
		//Y aqui resultado (Sobrepeso, delgado, etc... )
		HBox infoBox = new HBox();
		infoBox.setSpacing(5);
		infoBox.setAlignment(Pos.BASELINE_CENTER);
		infoBox.getChildren().addAll(textoInfo);
		
		//Creamos todo el conjunto
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(pesoBox, alturaBox, imcBox, infoBox);
		
		//Creamos una escena, que es la ventana
		Scene ventana = new Scene(root, 320, 200);
		
		primaryStage.setScene(ventana);
		primaryStage.setTitle("Calcula tu IMC");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
