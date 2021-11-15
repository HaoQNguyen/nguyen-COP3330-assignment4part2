package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;

public class Item {

    private final SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleStringProperty dueDate;
    private SimpleStringProperty status;

    public Item(String name)
    {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty();
        this.dueDate = new SimpleStringProperty();
        this.status = new SimpleStringProperty("Incomplete");
    }

    public String getName() {
        return name.get();
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public String getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(String dueDate) {
        this.dueDate = new SimpleStringProperty(dueDate);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status = new SimpleStringProperty(status);
    }
}
