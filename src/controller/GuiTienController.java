/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.SoTietKiemController.maSTK;
import hibernate.HibernateUtil;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//import model.GuiTienDetail;
import model.Khachhang;
import model.Loaitietkiem;
import model.PhieuGuiDetail;
import model.Phieugui;
import model.Sotietkiem;
import model.Thamso;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author vinh
 */
public class GuiTienController implements Initializable {

    private SessionFactory factory;
    private ObservableList<Phieugui> lsPhieugui;
    @FXML
    private TextField txtMaKH;
    @FXML
    private TextField txtTenKH;
    @FXML
    private TextField txtCMND;
    @FXML
    private TextField txtQuocTich;
    @FXML
    private TextField txtIdSTK;
    @FXML
    private TextField txtTenLoaiTK;
    @FXML
    private TextField txtSoTienGui;
    @FXML
    private TextField txtLaiSuat;
    @FXML
    private TextField txtTenNguoiGui;
    @FXML
    private DatePicker dpNgayMoSo;
    @FXML
    private DatePicker dpNgayGui;
    @FXML
    private CheckBox ckbDongSo;
    @FXML
    private TableView<Phieugui> tbPhieuguiDetail;
    private int idLoaiTK;
    private int type;
    private int typeTH = 1;
    private boolean guiThem = false;
    private int typeKTH = 2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        factory = HibernateUtil.getSessionFactory();
        this.lsPhieugui = FXCollections.observableArrayList();
        txtIdSTK.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                insertData(txtIdSTK.toString());
                // System.err.println("123");
            }
        });
//        this.cbQuocTich.getItems().addAll("Vietnam", "USA");
//        cbQuocTich.getSelectionModel().select(0);
        loadPhieuGuiDetailToApp();
