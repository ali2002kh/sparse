import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TabPane tabs;

    @FXML
    private VBox setMatrixBox;

    @FXML
    private TextField x;

    @FXML
    private TextField y;

    @FXML
    private Button setMatrixBtn;

    @FXML
    private Button toSparse;

    @FXML
    private Button transpose;

    @FXML
    private Button toNormal;

    @FXML
    private TextField x1;

    @FXML
    private TextField y1;

    @FXML
    private TextField x2;

    @FXML
    private TextField y2;

    @FXML
    private Button setMatrixBtn1;

    @FXML
    private VBox setMatrixBox1;

    @FXML
    private VBox setMatrixBox2;

    @FXML
    private Button add;

    @FXML
    private Button subtract;

    @FXML
    private Button multiply;

    @FXML
    private VBox normalResult;

    @FXML
    private VBox sparsedResult;

    @FXML
    private Button back;

    boolean sparsed = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //---------------- one matrix

        setMatrixBtn.setOnAction(event -> {
            sparsed = false;
            setMatrixBox.getChildren().clear();
            int rowCount = Integer.parseInt(x.getText());
            int colCount = Integer.parseInt(y.getText());
            for (int i = 0; i < rowCount; i++) {
                HBox row = new HBox();
                setMatrixBox.getChildren().add(row);
                row.setSpacing(10);
                for (int j = 0; j < colCount; j++) {
                    row.getChildren().add(new TextField());
                }
            }
        });

        toNormal.setOnAction(event -> {
            int[][] array = scan(setMatrixBox);
            if (sparsed) {
                array = Sparse.toNormal(array);
                sparsed = false;
            }
            setMatrixBox.getChildren().clear();
            for (int i = 0; i < array.length; i++) {
                HBox row = new HBox();
                setMatrixBox.getChildren().add(row);
                row.setSpacing(10);
                for (int j = 0; j < array[0].length; j++) {
                    row.getChildren().add(new TextField(String.valueOf(array[i][j])));
                }
            }
        });

        toSparse.setOnAction(event -> {
            int[][] array = scan(setMatrixBox);
            if (!sparsed) {
                array = Sparse.toSparse(array);
                sparsed = true;
            }
            setMatrixBox.getChildren().clear();
            for (int i = 0; i < array.length; i++) {
                HBox row = new HBox();
                setMatrixBox.getChildren().add(row);
                row.setSpacing(10);
                for (int j = 0; j < array[0].length; j++) {
                    row.getChildren().add(new TextField(String.valueOf(array[i][j])));
                }
            }
        });

        transpose.setOnAction(event -> {
            int[][] array = scan(setMatrixBox);
            if (sparsed) {
                array = Sparse.transpose(array);
            } else {
                array = Sparse.toNormal(Sparse.transpose(Sparse.toSparse(array)));
            }
            setMatrixBox.getChildren().clear();
            for (int i = 0; i < array.length; i++) {
                HBox row = new HBox();
                setMatrixBox.getChildren().add(row);
                row.setSpacing(10);
                for (int j = 0; j < array[0].length; j++) {
                    row.getChildren().add(new TextField(String.valueOf(array[i][j])));
                }
            }
        });

        //---------------- two matrices

        setMatrixBtn1.setOnAction(event -> {
            setMatrixBox1.getChildren().clear();
            int rowCount1 = Integer.parseInt(x1.getText());
            int colCount1 = Integer.parseInt(y1.getText());
            for (int i = 0; i < rowCount1; i++) {
                HBox row = new HBox();
                setMatrixBox1.getChildren().add(row);
                row.setSpacing(10);
                for (int j = 0; j < colCount1; j++) {
                    row.getChildren().add(new TextField());
                }
            }
            setMatrixBox2.getChildren().clear();
            int rowCount2 = Integer.parseInt(x2.getText());
            int colCount2 = Integer.parseInt(y2.getText());
            for (int i = 0; i < rowCount2; i++) {
                HBox row = new HBox();
                setMatrixBox2.getChildren().add(row);
                row.setSpacing(10);
                for (int j = 0; j < colCount2; j++) {
                    row.getChildren().add(new TextField());
                }
            }
        });

        add.setOnAction(event -> {
            int[][] array1 = Sparse.toSparse(scan(setMatrixBox1));
            int[][] array2 = Sparse.toSparse(scan(setMatrixBox2));
            int[][] result = Sparse.add(array1,array2);
            sparsedResult.getChildren().clear();
            for (int i = 0; i < result.length; i++) {
                HBox row = new HBox();
                sparsedResult.getChildren().add(row);
                row.setSpacing(10);
                for (int j = 0; j < result[0].length; j++) {
                    row.getChildren().add(new TextField(String.valueOf(result[i][j])));
                }
            }
            result = Sparse.toNormal(result);
            normalResult.getChildren().clear();
            for (int i = 0; i < result.length; i++) {
                HBox row = new HBox();
                normalResult.getChildren().add(row);
                row.setSpacing(10);
                for (int j = 0; j < result[0].length; j++) {
                    row.getChildren().add(new TextField(String.valueOf(result[i][j])));
                }
            }
            tabs.getSelectionModel().select(2);
        });

        subtract.setOnAction(event -> {
            int[][] array1 = Sparse.toSparse(scan(setMatrixBox1));
            int[][] array2 = Sparse.toSparse(scan(setMatrixBox2));
            int[][] result = Sparse.subtract(array1,array2);
            sparsedResult.getChildren().clear();
            for (int i = 0; i < result.length; i++) {
                HBox row = new HBox();
                sparsedResult.getChildren().add(row);
                row.setSpacing(10);
                for (int j = 0; j < result[0].length; j++) {
                    row.getChildren().add(new TextField(String.valueOf(result[i][j])));
                }
            }
            result = Sparse.toNormal(result);
            normalResult.getChildren().clear();
            for (int i = 0; i < result.length; i++) {
                HBox row = new HBox();
                normalResult.getChildren().add(row);
                row.setSpacing(10);
                for (int j = 0; j < result[0].length; j++) {
                    row.getChildren().add(new TextField(String.valueOf(result[i][j])));
                }
            }
            tabs.getSelectionModel().select(2);
        });

        multiply.setOnAction(event -> {
            int[][] array1 = Sparse.toSparse(scan(setMatrixBox1));
            int[][] array2 = Sparse.toSparse(scan(setMatrixBox2));
            int[][] result = Sparse.multiply(array1,array2);
            sparsedResult.getChildren().clear();
            for (int i = 0; i < result.length; i++) {
                HBox row = new HBox();
                sparsedResult.getChildren().add(row);
                row.setSpacing(10);
                for (int j = 0; j < result[0].length; j++) {
                    row.getChildren().add(new TextField(String.valueOf(result[i][j])));
                }
            }
            result = Sparse.toNormal(result);
            normalResult.getChildren().clear();
            for (int i = 0; i < result.length; i++) {
                HBox row = new HBox();
                normalResult.getChildren().add(row);
                row.setSpacing(10);
                for (int j = 0; j < result[0].length; j++) {
                    row.getChildren().add(new TextField(String.valueOf(result[i][j])));
                }
            }
            tabs.getSelectionModel().select(2);
        });

        //---------------- result for two matrices

        back.setOnAction(event -> {
            tabs.getSelectionModel().select(1);
        });

    }

    public static int[][] scan (VBox matrix) {

        HBox firstRow = (HBox) matrix.getChildren().get(0);
        int[][] array = new int[matrix.getChildren().size()][firstRow.getChildren().size()];
        for (int i = 0; i < array.length; i++) {
            HBox eachRow = (HBox) matrix.getChildren().get(i);
            for (int j = 0; j < array[0].length; j++) {
                TextField item = (TextField) eachRow.getChildren().get(j);
                if (item.getText().equals("")) {
                    item.setText("0");
                }
                array[i][j] = Integer.parseInt(item.getText());
            }
        }
        return array;
    }

}
