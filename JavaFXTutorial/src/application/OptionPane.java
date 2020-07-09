package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class OptionPane {
	
	//The logos that will be shown with respect to message type
	//By putting / in front of path, it is equivalent to src/...
	private static ImageView alertLogo = new ImageView( OptionPane.class.getResource("/Images/alertLogo.png").toString() );
	private static ImageView errorLogo = new ImageView (OptionPane.class.getResource("/Images/errorIcon.png").toString() );
	private static ImageView tickLogo = new ImageView (OptionPane.class.getResource("/Images/tickIcon.png").toString() );
	
	//Constants to be passed as argument to determine which logo to be shown beside the text
	public static final int DEFAULT_MESSAGE = 0;
	public static final int ALERT_MESSAGE = 1;
	public static final int ERROR_MESSAGE = 2;
	public static final int ACCEPT_MESSAGE = 3;
	
	public static final String YES_BUTTON = "YES";
	public static final String NO_BUTTON = "NO";
	public static final String CANCEL_BUTTON = "CANCEL";
	
	//The answers from the showInputDialog, will be returned everytime the dialog is closed
	private static String selection = CANCEL_BUTTON;
	private static String inputField = "";
	
	//static initializer to set each image to the suitable size
	static {
		alertLogo.setFitHeight(40);
		alertLogo.setFitWidth(40);
		alertLogo.setPreserveRatio(true);
		alertLogo.setSmooth(true);
		
		errorLogo.setFitHeight(40);
		errorLogo.setFitWidth(40);
		errorLogo.setPreserveRatio(true);
		errorLogo.setSmooth(true);
		
		tickLogo.setFitHeight(40);
		tickLogo.setFitWidth(40);
		tickLogo.setPreserveRatio(true);
		tickLogo.setSmooth(true);
	}
	
	//--------------------------------------ALERT WINDOWS-----------------------------------------------------------------
	
	//Show a message dialog with only a OK button
	
	public static void showMessageDialog(String title, String message, int messageType) {
		
		Label label = setUpLabel(message, messageType);
		
		Button okButton = new Button("OK");
		
		
		VBox layout = setUpVBox(label, okButton);
		
		Stage stage = setUpStage(layout, title);
		
		okButton.setOnAction(e -> stage.close() );
		stage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode().equals(KeyCode.ENTER))
				okButton.fire();
		});
		
		stage.showAndWait();
		
	}
	
	public static void showMessageDialog(String message) {
		showMessageDialog("Message", message, DEFAULT_MESSAGE);
	}
	
	public static void showMessageDialog(String title, String message) {
		showMessageDialog(title, message, DEFAULT_MESSAGE);
	}
	
	//-----------------------------------------------Confirm WIndows----------------------------------------------------------
	
	//Shows a confirm dialog with Yes, No and Cancel option
	//Returns the final field YES, NO, CANCEL
	
	public static String showConfirmDialog(String title, String message, int messageType) {
		
		Label label = setUpLabel(message, messageType);
		
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		Button cancelButton = new Button("Cancel");
		
		HBox buttonHBlayout = new HBox();
		buttonHBlayout.setSpacing(20);
		buttonHBlayout.getChildren().addAll(yesButton, noButton, cancelButton);
		buttonHBlayout.setAlignment(Pos.CENTER);
		
		VBox layout = setUpVBox(label, buttonHBlayout);
		
		Stage stage = setUpStage(layout, title);
		
		yesButton.setOnAction(e -> {
			selection = YES_BUTTON;
			stage.close();
		});
		noButton.setOnAction(e -> {
			selection = NO_BUTTON;
			stage.close();
		});
		cancelButton.setOnAction(e -> {
			selection = CANCEL_BUTTON;
			stage.close();
		});
		stage.setOnCloseRequest(e -> selection = CANCEL_BUTTON );
		
		stage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode().equals(KeyCode.ENTER))
				yesButton.fire();
		});
		
		stage.showAndWait();
		
		return selection;
	}
	
	public static String showConfirmDialog( String message) {
		return showConfirmDialog("Confirm", message, DEFAULT_MESSAGE);
	}
	
	public static String showConfirmDialog(String title, String message) {
		return showConfirmDialog(title , message, DEFAULT_MESSAGE);
	}
	
	//--------------------------------------------------Input Windows-----------------------------------------------------------------
	
	//Shows an input dialog with a textfield inside.
	//Returns the text in the textfield when OK button is clicked. Returns an empty string otherwise. If cancel button, then final field CANCEL is passed
	
	public static String showInputDialog (String title, String message, int messageType) {
		
		inputField = "";
		Label label = setUpLabel(message, messageType);
	
		TextField textfield = new TextField();
		
		Button okButton = new Button("OK");
		Button clearButton = new Button("Clear");
		Button cancelButton = new Button("Cancel");

		HBox buttonHBlayout = new HBox();
		buttonHBlayout.setSpacing(20);
		buttonHBlayout.setAlignment(Pos.CENTER);
		buttonHBlayout.getChildren().addAll(okButton, clearButton, cancelButton);
		
		VBox layout = setUpVBox(label, textfield, buttonHBlayout);
		
		Stage stage = setUpStage(layout, title);
		
		okButton.setOnAction(e -> {
			inputField = textfield.getText();
			stage.close();
		});
		clearButton.setOnAction(e -> {
			textfield.clear();
		});
		cancelButton.setOnAction(e -> {
			inputField = CANCEL_BUTTON;
			stage.close();
		});
		stage.setOnCloseRequest(e -> {
			inputField = CANCEL_BUTTON;
			stage.close();
		});
		
		textfield.setOnAction(e -> okButton.fire() );
		
		stage.showAndWait();
		
		return inputField;
		
	}
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	//Private helper methods to set up the nodes
	
	private static Label setUpLabel (String message, int messageType) {
		Label label;
		switch (messageType) {
			case ALERT_MESSAGE  : label = new Label(message, alertLogo);
								  break;
			case ERROR_MESSAGE  : label = new Label(message, errorLogo);
								  break;
			case ACCEPT_MESSAGE : label = new Label(message, tickLogo);
								  break;
			default             : label = new Label(message);
								  break;
		}

		label.setTextAlignment(TextAlignment.CENTER);
		label.setGraphicTextGap(50);
		label.setFont( Font.font("Arial", 15));
		label.setWrapText(true);
		
		return label;
	}
	
	private static VBox setUpVBox (Node... elements) {
		VBox layout = new VBox();
		layout.getChildren().addAll(elements);
		layout.setPadding( new Insets(30,10,20,10) );
		layout.setSpacing(20);
		layout.setAlignment(Pos.CENTER);
		
		return layout;
	}
	
	private static Stage setUpStage (VBox layout, String title) {
		Scene scene = new Scene(layout, 400, 150);
		Stage stage = new Stage();
		stage.setTitle(title);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		
		return stage;
	}
}
