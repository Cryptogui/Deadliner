package fxDeadliner;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Max
 * @version 17.2.2017
 *
 */
public class DeadlinerVirheController implements ModalControllerInterface<String> {

    @FXML private Button suljeNappi;
    
    @FXML
    void suljeIkkuna() {
        ModalController.closeStage(suljeNappi);
    }
    
    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String arg0) {
        // TODO Auto-generated method stub
        
    }

}
