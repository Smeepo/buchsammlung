package control;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import model.Collection;
import model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import repository.CollectionRepository;
import repository.ItemRepository;
import repository.PersonRepository;

@Controller
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class.getName());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @FXML
    private ChoiceBox<Collection> choiceBoxCollections;

    @FXML
    private ChoiceBox<Person> choiceBoxUsers;

    @FXML
    private void initialize(){
        initChoiceBoxes();
        initTableData();
    }

    private void initChoiceBoxes() {
        collectionRepository.findAll().forEach(collection -> choiceBoxCollections.getItems().add((Collection) collection));
        choiceBoxCollections.setConverter(new StringConverter<Collection>() {
            @Override
            public String toString(Collection object) {
                return object.getName();
            }

            @Override
            public Collection fromString(String string) {
                return collectionRepository.findFirstByName(string);
            }
        });
        personRepository.findAll().forEach(person -> choiceBoxUsers.getItems().add((Person) person));
    }

    private void initTableData() {

    }

}