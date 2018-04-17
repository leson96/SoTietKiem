/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hibernate.HibernateUtil;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Khachhang;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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
public class KhachHangFXController implements Initializable {

    private SessionFactory factory;
    private ObservableList<Khachhang> lsKhachhang;
    @FXML
    private TextField txtMaKH;
    @FXML
    private TextField txtTenKH;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private TextField txtCMND;
    @FXML
    private TextField txtQuocTich;
    @FXML
    private TextField txtQueQuan;
    @FXML
    private TextField txtNgheNghiep;
    @FXML
    private DatePicker dpNgaySinh;
    @FXML
    private TableView<Khachhang> tbKhachhang;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factory = HibernateUtil.getSessionFactory();
        this.lsKhachhang = FXCollections.observableArrayList();
        loadCustomerToApp();
        // xu ly su kien doubleclick
        this.tbKhachhang.setRowFactory(tv -> {
            TableRow<Khachhang> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent e) -> {
                if (e.getClickCount() == 2 && !row.isEmpty()) {
                    Khachhang a = row.getItem();
                    txtMaKH.setText(a.getIdkhachHang());
                    txtMaKH.setDisable(true);
                    txtTenKH.setText(a.getTenKhachHang());
                    txtCMND.setText(a.getCmnd());
                    txtDiaChi.setText(a.getDiaChi());
                    txtNgheNghiep.setText(a.getNgheNghiep());
                    txtQueQuan.setText(a.getQueQuan());
                    txtQuocTich.setText(a.getQuocTich());
                    String d = a.getNgaySinh().toString();
                    dpNgaySinh.setValue(LocalDate.parse(d));
                }
            });
            return row;
        });
    }

    public void loadCustomer() {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Khachhang.class);
        cr.add(Restrictions.eq("isActive", true));

//        Query query = session.createQuery("from Khachhang where idkhachHang = :code ");
//        query.setParameter("code", "12");
//        List result = query.list();
//        
        List result = cr.list();
        session.close();
        this.lsKhachhang.clear();
        // Nạp lên giao diện
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Khachhang c = (Khachhang) iterator.next();
            this.lsKhachhang.add(c);
        }
    }

    //tao bang
    private void loadCustomerToApp() {
        TableColumn cMaKH = new TableColumn("Mã khách hàng");
        cMaKH.setCellValueFactory(new PropertyValueFactory("idkhachHang"));

        TableColumn cTenKH = new TableColumn("Tên khách hàng");
        cTenKH.setCellValueFactory(new PropertyValueFactory("tenKhachHang"));

        TableColumn cDiaChi = new TableColumn("Địa chỉ");
        cDiaChi.setCellValueFactory(new PropertyValueFactory("diaChi"));

        TableColumn cCMND = new TableColumn("CMND");
        cCMND.setCellValueFactory(new PropertyValueFactory("cmnd"));

        TableColumn cNgaySinh = new TableColumn("Ngày sinh");
        cNgaySinh.setCellValueFactory(new PropertyValueFactory("ngaySinh"));

        TableColumn cQuocTich = new TableColumn("Quốc tịch");
        cQuocTich.setCellValueFactory(new PropertyValueFactory("quocTich"));

        TableColumn cQueQuan = new TableColumn("Quê quán");
        cQueQuan.setCellValueFactory(new PropertyValueFactory("queQuan"));

        TableColumn cNgheNghiep = new TableColumn("Nghề nghiệp");
        cNgheNghiep.setCellValueFactory(new PropertyValueFactory("ngheNghiep"));

//        TableColumn cIsActive = new TableColumn("Is active");
//        cIsActive.setCellValueFactory(new PropertyValueFactory("isActive"));
        this.loadCustomer();
        this.tbKhachhang.setItems(lsKhachhang);
        this.tbKhachhang.getColumns().addAll(
                cMaKH, cTenKH, cDiaChi, cCMND, cNgaySinh, cQuocTich, cQueQuan, cNgheNghiep);
    }

    public void saveKhachhangAction(ActionEvent e) {
        this.saveKhachhang();
    }

    public boolean Validate() {
        if (txtMaKH.getText() == "" || txtTenKH.getText() == ""
                || txtCMND.getText() == "" || txtDiaChi.getText() == ""
                || dpNgaySinh.getValue() == null) {
            Alert al = new Alert(Alert.AlertType.INFORMATION, "Cần nhập đầy đủ thông tin", ButtonType.OK);
            al.setHeaderText(null);
            al.show();
            return false;
        }
        return true;

    }

    public void saveKhachhang() {
        if (Validate()) {
            Session session = this.factory.openSession();
            //kiem tra trung
            //Get - k ton tai  => tra ve null
            //Load - k ton tai => exception
            Khachhang kh = (Khachhang) session.get(Khachhang.class, this.txtMaKH.getText());
            Transaction trans = session.beginTransaction();
            if (kh == null) { //them moi        

                kh = new Khachhang();
                kh.setIdkhachHang(txtMaKH.getText());
            } else if (txtMaKH.isDisable() == false) {
                // return thong bao da co du lieu can phai chinh sua , k dc them
                return;
            }

            kh.setTenKhachHang(txtTenKH.getText());
            kh.setCmnd(txtCMND.getText());
            kh.setDiaChi(txtDiaChi.getText());
            kh.setQueQuan(txtQueQuan.getText());
            kh.setNgheNghiep(txtNgheNghiep.getText());
            kh.setQuocTich(txtQuocTich.getText());
            kh.setNgaySinh(Date.from(
                    dpNgaySinh.getValue().atStartOfDay(
                            ZoneId.systemDefault()).toInstant()
            ));
            kh.setIsActive(true);
            session.save(kh);
            trans.commit();
            session.close();
            this.loadCustomer();
            refeshData();
        }
    }

    //refesh
    public void resetKhachhangAction(ActionEvent e) {
        refeshData();
        loadCustomer();
    }

    public void refeshData() {
        this.txtMaKH.setDisable(false);
        this.txtMaKH.setText("");
        this.txtTenKH.setText("");
        this.txtCMND.setText("");
        this.txtDiaChi.setText("");
        this.txtNgheNghiep.setText("");
        this.txtQueQuan.setText("");
        this.txtQuocTich.setText("");
        this.dpNgaySinh.setValue(null);
    }
    //delete

    public void deleteKhachhangAction(ActionEvent e) {
        Session session = this.factory.openSession();
        Transaction trans = session.beginTransaction();
        Khachhang kh = tbKhachhang.getSelectionModel().getSelectedItem();
        kh.setIsActive(FALSE);
        session.update(kh);
//                    String hqlUpdate = "update Khachhang c set c.isActive = 0 where c.idkhachHang = :maKH";
//                    // or String hqlUpdate = "update khachhang set IsActive = 0 where maKH = :maKH";
//                    int updatedEntities = session.createQuery( hqlUpdate )
//                            .setParameter("maKH", kh.getIdkhachHang())
//                            .executeUpdate();
        trans.commit();
        session.close();
        this.loadCustomer();

    }
}
