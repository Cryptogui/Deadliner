package fxDeadliner;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import deadliner.Kalenteri;
import fxDeadliner.DeadlinerGUIController;

/**
 * @author Max
 * @version 27.1.2017
 *
 */
public class DeadlinerMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("DeadlinerGUI.fxml"));
            final Pane root = (Pane)ldr.load(); //Pane on geneerisempi container, korvaa BorderPanen.
            final DeadlinerGUIController deadCtrl = (DeadlinerGUIController)ldr.getController();
		    
			final Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("Deadliner.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Deadliner");
			
			Kalenteri kalenteri = new Kalenteri();
			deadCtrl.setKalenteri(kalenteri); //luo "kalenteri" olion
			primaryStage.show(); //nayttaa ikkunan
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param args ffs
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
