package dad.IMC;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMC extends Application {

	//Labels
	private Label Peso;
	private Label kg;
	private Label Altura;
	private Label cm;
	private Label IMC;
	private Label numberIMC;
	private Label beredicto;
	
	//Textfields
	private TextField pesoText;
	private TextField alturaText;
	
	//Properties
	private StringProperty pesoProperty = new SimpleStringProperty();
	private StringProperty alturaProperty = new SimpleStringProperty();
	private StringProperty beredictoProperty = new SimpleStringProperty();
	
	private DoubleProperty pesoDouble = new SimpleDoubleProperty();
	private DoubleProperty alturaDouble = new SimpleDoubleProperty();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//inicializamos
		Peso = new Label("Peso : ");
		kg = new Label(" kg");
		Altura = new Label("Altura : ");
		cm = new Label(" cm");
		IMC = new Label("IMC : ");
		numberIMC = new Label ("Peso(kg) / altura(cm)^2");
		beredicto = new Label ("Bajo peso | Normal | Sobrepeso | Obesidad");
		
		pesoText = new TextField();
		alturaText = new TextField();

		//Hboxes
		HBox pesoBox = new HBox();
		pesoBox.setFillHeight(false);
		pesoBox.setAlignment(Pos.CENTER);
		pesoBox.getChildren().addAll(Peso, pesoText, kg);
		
		HBox alturaBox = new HBox();
		alturaBox.setFillHeight(false);
		alturaBox.setAlignment(Pos.CENTER);
		alturaBox.getChildren().addAll(Altura, alturaText, cm);
		
		HBox IMCBox = new HBox();
		IMCBox.setFillHeight(false);
		IMCBox.setAlignment(Pos.CENTER);
		IMCBox.getChildren().addAll(IMC, numberIMC);
		
		//VBox
		VBox root = new VBox();
		root.setFillWidth(false);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(pesoBox, alturaBox, IMCBox, beredicto);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("IMC");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//Bindeos
		pesoText.textProperty().bindBidirectional(pesoProperty);
		alturaText.textProperty().bindBidirectional(alturaProperty);
		beredicto.textProperty().bindBidirectional(beredictoProperty);
		
		Bindings.bindBidirectional(
				alturaProperty,
				alturaDouble,
				new NumberStringConverter()
				);
		
		Bindings.bindBidirectional(
				pesoProperty,
				pesoDouble,
				new NumberStringConverter()
				);
		
		numberIMC.textProperty().bind(
				pesoDouble
				.divide(
						alturaDouble.divide(100).multiply(alturaDouble.divide(100))
						)
				.asString()
				);
		
		DoubleProperty c = new SimpleDoubleProperty();
		c.bind(pesoDouble.divide(alturaDouble.divide(100).multiply(alturaDouble.divide(100))));
		
				
		StringExpression elBeredicto = 
				Bindings
				.concat(Bindings
							.when(c.lessThan(18.5))
							.then("Bajo Peso")
							.otherwise("")
							)
				.concat(Bindings
							.when(c.greaterThanOrEqualTo(18.5).and(c.lessThan(25)))
							.then("Normal")
							.otherwise("")
							)
				.concat(Bindings
							.when(c.greaterThanOrEqualTo(25).and(c.lessThan(30)))
							.then("Sobrepeso")
							.otherwise("")
							)
				.concat(Bindings
						.when(c.greaterThanOrEqualTo(30))
						.then("Obeso")
						.otherwise("")
				);
		
				
		beredicto.textProperty().bind(elBeredicto);
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
