package ucf.assignments;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class ListViewController implements Initializable {

    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> nameColumn;
    @FXML private TableColumn<Item, String> descriptionColumn;
    @FXML private TableColumn<Item, String> dueDateColumn;
    @FXML private TableColumn<Item, String> statusColumn;

    @FXML private TextField nameTextField;
    @FXML private TextField filterField;

    @FXML private Button detailedPersonViewButton;

    ObservableList<Item> list = FXCollections.observableArrayList();

    public void changeDescriptionCellEvent(CellEditEvent edittedCell)
    {
        Item itemSelected =  tableView.getSelectionModel().getSelectedItem();
        itemSelected.setDescription(edittedCell.getNewValue().toString());
    }

    public void changeDueDateEvent(CellEditEvent edittedCell)
    {
        Item itemSelected =  tableView.getSelectionModel().getSelectedItem();
        itemSelected.setDueDate(edittedCell.getNewValue().toString());
    }

    public void changeStatusEvent(CellEditEvent edittedCell)
    {
        Item itemSelected =  tableView.getSelectionModel().getSelectedItem();
        itemSelected.setStatus(edittedCell.getNewValue().toString());
    }

    public void userClickedOnTable()
    {
        this.detailedPersonViewButton.setDisable(false);
    }

    public void changeSceneToDetailedItemView(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ItemView.fxml"));
        Parent itemViewParent = loader.load();

        Scene tableViewScene = new Scene(itemViewParent);

        ItemViewController controller = loader.getController();
        controller.initData(tableView.getSelectionModel().getSelectedItem());

        Stage window = new Stage();

        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("dueDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("status"));

        tableView.setEditable(true);
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dueDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        this.detailedPersonViewButton.setDisable(true);
    }

    public void filterButtonPushed(ActionEvent event) // DOES NOT WORK
    {

        list = tableView.getItems();
        FilteredList<Item> filteredData = new FilteredList<>(list, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return item.getStatus().toLowerCase().contains(lowerCaseFilter);

            });
        });
    }

    public void deleteButtonPushed()
    {
        ObservableList<Item> selectedRows, items;
        items = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();

        for (Item item: selectedRows)
        {
            items.remove(item);
        }
    }

    public void clearListButtonPushed(ActionEvent event)
    {
        tableView.getItems().clear();;
    }

    public void newItemButtonPushed()
    {
        Item newItem = new Item(nameTextField.getText());
        tableView.getItems().add(newItem);
    }

}