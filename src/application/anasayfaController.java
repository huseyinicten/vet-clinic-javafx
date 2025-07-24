package application;

import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class anasayfaController implements Initializable {

	@FXML
    private AreaChart<?, ?> ana_grafik;

    @FXML
    private Label ana_kayıts;

    @FXML
    private Label ana_kaznc;

    @FXML
    private Label ana_personel;

    @FXML
    private AnchorPane anasayfa;

    @FXML
    private AnchorPane bord;

    @FXML
    private TableColumn<?, ?> fatura_adet;

    

    @FXML
    private JFXButton fatura_buton;

      @FXML
    private JFXButton fatura_eklebuton;

  
  
    @FXML
    private AnchorPane fatura_form;

  

   
    @FXML
    private Button hizmet;

    @FXML
    private Button home;
    
    @FXML
    private AnchorPane home_form;
    
    @FXML
    
    private JFXTextField islem_adet;

    @FXML
    private JFXTextField islem_adı;

    @FXML
    private TextField islem_ara;

    @FXML
    private TableColumn<ikayıtData, String> islem_col_adet;

    @FXML
    private TableColumn<ikayıtData, String> islem_col_adı;

    @FXML
    private TableColumn<ikayıtData, String> islem_col_fiat;

    @FXML
    private TableColumn<ikayıtData, String> islem_col_id;

    @FXML
    private TableColumn<ikayıtData, String> islem_col_skt;

    @FXML
    private JFXButton islem_dznle;

    @FXML
    private JFXButton islem_ekle;

    @FXML
    private JFXTextField islem_fiat;

    @FXML
    private AnchorPane islem_form;

    @FXML
    private JFXTextField islem_id;

    @FXML
    private JFXButton islem_si;

    @FXML
    private JFXTextField islem_skt;

    @FXML
    private TableView<ikayıtData> islem_tablewiev;

    @FXML
    private JFXButton islem_temizle;

    @FXML
    private Button islemkayıt_btn;

    @FXML
    private JFXTextField kk_adsoyad;

    @FXML
    private TextField kk_ara;

    @FXML
    private Button kk_btn;
    @FXML
    private JFXTextField kk_cins;
    
    @FXML
    private JFXComboBox<String> kk_cinsiyet;

    @FXML
    private JFXButton kk_düzenle;

    @FXML
    private JFXButton kk_ekle;

    @FXML
    private AnchorPane kk_form;

    @FXML
    private JFXTextField kk_id;

    @FXML
    private JFXTextField kk_isim;

    @FXML
  private JFXTextField kk_no;

    @FXML
    private JFXTextField fatura_h_isim;

    @FXML
    private JFXTextField fatura_id;

    @FXML
    private TableColumn<?, ?> fatura_islemid;

    @FXML
    private TableColumn<?, ?> fatura_islemismi;

    @FXML
    private TableView<?> fatura_tablewiev;

    @FXML
    private JFXTextField fatura_tutar;

    @FXML
    private JFXTextField faturae_isim;

    @FXML
    private JFXButton kk_sil;

    @FXML
    private TableColumn<kkayıtData, String> kk_t_adsoyad;

    @FXML
    private TableColumn<kkayıtData, String> kk_t_cins;

    @FXML
    private TableColumn<kkayıtData, String> kk_t_cinsiyet;

    @FXML
    private TableColumn<kkayıtData, String> kk_t_dt;

    @FXML
    private TableColumn<kkayıtData,String> kk_t_id;

    @FXML
    private TableColumn<kkayıtData, String> kk_t_isim;

    @FXML
    private TableColumn<kkayıtData, String> kk_t_no;

    @FXML
    private TableColumn<kkayıtData, String> kk_t_tür;

    @FXML
    private TableView<kkayıtData> kk_tablewiev;

    @FXML
    private JFXTextField kk_tarih;

    @FXML
    private JFXButton kk_temizle;

    @FXML
    private JFXTextField kk_tür;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private String[] cinsiyetlist = {"DİŞİ", "ERKEK"};

    public void ekcinsiyetlist() {
        List<String> lists = new ArrayList<>();

        for (String data : cinsiyetlist) {
            lists.add(data);
        }

        ObservableList<String> listD = FXCollections.observableArrayList(lists);
        kk_cinsiyet.setItems((ObservableList<String>) listD);
    }
    
    
    
        public void  kkayıtSearch() {
    	FilteredList<kkayıtData> filter = new FilteredList<>(kkayıtDataList, e-> true);
    	kk_ara.textProperty().addListener((Observable, oldValue, newValue) ->{
    	  
    	filter.setPredicate (predicatekkayıtData ->{
    	if (newValue == null || newValue.isEmpty()){
    	return true;
    	}
    	
    	
    	
    	if (newValue == null || newValue.isEmpty()) {
    		return true;
    	}
    		String searchKey = newValue.toLowerCase (); 
    		
    		if (predicatekkayıtData.getEid().toString().contains (searchKey)) {
    		return true;
    		}
    		else if (predicatekkayıtData.getAd().toLowerCase().contains (searchKey)) {
    		return true;
    		}
    		else if (predicatekkayıtData.getTel().toLowerCase().contains (searchKey)) {
    		return true;
    		}
    		else if(predicatekkayıtData.getIsim().toLowerCase().contains (searchKey)){
    		return true;
    		}  
    		else if(predicatekkayıtData.getTür().toLowerCase().contains (searchKey)){
        		return true;
        		}  
    		else if(predicatekkayıtData.getTar().toLowerCase().contains (searchKey)){
        		return true;
        		}
    		else if(predicatekkayıtData.getCins().toLowerCase().contains (searchKey)){
            		return true;
            		}
   
    		else {  return false;}
    	});
    	});

    	SortedList <kkayıtData> sortList = new SortedList<>(filter);
    	sortList.comparatorProperty().bind(kk_tablewiev.comparatorProperty());
    	kk_tablewiev.setItems (sortList);
    
    	}
    public void kkayıtekle() {
    	String sql = "INSERT INTO kkayıt (eid, ad, tel, isim,tür, cins,cinsiyet,tar,date) VALUES (?,?,?,?,?,?,?,?,?)";
    	
    	connect = Database.connectDb();
    	try{
    	Alert alert;
    	if(kk_id.getText().isEmpty()
    	|| kk_adsoyad.getText().isEmpty()
    	|| kk_cinsiyet.getSelectionModel().getSelectedItem()==null
    	|| kk_no.getText().isEmpty()
    	|| kk_isim.getText().isEmpty()
    	|| kk_tür.getText().isEmpty()
    	|| kk_cins.getText().isEmpty()
    	|| kk_tarih.getText().isEmpty()){
    	alert = new Alert (AlertType. ERROR);
    	alert.setTitle("Error Message");
    	alert.setHeaderText (null);
    	alert.setContentText ("KAYIT İÇİN EKSİK BİLGİ BIRAKMAYIN");
    	alert.showAndWait();
    	}
    	else{
    		String checkData = "SELECT eid FROM kkayıt WHERE eid ='"
    	     +kk_id .getText()+ "'";
    	
    		statement = connect.createStatement();
    		result = statement.executeQuery(checkData);
    		if(result.next()) {
    			alert = new Alert (AlertType. ERROR);
    	    	alert.setTitle("Error Message");
    	    	alert.setHeaderText (null);
    	    	alert.setContentText ("eid"+ kk_id.getText()+" kayıtlı");
    	    	alert.showAndWait();
    	    			
    		}else {
    		
    	prepare = connect.prepareStatement (sql);
    	prepare.setString(1,kk_id.getText());
    	prepare.setString(2,kk_adsoyad.getText());
    	prepare.setString(3,kk_no.getText());
    	prepare.setString(4,kk_isim.getText());
    	prepare.setString(5,kk_tür.getText());
    	prepare.setString(6,kk_cins.getText());
    	prepare.setString(7,(String) kk_cinsiyet.getSelectionModel().getSelectedItem());
    	prepare.setString(8,kk_tarih.getText());
    	
    	Date date= new Date ();
    	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    	prepare.setString (9, String.valueOf(sqlDate));
    	prepare.executeUpdate();
    	
    	alert= new Alert (AlertType.INFORMATION);
    	alert.setTitle("Information Message");
    	alert.setHeaderText (null);
    	alert.setContentText ("KAYIT OLUŞTURULDU");
    	alert.showAndWait();
    	kkayıtShowListData();
    	kkayıttemiz();
               		}
     		  }
    	}
    	catch (Exception e) { e.printStackTrace();}
    	
    	 }
    
    public void ikayıtekle() {
        String sql = "INSERT INTO ikayıt (lid, adet, lisim, litar, fiyat,DATE) VALUES (?, ?, ?, ?, ?,?)";
        
        connect = Database.connectDb();
        try {
            Alert alert;
            if (islem_id.getText().isEmpty()
                || islem_adet.getText().isEmpty()
                || islem_adı.getText().isEmpty()
                || islem_skt.getText().isEmpty()
                || islem_fiat.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("İŞLEM BİLGİLERİNİ EKSİKSİZ DOLDURUN");
                alert.showAndWait();
            } else {
                String checkData = "SELECT lid FROM ikayıt WHERE lid = '" + islem_id.getText() + "'";
                
                statement = connect.createStatement();
                result = statement.executeQuery(checkData);
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("lid " +islem_id.getText() + " already exists");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, islem_id.getText());
                    prepare.setString(2, islem_adet.getText());
                    prepare.setString(3, islem_adı.getText());
                    prepare.setString(4, islem_skt.getText());
                    prepare.setString(5, islem_fiat.getText());
                    
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(6, String.valueOf(sqlDate));
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("İŞLEM KAYDI  OLUŞTURULDU");
                    alert.showAndWait();
                    ikayıtShowListData();
                    ikayıttemiz();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ikayıttemiz() {
        islem_id.setText("");
        islem_adet.setText("");
        islem_adı.setText("");
        islem_skt.setText("");
        islem_fiat.setText("");
    
       
    
    }
    
   public void kkayıttemiz() {
    		kk_id.setText("");
    		kk_adsoyad.setText("");
    		kk_no.setText("");
    		kk_isim.setText("");
    		kk_tür.setText("");
    		kk_cins.setText("");
    		kk_cinsiyet.getSelectionModel().clearSelection();
    		kk_tarih.setText("");
    		
    	}
    
public void ikayısil() {
 String sql = "DELETE FROM ikayıt WHERE lid = '"+ islem_id.getText()+"'" ;
connect = Database.connectDb();
try{
   		Alert alert;
       	if(islem_id.getText().isEmpty()
       	|| islem_adet.getText().isEmpty()
        || islem_adı.getText().isEmpty()
       	|| islem_skt.getText().isEmpty()
       	|| islem_fiat.getText().isEmpty()){
       	alert = new Alert (AlertType. ERROR);
       	alert.setTitle("Error Message");
       	alert.setHeaderText (null);
       	alert.setContentText ("KAYIT İÇİN EKSİK BİLGİ BIRAKMAYIN");
       	alert.showAndWait();
       	}
       	else{
       		alert = new Alert (AlertType.CONFIRMATION);
           	alert.setTitle("confirmation Message");
           	alert.setHeaderText (null);
           	alert.setContentText (islem_id.getText() +" NUMARALI KAYIDIN BİLGİLERİ SİLİNSİN Mİ ?");
           	Optional<ButtonType> option = alert.showAndWait();
           	
           	if (option.get().equals (ButtonType.OK)) {
           		statement = connect.createStatement();
           		statement.executeUpdate(sql);
           		alert= new Alert (AlertType.INFORMATION);
               	alert.setTitle("Information Message");
               	alert.setHeaderText (null);
               	alert.setContentText ("SİLİNDİ");
               	alert.showAndWait();
               	ikayıtShowListData();
               	ikayıttemiz();
               	
           	}
       	
       	}
   		
   		
   	} catch (Exception e) {e.printStackTrace(); }
   
}
   public void kayısil() {
    	String sql = "DELETE FROM kkayıt WHERE eid = '"+ kk_id.getText()+"'" ;
    	connect = Database.connectDb();
    	try{
    		Alert alert;
        	if(kk_id.getText().isEmpty()
        	|| kk_adsoyad.getText().isEmpty()
        	|| kk_cinsiyet.getSelectionModel().getSelectedItem()==null
        	|| kk_no.getText().isEmpty()
        	|| kk_isim.getText().isEmpty()
        	|| kk_tür.getText().isEmpty()
        	|| kk_cins.getText().isEmpty()
        	|| kk_tarih.getText().isEmpty()){
        	alert = new Alert (AlertType. ERROR);
        	alert.setTitle("Error Message");
        	alert.setHeaderText (null);
        	alert.setContentText ("KAYIT İÇİN EKSİK BİLGİ BIRAKMAYIN");
        	alert.showAndWait();
        	}
        	else{
        		alert = new Alert (AlertType.CONFIRMATION);
            	alert.setTitle("confirmation Message");
            	alert.setHeaderText (null);
            	alert.setContentText (kk_id.getText() +" NUMARALI KAYIDIN BİLGİLERİ SİLİNSİN Mİ ?");
            	Optional<ButtonType> option = alert.showAndWait();
            	
            	if (option.get().equals (ButtonType.OK)) {
            		statement = connect.createStatement();
            		statement.executeUpdate(sql);
            		alert= new Alert (AlertType.INFORMATION);
                	alert.setTitle("Information Message");
                	alert.setHeaderText (null);
                	alert.setContentText ("SİLİNDİ");
                	alert.showAndWait();
                	kkayıtShowListData();
                	kkayıttemiz();
                	
            	}
        	
        	}
    	} catch (Exception e) {e.printStackTrace(); }
    
}
   public void ikayıtdüzenle() {
   	String sql = "UPDATE ikayıt SET adet = '" //lid, adet, lisim, litar, fiyat
   	+islem_adet.getText()+"',lisim ='"
   	+islem_adı.getText()+"',litar='"
   	+islem_skt.getText()+"',fiyat='"
   	+islem_fiat.getText()+"',lid='" 
   	+islem_id.getText()+"' WHERE lid = '"
   	+islem_id.getText()+"'"; 
   	
   	connect = Database.connectDb();
   	try{
   		Alert alert;
       	if(islem_id.getText().isEmpty()
       	|| islem_adet.getText().isEmpty()
       	|| islem_adı.getText().isEmpty()
       	|| islem_skt.getText().isEmpty()
       	|| islem_fiat.getText().isEmpty()
       	){
       	alert = new Alert (AlertType. ERROR);
       	alert.setTitle("Error Message");
       	alert.setHeaderText (null);
       	alert.setContentText ("KAYIT İÇİN EKSİK BİLGİ BIRAKMAYIN");
       	alert.showAndWait();
       	}
       	else{
       		alert = new Alert (AlertType.CONFIRMATION);
           	alert.setTitle("confirmation Message");
           	alert.setHeaderText (null);
           	alert.setContentText (islem_id.getText() +" NUMARALI KAYIDIN BİLGİLERİ DÜZENLENSİN Mİ ?");
           	Optional<ButtonType> option = alert.showAndWait();
           	
           	if (option.get().equals (ButtonType.OK)) {
           		statement = connect.createStatement();
           		statement.executeUpdate(sql);
           		alert= new Alert (AlertType.INFORMATION);
               	alert.setTitle("Information Message");
               	alert.setHeaderText (null);
               	alert.setContentText ("DÜZENLENDİ");
               	alert.showAndWait();
               	ikayıtShowListData();
               	ikayıttemiz();
               	
           	}
       	
       	}
   		
   	} catch (Exception e) {e.printStackTrace(); }
   	
   	}
    
   public void kkayıtdüzenle() {
    	String sql = "UPDATE kkayıt SET ad = '"
    	+kk_adsoyad.getText()+"',cinsiyet ='"
    	+kk_cinsiyet.getSelectionModel().getSelectedItem()+"',tel ='"
    	+kk_no.getText()+"',isim ='"
    	+kk_isim.getText()+"',tür='"
    	+kk_tür.getText()+"',cins='"
    	+kk_cins.getText()+"',tar='" 
    	+kk_tarih.getText()+"',eid='"
    	+kk_id.getText()+"' WHERE eid = '"
    	+kk_id.getText()+"'"; 
    	
    	connect = Database.connectDb();
    	try{
    		Alert alert;
        	if(kk_id.getText().isEmpty()
        	|| kk_adsoyad.getText().isEmpty()
        	|| kk_cinsiyet.getSelectionModel().getSelectedItem()==null
        	|| kk_no.getText().isEmpty()
        	|| kk_isim.getText().isEmpty()
        	|| kk_tür.getText().isEmpty()
        	|| kk_cins.getText().isEmpty()
        	|| kk_tarih.getText().isEmpty()){
        	alert = new Alert (AlertType. ERROR);
        	alert.setTitle("Error Message");
        	alert.setHeaderText (null);
        	alert.setContentText ("KAYIT İÇİN EKSİK BİLGİ BIRAKMAYIN");
        	alert.showAndWait();
        	}
        	else{
        		alert = new Alert (AlertType.CONFIRMATION);
            	alert.setTitle("confirmation Message");
            	alert.setHeaderText (null);
            	alert.setContentText (kk_id.getText() +" NUMARALI KAYIDIN BİLGİLERİ DÜZENLENSİN Mİ ?");
            	Optional<ButtonType> option = alert.showAndWait();
            	
            	if (option.get().equals (ButtonType.OK)) {
            		statement = connect.createStatement();
            		statement.executeUpdate(sql);
            		alert= new Alert (AlertType.INFORMATION);
                	alert.setTitle("Information Message");
                	alert.setHeaderText (null);
                	alert.setContentText ("DÜZENLENDİ");
                	alert.showAndWait();
                	kkayıtShowListData();
                	kkayıttemiz();
                	
            	}
        	
        	}
    		
    	} catch (Exception e) {e.printStackTrace(); }
    	
    	}
    
  public ObservableList<kkayıtData> kkayıtListData() {
    	ObservableList<kkayıtData> listData = FXCollections.observableArrayList();
    	String sql = "SELECT * FROM kkayıt";
    	
    	connect = Database.connectDb();
    	try{
    	prepare = connect.prepareStatement (sql);
    	result = prepare.executeQuery();
    	
       kkayıtData kkayıtD;
    	
    	while(result.next()) {
    		kkayıtD = new kkayıtData(result.getString("eid")
    				,result.getString("ad")
    				,result.getString("tel")
    				,result.getString("isim")
    				,result.getString("tür")
    				,result.getString("cins")
    				,result.getString("cinsiyet")
    				,result.getString("tar"));
    		
    		listData.add(kkayıtD);
    	}
    	
    	
    	} catch (Exception e) { e.printStackTrace();
    	}
    	return listData;
    }
   
  public ObservableList<ikayıtData> ikayıtListData() {
        ObservableList<ikayıtData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM ikayıt";

        connect = Database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ikayıtData ikayıtD;

            while (result.next()) {
                ikayıtD = new ikayıtData(result.getString("Lid"),
                        result.getString("adet"),
                        result.getString("lisim"),
                        result.getString("litar"),
                        result.getString("fiyat"));

                listData.add(ikayıtD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
  public void ikayıtShowListData() {
    	
    	ikayıtDataList = ikayıtListData();
        islem_col_id.setCellValueFactory(new PropertyValueFactory<>("lid"));
        islem_col_adet.setCellValueFactory(new PropertyValueFactory<>("adet"));
        islem_col_adı.setCellValueFactory(new PropertyValueFactory<>("lisim"));
        islem_col_skt.setCellValueFactory(new PropertyValueFactory<>("litar"));
        islem_col_fiat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));

        islem_tablewiev.setItems(ikayıtDataList);
    }
  
  private ObservableList<kkayıtData> kkayıtDataList;
    public void kkayıtShowListData() {
    	kkayıtDataList =  kkayıtListData();
    	kk_t_id.setCellValueFactory (new PropertyValueFactory<>("eid"));
    	kk_t_adsoyad.setCellValueFactory (new PropertyValueFactory<>("ad"));
    	kk_t_no.setCellValueFactory (new PropertyValueFactory<>("tel"));
    	kk_t_isim.setCellValueFactory (new PropertyValueFactory<>("isim"));
    	kk_t_tür.setCellValueFactory (new PropertyValueFactory<>("tür"));
    	kk_t_cins.setCellValueFactory (new PropertyValueFactory<>("cins"));
    	kk_t_cinsiyet.setCellValueFactory (new PropertyValueFactory<>("cinsiyet"));
    	kk_t_dt.setCellValueFactory (new PropertyValueFactory<>("tar"));

    	kk_tablewiev.setItems(kkayıtDataList);
    
    }
    
    public void kayıtsec () {
    	kkayıtData kayıtd = kk_tablewiev.getSelectionModel().getSelectedItem();
    	
    	int num = kk_tablewiev.getSelectionModel().getSelectedIndex();
    
    	if((num - 1) <-1) {return;}
    	
    	kk_adsoyad.setText(kayıtd.getAd());
    	kk_no.setText(kayıtd.getTel());
    	kk_id.setText(String.valueOf(kayıtd.getEid()));
    	kk_isim.setText(kayıtd.getIsim());
    	kk_tür.setText(kayıtd.getTür());
    	kk_cins.setText(kayıtd.getCins());
    	kk_tarih.setText(kayıtd.getTar());    	
    	
    	
    }
    
    
    private ObservableList<ikayıtData> ikayıtDataList;

  
    public void ikayıtsec() {
        ikayıtData kayıtd = islem_tablewiev.getSelectionModel().getSelectedItem();

        int num = islem_tablewiev.getSelectionModel().getSelectedIndex();

        if (num < 0) {
            return;
        }

        islem_id.setText(kayıtd.getLid());
        islem_adet.setText(kayıtd.getAdet());
        islem_adı.setText(kayıtd.getLisim());
        islem_skt.setText(kayıtd.getLitar());
        islem_fiat.setText(kayıtd.getFiyat());
    }
    
   
public void switchForm(ActionEvent event) {
    	
    	if (event.getSource() == home ) {
    	home_form.setVisible(true);
    	fatura_form.setVisible(false);
    	kk_form.setVisible(false);
    	islem_form.setVisible(false);
    	home.setStyle("-fx-background-color: #0481a4;");
    	kk_btn.setStyle("-fx-background-color: transparent");
    	islemkayıt_btn.setStyle("-fx-background-color: transparent");
    	hizmet.setStyle("-fx-background-color: transparent");
    }
    	
    	else if (event.getSource() == kk_btn) {
    		
    	    home_form.setVisible(false);
        	fatura_form.setVisible(false);
        	kk_form.setVisible(true);
        	islem_form.setVisible(false);
        	kk_btn.setStyle("-fx-background-color:#0481a4;");
        	home.setStyle("-fx-background-color: transparent");
        	islemkayıt_btn.setStyle("-fx-background-color: transparent");
        	hizmet.setStyle("-fx-background-color: transparent");
        	kkayıtSearch();
            kkayıtShowListData();
        	ekcinsiyetlist();
    	
    	}
    	
            
    	else if (event.getSource() == islemkayıt_btn) {
    		home_form.setVisible(false);
        	fatura_form.setVisible(false);
        	kk_form.setVisible(false);
        	islem_form.setVisible(true);
        	kk_btn.setStyle("-fx-background-color: transparent");
        	home.setStyle("-fx-background-color: transparent");
        	islemkayıt_btn.setStyle("-fx-background-color: #0481a4;");
        	hizmet.setStyle("-fx-background-color: transparent");	
        	ikayıtShowListData();
        	ekcinsiyetlist();
        	
        	
    	}
    	else if (event.getSource() == hizmet) {
    		home_form.setVisible(false);
        	fatura_form.setVisible(true);
        	kk_form.setVisible(false);
        	islem_form.setVisible(false);
        	kk_btn.setStyle("-fx-background-color: transparent");
        	home.setStyle("-fx-background-color: transparent");
        	islemkayıt_btn.setStyle("-fx-background-color: transparent;");
        	hizmet.setStyle("-fx-background-color:#0481a4");	
        	
    	}
    	

    }
    
   @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	   
       kkayıtShowListData();
    	ikayıtShowListData();
    	ekcinsiyetlist();
    		}
	



}

	    
	    
	    


