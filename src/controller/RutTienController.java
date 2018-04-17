/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hibernate.HibernateUtil;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Khachhang;
import model.Loaitietkiem;
import model.PhieuGuiDetail;
import model.Phieugui;
import model.Phieurut;
import model.Sotietkiem;
import model.Thamso;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author vinh
 */
public class RutTienController implements Initializable {

    private SessionFactory factory;
    private ObservableList<Phieurut> lsPhieurut;
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
    private TextField txtSoTienRut;
    @FXML
    private TextField txtLaiSuat;
    @FXML
    private TextField txtTenNguoiRut;
    @FXML
    private DatePicker dpNgayMoSo;
    @FXML
    private DatePicker dpNgayRut;
    @FXML
    private CheckBox ckbDongSo;
    @FXML
    private TableView<Phieurut> tbPhieurutDetail;
    private int idLoaiTK;
    private int type;
    private int typeRUT = 3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        factory = HibernateUtil.getSessionFactory();
        this.lsPhieurut = FXCollections.observableArrayList();
        txtIdSTK.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                insertData(txtIdSTK.toString());
                // System.err.println("123");
            }
        });
//        this.cbQuocTich.getItems().addAll("Vietnam", "USA");
//        cbQuocTich.getSelectionModel().select(0);
        loadPhieuRutDetailToApp();
// xu ly su kien doubleclick
        this.tbPhieurutDetail.setRowFactory(tv -> {
            TableRow<Phieurut> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent e) -> {
                if (e.getClickCount() == 2 && !row.isEmpty()) {
                    Phieurut a = row.getItem();
                    txtIdSTK.setText(a.getIdsoTk());
                    insertData(txtIdSTK.toString());
                }
            });
            return row;
        });
    }

    public void loadPhieuRutDetail() {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Phieurut.class);
        //cr.add(Restrictions.eq("isActive", true));
        //query.setParameter("code", "7277");
        List result = cr.list();

        session.close();
        this.lsPhieurut.clear();
        // Nạp lên giao diện
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Phieurut c = (Phieurut) iterator.next();
            this.lsPhieurut.add(c);
        }
    }

    //tao bang
    private void loadPhieuRutDetailToApp() {
        TableColumn cIdsoTk = new TableColumn("Mã sổ tiết kiệm");
        cIdsoTk.setCellValueFactory(new PropertyValueFactory("idsoTk"));

        TableColumn cSoTienRut = new TableColumn("Số tiền rút");
        cSoTienRut.setCellValueFactory(new PropertyValueFactory("soTienRut"));

        TableColumn cNgayRut = new TableColumn("Ngày rút");
        cNgayRut.setCellValueFactory(new PropertyValueFactory("ngayRut"));

        TableColumn cNguoiRutTien = new TableColumn("Người rút");
        cNguoiRutTien.setCellValueFactory(new PropertyValueFactory("nguoiRut"));

//        TableColumn cIsActive = new TableColumn("Is active");
//        cIsActive.setCellValueFactory(new PropertyValueFactory("isActive"));
        this.loadPhieuRutDetail();
        this.tbPhieurutDetail.setItems(lsPhieurut);
        this.tbPhieurutDetail.getColumns().addAll(
                cIdsoTk, cSoTienRut, cNgayRut, cNguoiRutTien);
    }

    public boolean Validate() {
        if (txtIdSTK.getText() == "" || txtMaKH.getText() == "" || txtSoTienRut.getText() == ""
                || txtTenNguoiRut.getText() == ""
                || dpNgayRut.getValue() == null) {
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
            Phieurut ls = new Phieurut();
            //Phieurut stk = (Phieurut) session.get(Phieurut.class, this.txt.getText());
            Transaction trans = session.beginTransaction();

            ls.setIdsoTk(txtIdSTK.getText());
            //Kiem tra tien gui

//            if (idLoaiTK != 0) {
//                type = typeTH;
//                guiThem = false;
//            } else {
//                type = typeKTH;
//                guiThem = true;
//            }
            boolean ts = KiemTraTienRut(Float.parseFloat(txtSoTienRut.getText()), typeRUT);
            if (!ts) {
                Alert al = new Alert(Alert.AlertType.INFORMATION, "Số tiền rút không phù hợp", ButtonType.OK);
                al.setHeaderText(null);
                al.show();
            } else {

                ls.setSoTienRut(Float.parseFloat(txtSoTienRut.getText()));
                ls.setNgayRut(Date.from(
                        dpNgayRut.getValue().atStartOfDay(
                                ZoneId.systemDefault()).toInstant()
                ));
                ls.setNguoiRut(txtTenNguoiRut.getText());

                session.save(ls);
                trans.commit();
                session.close();
                Alert al = new Alert(Alert.AlertType.INFORMATION, "Rút tiền thành công", ButtonType.OK);
                al.setHeaderText(null);
                al.show();
                this.loadPhieuRutDetail();
                refeshData();
            }

        }
    }

    public boolean KiemTraTienRut(Float tienRut, int type) {
        Session session = this.factory.openSession();
        Thamso ts = (Thamso) session.get(Thamso.class, type);

        Sotietkiem stk = (Sotietkiem) session.get(Sotietkiem.class, this.txtIdSTK.getText());
        session.close();
        if (tienRut < ts.getSoTienGuiToiThieu() || tienRut > ts.getSoTienGuiToiDa() 
                || tienRut > stk.getTongTien() || stk.getDongSo() == true) {
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
        this.txtSoTienRut.setText("");
        this.txtTenNguoiRut.setText("");
        this.ckbDongSo.setSelected(false);
        this.dpNgayMoSo.setValue(null);
        this.dpNgayRut.setValue(null);
    }

    public void refreshAction(ActionEvent e) {
        refeshData();
        loadPhieuRutDetail();
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
