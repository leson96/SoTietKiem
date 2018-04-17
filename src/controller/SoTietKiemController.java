/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hibernate.HibernateUtil;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JComboBox;
import model.Khachhang;
import model.Loaitietkiem;
import model.Sotietkiem;
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
public class SoTietKiemController implements Initializable {

    private SessionFactory factory;
    private ObservableList<Sotietkiem> lsStk;
    private ObservableList<Khachhang> lsKhachhang;
    private ObservableList<Loaitietkiem> lsLoaitietkiem;
    @FXML
    private TextField txtMaSoTietKiem;
    @FXML
    private TextField txtSoTienGui;
    @FXML
    private TextField txtTongTien;
    @FXML
    private TextField txtLoaiTien;
    @FXML
    private DatePicker dpNgayMo;
    @FXML
    private DatePicker dpNgayTinhLai;
    @FXML
    private DatePicker dpNgayDenHan;
    @FXML
    private ComboBox<Khachhang> cbMaKhachHang;
    @FXML
    private TextField txtTenKhachHang;
    @FXML
    private ComboBox<Loaitietkiem> cbLoaiTietKiem;
    @FXML
    private CheckBox ckbDongSo;
    @FXML
    private TableView<Sotietkiem> tbStk;
    private DatePicker dpNgayTinh;
    private Date dpNgayHan;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    final ObservableList options = FXCollections.observableArrayList("A", "B", "C");
    public static String maSTK = "";

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        factory = HibernateUtil.getSessionFactory();
        this.lsStk = FXCollections.observableArrayList();
        this.lsKhachhang = FXCollections.observableArrayList();
        this.lsLoaitietkiem = FXCollections.observableArrayList();
        loadCombo();
        loadComboLoaiTK();
        loadSTKApp();
        //loadCustomerToApp(); 
        // xu ly su kien doubleclick

        this.tbStk.setRowFactory(tv -> {
            TableRow<Sotietkiem> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent e) -> {
                if (e.getClickCount() == 2 && !row.isEmpty()) {
                    Sotietkiem a = row.getItem();
                    txtMaSoTietKiem.setText(a.getIdstk());
                    txtMaSoTietKiem.setDisable(true);
                    //txtTenKhachHang.setText(a.getTenKhachHang());
                    //cbMaKhachHang.seti((Khachhang) a.getIdkh() );
//                    txtSoTienGui.setText(String.valueOf(a.getSoTienGuiTk()));
                    String d = a.getNgayMo().toString();
                    dpNgayMo.setValue(LocalDate.parse(d));

                    txtLoaiTien.setText(a.getLoaiTien());

                    d = a.getNgayTinhLai().toString();
                    dpNgayTinhLai.setValue(LocalDate.parse(d));
                    d = a.getNgayDenHan().toString();
                    dpNgayDenHan.setValue(LocalDate.parse(d));
                    txtTongTien.setText(String.valueOf(a.getTongTien()));
                    //cbLoaiTietKiem.setId(String.valueOf(a.getIdloaiTietKiem()));
                    int idCbBox = a.getIdloaiTietKiem();
                    //set combobox by id (so thu tu)
                    cbLoaiTietKiem.getSelectionModel().select(idCbBox);
                    //set combobox by value object (phu hop hon)
                    cbMaKhachHang.setValue(findKhachHang(a.getIdkh()));
                    if (a.getDongSo().booleanValue() == TRUE) {
                        ckbDongSo.setSelected(true);
                    } else {
                        ckbDongSo.setSelected(false);
                    }
                    //Combobox

                } //end clickCount =2
            });
            return row;
        });
    }

    //find khach hang for combobox
    private Khachhang findKhachHang(String id) {
        Session session = factory.openSession();
        Khachhang kh = (Khachhang) session.get(Khachhang.class, id);
        return kh;
    }

    public void loadSTK() {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Sotietkiem.class);
        cr.add(Restrictions.eq("isActive", true));

