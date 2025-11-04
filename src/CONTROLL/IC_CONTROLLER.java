package CONTROLL;

import MODEL.ice;
import MODEL.listen;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class IC_CONTROLLER {
    @FXML
    private Label NameLabel;

    @FXML
    private Label PriceLabel;

    @FXML
    private ImageView img;

    @FXML
    private AnchorPane anchorPane;

    private ice Ice;
    private listen Listen;

    public void setData(ice Ice, listen Listen) {
        this.Ice = Ice;
        this.Listen = Listen;
        NameLabel.setText(Ice.getName());
        PriceLabel.setText("₱" + Ice.getPrice());
        try {
            Image image = new Image(getClass().getResourceAsStream(Ice.getImgsrc()));
            img.setImage(image);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());


        }
    }

    @FXML
    private void handleItemClick(MouseEvent mouseEvent) {
        if (Listen != null) {
            Listen.onClickListener(Ice);
            System.out.println("Item clicked: " + Ice.getName());  // Debug
        }
    }
}
     