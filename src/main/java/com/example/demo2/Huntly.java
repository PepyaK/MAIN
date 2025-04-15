package com.example.demo2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Huntly extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    private Scene loginScene;
    private Scene chooseScene;
    private Scene regScene;
    private Scene regCompScene;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);

        loginScene = createLoginScene(primaryStage);
        chooseScene = createChooseScene(primaryStage);
        regScene = createRegScene(primaryStage);
        regCompScene = createRegComScene(primaryStage);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private Scene createLoginScene(Stage primaryStage) {
        BorderPane root = new BorderPane();

        HBox titleBar = createTitleBar(primaryStage, false);
        root.setTop(titleBar);

        Text text = createGradientText("Huntly");

        StackPane centerPane = new StackPane(text);

        TextField loginField = createTextField("Логин");
        TextField passwordField = createTextField("Пароль");

        Button loginButton = createButton("Войти");
        Button registerButton = createButton("Зарегистрироваться");
        registerButton.setOnAction(e -> primaryStage.setScene(chooseScene));
        loginButton.setPrefWidth(190);

        // Обработка логина
        loginButton.setOnAction(e -> {
            String login = loginField.getText();
            String password = passwordField.getText();
            if (login.isEmpty() || password.isEmpty()) {
                System.out.println("Ошибка: Заполните все поля для входа.");
                return;
            }
            if (Authentication.authenticateUser(login, password)) {
                System.out.println("Вход успешен!");
                loginField.clear();
                passwordField.clear();
            } else {
                System.out.println("Неверный логин или пароль. Пожалуйста, зарегистрируйтесь.");
            }
        });

        VBox inputContainer = new VBox(40, loginField, passwordField, new HBox(30, loginButton, registerButton));
        inputContainer.setAlignment(Pos.CENTER);
        inputContainer.setTranslateY(40);
        inputContainer.setFillWidth(false);

        centerPane.getChildren().add(inputContainer);
        root.setCenter(centerPane);

        return new Scene(root, 700, 720);
    }

    private Scene createRegScene(Stage primaryStage) {
        BorderPane root = new BorderPane();

        HBox titleBar = createTitleBar(primaryStage, true);
        root.setTop(titleBar);

        Text text = createGradientText("Huntly");

        StackPane centerPane = new StackPane(text);

        TextField firstNameField = createTextField("Имя");
        TextField lastNameField = createTextField("Фамилия");
        TextField loginField = createTextField("Логин");
        TextField passwordField = createTextField("Пароль");

        Button registerButton = createButton("Зарегистрироваться");
        registerButton.setTranslateY(20);

        registerButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String login = loginField.getText();
            String password = passwordField.getText();
            String company = "none"; // Пользователь ищет работу

            if (firstName.isEmpty() || lastName.isEmpty() || login.isEmpty() || password.isEmpty()) {
                System.out.println("Ошибка: Заполните все поля для регистрации.");
                return;
            }

            Registration.registerAccount(firstName, lastName, login, password, company);
            firstNameField.clear();
            lastNameField.clear();
            loginField.clear();
            passwordField.clear();
        });

        VBox inputContainer = new VBox(25, firstNameField, lastNameField, loginField, passwordField, registerButton);
        inputContainer.setAlignment(Pos.CENTER);
        inputContainer.setTranslateY(40);
        inputContainer.setFillWidth(false);

        centerPane.getChildren().add(inputContainer);
        root.setCenter(centerPane);

        return new Scene(root, 700, 720);
    }
    private TextField createTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-background-color: #D9D9D9; -fx-text-fill: #333333; -fx-border-radius: 40; -fx-background-radius: 40;");
        textField.setFont(Font.font("Sans-Serif", FontWeight.BOLD, 24));
        textField.setPrefWidth(500);
        return textField;
    }

    private Scene createRegComScene(Stage primaryStage) {
        BorderPane root = new BorderPane();

        HBox titleBar = createTitleBar(primaryStage, true);
        root.setTop(titleBar);

        Text text = createGradientText("Huntly");
        text.setLayoutY(-5);

        StackPane centerPane = new StackPane(text);

        TextField company = createTextField("Компания");
        TextField firstNameField = createTextField("Имя");
        TextField lastNameField = createTextField("Фамилия");
        TextField loginField = createTextField("Логин");
        TextField passwordField = createTextField("Пароль");

        Button registerButton = createButton("Зарегистрироваться");
        registerButton.setTranslateY(20);

        registerButton.setOnAction(e -> {
            String companyName = company.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String login = loginField.getText();
            String password = passwordField.getText();

            if (companyName.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || login.isEmpty() || password.isEmpty()) {
                System.out.println("Ошибка: Заполните все поля для регистрации.");
                return;
            }

            Registration.registerAccount(firstName, lastName, login, password, companyName);
            company.clear();
            firstNameField.clear();
            lastNameField.clear();
            loginField.clear();
            passwordField.clear();
        });

        VBox inputContainer = new VBox(20, company, firstNameField, lastNameField, loginField, passwordField, registerButton);
        inputContainer.setAlignment(Pos.CENTER);
        inputContainer.setTranslateY(60);
        inputContainer.setFillWidth(false);

        centerPane.getChildren().add(inputContainer);
        root.setCenter(centerPane);

        return new Scene(root, 700, 720);
    }

    private Scene createChooseScene(Stage primaryStage) {
        BorderPane root = new BorderPane();

        HBox titleBar = createTitleBar(primaryStage, true);
        root.setTop(titleBar);

        Text text = createGradientText("Huntly");

        Text questionText = new Text("Что вы ищите?");
        questionText.setFont(Font.font("Sans-Serif", FontWeight.BOLD, 40));

        RadioButton jobRadioButton = new RadioButton("Работу");
        RadioButton employeeRadioButton = new RadioButton("Сотрудников");
        ToggleGroup toggleGroup = new ToggleGroup();
        jobRadioButton.setToggleGroup(toggleGroup);
        employeeRadioButton.setToggleGroup(toggleGroup);

        HBox radioButtonsContainer = new HBox(60, jobRadioButton, employeeRadioButton);
        radioButtonsContainer.setAlignment(Pos.CENTER);

        Button nextButton = createButton("Далее");
        nextButton.setOnAction(e -> {
            if (jobRadioButton.isSelected()) {
                primaryStage.setScene(regScene);
            } else {
                primaryStage.setScene(regCompScene);
            }
        });

        VBox contentContainer = new VBox(40, questionText, radioButtonsContainer, nextButton);
        contentContainer.setAlignment(Pos.CENTER);

        StackPane centerPane = new StackPane(text, contentContainer);
        root.setCenter(centerPane);

        return new Scene(root, 700, 720);
    }
    private Text createGradientText(String content) {
        Text text = new Text(content);
        text.setFont(Font.font("Sans-Serif", FontWeight.EXTRA_BOLD, 96));
        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 0,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#01C7FC")),
                new Stop(0.4, Color.web("#00A1D8")),
                new Stop(1, Color.web("#0056D6"))
        );
        text.setFill(gradient);
        text.setTranslateY(-220);
        return text;
    }
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: linear-gradient(to right, #01C7FC, #00A1D8, #0056D6); -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold; -fx-background-radius: 40; -fx-border-radius: 40; -fx-padding: 5 20;");
        button.setPrefWidth(280);
        return button;
    }
    private HBox createTitleBar(Stage primaryStage, boolean showBackButton) {
        HBox titleBar = new HBox();
        titleBar.setStyle("-fx-padding: 5; -fx-background-color: linear-gradient(to right, #01C7FC, #00A1D8, #0056D6);");

        Button backButton = new Button("\u2B05");
        backButton.setFont(Font.font("Sans-Serif", FontWeight.BOLD, 20));
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff;");
        backButton.setOnAction(e -> {
            if (primaryStage.getScene() == chooseScene) {
                primaryStage.setScene(loginScene);
            } else if ((primaryStage.getScene() == regScene) || (primaryStage.getScene() == regCompScene)) {
                primaryStage.setScene(chooseScene);
            }
        });

        Button closeButton = new Button("\u2715");
        closeButton.setFont(Font.font("Sans-Serif", FontWeight.BOLD, 20));
        closeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        closeButton.setOnAction(e -> primaryStage.close());

        Button minimizeButton = new Button("\u2014");
        minimizeButton.setFont(Font.font("Sans-Serif", FontWeight.BOLD, 20));
        minimizeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        minimizeButton.setOnAction(e -> primaryStage.setIconified(true));

        Button maximizeButton = new Button("\u25A1");
        maximizeButton.setFont(Font.font("Sans-Serif", FontWeight.BOLD, 20));
        maximizeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        maximizeButton.setOnAction(e -> {
            if (primaryStage.isFullScreen()) {
                primaryStage.setFullScreen(false);
            } else {
                primaryStage.setFullScreen(true);
            }
        });

        titleBar.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        titleBar.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        HBox leftButtons = new HBox();
        if (showBackButton) {
            leftButtons.getChildren().add(backButton);
        }
        leftButtons.setAlignment(Pos.CENTER_LEFT);
        HBox rightButtons = new HBox(minimizeButton, maximizeButton, closeButton);
        rightButtons.setAlignment(Pos.CENTER_RIGHT);

        HBox.setHgrow(leftButtons, Priority.ALWAYS);

        titleBar.getChildren().addAll(leftButtons, rightButtons);
        return titleBar;
    }

    public static void main(String[] args) {
        launch(args);
    }
}