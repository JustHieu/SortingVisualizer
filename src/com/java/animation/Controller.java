package com.java.animation;

import com.java.animation.algorithms.*;
import com.java.animation.controller.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Controller {

    @FXML
    private Button binButton;

    @FXML
    private Button infoBtn;

    @FXML
    private Button binInBtn;

    @FXML
    private Button bubbleBtn;

    @FXML
    private Button clrBtn;

    @FXML
    private Button heapBtn;

    @FXML
    private Button inputButton;

    @FXML
    private TextField inputField;

    @FXML
    private TextField inputXField;

    @FXML
    private Button insertBtn;

    @FXML
    private Button interBtn;

    @FXML
    private Button linearButton;

    @FXML
    private Pane mainPane;

    @FXML
    private Button merBtn;

    @FXML
    private Button nhapXBTn;

    @FXML
    private Button pauseBtn;

    @FXML
    private Button quickBtn;


    @FXML
    private Button randomButton;

    @FXML
    private Button runBtn;

    @FXML
    private Button selectBtn;

    @FXML
    private Button shakerBtn;
    @FXML
    private TextField Field1;

    @FXML
    private TextField Field2;

    @FXML
    private TextField Field3;

    @FXML
    private TextField Field4;

    @FXML
    private TextField Field5;

    @FXML
    private TextField Field6;

    @FXML
    private TextField Field7;

    @FXML
    private TextField Field8;
    @FXML
    private Button shellBtn;
    private final int DEFAULT_SIZE = 8;
    private int[] myArray = new int[DEFAULT_SIZE];
    private int selected = 0;
    private LinearSearch linearSearch;
    private Linear linear;
    private BinarySearchC binarySearchC;
    private BinarySearch binarySearch;
    private Bubble bubble;
    private BubbleSort bubbleSort;
    private Interchange interchange;
    private InterchangeSort interchangeSort;
    private Insertion insertion;
    private InsertionSort insertionSort;
    private Selection selection;
    private SelectionSort selectionSort;
    private BinaryInsertion binaryInsertion;
    private BinaryInsertionSort binaryInsertionSort;
    private Shaker shaker;
    private ShakerSort shakerSort;
    private Shell shell;
    private ShellSort shellSort;
    private Quick quick;
    private QuickSort quickSort;
    private Merge merge;
    private MergeSort mergeSort;
    private TextField[] ListTF ;


    @FXML
    void initialize() {
        ListTF = new TextField[]{Field1, Field2, Field3, Field4, Field5, Field6, Field7, Field8};


        for (TextField textField : ListTF) {
            if (textField != null) {
                textField.setText("");
            }
        }
    }
    @FXML
    void handleInputButton(ActionEvent event) {
        String input = inputField.getText().trim();

        if (input.isEmpty()) {
            System.out.println("Vui lòng nhập dữ liệu.");
            return;
        }

        String[] parts = input.split(",");
        if (parts.length != DEFAULT_SIZE) {
            showAlert("Warning","Vui lòng nhập đúng " + DEFAULT_SIZE + " số, cách nhau bằng dấu phẩy.");
            return;
        }
        try {
            for (int i = 0; i < DEFAULT_SIZE; i++) {
                myArray[i] = Integer.parseInt(parts[i].trim());
                ListTF[i].setText(parts[i].trim());
            }
        } catch (NumberFormatException e) {
            showAlert("Warning","Vui lòng nhập số hợp lệ");
        }
    }

    @FXML
    void handleBinButton(ActionEvent event) {
        selected = 2;
        Arrays.sort(myArray);
        int[] binaryArray = myArray;
        mainPane.getChildren().clear();
        if (inputXField.getText() == null || inputXField.getText().trim().isEmpty()) {
            showAlert("Warning", "Vui lòng nhập giá trị hợp lệ cho X.");
            return;
        }

        int x;
        try {
            x = Integer.parseInt(inputXField.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Warning", "Vui lòng nhập một số nguyên hợp lệ cho X.");
            return;
        }


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BinarySearch.fxml"));
            Pane newPane = loader.load();
            binarySearchC = loader.getController();
            binarySearchC.LoadValues(binaryArray, x);
            binarySearch = new BinarySearch(binarySearchC.getListTF());
            mainPane.getChildren().setAll(newPane);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải giao diện BinarySearch.");
        }
    }

    @FXML
    void handleBubbleButton(ActionEvent event) {
        selected = 3;
        mainPane.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BubbleSortView.fxml"));
            Pane newPane = loader.load();
            bubble = loader.getController();
            bubble.LoadValues(myArray);
            bubbleSort = new BubbleSort(bubble.getListTF());
            mainPane.getChildren().setAll(newPane);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải giao diện Bubble.");
        }
    }

    @FXML
    void handleClearButton(ActionEvent event) {
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            myArray[i] = 0;
            ListTF[i].setText("");
        }
        mainPane.getChildren().clear();
    }
    @FXML
    void handleInterchangeSort(ActionEvent event) {
        selected =4;
        mainPane.getChildren().clear();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InterchangeSortView.fxml"));
            Pane newPane = loader.load();
            interchange = loader.getController();
            interchange.LoadValues(myArray);
            interchangeSort = new InterchangeSort(interchange.getListTF());
            mainPane.getChildren().setAll(newPane);
        }catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải giao diện Interchange.");
        }
    }
    @FXML
    void handleInsertionSort(ActionEvent event){
        selected =5;
        mainPane.getChildren().clear();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InsertionSortView.fxml"));
            Pane newPane = loader.load();
            insertion = loader.getController();
            insertion.LoadValues(myArray);
            insertionSort = new InsertionSort(insertion.getListTF());
            mainPane.getChildren().setAll(newPane);
        }catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải giao diện Interchange.");
        }
    }
    @FXML
    void handleSelectionSort(ActionEvent event){
        selected =6;
        mainPane.getChildren().clear();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SelectionSort.fxml"));
            Pane newPane = loader.load();
            selection = loader.getController();
            selection.LoadValues(myArray);
            selectionSort = new SelectionSort(selection.getListTF());
            mainPane.getChildren().setAll(newPane);
        }catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải giao diện Interchange.");
        }
    }
    @FXML
    void handleBinaryInsertionSort(ActionEvent event){
        selected =7;
        mainPane.getChildren().clear();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BinaryInsertionSort.fxml"));
            Pane newPane = loader.load();
            binaryInsertion = loader.getController();
            binaryInsertion.LoadValues(myArray);
            binaryInsertionSort = new BinaryInsertionSort(binaryInsertion.getListTF());
            mainPane.getChildren().setAll(newPane);
        }catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải giao diện Interchange.");
        }
    }
    @FXML
    void handleShakerSort(ActionEvent event){
        selected = 8;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShakerSort.fxml"));
            Pane newPane = loader.load();
            shaker = loader.getController();
            shaker.LoadValues(myArray);
            shakerSort = new ShakerSort(shaker.getListTF());
            mainPane.getChildren().setAll(newPane);
        }catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải giao diện Interchange.");
        }
    }
    @FXML
    void handleShellSort(ActionEvent event){
        selected = 9;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShellSort.fxml"));
            Pane newPane = loader.load();
            shell = loader.getController();
            shell.LoadValues(myArray);
            shellSort = new ShellSort(shell.getListTF());
            mainPane.getChildren().setAll(newPane);
        }catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải giao diện Interchange.");
        }
    }

    @FXML
    void handleQuickSort(ActionEvent event) {
        selected = 10;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/QuickSort.fxml"));
            Pane newPane = loader.load();
            quick = loader.getController();
            quick.LoadValues(myArray);
            quickSort = new QuickSort(quick.getListTF());
            mainPane.getChildren().setAll(newPane);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải giao diện QuickSort.");
        }
    }
    @FXML
    void handleMergeSort(ActionEvent event){
        selected = 11;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MergeSort.fxml"));
            Pane newPane = loader.load();
            merge = loader.getController();
            merge.LoadValues(myArray);
            mergeSort = new MergeSort( merge.getListTF());
            mainPane.getChildren().setAll(newPane);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải giao diện QuickSort.");
        }
    }


    @FXML
    void handleLinearButton(ActionEvent event) {
        selected = 1;
        mainPane.getChildren().clear();


        if (inputXField.getText() == null || inputXField.getText().trim().isEmpty()) {
            showAlert("Warning", "Vui lòng nhập giá trị hợp lệ cho X.");
            return;
        }

        int x;
        try {
            x = Integer.parseInt(inputXField.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Warning", "Vui lòng nhập một số nguyên hợp lệ cho X.");
            return;
        }

        linearSearch = new LinearSearch(ListTF);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Linear.fxml"));
            Pane newPane = loader.load();
            linear = loader.getController();
            linear.LoadValues(myArray, x);
            mainPane.getChildren().setAll(newPane);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải giao diện Linear.");
        }
    }

    @FXML
    void handleNhapXButton(ActionEvent event) {
        if(inputXField.getText().isEmpty()){
            showAlert("Warning","Vui lòng nhập X");
        }
    }



    @FXML
    void handleRandomButton(ActionEvent event) {
        Random random = new Random(); // Create a Random object

        // Generate random numbers and populate both myArray and ListTF
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            int randomValue = random.nextInt(1000); // Generate a random number between 0 and 999
            myArray[i] = randomValue;               // Update myArray
            ListTF[i].setText(String.valueOf(randomValue)); // Update the corresponding TextField
        }
    }


    @FXML
    void handleRunButton(ActionEvent event) {
        if (selected == 1) {
            if (ListTF == null || inputXField.getText() == null || inputXField.getText().isEmpty()) {
                showAlert("Warning", "Vui lòng chọn thuật toán và nhập X để chạy.");
                return;
            }
            int target;
            try {
                target = Integer.parseInt(inputXField.getText());
            } catch (NumberFormatException e) {
                showAlert("Warning", "Vui lòng nhập một số hợp lệ cho X.");
                return;
            }

            if (linearSearch == null) {
                linearSearch = new LinearSearch(ListTF);
            }
            linearSearch.runAnimation(target);
        }else if(selected==2){
            if (ListTF == null || inputXField.getText() == null || inputXField.getText().isEmpty()) {
                showAlert("Warning", "Vui lòng chọn thuật toán và nhập X để chạy.");
                return;
            }
            int target;
            try {
                target = Integer.parseInt(inputXField.getText());
            } catch (NumberFormatException e) {
                showAlert("Warning", "Vui lòng nhập một số hợp lệ cho X.");
                return;
            }

            if (binarySearch == null) {
                binarySearch = new BinarySearch(ListTF);
            }
            binarySearch.runAnimation(target);
        }else if(selected==3){
            if(ListTF == null){
                showAlert("Warning","Vui lòng chọn thuật toán");
                return;
            }
            if(bubbleSort == null){
                bubbleSort = new BubbleSort(ListTF);
            }
            bubbleSort.runAnimation();
        }else if(selected==4){
            if(ListTF==null){
                showAlert("Warning","Vui lòng chọn thuật toán");
                return;
            }
            if(interchangeSort==null){
                interchangeSort = new InterchangeSort(ListTF);
            }
            interchangeSort.runAnimation();
        }else if(selected==5){
            if(ListTF==null){
                showAlert("Warning","Vui lòng chọn thuật toán");
                return;
            }
            if(insertionSort==null)
                insertionSort = new InsertionSort(ListTF);
            insertionSort.runAnimation();
        }else if(selected == 6){
            if(ListTF==null){
                showAlert("Warning","Vui lòng chọn thuật toán");
                return;
            }
            if(selectionSort==null)
                selectionSort = new SelectionSort(ListTF);
            selectionSort.runAnimation();
        }else if(selected == 7) {
            if (ListTF == null) {
                showAlert("Warning", "Vui lòng chọn thuật toán");
                return;
            }
            if (binaryInsertionSort == null)
                binaryInsertionSort = new BinaryInsertionSort(ListTF);
            binaryInsertionSort.runAnimation();
        }else if(selected==8){
            if (ListTF == null) {
                showAlert("Warning", "Vui lòng chọn thuật toán");
                return;
            }
            if(shakerSort==null)
                shakerSort = new ShakerSort(ListTF);
            shakerSort.runAnimation();
        }else if(selected == 9){
            if (ListTF == null) {
                showAlert("Warning", "Vui lòng chọn thuật toán");
                return;
            }
            if(shellSort==null)
                shellSort = new ShellSort(ListTF);
            shellSort.runAnimation();
        }else if(selected==10){
            if (ListTF == null) {
                showAlert("Warning", "Vui lòng chọn thuật toán");
                return;
            }
            if(quickSort==null)
                quickSort = new QuickSort(ListTF);
            quickSort.runAnimation();
        }else if(selected==11){
            if (ListTF == null) {
                showAlert("Warning", "Vui lòng chọn thuật toán");
                return;
            }
            if(mergeSort==null){
                mergeSort = new MergeSort(ListTF);
            }
            mergeSort.runAnimation();
        }
        else {
            showAlert("Warning", "Chưa chọn thuật toán!");
        }


    }
    @FXML
    void handleInfoButton(ActionEvent event){
       switch (selected){
           case 1:
               showAlert2("Information","Tìm kiếm tuyến tính (Linear Search) là một thuật toán đơn giản, dùng để tìm kiếm một giá trị trong một danh sách theo cách tuần tự từ đầu đến cuối.\n"+"Độ phức tạp: O(n)");
               break;
           case 2:
               showAlert2("Information","Tìm kiếm nhị phân là một thuật toán tìm kiếm cực kỳ hiệu quả, đặc biệt khi áp dụng lên các danh sách đã được sắp xếp. Ý tưởng chính của thuật toán này là liên tục thu hẹp phạm vi tìm kiếm bằng cách chia đôi danh sách.\n" +"Độ phức tạp trung bình O(log2n)");
               break;
           case 3:
               showAlert2("Information","Bubble Sort là thuật toán sắp xếp đơn giản nhất hoạt động bằng cách hoán đổi nhiều lần các phần tử liền kề nếu chúng sai thứ tự.\n"+"\nĐộ phức tập xấu nhất và trung bình: O(n*n). Tốt nhất O(n)" );
               break;
           case 4:
               showAlert2("Information","InterchangeSort: Xuất phát từ phần tử đầu danh sách, tìm tất các các cặp nghịch thế chứa phần tử này. Triệt tiêu chúng bằng cách đổi chỗ 2 phần tử trong cặp nghịch thế. Lặp lại xử lý trên với phần tử kế tiếp trong danh sách\n" +"Độ phức tạp thuật toán là O(n*n)");
               break;
           case 5:
               showAlert2("Information","Thuật toán sắp xếp chèn thực hiện sắp xếp dãy số theo cách duyệt từng phần tử và chèn từng phần tử đó vào đúng vị trí trong mảng con(dãy số từ đầu đến phần tử phía trước nó) đã sắp xếp sao cho dãy số trong mảng sắp đã xếp đó vẫn đảm bảo tính chất của một dãy số tăng dầnĐộ  phức tạp thuật toán\n" +
                       "\n" +
                       "Trường hợp tốt: O(n)\n" +
                       "Trung bình: O(n*n)\n" +
                       "Trường hợp xấu: O(n*n)");
               break;
           case 6:
               showAlert2("Information","Thuật toán Selection sort sắp xếp một mảng bằng cách đi tìm phần tử có giá trị nhỏ nhất(giả sử với sắp xếp mảng tăng dần) trong đoạn đoạn chưa được sắp xếp và đổi cho phần tử nhỏ nhất đó với phần tử ở đầu đoạn chưa được sắp xếp(không phải đầu mảng). Thuật toán sẽ chia mảng làm 2 mảng con:\n" +
                       "\n" +
                       "1. Một mảng con đã được sắp xếp\n" +
                       "2. Một mảng con chưa được sắp xếp\n" +
                       "Tại mỗi bước lặp của thuật toán, phần tử nhỏ nhất ở mảng con chưa được sắp xếp sẽ được di chuyển về đoạn đã sắp xếp.\nĐộ  phức tạp thuật toán\n" +
                       "\nĐộ phức tạp: " +
                       "Trường hợp tốt: O(n*n)\n" +
                       "Trung bình: O(n*n)\n" +
                       "Trường hợp xấu: O(n*n)");
               break;
           case 7:
               showAlert2("Information","Thuật toán sắp xếp chèn nhị phân (Binary Insertion Sort) là một cải tiến của thuật toán sắp xếp chèn truyền thống (Insertion Sort), sử dụng tìm kiếm nhị phân để xác định vị trí chèn chính xác của phần tử trong danh sách đã được sắp xếp.\n"+
                       "Độ phức tạp :\n"+
                       "Trong trường hợp xấu nhất: O(n*n)\n"
                        +"O(log n) cho mỗi lần chèn.");
               break;
           case 8:
               showAlert2("Information","Shaker Sort là một biến thể cải tiến của thuật toán Bubble Sort, nhằm tăng tốc độ sắp xếp bằng cách thực hiện việc so sánh và đổi chỗ theo cả hai chiều (từ đầu đến cuối và từ cuối đến đầu) trong mỗi vòng lặp. Nhờ đó, các phần tử lớn sẽ nhanh chóng \"chìm\" xuống cuối danh sách, trong khi các phần tử nhỏ sẽ \"nổi lên\" đầu danh sách.\n"+"Trong trường hợp xấu nhất vẫn là O(n*n).");
               break;
           case 9:
               showAlert2("Information","Shell Sort là một thuật toán sắp xếp cải tiến dựa trên ý tưởng của Insertion Sort. Nó khắc phục hạn chế của Insertion Sort khi phải di chuyển các phần tử một khoảng cách ngắn trong mỗi lần so sánh, đặc biệt khi các phần tử cần di chuyển một khoảng cách xa.\n"+"Độ phức tạp thời gian: Độ phức tạp thời gian của Shell Sort phụ thuộc vào dãy khoảng cách được chọn. Trong trường hợp xấu nhất, độ phức tạp có thể lên tới O(n*n), nhưng trong thực tế, nó thường hoạt động nhanh hơn nhiều.");
               break;
           case 10:
               showAlert2("Information","Ý tưởng cơ bản của thuật toán Quick Sort là chọn một phần tử trong mảng làm pivot (giá trị chốt), sau đó chia mảng thành hai phần: Các phần tử có giá trị nhỏ hơn pivot và các phần tử có giá trị lớn hơn hoặc bằng pivot. Sau đó, tiếp tục sử dụng thuật toán Quick Sort đệ quy trên hai mảng được tạo ra.\n"+" Độ phức tạp trung bình của QuickSort là O(nlog n), nhưng trong trường hợp xấu nhất có thể lên đến O(n*n).");
               break;
           case 11:
               showAlert2("Information","Merge sort hoạt động trên nguyên tắc chia để trị. Merge sort chia nhỏ nhiều lần một mảng (array) thành hai mảng con bằng nhau cho đến khi mỗi mảng con (subarray) bao gồm một phần tử duy nhất. Cuối cùng, tất cả các mảng con đó được hợp nhất để sắp xếp mảng kết quả.\n"+"Độ phức tạp thời gian trong trường hợp tốt nhất của merge sort: O (nlogn)\n" +
                       "\n" +
                       "Độ phức tạp thời gian theo trường hợp trung bình của merge sort: O(nlogn)\n" +
                       "\n" +
                       "Độ phức tạp thời gian trong trường hợp xấu nhất của merge sort: O(nlogn)");
               break;
           default:
               showAlert("Warning","Lỗi");
       }
    }
    private void showAlert2(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title); alert.setHeaderText(null);
        TextArea textArea = new TextArea(message); textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        alert.getDialogPane().setContent(textArea);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/values/alertcustom.css").toExternalForm());
        alert.showAndWait();
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/values/alertcustom.css").toExternalForm());
        alert.showAndWait();
    }


}