//        Query query = session.createQuery("from Khachhang where idkhachHang = :code ");
//        query.setParameter("code", "12");
//        List result = query.list();
//        
        List result = cr.list();
        session.close();
        this.lsStk.clear();
        // Nạp lên giao diện
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Sotietkiem c = (Sotietkiem) iterator.next();
            this.lsStk.add(c);
        }
    }

    private void loadSTKApp() {
        TableColumn cMaSTK = new TableColumn("Mã sổ tiết kiệm");
        cMaSTK.setCellValueFactory(new PropertyValueFactory("idstk"));

        TableColumn cMaLoaiTK = new TableColumn("Mã loại tiết kiệm");
        cMaLoaiTK.setCellValueFactory(new PropertyValueFactory("idloaiTietKiem"));

        TableColumn cMaKH = new TableColumn("Mã khách hàng");
        cMaKH.setCellValueFactory(new PropertyValueFactory("idkh"));

//        TableColumn cTienGuiTK = new TableColumn("Số tiền gửi tiết kiệm");
//        cTienGuiTK.setCellValueFactory(new PropertyValueFactory("soTienGuiTk"));

        TableColumn cNgayMo = new TableColumn("Ngày mở");
        cNgayMo.setCellValueFactory(new PropertyValueFactory("ngayMo"));

//        TableColumn cCMND = new TableColumn("Tình trạng đ");
//        cCMND.setCellValueFactory(new PropertyValueFactory("dongSo"));
        TableColumn cLoaiTien = new TableColumn("Loại tiền");
        cLoaiTien.setCellValueFactory(new PropertyValueFactory("loaiTien"));

        TableColumn cNgayTinhLai = new TableColumn("Ngày tính lãi");
        cNgayTinhLai.setCellValueFactory(new PropertyValueFactory("ngayTinhLai"));

        TableColumn cNgayDenHan = new TableColumn("Ngày hạn lãi");
        cNgayDenHan.setCellValueFactory(new PropertyValueFactory("ngayDenHan"));

        TableColumn cTongTien = new TableColumn("Tổng tiền");
        cTongTien.setCellValueFactory(new PropertyValueFactory("tongTien"));

//        TableColumn cIsActive = new TableColumn("Is active");
//        cIsActive.setCellValueFactory(new PropertyValueFactory("isActive"));
        this.loadSTK();
        this.tbStk.setItems(lsStk);
        this.tbStk.getColumns().addAll(
                cMaSTK, cMaLoaiTK, cMaKH,  cNgayMo, cLoaiTien, cNgayTinhLai, cNgayDenHan, cTongTien);
    }

    public void loadCombo() {
        Session session = factory.openSession();

        ///Load combobox for Khachhang (id, name)
        Criteria cr = session.createCriteria(Khachhang.class);
        cr.add(Restrictions.eq("isActive", true));

        List result = cr.list();
        session.close();
        this.lsKhachhang.clear();
        // Nạp lên giao diện
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Khachhang c = (Khachhang) iterator.next();
            this.lsKhachhang.add(c);
        }
        //add from lskhachhang
        cbMaKhachHang.setItems(lsKhachhang);

        //Convert option
        StringConverter<Khachhang> converter = new StringConverter<Khachhang>() {
            @Override
            public String toString(Khachhang object) {
                return object.getIdkhachHang();
            }

            @Override
            public Khachhang fromString(String id) {
                return lsKhachhang.stream()
                        .filter(item -> item.getIdkhachHang().equals(id))
                        .collect(Collectors.toList()).get(0);
            }
        };

        cbMaKhachHang.setConverter(converter);

        //Gan ten khach hang vao textbox tu idkhachhang cua combobox
        cbMaKhachHang.getSelectionModel().selectedItemProperty().addListener((o, ol, nw) -> {
            txtTenKhachHang.setText(cbMaKhachHang.getValue().getTenKhachHang());
        });

        //hien thi selectec item
