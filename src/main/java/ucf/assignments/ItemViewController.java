package ucf.assignments;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ItemViewController implements Initializable {

    private Item selectedItem;

    @FXML private Label nameLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label dueDateLabel;
    @FXML private Label statusLabel;

    public void initData(Item item)
    {
        selectedItem = item;
        nameLabel.setText(selectedItem.getName());
        descriptionLabel.setText(selectedItem.getDescription());
        dueDateLabel.setText(selectedItem.getDueDate());
        statusLabel.setText(selectedItem.getStatus());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}