// xu ly su kien doubleclick
        this.tbPhieuguiDetail.setRowFactory(tv -> {
            TableRow<Phieugui> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent e) -> {
                if (e.getClickCount() == 2 && !row.isEmpty()) {
                    Phieugui a = row.getItem();
                    txtIdSTK.setText(a.getIdsoTk());
                    insertData(txtIdSTK.toString());
                }
            });
            return row;
        });
        if (maSTK != "") {
            insertData(maSTK);
        }
    }

    public void loadPhieuGuiDetail() {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Phieugui.class);
        //cr.add(Restrictions.eq("isActive", true));
        //query.setParameter("code", "7277");
        List result = cr.list();

        session.close();
        this.lsPhieugui.clear();
        // Nạp lên giao diện
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Phieugui c = (Phieugui) iterator.next();
            this.lsPhieugui.add(c);
        }
    }

    //tao bang
    private void loadPhieuGuiDetailToApp() {
        TableColumn cIdsoTk = new TableColumn("Mã sổ tiết kiệm");
        cIdsoTk.setCellValueFactory(new PropertyValueFactory("idsoTk"));

        TableColumn cSoTienGui = new TableColumn("Số tiền gửi");
        cSoTienGui.setCellValueFactory(new PropertyValueFactory("soTienGui"));

        TableColumn cNgayGui = new TableColumn("Ngày gửi");
        cNgayGui.setCellValueFactory(new PropertyValueFactory("ngayGui"));

        TableColumn cNguoiGuiTien = new TableColumn("Người gửi");
        cNguoiGuiTien.setCellValueFactory(new PropertyValueFactory("nguoiGuiTien"));

//        TableColumn cIsActive = new TableColumn("Is active");
//        cIsActive.setCellValueFactory(new PropertyValueFactory("isActive"));
        this.loadPhieuGuiDetail();
        this.tbPhieuguiDetail.setItems(lsPhieugui);
        this.tbPhieuguiDetail.getColumns().addAll(
                cIdsoTk, cSoTienGui, cNgayGui, cNguoiGuiTien);
    }

    public boolean Validate() {
        if (txtIdSTK.getText() == "" || txtMaKH.getText() == "" || txtSoTienGui.getText() == ""
                || txtTenNguoiGui.getText() == ""
                || dpNgayGui.getValue() == null) {
            Alert al = new Alert(Alert.AlertType.INFORMATION, "Cần nhập đầy đủ thông tin", ButtonType.OK);
            al.setHeaderText(null);
            al.show();
            return false;
        }
        return true;

    }

    public void saveAction(ActionEvent e) {
        if (Validate()) {
            Session session = this.factory.openSession();
            //kiem tra trung
            //Get - k ton tai  => tra ve null
            //Load - k ton tai => exception
            Phieugui ls = new Phieugui();
            //Phieugui stk = (Phieugui) session.get(Phieugui.class, this.txt.getText());
            Transaction trans = session.beginTransaction();

            ls.setIdsoTk(txtIdSTK.getText());
            //Kiem tra tien gui

            if (idLoaiTK != 0) {
                type = typeTH;
                guiThem = false;
            } else {
                type = typeKTH;
                guiThem = true;
            }

            boolean ts = KiemTraTienGui(Float.parseFloat(txtSoTienGui.getText()), type);
            if (!ts) {
                Alert al = new Alert(Alert.AlertType.INFORMATION, "Số tiền không phù hợp", ButtonType.OK);
                al.setHeaderText(null);
                al.show();
            } else {
                if (KiemTraGuiThem(txtIdSTK.getText(), idLoaiTK)) {
                    ls.setSoTienGui(Float.parseFloat(txtSoTienGui.getText()));
                    ls.setNgayGui(Date.from(
                            dpNgayGui.getValue().atStartOfDay(
                                    ZoneId.systemDefault()).toInstant()
                    ));
                    ls.setNguoiGuiTien(txtTenNguoiGui.getText());

                    session.save(ls);
                    trans.commit();
                    session.close();
                    Alert al = new Alert(Alert.AlertType.INFORMATION, "Gửi thành công", ButtonType.OK);
                    al.setHeaderText(null);
                    al.show();
                } else {
                    Alert al = new Alert(Alert.AlertType.INFORMATION, "Không được gửi thêm", ButtonType.OK);
                    al.setHeaderText(null);
                    al.show();
                }
            }
            this.loadPhieuGuiDetail();
            refeshData();
        }

    }

    public boolean KiemTraGuiThem(String maSTK, int idLoaiTK) {
        Session session = this.factory.openSession();
        Criteria cr = session.createCriteria(Sotietkiem.class);
        cr.add(Restrictions.eq("isActive", true));
        cr.add(Restrictions.eq("idstk", maSTK));

        List result = cr.list();
        session.close();
        if (result != null && idLoaiTK != 0) {
            return false;
        }
        return true;

    }

    public boolean KiemTraTienGui(Float tienGui, int type) {
        Session session = this.factory.openSession();
        Thamso ts = (Thamso) session.get(Thamso.class, type);
        Sotietkiem stk = (Sotietkiem) session.get(Sotietkiem.class, this.txtIdSTK.getText());
        if (tienGui < ts.getSoTienGuiToiThieu() || tienGui > ts.getSoTienGuiToiDa() || stk.getDongSo() == true) {
            return false;
        }
        return true;
    }

    private void refeshData() {
        this.txtIdSTK.setDisable(false);
        this.txtIdSTK.setText("");
        this.txtTenKH.setText("");
        this.txtMaKH.setText("");
        this.txtCMND.setText("");
        this.txtQuocTich.setText("");
        this.txtTenLoaiTK.setText("");
        this.txtLaiSuat.setText("");
        this.txtSoTienGui.setText("");
        this.txtTenNguoiGui.setText("");
        this.ckbDongSo.setSelected(false);
        this.dpNgayMoSo.setValue(null);
        this.dpNgayGui.setValue(null);
    }

    public void refreshAction(ActionEvent e) {
        refeshData();
        loadPhieuGuiDetail();
    }

    public void insertData(String maKH) {
        Session session = factory.openSession();

        //SoTK        
        Sotietkiem stk = (Sotietkiem) session.get(Sotietkiem.class, txtIdSTK.getText());
        if (stk.getDongSo().booleanValue() == TRUE) {
            ckbDongSo.setSelected(true);
        } else {
            ckbDongSo.setSelected(false);
        }
        String d = stk.getNgayMo().toString();
        dpNgayMoSo.setValue(LocalDate.parse(d));
        //LoaiTK
        Loaitietkiem ltk = (Loaitietkiem) session.get(Loaitietkiem.class, stk.getIdloaiTietKiem());
        idLoaiTK = ltk.getIdloaiTietKiem();
        txtTenLoaiTK.setText(ltk.getTenLtk());
        txtLaiSuat.setText(String.valueOf(ltk.getLaiSuat()));

        //Khachhang
        Khachhang kh = (Khachhang) session.get(Khachhang.class, stk.getIdkh());
        txtMaKH.setText(kh.getIdkhachHang());
        txtTenKH.setText(kh.getTenKhachHang());
        txtCMND.setText(kh.getCmnd());
        txtQuocTich.setText(kh.getQuocTich());
    }
}
