package CONTROLL;

import MODEL.ice;
import MODEL.listen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewControl implements Initializable {
    @FXML
    private VBox OrderCard;

    @FXML
    private Label OrderNameLabel;

    @FXML
    private Label OrderPriceLabel;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private ImageView ORDERIMG;

    @FXML
    private Button OrderButton;

    @FXML
    private ListView<String> OrderListView;

    @FXML
    private ComboBox<Integer> quantityComboBox;


    private List<ice> ices = new ArrayList<>();
    private Image image;
    private listen Listen;
    private ice selectedIce;
    private ObservableList<String> orderItems = FXCollections.observableArrayList();


    public ViewControl() {

    }


    private List<ice> getData() {
        List<ice> ices = new ArrayList<>();
        ice Ice;

        Ice = new ice();
        Ice.setName("MANGO SUPREME");
        Ice.setPrice(100);
        Ice.setImgsrc("/IMG/miguelitos_mangosupreme.png");
        Ice.setColor("FCC61D");
        ices.add(Ice);

        Ice = new ice();
        Ice.setName("MANGO ICE CREAM");
        Ice.setPrice(35);
        Ice.setImgsrc("/IMG/miguelitos_mangoicecream.png");
        Ice.setColor("FCC61D");
        ices.add(Ice);

        Ice = new ice();
        Ice.setName("HYPED MANGO");
        Ice.setPrice(110);
        Ice.setImgsrc("/IMG/miguelitos_hypedmango.png");
        Ice.setColor("FCC61D");
        ices.add(Ice);

        Ice = new ice();
        Ice.setName("UBE MANGO CONE");
        Ice.setPrice(35);
        Ice.setImgsrc("/IMG/miguelitos_ubemangocone.png");
        Ice.setColor("FCC61D");
        ices.add(Ice);

        Ice = new ice();
        Ice.setName("MANGO FLOAT");
        Ice.setPrice(100);
        Ice.setImgsrc("/IMG/miguelitos_mangofloat.png");
        Ice.setColor("FCC61D");
        ices.add(Ice);

        Ice = new ice();
        Ice.setName("MANGO JUICE");
        Ice.setPrice(80);
        Ice.setImgsrc("/IMG/miguelitos_mangojuice.png");
        Ice.setColor("FCC61D");
        ices.add(Ice);

        Ice = new ice();
        Ice.setName("MANGO SHAKE");
        Ice.setPrice(110);
        Ice.setImgsrc("/IMG/miguelitos_mangoshake.png");
        Ice.setColor("FCC61D");
        ices.add(Ice);

        Ice = new ice();
        Ice.setName("NUTTY MANGOES");
        Ice.setPrice(140);
        Ice.setImgsrc("/IMG/miguelitos_nuttymango.png");
        Ice.setColor("FCC61D");
        ices.add(Ice);

        Ice = new ice();
        Ice.setName("PURPLE YUMANGO");
        Ice.setPrice(145);
        Ice.setImgsrc("/IMG/miguelitos_purpleyamango.png");
        Ice.setColor("FCC61D");
        ices.add(Ice);

        Ice = new ice();
        Ice.setName("MANGO CHEESECAKE");
        Ice.setPrice(135);
        Ice.setImgsrc("/IMG/miguelitos_mangocheesecake.png");
        Ice.setColor("FCC61D");
        ices.add(Ice);

        Ice = new ice();
        Ice.setName("MANGO INUTAK");
        Ice.setPrice(135);
        Ice.setImgsrc("/IMG/miguelitos_mangoinutak.png");
        Ice.setColor("FCC61D");
        ices.add(Ice);

        Ice = new ice();
        Ice.setName("MANGO SAGO");
        Ice.setPrice(110);
        Ice.setImgsrc("/IMG/miguelitos_mangosago.png");
        Ice.setColor("FCC61D");
        ices.add(Ice);

        return ices;
    }

    private void chosenOrder(ice Ice) {
        selectedIce = Ice;
        System.out.println("chosenOrder called for: " + Ice.getName());
        OrderNameLabel.setText(Ice.getName());
        OrderPriceLabel.setText("" + Ice.getPrice());
        image = new Image(getClass().getResourceAsStream(Ice.getImgsrc()));
        ORDERIMG.setImage(image);
        OrderCard.setStyle("-fx-background-color: #" + Ice.getColor() + ";\n" +
                "-fx-background-radius: 48px 0;");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ices.addAll(getData());
        OrderListView.setItems(orderItems);

        quantityComboBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        quantityComboBox.setValue(1);
        if (ices.size() > 0) {
            chosenOrder(ices.get(0));

            Listen = new listen() {
                @Override
                public void onClickListener(ice Ice) {
                    chosenOrder(Ice);
                }
            };
        }
        int column = 0;
        int row = 0;

        //baguhin kolang yung width niggeru
        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
        grid.setMinWidth(Region.USE_PREF_SIZE);

        //baguhin kolang yung HEIGHT niggeru
        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
        grid.setMaxHeight(Region.USE_PREF_SIZE);



        try {
            for (int i = 0; i < ices.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/PROJ/ICE_CREAM.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                IC_CONTROLLER iC_CONTROLLER = fxmlLoader.getController();
                iC_CONTROLLER.setData(ices.get(i), Listen);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleOrder() {
        System.out.println("ORDER button clicked");  // Debug
        if (selectedIce != null) {
            double quantity = quantityComboBox.getValue();
            double totalPrice = selectedIce.getPrice() * quantity;
            String orderEntry = quantity + " x " + selectedIce.getName() + " - ₱" + totalPrice;
            orderItems.add(orderEntry);
            System.out.println("Added to order: " + orderEntry);  // Debug
        } else {
            System.out.println("No item selected! Click an item first.");  // Debug
        }
    }

}
