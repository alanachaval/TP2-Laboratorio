package gui.controladores;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import excepciones.FechasInvalidasException;
import excepciones.StringVacioException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import laboratorio.Laboratorio;

public class MostrarEstadisticaControlador {

	@FXML
	private AnchorPane anchorPaneMain;

	@FXML
	private Button buttonAceptar;

	@FXML
	private GridPane gridPaneDate;

	@FXML
	private Button buttonMostrar;

	@FXML
	private TextArea textAreaEstadistica;

	private DatePicker desdeDatePicker;

	private DatePicker hastaDatePicker;

	private LaboratorioControlador laboratorioControlador;

	private Laboratorio laboratorio = Laboratorio.getIntance();

	@FXML
	private void initialize() {
		assert anchorPaneMain != null : "fx:id=\"anchorPaneMain\" was not injected: check your FXML file 'MostrarEstadistica.fxml'.";
		assert buttonAceptar != null : "fx:id=\"buttonAceptar\" was not injected: check your FXML file 'MostrarEstadistica.fxml'.";
		assert gridPaneDate != null : "fx:id=\"gridPaneDate\" was not injected: check your FXML file 'MostrarEstadistica.fxml'.";
		assert buttonMostrar != null : "fx:id=\"buttonMostrar\" was not injected: check your FXML file 'MostrarEstadistica.fxml'.";
		assert textAreaEstadistica != null : "fx:id=\"textAreaEstadistica\" was not injected: check your FXML file 'MostrarEstadistica.fxml'.";
		inicializarDatePickers();

	}

	private void inicializarDatePickers() {
		// Initialize the DatePicker for birthday
		desdeDatePicker = new DatePicker(Locale.getDefault());
		desdeDatePicker.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		desdeDatePicker.getCalendarView().todayButtonTextProperty().set("Hoy");
		desdeDatePicker.getCalendarView().setShowWeeks(false);
		desdeDatePicker.getStylesheets().add("gui/vistas/DatePicker.css");

		hastaDatePicker = new DatePicker(Locale.getDefault());
		hastaDatePicker.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		hastaDatePicker.getCalendarView().todayButtonTextProperty().set("Hoy");
		hastaDatePicker.getCalendarView().setShowWeeks(false);
		hastaDatePicker.getStylesheets().add("gui/vistas/DatePicker.css");

		// Add DatePicker to grid
		gridPaneDate.add(desdeDatePicker, 0, 1);
		gridPaneDate.add(hastaDatePicker, 1, 1);
	}

	public void inicializarDeLaboratorio(LaboratorioControlador l) {
		this.laboratorioControlador = l;
	}

	@FXML
	void buttonAceptarOnAction() {

	}

	@FXML
	void buttonMostrarOnAction() throws IOException {
		try {
			if (this.desdeDatePicker.getSelectedDate() == null || this.hastaDatePicker.getSelectedDate() == null) {
				throw new StringVacioException();
			}
			this.textAreaEstadistica.setText(this.laboratorio.listarEstadisticas(desdeDatePicker.getSelectedDate(),
					hastaDatePicker.getSelectedDate()));

		} catch (FechasInvalidasException | StringVacioException e) {
			this.laboratorioControlador.mensaje("Error", e.getMessage());
		}
	}
}
