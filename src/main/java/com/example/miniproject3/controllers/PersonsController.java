package com.example.miniproject3.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import com.example.miniproject3.models.Person;
import com.example.miniproject3.models.PersonsStore;

public class PersonsController {
    @FXML
    private Button addBtn;
    @FXML
    private TableColumn<Person, SimpleStringProperty> addressCol;
    @FXML
    private TextField addressFld;
    @FXML
    private TableColumn<Person, SimpleIntegerProperty> ageCol;
    @FXML
    private TextField ageFld;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableColumn<Person, SimpleStringProperty> nameCol;
    @FXML
    private TextField nameFld;
    @FXML
    private TableView<Person> personsTable;
    @FXML
    private Button updateBtn;
    @FXML
    private Text errorMsg;

    // New fields for GPA, Gender, Major, Phone Number
    @FXML
    private TableColumn<Person, SimpleDoubleProperty> satisfactionScoreCol;
    @FXML
    private TextField satisfactionScoreFld;
    @FXML
    private TableColumn<Person, SimpleStringProperty> genderCol;
    @FXML
    private RadioButton maleRadioBtn;
    @FXML
    private RadioButton femaleRadioBtn;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private TableColumn<Person, SimpleStringProperty> companyCol;
    @FXML
    private TextField companyFld;
    @FXML
    private TableColumn<Person, SimpleStringProperty> phoneNumberCol;
    @FXML
    private TextField phoneNumberFld;

    private final PersonsStore personStore = new PersonsStore();