//        cbMaKhachHang.setButtonCell(new ListCell<Khachhang>() {
//            @Override
//            protected void updateItem(Khachhang t, boolean bln) {
//                super.updateItem(t, bln);
//                if (t != null) {
//                    setText(t.getIdkhachHang().toUpperCase());
//                } else {
//                    setText("");
//                }
//            }
//        });
        //cbMaKhachHang.setSelectionModel(lsKhachhang[1]);
    }

    public void loadComboLoaiTK() {
        //Load combobox for loaitietkiem
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Loaitietkiem.class);
        cr.add(Restrictions.eq("isActive", true));

        List result = cr.list();
        session.close();
        this.lsLoaitietkiem.clear();
        // Nạp lên giao diện

        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Loaitietkiem c = (Loaitietkiem) iterator.next();
            this.lsLoaitietkiem.add(c);
        }
        //add from lskhachhang
        cbLoaiTietKiem.setItems(lsLoaitietkiem);

        StringConverter<Loaitietkiem> cv = new StringConverter<Loaitietkiem>() {
            @Override
            public String toString(Loaitietkiem object) {
                return String.valueOf(object.getTenLtk());
            }

            @Override
            public Loaitietkiem fromString(String id) {
                return lsLoaitietkiem.stream()
                        .filter(item -> String.valueOf(item.getIdloaiTietKiem()).equals(id))
                        .collect(Collectors.toList()).get(0);
            }
        };
        //Convert option       

        cbLoaiTietKiem.setConverter(cv);
    }

    public boolean Validate() {
        if (txtMaSoTietKiem.getText() == "" 
                || cbLoaiTietKiem.getValue() == null || cbMaKhachHang.getValue() == null
                || dpNgayMo.getValue() == null) {
            Alert al = new Alert(Alert.AlertType.INFORMATION, "Cần nhập đầy đủ thông tin", ButtonType.OK);
            al.setHeaderText(null);
            al.show();
            return false;
        }
        return true;
        
    }

    public void saveSoTietKiemAction(ActionEvent e) {
        if (Validate()) {
            Session session = this.factory.openSession();
            //kiem tra trung
            //Get - k ton tai  => tra ve null
            //Load - k ton tai => exception
            Sotietkiem stk = (Sotietkiem) session.get(Sotietkiem.class, this.txtMaSoTietKiem.getText());
            Transaction trans = session.beginTransaction();
            if (stk == null) { //them moi        

                stk = new Sotietkiem();
                stk.setIdstk(txtMaSoTietKiem.getText());
            } else if (txtMaSoTietKiem.isDisable() == false) {
                // return thong bao da co du lieu can phai chinh sua , k dc them
                return;
            }

            stk.setIdloaiTietKiem(cbLoaiTietKiem.getValue().getIdloaiTietKiem());
            stk.setIdkh(cbMaKhachHang.getValue().getIdkhachHang());
//            stk.setSoTienGuiTk(Float.parseFloat(txtSoTienGui.getText()));
            stk.setNgayMo(Date.from(
                    dpNgayMo.getValue().atStartOfDay(
                            ZoneId.systemDefault()).toInstant()
            ));
            if (ckbDongSo.isSelected()) {
                stk.setDongSo(true);
            } else {
                stk.setDongSo(false);
            }
            stk.setLoaiTien(txtLoaiTien.getText());
            setDate(dpNgayMo, 2);
            stk.setNgayTinhLai(Date.from(
                    dpNgayTinh.getValue().atStartOfDay(
                            ZoneId.systemDefault()).toInstant()
            ));
            stk.setNgayDenHan(dpNgayHan);

            stk.setTongTien(Float.valueOf(txtSoTienGui.getText()));
            stk.setIsActive(true);
            session.save(stk);
            trans.commit();
            session.close();
            this.loadSTK();
            refeshData();
        }
    }

    private void setDate(DatePicker date, int thang) {
        dpNgayTinh = date;

        Date currentDate = new Date();

        String sourceDate = String.valueOf(date.getValue());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.MONTH, thang);

        dpNgayHan = c.getTime();
    }

    public void deleteSoTietKiemAction(ActionEvent e) {
        Session session = this.factory.openSession();
        Transaction trans = session.beginTransaction();
        Sotietkiem stk = tbStk.getSelectionModel().getSelectedItem();
        stk.setIsActive(FALSE);
        session.update(stk);

        trans.commit();
        session.close();
        this.loadSTK();
    }

    private void refeshData() {
        this.txtMaSoTietKiem.setDisable(false);
        this.txtMaSoTietKiem.setText("");
        this.cbLoaiTietKiem.getSelectionModel().select(0);
        this.cbMaKhachHang.getSelectionModel().select(0);
        this.txtSoTienGui.setText("");
        this.ckbDongSo.setSelected(false);
        this.txtLoaiTien.setText("");
        this.txtTongTien.setText("");
        this.dpNgayMo.setValue(null);
        this.dpNgayTinhLai.setValue(null);
        this.dpNgayDenHan.setValue(null);
    }

    public void resetSoTietKiemAction(ActionEvent e) {
        refeshData();
        loadSTK();
    }
    public void openGuiTienAction(ActionEvent e) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/sotietkiemapp/GuiTien.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
         ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
         
         maSTK = txtMaSoTietKiem.getText();
    }
}
