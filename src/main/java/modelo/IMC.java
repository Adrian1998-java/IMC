package modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class IMC {
	//Declaramos las variables necesarias para nuestra aplicación
	private SimpleDoubleProperty altura;
	private SimpleDoubleProperty peso;
	private SimpleDoubleProperty indice;
	
	//Declaramos Geters y Seters
	
	//Altura
	public final SimpleDoubleProperty getAlturaProperty() {
		return altura;
	}
	public final void setAltura(SimpleDoubleProperty altura) {
		this.altura = altura;
	}
	//Te devuelve en formato double
	public final double getAltura() {
		return this.getAlturaProperty().get();
	}
	
	public final void setAltura(double altura) {
		this.getAlturaProperty().set(altura);
	}
	
	
	//Peso
	public final SimpleDoubleProperty getPeso() {
		return this.peso;
	}
	public final void setPeso(SimpleDoubleProperty peso) {
		this.peso = peso;
	}
	public final void setPeso(double peso) {
		this.getPeso().set(peso);
	}
	
	//Indice
	public final SimpleDoubleProperty getIndice() {
		return indice;
	}
	public final void setIndice(SimpleDoubleProperty indice) {
		this.indice = indice;
	}
	
	//Función que calcula el Indice de Masa Corporal
	public IMC() {
		this.altura = new SimpleDoubleProperty();
		this.peso = new SimpleDoubleProperty();
		this.indice = new SimpleDoubleProperty();
		
		indice.bind(getPeso().divide(getAlturaProperty().divide(100).multiply(getAlturaProperty().divide(100)) ));
		
	}
	
	
	
	
}