    @FXML
    public void initialize() {
        // Set up all table columns
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        satisfactionScoreCol.setCellValueFactory(new PropertyValueFactory<>("satisfactionScore"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        companyCol.setCellValueFactory(new PropertyValueFactory<>("company"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        ObservableList<Person> persons = personStore.getPersonsList();
        personsTable.setItems(persons);

        // Selection listener to populate form fields
        personsTable.getSelectionModel().selectedItemProperty().addListener(evt -> {
            Person selectedPerson = personsTable.getSelectionModel().getSelectedItem();
            if(selectedPerson != null) {
                nameFld.setText(selectedPerson.getName());
                ageFld.setText(Integer.toString(selectedPerson.getAge()));
                addressFld.setText(selectedPerson.getAddress());
                satisfactionScoreFld.setText(Double.toString(selectedPerson.getSatisfactionScore()));
                if(selectedPerson.getGender().equalsIgnoreCase("Male")) {
                    maleRadioBtn.setSelected(true);
                } else if(selectedPerson.getGender().equalsIgnoreCase("Female")) {
                    femaleRadioBtn.setSelected(true);
                }
                companyFld.setText(selectedPerson.getMajor());
                phoneNumberFld.setText(selectedPerson.getPhoneNumber());
            }
        });
    }

    @FXML
    void addPerson(ActionEvent event) {
        String error = "";
        boolean isValid = true;

        // Validate Name
        String name = nameFld.getText();
        if(name.isEmpty()) {
            error += "Error: Name is required\n";
            isValid = false;
        }

        // Validate Age
        Integer age = null;
        if(ageFld.getText().isEmpty()) {
            error += "Error: Age is required\n";
            isValid = false;
        }
        else try {
            age = Integer.parseInt(ageFld.getText());
            if(age <= 0) {
                error += "Error: Age must be positive\n";
                isValid = false;
            }
        } catch(NumberFormatException ex) {
            error += "Error: Invalid age value!\n";
            isValid = false;
        }

        // Validate Address
        String address = addressFld.getText();
        if(address.isEmpty()) {
            error += "Error: Address is required\n";
            isValid = false;
        }

        // Validate GPA
        Double satisfactionScore = null;
        if(satisfactionScoreFld.getText().isEmpty()) {
            error += "Error: Satisfaction Score is required\n";
            isValid = false;
        }
        else try {
            satisfactionScore = Double.parseDouble(satisfactionScoreFld.getText());
            if(satisfactionScore < 0.0 || satisfactionScore > 5.0) {
                error += "Error: Satisfaction Score must be between 0.0 and 5.0\n";
                isValid = false;
            }
        } catch(NumberFormatException ex) {
            error += "Error: Invalid Satisfaction Score value!\n";
            isValid = false;
        }

        // Validate Gender
        // Validate Gender
        String gender = null;
        if(maleRadioBtn.isSelected()) {
            gender = "Male";
        } else if(femaleRadioBtn.isSelected()) {
            gender = "Female";
        } else {
            error += "Error: Gender is required\n";
            isValid = false;
        }

        // Validate Major
        String company = companyFld.getText();
        if(company.isEmpty()) {
            error += "Error: Major is required\n";
            isValid = false;
        }

        // Validate Phone Number
        String phoneNumber = phoneNumberFld.getText();
        if(phoneNumber.isEmpty()) {
            error += "Error: Phone number is required\n";
            isValid = false;
        }

        if(isValid) {
            personStore.addPerson(new Person(name, age, address, satisfactionScore, gender, company, phoneNumber));
            // Clear all fields
            nameFld.setText("");
            ageFld.setText("");
            addressFld.setText("");
            satisfactionScoreFld.setText("");
            genderGroup.selectToggle(null);
            companyFld.setText("");
            phoneNumberFld.setText("");
            errorMsg.setText("");
        }
        else {
            errorMsg.setText(error);
        }
    }

    @FXML
    void deletePerson(ActionEvent event) {
        Person selectedPerson = personsTable.getSelectionModel().getSelectedItem();
        if(selectedPerson != null) {
            personStore.deletePerson(selectedPerson);
            // Clear all fields
            nameFld.setText("");
            ageFld.setText("");
            addressFld.setText("");
            satisfactionScoreFld.setText("");
            genderGroup.selectToggle(null);
            companyFld.setText("");
            phoneNumberFld.setText("");
            errorMsg.setText("");
        }
    }

    @FXML
    void updatePerson(ActionEvent event) {
        Person selectedPerson = personsTable.getSelectionModel().getSelectedItem();
        if(selectedPerson != null) {
            String error = "";
            boolean isValid = true;

            // Validate Name
            String name = nameFld.getText();
            if(name.isEmpty()) {
                error += "Error: Name is required!\n";
                isValid = false;
            }

            // Validate Age
            Integer age = null;
            if(ageFld.getText().isEmpty()) {
                error += "Error: Age is required!\n";
                isValid = false;
            } else try {
                age = Integer.parseInt(ageFld.getText());
                if(age <= 0) {
                    error += "Error: Age must be positive!\n";
                    isValid = false;
                }
            } catch(NumberFormatException ex) {
                error += "Error: Invalid age value!\n";
                isValid = false;
            }

            // Validate Address
            String address = addressFld.getText();
            if(address.isEmpty()) {
                error += "Error: Address is required!\n";
                isValid = false;
            }

            // Validate GPA
            // Validate Satisfaction Score
            Double satisfactionScore = null;
            if(satisfactionScoreFld.getText().isEmpty()) {
                error += "Error: Satisfaction Score is required!\n";
                isValid = false;
            } else try {
                satisfactionScore = Double.parseDouble(satisfactionScoreFld.getText());
                if(satisfactionScore < 0.0 || satisfactionScore > 5.0) {
                    error += "Error: Satisfaction Score must be between 0.0 and 5.0!\n";
                    isValid = false;
                }
            } catch(NumberFormatException ex) {
                error += "Error: Invalid Satisfaction Score value!\n";
                isValid = false;
            }

            // Validate Gender
            // Validate Gender
            String gender = null;
            if(maleRadioBtn.isSelected()) {
                gender = "Male";
            } else if(femaleRadioBtn.isSelected()) {
                gender = "Female";
            } else {
                error += "Error: Gender is required!\n";
                isValid = false;
            }

            // Validate Major
            String company = companyFld.getText();
            if(company.isEmpty()) {
                error += "Error: Major is required!\n";
                isValid = false;
            }

            // Validate Phone Number
            String phoneNumber = phoneNumberFld.getText();
            if(phoneNumber.isEmpty()) {
                error += "Error: Phone number is required!\n";
                isValid = false;
            }

            if(isValid) {
                personStore.updatePerson(selectedPerson, name, age, address, satisfactionScore, gender, company, phoneNumber);
                errorMsg.setText("");
            }
            else {
                errorMsg.setText(error);
            }
        }
    }
}