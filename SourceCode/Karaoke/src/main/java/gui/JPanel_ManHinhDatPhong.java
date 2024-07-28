/*
q * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.Clock;
import java.time.LocalTime;

import connectDB.ConnectDB;
import dao.ChiTietDichVu_DAO;
import dao.DichVu_DAO;
import dao.DonDatPhong_DAO;
import dao.HoaDonThuePhong_DAO;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import dao.NhanVien_DAO;
import dao.Phong_DAO;
import dao.TaiKhoan_DAO;
import dao.HoaDonThanhToan_DAO;
import entity.*;
import gui.JPanel_ManHinhDatPhong.luaChonDatPhong;
import gui.JPanel_ManHinhDatPhong.luaChonNhanPhong;
import gui.JPanel_ManHinhDatPhong.phieuTraPhong;

/**
 *
 * @author admin
 */
public class JPanel_ManHinhDatPhong extends javax.swing.JFrame {

	private JLabel time;
	protected JComboBox phut;
	protected JComboBox gio;
	private JDateChooser dchNgayDatPhong;
	private DonDatPhong_DAO donDatPhong_dao;
	private String maPH;
	private NhanVien_DAO nhanVien_dao;
	protected JPanel pnlChinh;
	private HoaDonThuePhong_DAO hoaDonThuePhong_dao;
	private HoaDonThuePhong hd;
	protected String gioNhapVao;
	protected String phutNhapVao;
	private Object listDichVu;
	private DichVu_DAO dichvu_dao;
	private ArrayList<DichVu> dsDV;
	private double tongTien;
	private ChiTietDichVu_DAO chiTietDichVu_dao;
	private phieuPhongTrong1 pPT1;
	private phieuPhongTrong2 pPT2;
	private phieuPhongTrong3 pPT3;
	private luaChonDatPhong lCDP;
	private luaChonNhanPhong lCNP;

	/**
	 * Creates new form Phieudatphong
	 */
	public JPanel_ManHinhDatPhong() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		phong_dao = new Phong_DAO();
		loaiPhong_dao = new LoaiPhong_DAO();
		khachHang_dao = new KhachHang_DAO();
		donDatPhong_dao = new DonDatPhong_DAO();
		nhanVien_dao = new NhanVien_DAO();
		hoaDonThuePhong_dao = new HoaDonThuePhong_DAO();
		chiTietDichVu_dao = new ChiTietDichVu_DAO();
		initComponents();
		initComponents_1();

	}

	protected void initComponents() {
		pnlChinh = new JPanel();
		pnlChinh.setLayout(null);
		pnlBody = new JPanel();
		// Lấy giờ hiện tại
		JLabel label = new JLabel("");
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setBounds(530, 5, 200, 20);
		pnlChinh.add(label);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					// Lấy giờ hiện tại
					LocalTime localTime = LocalTime.now();

					// Cập nhật giờ hiện tại lên label
					label.setText("   " + localTime.getHour() + ":" + localTime.getMinute() + ":"
							+ localTime.getSecond() + "  ");
					try {
						// Ngủ trong 1 giây
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();

		pnlBody.setLayout(new GridLayout(3, 7));
		pnlBody.setBounds(80, 60, 1100, 600);
		pnlBody.setBackground(new Color(255, 255, 255));
		pnlChinh.add(pnlBody);
		pnlChinh.setBackground(new java.awt.Color(255, 255, 255));
		pnlChinh.setPreferredSize(new java.awt.Dimension(1250, 755));
		listPhong = phong_dao.getAllPhong();
		count = listPhong.size();
		pnlPhongs = new JPanel[count];
		btnPhongs = new JButton[count];
		lblTenPhongs = new JLabel[count];
		lblSucChuas = new JLabel[count];
		time = new JLabel("00:00:00");
		time.setFont(new java.awt.Font("Arial", 14, java.awt.Font.PLAIN));
		time.setBounds(20, 10, 950, 10);
		pnlChinh.add(time);

		JLabel chuthich = new JLabel();
		chuthich.setBounds(40, 680, 1170, 30);
		pnlChinh.add(chuthich);
		chuthich.setIcon(new javax.swing.ImageIcon("item/chuthich.png"));
		for (int i = 0; i < count; i++) {
			pnlPhongs[i] = new JPanel();
			lblTenPhongs[i] = new JLabel();
			lblSucChuas[i] = new JLabel();
			pnlPhongs[i].setLayout(null);
			pnlPhongs[i].setPreferredSize(new Dimension(135, 135));
			pnlPhongs[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			pnlPhongs[i].setBounds(60, 130, 135, 135);
			pnlPhongs[i].setBackground(new Color(255, 255, 255));
			loadHA();
			btnPhongs[i].setBounds(0, 10, 135, 80);
			lblTenPhongs[i].setText("Phòng: " + listPhong.get(i).getTenPhongHat());
			lblTenPhongs[i].setBounds(0, 95, 135, 20);
			lblTenPhongs[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblSucChuas[i].setText("Sức chứa: " + listPhong.get(i).getSucChua());
			lblSucChuas[i].setBounds(0, 110, 135, 20);
			lblSucChuas[i].setHorizontalAlignment(SwingConstants.CENTER);
			pnlPhongs[i].add(btnPhongs[i]);
			pnlPhongs[i].add(lblTenPhongs[i]);
			pnlPhongs[i].add(lblSucChuas[i]);
			pnlBody.add(pnlPhongs[i]);
			showFrame();
		}

	}

	// tìm kiếm phòng
	public void timKiem() {
		String sdt = txtTim.getText().trim();

		if (sdt.isEmpty())
			JOptionPane.showMessageDialog(null, "Vui lòng nhập SDT!!!");
		else if (khachHang_dao.getKHtheoSDT(sdt) == null)
			JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng có số " + sdt);
		else {
			KhachHang kh = khachHang_dao.getKHtheoSDT(sdt);
			lblSDT.setText("Số điện thoại: " + kh.getSoDienThoai().toString());
			lblTenKH.setText("Tên khách hàng : " + kh.getTenKhachHang().toString());
			btnDatPhong.setEnabled(true);
			btnThuePhong.setEnabled(true);
		}
	}

//	Hiện JFrame khi click vào 1 phòng
	public void showFrame() {
		for (int i = 0; i < count; i++) {
			final int index = i;
			if (listPhong.get(i).getTinhTrang().equalsIgnoreCase("Phòng trống")) {
				btnPhongs[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						maPH = listPhong.get(index).getMaPhongHat();
//	    					pdp = new phieuDatPhong(maPH);
//	    					pdp.setVisible(true);
//	    					btnDatPhong.addActionListener(dchNgayDatPhong);	
						lCDP = new luaChonDatPhong(maPH);
						lCDP.setVisible(true);
						
					}
				});
			} else if (listPhong.get(i).getTinhTrang().equalsIgnoreCase("Đang sử dụng")) {
				btnPhongs[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						maPH = listPhong.get(index).getMaPhongHat();
						ptp = new phieuTraPhong(maPH);
						ptp.setVisible(true);
						String lblSoP = lblSoPhong.getText();
						String soP = lblSoP.substring(10);
						PhongHat p = phong_dao.getPhongtheoSoPhong(soP);
	
						DonDatPhong donDatPhong = donDatPhong_dao.getDonDatPhongtheoMaPH(maPH);
						
						int SLDDP = donDatPhong_dao.getSoLuongMaDDPTheoMaPhong(maPH);
						List<DonDatPhong>  dsDDP = donDatPhong_dao.getAllDonDatPhongTheoMaPH(maPH) ;
						
						if(SLDDP > 1) {
							for(DonDatPhong ddp : dsDDP) {
								for(DonDatPhong ddp1 :dsDDP) {
									if(ddp.getNgayDat().getDate()<ddp1.getNgayDat().getDate()){
										
										lblSDT.setText("Số điện thoại: " + ddp.getKhachHang().getSoDienThoai().toString());
										lblTenKH.setText("Tên khách hàng : " + ddp.getKhachHang().getTenKhachHang().toString());
										dchNgayDatPhong.setDate(ddp.getNgayDat());
										gio.setSelectedItem(ddp.getGioDat().getHours());
										phut.setSelectedItem(ddp.getGioDat().getMinutes());
										System.out.println("1");
									}
									else if((ddp.getNgayDat().getDate()==ddp1.getNgayDat().getDate())&&(ddp.getGioDat().getHours()<ddp1.getGioDat().getHours())){
										lblSDT.setText("Số điện thoại: " + ddp.getKhachHang().getSoDienThoai().toString());
										lblTenKH.setText("Tên khách hàng : " + ddp.getKhachHang().getTenKhachHang().toString());
										dchNgayDatPhong.setDate(ddp.getNgayDat());
										gio.setSelectedItem(ddp.getGioDat().getHours());
										phut.setSelectedItem(ddp.getGioDat().getMinutes());
									}
									
								}
							}
						}
						else {
							lblSDT.setText("Số điện thoại: " + donDatPhong.getKhachHang().getSoDienThoai().toString());
							lblTenKH.setText("Tên khách hàng : " + donDatPhong.getKhachHang().getTenKhachHang().toString());
							dchNgayDatPhong.setDate(donDatPhong.getNgayDat());
							gio.setSelectedItem(donDatPhong.getGioDat().getHours());
							phut.setSelectedItem(donDatPhong.getGioDat().getMinutes());
						}
						
						
						
					}
				});

			} else {
				btnPhongs[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						maPH = listPhong.get(index).getMaPhongHat();
						lCNP = new luaChonNhanPhong(maPH);
						lCNP.setVisible(true);

					}
				});
			}
		}
	}

//	nội dung trong JFrame đặt phòng và thuê phòng
	public void text() {
		lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setBounds(10, 68, 400, 16);
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		khung.add(lblTenKH);

		lblSDT = new JLabel("Số điện thoại: ");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSDT.setBounds(10, 108, 400, 16);
		khung.add(lblSDT);

		JLabel lblNgayDat = new JLabel("Ngày đặt: ");
		lblNgayDat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgayDat.setBounds(10, 148, 108, 16);
		khung.add(lblNgayDat);

		dchNgayDatPhong = new com.toedter.calendar.JDateChooser();
		dchNgayDatPhong.setDateFormatString("dd-MM-yyyy");
		dchNgayDatPhong.setBounds(10, 178, 180, 30);

		khung.add(dchNgayDatPhong);
		JLabel lblGioDat = new JLabel("Giờ đặt:");
		lblGioDat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGioDat.setBounds(281, 148, 46, 16);
		khung.add(lblGioDat);
		gio = new JComboBox<>();
		for (int j = 10; j <= 23; j++) {
			gio.addItem(j);
		}

		gio.setBounds(281, 178, 50, 30);
		khung.add(gio);
		JLabel dau2Cham = new JLabel(":");
		dau2Cham.setFont(new Font("Tahoma", Font.BOLD, 18));
		dau2Cham.setBounds(345, 173, 20, 20);
		khung.add(dau2Cham);
		phut = new JComboBox<>();
		for (int j = 0; j < 60; j++) {
			phut.addItem(j);
		}
		phut.setBounds(365, 178, 50, 30);
		khung.add(phut);

	}

//	Nội dung trong JFrame nhận phòng
	public void text1() {
		lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setBounds(10, 38, 400, 16);
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		khung.add(lblTenKH);

		lblSDT = new JLabel("Số điện thoại: ");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSDT.setBounds(10, 88, 400, 16);
		khung.add(lblSDT);

		JLabel lblNgayDat = new JLabel("Ngày đặt: ");
		lblNgayDat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgayDat.setBounds(10, 128, 108, 16);
		khung.add(lblNgayDat);

		JLabel lblGioDat = new JLabel("Giờ đặt:");
		lblGioDat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGioDat.setBounds(281, 128, 46, 16);
		khung.add(lblGioDat);

		dchNgayDatPhong = new com.toedter.calendar.JDateChooser();
		dchNgayDatPhong.setDateFormatString("dd-MM-yyyy");
		dchNgayDatPhong.setBounds(10, 150, 180, 30);
		khung.add(dchNgayDatPhong);
		gio = new JComboBox<>();
		for (int j = 0; j < 24; j++) {
			gio.addItem(j);
		}
		gio.setBounds(281, 150, 50, 30);
		khung.add(gio);
		JLabel dau2Cham = new JLabel(":");
		dau2Cham.setFont(new Font("Tahoma", Font.BOLD, 18));
		dau2Cham.setBounds(345, 155, 20, 20);
		khung.add(dau2Cham);
		phut = new JComboBox<>();
		for (int j = 0; j < 60; j++) {
			phut.addItem(j);
		}
		phut.setBounds(365, 150, 50, 30);
		khung.add(phut);

	}

//	Nội dung trong JFrame trả phòng
	public void text2() {
		lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setBounds(10, 38, 400, 16);
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		khung.add(lblTenKH);

		lblSDT = new JLabel("Số điện thoại: ");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSDT.setBounds(10, 88, 400, 16);
		khung.add(lblSDT);

		JLabel lblNgayDat = new JLabel("Ngày nhận phòng: ");
		lblNgayDat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgayDat.setBounds(10, 128, 150, 16);
		khung.add(lblNgayDat);

		JLabel lblGioDat = new JLabel("Giờ vào:");
		lblGioDat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGioDat.setBounds(281, 128, 50, 16);
		khung.add(lblGioDat);

		dchNgayDatPhong = new com.toedter.calendar.JDateChooser();
		dchNgayDatPhong.setDateFormatString("dd-MM-yyyy");
		dchNgayDatPhong.setBounds(10, 150, 180, 30);
		dchNgayDatPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		dchNgayDatPhong.setForeground(Color.black);

		khung.add(dchNgayDatPhong);

		gio = new JComboBox<>();
		for (int j = 0; j < 24; j++) {
			gio.addItem(j);
		}
		gio.setBounds(281, 150, 60, 30);
		khung.add(gio);
		JLabel dau2Cham = new JLabel(":");
		dau2Cham.setFont(new Font("Tahoma", Font.BOLD, 18));
		dau2Cham.setBounds(345, 155, 20, 20);
		khung.add(dau2Cham);
		phut = new JComboBox<>();
		for (int j = 0; j < 60; j++) {
			phut.addItem(j);
		}
		phut.setBounds(365, 150, 60, 30);
		khung.add(phut);
		phut.setFont(new Font("Tahoma", Font.BOLD, 14));
		gio.setFont(new Font("Tahoma", Font.BOLD, 14));
		dchNgayDatPhong.setEnabled(false);
		gio.setEnabled(false);
		phut.setEnabled(false);
	}


//	public List<JButton> createButtonList(Connection conn, String sql) throws SQLException {
//	    // Khai báo các biến cần thiết
//	    List<JButton> buttonList = new ArrayList<>();
//	    Statement stmt = conn.createStatement();
//	    ResultSet rs = stmt.executeQuery(sql);
//
//	    // Tạo một vòng lặp để truy vấn tất cả dữ liệu từ database
//	    while (rs.next()) {
//	        // Tạo một button mới
//	        JButton button = new JButton();
//
//	        // Gán tên cho button
//	        button.setText(rs.getString("name"));
//
//	        // Gán hình ảnh cho button
//	        byte[] imageData = rs.getBytes("image");
//
//	        button.setIcon(imageIcon);
//
//	        // Thêm button vào danh sách các button
//	        buttonList.add(button);
//	    }
//
//	    // Trả về danh sách các button
//	    return buttonList;
//	}
	//	load hình ảnh lên button
	public void loadHA() {
		listPhong = phong_dao.getAllPhong();
		for (int i = 0; i < count; i++) {
			btnPhongs[i] = new JButton();
			if (listPhong.get(i).getLoaiPhong().getTenLoaiPhong().equalsIgnoreCase("Vip")) {
				if (listPhong.get(i).getTinhTrang().equalsIgnoreCase("Phòng trống")) {
					btnPhongs[i].setIcon(new javax.swing.ImageIcon("item/VipTrong.png")); // NOI18N
				} else if (listPhong.get(i).getTinhTrang().equalsIgnoreCase("Phòng chờ")) {
					btnPhongs[i].setIcon(new javax.swing.ImageIcon("item/VipCho.png")); // NOI18N
				} else {
					btnPhongs[i].setIcon(new javax.swing.ImageIcon("item/VipDangSD.png"));
				}

			} else {
				if (listPhong.get(i).getTinhTrang().equalsIgnoreCase("Phòng trống")) {
					btnPhongs[i].setIcon(new javax.swing.ImageIcon("item/ThuongTrong.png")); // NOI18N
				} else if (listPhong.get(i).getTinhTrang().equalsIgnoreCase("Phòng chờ")) {
					btnPhongs[i].setIcon(new javax.swing.ImageIcon("item/ThuongCho.png")); // NOI18N
				} else {
					btnPhongs[i].setIcon(new javax.swing.ImageIcon("item/ThuongDangSD.png"));
				}
			}
		}
	
	}

//	Xóa hình ảnh 
	public void updateHA() {
		pnlBody.removeAll();
		for (int i = 0; i < count; i++) {
			pnlPhongs[i] = new JPanel();
			lblTenPhongs[i] = new JLabel();
			lblSucChuas[i] = new JLabel();
			pnlPhongs[i].setLayout(null);
			pnlPhongs[i].setPreferredSize(new Dimension(135, 135));
			pnlPhongs[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			pnlPhongs[i].setBounds(60, 130, 135, 135);
			pnlPhongs[i].setBackground(new Color(255, 255, 255));
			loadHA();
			btnPhongs[i].setBounds(0, 10, 135, 80);
			lblTenPhongs[i].setText("Phòng: " + listPhong.get(i).getTenPhongHat());
			lblTenPhongs[i].setBounds(0, 95, 135, 20);
			lblTenPhongs[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblSucChuas[i].setText("Sức chứa: " + listPhong.get(i).getSucChua());
			lblSucChuas[i].setBounds(0, 110, 135, 20);
			lblSucChuas[i].setHorizontalAlignment(SwingConstants.CENTER);
			pnlPhongs[i].add(btnPhongs[i]);
			pnlPhongs[i].add(lblTenPhongs[i]);
			pnlPhongs[i].add(lblSucChuas[i]);
			pnlBody.add(pnlPhongs[i]);
			showFrame();
		}
	}
// Lựa chọn đặt khi là phong trong

	public class luaChonDatPhong extends JFrame implements ActionListener {
		public luaChonDatPhong(String ma) {
			super("");
			this.setSize(213, 143);
			setVisible(true);
			setLocationRelativeTo(null);
			setLayout(null);
			setResizable(false);
			JButton btn1 = new JButton("ĐẶT PHÒNG");
			JButton btn2 = new JButton("NHẬN PHÒNG");

			btn1.setBounds(0, 0, 200, 50);
			btn1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					lCDP.setVisible(false);
					pPT1 = new phieuPhongTrong1(ma);
					pPT1.setVisible(true);

				}
			});
			btn2.setBounds(0, 60, 200, 50);
			btn2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					lCDP.setVisible(false);
					pPT2 = new phieuPhongTrong2(ma);
					pPT2.setVisible(true);
				}
			});
			this.add(btn1);
			this.add(btn2);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}

	// Lua chon khi phong dang o trang thai cho
	public class luaChonNhanPhong extends JFrame implements ActionListener {
		public luaChonNhanPhong(String ma) {
			super("");
			this.setSize(213, 143);
			setVisible(true);
			setLocationRelativeTo(null);
			setLayout(null);
			setResizable(false);
			JButton btn1 = new JButton("XEM THÔNG TIN CHI TIẾT");
			JButton btn2 = new JButton("NHẬN PHÒNG");

			btn1.setBounds(0, 0, 200, 50);
			btn1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					lCNP.setVisible(false);
					pnp = new phieuNhanPhong(maPH);
					pnp.setVisible(true);
					String lblSoP = lblSoPhong.getText();
					String soP = lblSoP.substring(10);
					PhongHat p = phong_dao.getPhongtheoSoPhong(soP);
					String maP = p.getMaPhongHat();
					DonDatPhong maDdp = donDatPhong_dao.getDonDatPhongtheoMaPH(ma);
					lblSDT.setText("Số điện thoại: " + maDdp.getKhachHang().getSoDienThoai().toString());
					lblTenKH.setText("Tên khách hàng : " + maDdp.getKhachHang().getTenKhachHang().toString());
					dchNgayDatPhong.setDate(maDdp.getNgayDat());
					gio.setSelectedItem(maDdp.getGioDat().getHours());
					System.out.println(maDdp);
					phut.setSelectedItem(maDdp.getGioDat().getMinutes());

				}
			});
			btn2.setBounds(0, 60, 200, 50);
			btn2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

//					java.sql.Date ngayDP = new java.sql.Date(dchNgayDatPhong.getDate().getTime());

//					int gioDat = dchNgayDatPhong.getDate().getHours();

					DonDatPhong ddp = donDatPhong_dao.getDonDatPhongtheoMaPH(ma);
					int gioDat = ddp.getGioDat().getHours();
					int gioHienTai = java.time.LocalDateTime.now().getHour();
					long millis = System.currentTimeMillis();
					Date today = new Date(millis);
//					dchNgayDatPhong = new com.toedter.calendar.JDateChooser(today);
//					dchNgayDatPhong.setDateFormatString("dd-MM-yyyy");
//					dchNgayDatPhong.setBounds(10, 178, 180, 30);

					if ((today.getDate() == ddp.getNgayDat().getDate()) && ((gioHienTai + 2) < gioDat)) {
						lCNP.setVisible(false);
						pPT3 = new phieuPhongTrong3(ma);
					} else if (today.getDate() < ddp.getNgayDat().getDate()) {
						lCNP.setVisible(false);
						pPT3 = new phieuPhongTrong3(ma);
					} else {
						JOptionPane.showMessageDialog(lCNP, "Không thể nhận phòng, khách sắp đến nhận phòng", "Error", JOptionPane.INFORMATION_MESSAGE);

					}

				}
			});
			this.add(btn1);
			this.add(btn2);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}

//  JFrame phiếu đặt phòng 
	public class phieuPhongTrong1 extends JFrame {
		public phieuPhongTrong1(String ma) {
			super("Phiếu đặt phòng");
			this.setSize(465, 369);
			setVisible(true);
			setLocationRelativeTo(null);
			setResizable(false);
			pCtn = new JPanel();
			pCtn.setPreferredSize(new Dimension(455, 360));
			getContentPane().add(pCtn, BorderLayout.NORTH);
			pCtn.setLayout(null);

			JLabel lblSoPhong = new JLabel("Số phòng :");
			lblSoPhong.setBounds(320, 20, 190, 25);
			lblSoPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
			pCtn.add(lblSoPhong);
			PhongHat soPhong = phong_dao.getPhongtheoMa(ma);
			lblSoPhong.setText("Số phòng :" + soPhong.getTenPhongHat());

			JLabel lblTitle = new JLabel("Thông tin phiếu đặt phòng");
			lblTitle.setBounds(10, 20, 190, 25);
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
			pCtn.add(lblTitle);

			khung = new JPanel();
			khung.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			khung.setBounds(10, 45, 431, 221);
			pCtn.add(khung);
			khung.setLayout(null);
			text();
			txtTim = new JTextField();
			txtTim.setBounds(124, 21, 238, 30);
			khung.add(txtTim);
			txtTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtTim.setColumns(10);
			btnTim = new JButton("Tìm");
			btnTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnTim.setBounds(362, 20, 59, 30);
			khung.add(btnTim);
			btnTim.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String sdt = txtTim.getText().trim();

					if (sdt.isEmpty())
						JOptionPane.showMessageDialog(null, "Vui lòng nhập SDT!!!");
					else if (khachHang_dao.getKHtheoSDT(sdt) == null)
						JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng có số " + sdt);
					else {
						KhachHang kh = khachHang_dao.getKHtheoSDT(sdt);
						lblSDT.setText("Số điện thoại: " + kh.getSoDienThoai().toString());
						lblTenKH.setText("Tên khách hàng : " + kh.getTenKhachHang().toString());
						btnDatPhong.setEnabled(true);
					}
				}
			});

			JLabel lblTim = new JLabel("Tìm số điện thoại: ");
			lblTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTim.setBounds(10, 28, 108, 16);
			khung.add(lblTim);
//  		Đặt phòng
			btnDatPhong = new JButton("Đặt phòng");
			btnDatPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnDatPhong.setBounds(324, 280, 115, 37);
			btnDatPhong.setEnabled(false);
			pCtn.add(btnDatPhong);
			btnDatPhong.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					PhongHat ph = phong_dao.getPhongtheoMa(ma);
					DatPhong();
					pPT1.setVisible(false);
					updateHA();
					
				}
			});
			btnDatPhong.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					loadHA();
				}
			});
		}
	}

//  JFrame phiếu đặt phòng
	public class phieuPhongTrong2 extends JFrame {
		public phieuPhongTrong2(String ma) {
			super("Phiếu nhận phòng");
			this.setSize(465, 299);
			setVisible(true);
			setLocationRelativeTo(null);
			setResizable(false);
			pCtn = new JPanel();
			pCtn.setPreferredSize(new Dimension(455, 360));
			getContentPane().add(pCtn, BorderLayout.NORTH);
			pCtn.setLayout(null);

			JLabel lblSoPhong = new JLabel("Số phòng :");
			lblSoPhong.setBounds(320, 20, 190, 25);
			lblSoPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
			pCtn.add(lblSoPhong);
			PhongHat soPhong = phong_dao.getPhongtheoMa(ma);
			lblSoPhong.setText("Số phòng :" + soPhong.getTenPhongHat());

			JLabel lblTitle = new JLabel("Thông tin phiếu đặt phòng");
			lblTitle.setBounds(10, 20, 190, 25);
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
			pCtn.add(lblTitle);

			khung = new JPanel();
			khung.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			khung.setBounds(10, 45, 431, 151);
			pCtn.add(khung);
			khung.setLayout(null);
			lblTenKH = new JLabel("Tên khách hàng:");
			lblTenKH.setBounds(10, 68, 400, 16);
			lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));

			lblSDT = new JLabel("Số điện thoại: ");
			lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSDT.setBounds(10, 108, 400, 16);

			khung.add(lblTenKH);
			khung.add(lblSDT);

			JLabel lblNgayDat = new JLabel("Ngày đặt: ");
			lblNgayDat.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNgayDat.setBounds(10, 148, 108, 16);

			long millis = System.currentTimeMillis();
			Date today = new Date(millis);
			dchNgayDatPhong = new com.toedter.calendar.JDateChooser(today);
			dchNgayDatPhong.setDateFormatString("dd-MM-yyyy");
			dchNgayDatPhong.setBounds(10, 178, 180, 30);

			dchNgayDatPhong.setEnabled(false);

			JLabel lblGioDat = new JLabel("Giờ đặt:");
			lblGioDat.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblGioDat.setBounds(281, 148, 46, 16);

//			khung.add(dchNgayDatPhong);
//			khung.add(lblNgayDat);
//			khung.add(lblGioDat);

//		int h = date.getHours();
//		int m = date.getMinutes();
			gio = new JComboBox<>();
			gio.addItem(java.time.LocalDateTime.now().getHour());
			gio.setBounds(281, 178, 50, 30);
			gio.setEnabled(false);

			JLabel dau2Cham = new JLabel(":");
			dau2Cham.setFont(new Font("Tahoma", Font.BOLD, 18));
			dau2Cham.setBounds(345, 180, 20, 20);

			phut = new JComboBox<>();
			phut.addItem(java.time.LocalDateTime.now().getMinute());
			phut.setEnabled(false);
			phut.setBounds(365, 178, 50, 30);

//			khung.add(lblTenKH);
//			khung.add(lblSDT);
//			khung.add(gio);
//			khung.add(dau2Cham);
//			khung.add(phut);

			txtTim = new JTextField();
			txtTim.setBounds(124, 21, 238, 30);
			khung.add(txtTim);
			txtTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtTim.setColumns(10);
			btnTim = new JButton("Tìm");
			btnTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnTim.setBounds(362, 20, 59, 30);
			khung.add(btnTim);
			btnTim.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String sdt = txtTim.getText().trim();

					if (sdt.isEmpty())
						JOptionPane.showMessageDialog(null, "Vui lòng nhập SDT!!!");
					else if (khachHang_dao.getKHtheoSDT(sdt) == null)
						JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng có số " + sdt);
					else {
						KhachHang kh = khachHang_dao.getKHtheoSDT(sdt);
						lblSDT.setText("Số điện thoại: " + kh.getSoDienThoai().toString());
						lblTenKH.setText("Tên khách hàng : " + kh.getTenKhachHang().toString());
						btnThuePhong.setEnabled(true);
					}
				}
			});

			JLabel lblTim = new JLabel("Tìm số điện thoại: ");
			lblTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTim.setBounds(10, 28, 108, 16);
			khung.add(lblTim);
//  		Thuê phòng
			btnThuePhong = new JButton("Thuê phòng");
			btnThuePhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnThuePhong.setBounds(10, 210, 115, 37);
			btnThuePhong.setEnabled(false);
			pCtn.add(btnThuePhong);
			btnThuePhong.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn nhận phòng không ?");
					if (choice == JOptionPane.YES_OPTION) {
						String tt = "Đang sử dụng";
						phong_dao.nhanPhong(tt, ma);

						java.util.Date dateTime = dchNgayDatPhong.getDate();
						java.sql.Date ngayDP = new java.sql.Date(dateTime.getTime());
						long millis = System.currentTimeMillis();

						Time timeday = new Time(millis);

						String sdt = txtTim.getText().trim();

						PhongHat ph = new PhongHat(maPH);
						KhachHang kh = khachHang_dao.getKHtheoSDT(sdt);
						String maDonDatPhong = generateCode();
						int count = donDatPhong_dao.kiemTraMaDonDatPhong(maDonDatPhong);
						do {
							maDonDatPhong = generateCode();
						} while (count > 0);
						DonDatPhong ddp = new DonDatPhong(maDonDatPhong, ngayDP, timeday, ph, kh);
						donDatPhong_dao.addDonDatPhong(ddp);

						JOptionPane.showMessageDialog(null, "Thuê phòng thành công");
						pPT2.setVisible(false);
						updateHA();
					}

				}

			});
		}
	}

//  JFrame phiếu nhận phòng khi phòng đã có người đặt
	public class phieuPhongTrong3 extends JFrame {
		private Date today;

		public phieuPhongTrong3(String ma) {
			super("Phiếu nhận phòng");
			this.setSize(465, 299);
			setVisible(true);
			setLocationRelativeTo(null);
			setResizable(false);
			pCtn = new JPanel();
			pCtn.setPreferredSize(new Dimension(455, 360));
			getContentPane().add(pCtn, BorderLayout.NORTH);
			pCtn.setLayout(null);

			JLabel lblSoPhong = new JLabel("Số phòng :");
			lblSoPhong.setBounds(320, 20, 190, 25);
			lblSoPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
			pCtn.add(lblSoPhong);
			PhongHat soPhong = phong_dao.getPhongtheoMa(ma);
			lblSoPhong.setText("Số phòng :" + soPhong.getTenPhongHat());

			JLabel lblTitle = new JLabel("Thông tin phiếu đặt phòng");
			lblTitle.setBounds(10, 20, 190, 25);
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
			pCtn.add(lblTitle);

			khung = new JPanel();
			khung.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			khung.setBounds(10, 45, 431, 151);
			pCtn.add(khung);
			khung.setLayout(null);
			lblTenKH = new JLabel("Tên khách hàng:");
			lblTenKH.setBounds(10, 68, 400, 16);
			lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));

			lblSDT = new JLabel("Số điện thoại: ");
			lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSDT.setBounds(10, 108, 400, 16);

			khung.add(lblTenKH);
			khung.add(lblSDT);

			JLabel lblNgayDat = new JLabel("Ngày đặt: ");
			lblNgayDat.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNgayDat.setBounds(10, 148, 108, 16);

			long millis = System.currentTimeMillis();
			today = new Date(millis);
			dchNgayDatPhong = new com.toedter.calendar.JDateChooser(today);
			dchNgayDatPhong.setDateFormatString("dd-MM-yyyy");
			dchNgayDatPhong.setBounds(10, 178, 180, 30);

			dchNgayDatPhong.setEnabled(false);

			JLabel lblGioDat = new JLabel("Giờ đặt:");
			lblGioDat.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblGioDat.setBounds(281, 148, 46, 16);

//			khung.add(dchNgayDatPhong);
//			khung.add(lblNgayDat);
//			khung.add(lblGioDat);

//		int h = date.getHours();
//		int m = date.getMinutes();
			gio = new JComboBox<>();
			gio.addItem(java.time.LocalDateTime.now().getHour());
			gio.setBounds(281, 178, 50, 30);
			gio.setEnabled(false);

			JLabel dau2Cham = new JLabel(":");
			dau2Cham.setFont(new Font("Tahoma", Font.BOLD, 18));
			dau2Cham.setBounds(345, 180, 20, 20);

			phut = new JComboBox<>();
			phut.addItem(java.time.LocalDateTime.now().getMinute());
			phut.setEnabled(false);
			phut.setBounds(365, 178, 50, 30);

//			khung.add(lblTenKH);
//			khung.add(lblSDT);
//			khung.add(gio);
//			khung.add(dau2Cham);
//			khung.add(phut);

			txtTim = new JTextField();
			txtTim.setBounds(124, 21, 238, 30);
			khung.add(txtTim);
			txtTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtTim.setColumns(10);
			btnTim = new JButton("Tìm");
			btnTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnTim.setBounds(362, 20, 59, 30);
			khung.add(btnTim);
			btnTim.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String sdt = txtTim.getText().trim();

					if (sdt.isEmpty())
						JOptionPane.showMessageDialog(null, "Vui lòng nhập SDT!!!");
					else if (khachHang_dao.getKHtheoSDT(sdt) == null)
						JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng có số " + sdt);
					else {
						KhachHang kh = khachHang_dao.getKHtheoSDT(sdt);
						lblSDT.setText("Số điện thoại: " + kh.getSoDienThoai().toString());
						lblTenKH.setText("Tên khách hàng : " + kh.getTenKhachHang().toString());
						btnThuePhong.setEnabled(true);
					}
				}
			});

			JLabel lblTim = new JLabel("Tìm số điện thoại: ");
			lblTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTim.setBounds(10, 28, 108, 16);
			khung.add(lblTim);
//  		Thuê phòng
			btnThuePhong = new JButton("Thuê phòng");
			btnThuePhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnThuePhong.setBounds(10, 210, 115, 37);
			btnThuePhong.setEnabled(false);
			pCtn.add(btnThuePhong);
			btnThuePhong.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
//					DonDatPhong ddp = donDatPhong_dao.getDonDatPhongtheoMaPH(ma);
					int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn nhận phòng không ?");
					if (choice == JOptionPane.YES_OPTION) {

						long millis = System.currentTimeMillis();
						Date today = new Date(millis);
						java.util.Date dateTime = dchNgayDatPhong.getDate();
						java.sql.Date ngayDP = new java.sql.Date(dateTime.getTime());

						Time timeday = new Time(millis);

						PhongHat ph = new PhongHat(maPH);
						KhachHang kh = khachHang_dao.getKHtheoSDT(txtTim.getText().trim());
						String maDonDatPhong = generateCode();
						int count = donDatPhong_dao.kiemTraMaDonDatPhong(maDonDatPhong);
						do {
							maDonDatPhong = generateCode();
						} while (count > 0);
						DonDatPhong ddp = new DonDatPhong(maDonDatPhong, ngayDP, timeday, ph, kh);

						donDatPhong_dao.addDonDatPhong(ddp);
						String tt = "Đang sử dụng";
						phong_dao.nhanPhong(tt, ma);
						pPT3.setVisible(false);
						JOptionPane.showMessageDialog(null, "Thuê phòng thành công");
						updateHA();
					}

				}

			});
		}
	}

	// JFrame phiếu đặt phòng
	public class phieuDatPhong extends JFrame {
		public phieuDatPhong(String ma) {
			super("Phiếu đặt phòng");
			this.setSize(465, 369);
			setVisible(true);
			setLocationRelativeTo(null);
			setResizable(false);
			pCtn = new JPanel();
			pCtn.setPreferredSize(new Dimension(455, 360));
			getContentPane().add(pCtn, BorderLayout.NORTH);
			pCtn.setLayout(null);

			JLabel lblSoPhong = new JLabel("Số phòng :");
			lblSoPhong.setBounds(320, 20, 190, 25);
			lblSoPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
			pCtn.add(lblSoPhong);
			PhongHat soPhong = phong_dao.getPhongtheoMa(ma);
			lblSoPhong.setText("Số phòng :" + soPhong.getTenPhongHat());

			JLabel lblTitle = new JLabel("Thông tin phiếu đặt phòng");
			lblTitle.setBounds(10, 20, 190, 25);
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
			pCtn.add(lblTitle);

			khung = new JPanel();
			khung.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			khung.setBounds(10, 45, 431, 221);
			pCtn.add(khung);
			khung.setLayout(null);
			text();
			txtTim = new JTextField();
			txtTim.setBounds(124, 21, 238, 30);
			khung.add(txtTim);
			txtTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtTim.setColumns(10);
			btnTim = new JButton("Tìm");
			btnTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnTim.setBounds(362, 20, 59, 30);
			khung.add(btnTim);
			btnTim.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					timKiem();
				}
			});

			JLabel lblTim = new JLabel("Tìm số điện thoại: ");
			lblTim.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTim.setBounds(10, 28, 108, 16);
			khung.add(lblTim);
//    		Thuê phòng
			btnThuePhong = new JButton("Thuê phòng");
			btnThuePhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnThuePhong.setBounds(10, 280, 115, 37);
			btnThuePhong.setEnabled(false);
			pCtn.add(btnThuePhong);
			btnThuePhong.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn nhận phòng không ?");
					if (choice == JOptionPane.YES_OPTION) {
						String tt = "Đang sử dụng";
						phong_dao.nhanPhong(tt, ma);
						pdp.setVisible(false);
//						gioVao = System.currentTimeMillis();
						java.sql.Date ngayDP = new java.sql.Date(dchNgayDatPhong.getDate().getTime());
						int gioDat = dchNgayDatPhong.getDate().getHours();
						int phutDat = dchNgayDatPhong.getDate().getMinutes();
						Time gioDP = new Time(gioDat, phutDat, 0);
						PhongHat ph = new PhongHat(maPH);
						KhachHang kh = khachHang_dao.getKHtheoSDT(txtTim.getText());
						String maDonDatPhong = generateCode();
						int count = donDatPhong_dao.kiemTraMaDonDatPhong(maDonDatPhong);
						do {
							maDonDatPhong = generateCode();
						} while (count > 0);
						DonDatPhong ddp = new DonDatPhong(maDonDatPhong, ngayDP, gioDP, ph, kh);
						donDatPhong_dao.addDonDatPhong(ddp);
						JOptionPane.showMessageDialog(null, "Thuê phòng thành công");
					}

				}

			});
//    		Đặt phòng
			btnDatPhong = new JButton("Đặt phòng");
			btnDatPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnDatPhong.setBounds(324, 280, 115, 37);
			btnDatPhong.setEnabled(false);
			pCtn.add(btnDatPhong);
			btnDatPhong.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					PhongHat ph = phong_dao.getPhongtheoMa(ma);
					DatPhong();
//	    			String tenPhong = ph.getTenPhongHat();
//	    			int index = -1;
//	    			for (int i = 0; i < listPhong.size(); i++) {
//	    				int cat = lblTenPhongs[i].getText().trim().indexOf(" ");
//	    				String chuoi = lblTenPhongs[i].getText().trim().substring(cat + 1);
//	    				if (chuoi.equalsIgnoreCase(tenPhong)) {
//	    					index = i;
//	    					break;
//	    				}
//	    			}
					pdp.setVisible(false);
				}
			});
			btnDatPhong.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					loadHA();
				}
			});
		}
	}

	// đặt phòng
	public void DatPhong() {
		String ten = lblTenKH.getText().trim();
		String soDienThoai = txtTim.getText().trim();
		java.util.Date dateTime = dchNgayDatPhong.getDate();
		// thời gian của hệ thống tính bằng mili giây
		long millis = System.currentTimeMillis();
		Date today = new Date(millis);
		// giờ phút của ngày hiện tại
		int hour = dateTime.getHours();
		int minute = dateTime.getMinutes();
		// giờ phút của ngày nhập vào
		gioNhapVao = gio.getSelectedItem().toString();
		phutNhapVao = phut.getSelectedItem().toString();
		int hourNhapVao = Integer.parseInt(gio.getSelectedItem().toString());
		int minuteNhapVao = Integer.parseInt(phut.getSelectedItem().toString());

		Time thoiGianDatPhong = new Time(hourNhapVao, minuteNhapVao, 0);

		Time timeday = new Time(hour, minute, 0);

		if (dateTime.getDate() < today.getDate()) {
			JOptionPane.showMessageDialog(null, "Vui lòng đặt phòng ngày hiện tại hoặc sau ngày hiện tại!");
		} else if (dateTime.getDate() == today.getDate()) {
			if (thoiGianDatPhong.before(timeday)) {
				JOptionPane.showMessageDialog(null, "Vui lòng đặt phòng sau giờ hiện tại!");
			} else {

				java.sql.Date ngayDP = new java.sql.Date(dateTime.getTime());
				Time gioDP = new Time(Integer.parseInt(gio.getSelectedItem().toString()),
						Integer.parseInt(phut.getSelectedItem().toString()), 0);
				PhongHat ph = new PhongHat(maPH);
				KhachHang kh = khachHang_dao.getKHtheoSDT(soDienThoai);
				String maDonDatPhong = generateCode();
				int count = donDatPhong_dao.kiemTraMaDonDatPhong(maDonDatPhong);
				do {
					maDonDatPhong = generateCode();
				} while (count > 0);
				DonDatPhong ddp = new DonDatPhong(maDonDatPhong, ngayDP, gioDP, ph, kh);

				donDatPhong_dao.addDonDatPhong(ddp);
				String tt = "Phòng chờ";
				phong_dao.datPhong(tt, ph.getMaPhongHat());
				JOptionPane.showMessageDialog(null, "Đặt phòng thành công");
			}
		} else {
			java.sql.Date ngayDP = new java.sql.Date(dateTime.getTime());
			Time gioDP = new Time(Integer.parseInt(gio.getSelectedItem().toString()),
					Integer.parseInt(phut.getSelectedItem().toString()), 0);
			PhongHat ph = new PhongHat(maPH);
			KhachHang kh = khachHang_dao.getKHtheoSDT(soDienThoai);
			String maDonDatPhong = generateCode();
			int count = donDatPhong_dao.kiemTraMaDonDatPhong(maDonDatPhong);
			do {
				maDonDatPhong = generateCode();
			} while (count > 0);
			DonDatPhong ddp = new DonDatPhong(maDonDatPhong, ngayDP, gioDP, ph, kh);

			donDatPhong_dao.addDonDatPhong(ddp);
			String tt = "Phòng chờ";
			phong_dao.datPhong(tt, ph.getMaPhongHat());
			JOptionPane.showMessageDialog(null, "Đặt phòng thành công");
		}
	}

	// phát sinh mã đơn đặt phòng
	public String generateCode() {
		// Khởi tạo một biến để lưu trữ mã
		String maDDP = "";
		String prefix = "DP";
		// Tạo một biến ngẫu nhiên
		Random random = new Random();

		// Tạo một chuỗi gồm 3 số ngẫu nhiên
		for (int i = 0; i < 3; i++) {
			maDDP += random.nextInt(10);
		}
		return prefix + maDDP;
	}

	// phát sinh mã hóa đơn
	public String generateCode_HD() {
		// Khởi tạo một biến để lưu trữ mã
		String maHD = "";
		String prefix = "HD";
		// Tạo một biến ngẫu nhiên
		Random random = new Random();

		// Tạo một chuỗi gồm 3 số ngẫu nhiên
		for (int i = 0; i < 3; i++) {
			maHD += random.nextInt(10);
		}
		return prefix + maHD;
	}

//    Phiếu nhận phòng
	public class phieuNhanPhong extends JFrame {
		public JPanel pCtn;
		public DefaultTableModel dm;
		public JTable tb;
		public JScrollPane pane;
		public JTextField txtTim;

		public phieuNhanPhong(String ma) {
			super("Phiếu nhận phòng");
			setSize(465, 369);
			setVisible(true);
			setLocationRelativeTo(null);
			setResizable(false);
			pCtn = new JPanel();
			pCtn.setPreferredSize(new Dimension(455, 360));
			getContentPane().add(pCtn, BorderLayout.NORTH);
			pCtn.setLayout(null);

			lblSoPhong = new JLabel("Số phòng :");
			lblSoPhong.setBounds(320, 20, 190, 25);
			lblSoPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
			pCtn.add(lblSoPhong);
			PhongHat soPhong = phong_dao.getPhongtheoMa(ma);
			lblSoPhong.setText("Số phòng :" + soPhong.getTenPhongHat());

			JLabel lblTitle = new JLabel("Thông tin phiếu nhận phòng");
			lblTitle.setBounds(10, 20, 210, 25);
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
			pCtn.add(lblTitle);

			khung = new JPanel();
			khung.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			khung.setBounds(10, 45, 431, 221);
			pCtn.add(khung);
			khung.setLayout(null);

			text1();

			JButton btnCapNhap = new JButton("Cập nhập");
			btnCapNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnCapNhap.setBounds(10, 280, 115, 37);
			pCtn.add(btnCapNhap);
			// btn cập nhật phòng
			btnCapNhap.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					CapNhat_DDP();
				}
			});

			JButton btnHuyPhong = new JButton("Hủy phòng");
			btnHuyPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnHuyPhong.setBounds(165, 280, 115, 37);
			pCtn.add(btnHuyPhong);
			btnHuyPhong.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					String lblSoP = lblSoPhong.getText();
					String soP = lblSoP.substring(10);
					PhongHat p = phong_dao.getPhongtheoSoPhong(soP);
					String maP = p.getMaPhongHat();
					DonDatPhong Ddp = donDatPhong_dao.getDonDatPhongtheoMaPH(maP);
					int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn Hủy phòng không ?");
					if (choice == JOptionPane.YES_OPTION) {
						donDatPhong_dao.deletePhieuDatPhong(Ddp.getMaDonDatPhong());
						String tt = "Phòng trống";
						phong_dao.nhanPhong(tt, ma);
						pnp.setVisible(false);
						updateHA();
					}
				}
			});

			JButton btnNhanPhong = new JButton("Nhận phòng");
			btnNhanPhong.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn nhận phòng không ?");
					if (choice == JOptionPane.YES_OPTION) {
						String tt = "Đang sử dụng";
						phong_dao.nhanPhong(tt, ma);
						// thời gian của hệ thống tính bằng mili giây
						long millis = System.currentTimeMillis();
						Time now = new Time(millis);
				    	java.sql.Date today = new java.sql.Date(millis);
				    	DonDatPhong ddp = donDatPhong_dao.getDonDatPhongtheoMaPH(maPH);
				    	PhongHat ph = phong_dao.getPhongtheoMa(ma);
				    	
				    	DonDatPhong ddp1 = new DonDatPhong(ddp.getMaDonDatPhong(),today,now,ph,ddp.getKhachHang());
				    	donDatPhong_dao.capNhat_DonDatPhong(ddp1);
					
				    	
						JOptionPane.showMessageDialog(null, "Nhận phòng thành công");
						pnp.setVisible(false);
						updateHA();
					}
				}
			});
			btnNhanPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnNhanPhong.setBounds(325, 280, 115, 37);
			pCtn.add(btnNhanPhong);
		}
	}

	// cập nhật đơn đặt phòng
	public void CapNhat_DDP() {
		Date dateTime = dchNgayDatPhong.getDate();
//    	String soDienThoai = lblSDT.getText().trim();
//    	String sdt = soDienThoai.substring(15);
		// thời gian của hệ thống tính bằng mili giây
		long millis = System.currentTimeMillis();
		int choice = JOptionPane.showConfirmDialog(null, "Ban có chắc chắn muốn cập nhật không ?");
		if (choice == JOptionPane.YES_OPTION) {
			java.sql.Date ngayDP = new java.sql.Date(dateTime.getTime());
			Time gioDP = new Time(Integer.parseInt(gio.getSelectedItem().toString()),
					Integer.parseInt(phut.getSelectedItem().toString()), 0);
			PhongHat ph = phong_dao.getPhongtheoMa(maPH);
			DonDatPhong ddp = donDatPhong_dao.getDonDatPhongtheoMaPH(ph.getMaPhongHat());
			ddp.setNgayDat(ngayDP);
			ddp.setGioDat(gioDP);
			donDatPhong_dao.capNhat_DonDatPhong(ddp);
			
		}

	}
	public void update(String ma) {
		
	}

////  load dữ liệu đơn đặt phòng vào các phòng chờ
//	public void loadDonDatPhong() {
//		ArrayList<DonDatPhong> listDonDatPhongs = donDatPhong_dao.getAllDonDatPhong("Phòng Chờ");
//		for (int i = 0; i < count; i++) {
//
//		}
//	}

//    Phiếu trả phòng
	public class phieuTraPhong extends JFrame {
		public JPanel pCtn;
		public DefaultTableModel dm;
		public JTable tb;
		public JScrollPane pane;
		public JTextField txtTim;
		private JTable tbl_dv;
		private DefaultTableModel dm_dv;
		private JScrollPane pane_dv;
		private JPanel khung1;
		private JLabel lblTenDV;
		private JLabel lblSoLuong;
		private JComboBox cbbTenDV;
		private JComboBox cbbSoLuong;
		private ArrayList<ChiTietDichVu> listChiTietDichVu;
		private ArrayList<HoaDonThuePhong> listHoaDonThuePhong;

		public phieuTraPhong(String ma) {
			super("Phiếu trả phòng");
			setSize(740, 369);
			setVisible(true);
			setLocationRelativeTo(null);
			setResizable(false);
			ConnectDB.getInstance();
			Connection conn = ConnectDB.getConnection();
			pCtn = new JPanel();
			pCtn.setPreferredSize(new Dimension(455, 360));
			getContentPane().add(pCtn, BorderLayout.NORTH);
			pCtn.setLayout(null);

			lblSoPhong = new JLabel("Số phòng :");
			lblSoPhong.setBounds(610, 20, 190, 25);
			lblSoPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
			pCtn.add(lblSoPhong);
			PhongHat soPhong = phong_dao.getPhongtheoMa(ma);
			lblSoPhong.setText("Số phòng :" + soPhong.getTenPhongHat());

			JLabel lblTitle = new JLabel("Thông tin phiếu trả phòng");
			lblTitle.setBounds(272, 20, 190, 25);
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
			pCtn.add(lblTitle);

			JLabel lblTitleDV = new JLabel("Dich vụ");
			lblTitleDV.setBounds(10, 20, 100, 25);
			lblTitleDV.setFont(new Font("Tahoma", Font.BOLD, 14));
			pCtn.add(lblTitleDV);

			khung = new JPanel();
			khung.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			khung.setBounds(275, 45, 431, 221);
			pCtn.add(khung);
			khung.setLayout(null);

			khung1 = new JPanel();
			khung1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			khung1.setBounds(10, 45, 250, 221);
			pCtn.add(khung1);
			khung1.setLayout(null);
			tbl_dv = new JTable();
			tbl_dv.setModel(dm_dv = new javax.swing.table.DefaultTableModel(new Object[][] {

			}, new String[] { "Tên dịch vụ", "Số lượng" }) {
				Class[] types = new Class[] { java.lang.String.class, java.lang.Integer.class };
//    		            boolean[] canEdit = new boolean [] {
//    		                false, false, false, false
//    		            };

				public Class getColumnClass(int columnIndex) {
					return types[columnIndex];
				}

//    		            public boolean isCellEditable(int rowIndex, int columnIndex) {
//    		                return canEdit [columnIndex];
//    		            }
			});

			tbl_dv.setGridColor(new java.awt.Color(220, 155, 155));
			pane_dv = new JScrollPane();
			pane_dv.setViewportView(tbl_dv);
			pane_dv.setBounds(0, 0, 250, 140);

			lblTenDV = new JLabel("Tên dịch vụ");
			lblTenDV.setBounds(5, 145, 100, 20);
			cbbTenDV = new JComboBox<>();
			cbbTenDV.setBounds(5, 175, 100, 30);
			lblSoLuong = new JLabel("Số lượng");
			lblSoLuong.setBounds(130, 145, 100, 20);
			cbbSoLuong = new JComboBox<>();

			cbbSoLuong.setBounds(130, 175, 100, 30);
			for (int i = 1; i <= 100; i++) {
				cbbSoLuong.addItem(i);
			}
			ArrayList<DichVu> dsDV = new ArrayList<>();
			DichVu_DAO dichVu_dao = new DichVu_DAO();
			dsDV = dichVu_dao.getAllDichVu();
			for (DichVu dv : dsDV) {
				cbbTenDV.addItem(dv.getTenDichVu().toString());
			}
			tbl_dv.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					int pos = tbl_dv.getSelectedRow();
					cbbTenDV.setSelectedItem(dm_dv.getValueAt(pos, 0));
					cbbSoLuong.setSelectedItem(dm_dv.getValueAt(pos, 1));
				}
			});
			khung1.add(lblTenDV);
			khung1.add(lblSoLuong);
			khung1.add(cbbSoLuong);
			khung1.add(cbbTenDV);
			khung1.add(pane_dv);

			text2();

			JButton btnTraPhong = new JButton("Trả phòng");
			btnTraPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnTraPhong.setBounds(585, 280, 115, 37);
			pCtn.add(btnTraPhong);
			String lblSoP = lblSoPhong.getText();
			String soP = lblSoP.substring(10);

			listHoaDonThuePhong = hoaDonThuePhong_dao.getAllHoaDonThuePhong();
			Boolean ok = false;
			if (listHoaDonThuePhong != null) {
				for (HoaDonThuePhong HD : listHoaDonThuePhong) {
					if (HD.getPhongHat().getTenPhongHat().equalsIgnoreCase(soP)) {

						ok = true;
						hd = HD;
						break;
					}

				}
			}
			if (ok) {
				// đọc dữ liệu vào bảng dịch vụ

				dm_dv.setRowCount(0);

				listChiTietDichVu = chiTietDichVu_dao.getAllChiTietDichVu();
				for (ChiTietDichVu ctdv : listChiTietDichVu) {
					if (hd.getMaHoaDon().equalsIgnoreCase(ctdv.getHoaDonThuePhong().getMaHoaDon())) {
						DichVu dv = chiTietDichVu_dao.getCTDVtheoMa(ctdv.getDichVu().getMaDichVu());
						dm_dv.addRow(new Object[] { dv.getTenDichVu(), ctdv.getSoLuong()

						});
					}
				}
			}
//			System.out.println(hd);
			PhongHat p = phong_dao.getPhongtheoSoPhong(soP);
			String maP = p.getMaPhongHat();
			DonDatPhong maDdp = donDatPhong_dao.getDonDatPhongtheoMaPH(ma);
			dchNgayDatPhong.setDate(maDdp.getNgayDat());
			// lấy thời gian hiện tại tính bằng giây
			long millis = System.currentTimeMillis();
			if (ok == false) {
				String maHD = generateCode_HD();
				Date ngayDat = dchNgayDatPhong.getDate();
				KhachHang kh = maDdp.getKhachHang();
				String maKH = maDdp.getKhachHang().getMaKhachHang().toString();
				TaiKhoan_DAO dsTK = new TaiKhoan_DAO();
				JFrame_DangNhap dangNhap = new JFrame_DangNhap();
				TaiKhoan tk = dsTK.getTaiKHoanTheoTenDN(dangNhap.txtUser.getText().trim());
				String maNV = tk.getNhanVien().getMaNhanVien();
				NhanVien nv = nhanVien_dao.getNVtheoMa(maNV);
//				int hour = Integer.parseInt(gio.getSelectedItem());
//				System.out.println(hour);
//				int m = Integer.parseInt(phut.getSelectedItem().toString());
//				Time time = new Time(hour, m, 0);
				int SLDDP = donDatPhong_dao.getSoLuongMaDDPTheoMaPhong(maPH);
				List<DonDatPhong>  dsDDP = donDatPhong_dao.getAllDonDatPhongTheoMaPH(maPH) ;
				System.out.println(dsDDP);
				if(SLDDP > 1) {
					for(DonDatPhong ddp : dsDDP) {
						for(DonDatPhong ddp1 :dsDDP) {
							if(ddp.getNgayDat().getDate()<ddp1.getNgayDat().getDate()){
								hd = new HoaDonThuePhong(maHD, new java.sql.Date(millis), ddp.getGioDat(), new java.sql.Time(millis), kh, nv, p);
								System.out.println(hd);
							}
							else if((ddp.getNgayDat().getDate()==ddp1.getNgayDat().getDate())&&(ddp.getGioDat().getHours()<ddp1.getGioDat().getHours())){
								hd = new HoaDonThuePhong(maHD, new java.sql.Date(millis), ddp.getGioDat(), new java.sql.Time(millis), kh, nv, p);
								System.out.println(hd);
							}
							
						}
					}
				}
				else {
					hd = new HoaDonThuePhong(maHD, new java.sql.Date(millis), maDdp.getGioDat(), new java.sql.Time(millis), kh, nv, p);
				}

//				System.out.println(time);
		
				
				hoaDonThuePhong_dao.addHoaDon(hd);
			}
			btnTraPhong.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					loadDuLieuHoaDonThanhToan();
					txtTienNhan.setText("");
					txtTienThua.setText("");
					ptp.setVisible(false);
					pnlChinh.setVisible(false);
					pnlHoaDonChinh.setVisible(true);
					tonggio = hd.getGioRa().getHours() - hd.getGioVao().getHours();
					int phut = hd.getGioRa().getMinutes() - hd.getGioVao().getMinutes();
					
					if(tonggio == 0) {
						tonggio++;
					}
					if (phut > 30)
						tonggio++;
					int tienPhong = (int) ((int) tonggio * (hd.getPhongHat().getGiaPhong()));
					String TienPhong = String.valueOf(tienPhong);
					txtTienPhong.setText(TienPhong.toString());
					double tongTienDichVu = 0;
					for (int i = 0; i < dm_dv.getRowCount(); i++) {
						String ten = (String) dm_dv.getValueAt(i, 0);
						int sl = (int) dm_dv.getValueAt(i, 1);
						double gia = 0;
						ArrayList<DichVu> dsDV = new ArrayList<>();
						DichVu_DAO dichVu_dao = new DichVu_DAO();
						dsDV = dichVu_dao.getAllDichVu();
						for (DichVu dv : dsDV) {
							if (ten.equals(dv.getTenDichVu())) {
								gia = dv.getGiaDichVu();
								break;
							}
						}
						double thanhTien = gia * sl;
						tongTienDichVu = tongTienDichVu + thanhTien;
						model_ChiTietDichVu.addRow(new Object[] { ten, sl, gia, thanhTien });
					}
					String TongTienDichVu = String.valueOf(tongTienDichVu);
					lbl_txtThanhTienDichVu.setText(TongTienDichVu);
					txtTienDichVu.setText(TongTienDichVu);
					tongTien = tienPhong + tongTienDichVu;
					String TongTien = String.valueOf(tongTien);
					txtTongTien.setText(TongTien);
					

				}
			});
			JButton btnThem = new JButton("Thêm");
			btnThem.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnThem.setBounds(10, 280, 80, 37);
			pCtn.add(btnThem);
			btnThem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// Lấy dữ liệu từ hai combobox
					String ten = (String) cbbTenDV.getSelectedItem();
					int sl = (int) cbbSoLuong.getSelectedItem();

					// Thêm dữ liệu vào bảng dịch vụ

					// Kiểm tra xem tên dịch vụ đã được chọn có trùng với tên dịch vụ của bất kỳ
					// hàng nào trong bảng dịch vụ hay không
					int rowIndex = -1;
					for (int i = 0; i < dm_dv.getRowCount(); i++) {
						if (ten.equals(dm_dv.getValueAt(i, 0))) {
							rowIndex = i;
							break;
						}
					}

					// Nếu trùng, thì tăng số lượng của hàng đó lên theo so luong thêm
					if (rowIndex != -1) {

						sl = (int) dm_dv.getValueAt(rowIndex, 1) + sl;
						dm_dv.setValueAt(sl, rowIndex, 1);
						DichVu dv = dichVu_dao.getDVtheoTen(ten);

						ChiTietDichVu ctdv = new ChiTietDichVu(hd, dv, sl);
						chiTietDichVu_dao.capNhat_ChiTietDichVu(ctdv);

					} else {
						// Nếu không trùng, thì thêm hàng mới vào bảng dịch vụ
						DichVu dv = dichVu_dao.getDVtheoTen(ten);

						ChiTietDichVu ctdv = new ChiTietDichVu(hd, dv, sl);
						dm_dv.addRow(new Object[] { ten, sl });
						chiTietDichVu_dao.addChiTietDichVu(ctdv);

					}
				}
			});

			JButton btnCapNhap = new JButton("Sửa");
			btnCapNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnCapNhap.setBounds(96, 280, 80, 37);
			pCtn.add(btnCapNhap);
			btnCapNhap.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int rowIndex = tbl_dv.getSelectedRow();
					String ten = (String) cbbTenDV.getSelectedItem();
					int sl = (int) cbbSoLuong.getSelectedItem();

					// Cập nhật dữ liệu trong bảng dịch vụ
					dm_dv.setValueAt(ten, rowIndex, 0);
					dm_dv.setValueAt(sl, rowIndex, 1);
					DichVu dv = dichVu_dao.getDVtheoTen(ten);

					ChiTietDichVu ctdv = new ChiTietDichVu(hd, dv, sl);
					chiTietDichVu_dao.capNhat_ChiTietDichVu(ctdv);

				}
			});

			JButton btnXoa = new JButton("Xóa");
			btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnXoa.setBounds(176, 280, 80, 37);
			pCtn.add(btnXoa);
			btnXoa.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// Lấy vị trí của hàng cần xóa
					int rowIndex = tbl_dv.getSelectedRow();

					// Xóa hàng đó khỏi bảng dịch vụ
					dm_dv.removeRow(rowIndex);
					String ten = (String) cbbTenDV.getSelectedItem();
					DichVu dv = dichVu_dao.getDVtheoTen(ten);

					chiTietDichVu_dao.delete_ChiTietDichVu(dv.getMaDichVu(), hd.getMaHoaDon());
				}
			});

		}
	}

	public void loadDuLieuHoaDonThanhToan() {
		txtMaNhanVien.setText(hd.getNhanVien().getMaNhanVien().toString());
		txtTenNhanVien.setText(hd.getNhanVien().getTenNhanVien().toString());
		txtTenKhachHang.setText(hd.getKhachHang().getTenKhachHang().toString());
		txtSDT.setText(hd.getKhachHang().getSoDienThoai().toString());
		txtPhong.setText(hd.getPhongHat().getTenPhongHat().toString());
		txtGioVao.setText(hd.getGioVao().toString());
		txtGiaRa.setText(hd.getGioRa().toString());
		txtMaHoaDon.setText(hd.getMaHoaDon().toString());
		txtNgayTaoHoaDon.setText(hd.getNgayTaoHoaDon().toString());
	}

	/* khai báo all của đặt phòng */
	private Phong_DAO phong_dao;

	private LoaiPhong_DAO loaiPhong_dao;
	protected JPanel pnlBody;
	private int count;
	private ArrayList<PhongHat> listPhong;
	private KhachHang_DAO khachHang_dao;
	private JPanel pCtn;
	private DefaultTableModel dm;
	private JTable tb;
	private JScrollPane pane;
	private JTextField txtTim;
	private JLabel lblSDT;
	private JLabel lblTenKH;
	private JButton btnTim;
	private JLabel lblSoPhong;
	private JPanel[] pnlPhongs;
	private JButton[] btnPhongs;
	private JLabel[] lblTenPhongs;
	private JLabel[] lblSucChuas;
	private JPanel khung;
	private JButton btnDatPhong;
	private JButton btnThuePhong;
	private phieuDatPhong pdp;
	private phieuTraPhong ptp;
	private phieuNhanPhong pnp;
	private LocalTime gioRa;
//	private LocalTime giovao;
	private long gioVao;
//	protected JPanel pnlHoaDonChinh;
	private DefaultTableModel model_ChiTietDichVu;

	/* Thanh toán phòng hát */
	private void initComponents_1() {

		pnlHoaDonChinh = new javax.swing.JPanel();
		lblThanhToanHoaDon = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		lblThongTinHoaDon = new javax.swing.JLabel();
		lblMaHoaDon = new javax.swing.JLabel();
		lblPhong = new javax.swing.JLabel();
		lblGioVao = new javax.swing.JLabel();
		lblGioRa = new javax.swing.JLabel();
		lblNgayTaoHoaDon = new javax.swing.JLabel();
		txtNgayTaoHoaDon = new javax.swing.JLabel();
		txtGiaRa = new javax.swing.JLabel();
		txtGioVao = new javax.swing.JLabel();
		txtPhong = new javax.swing.JLabel();
		txtMaHoaDon = new javax.swing.JLabel();
		lblTongTienThanhToan = new javax.swing.JLabel();
		lblTienPhong = new javax.swing.JLabel();
		txtTienPhong = new javax.swing.JLabel();
		txtTienDichVu = new javax.swing.JLabel();
		lblTienDichVu = new javax.swing.JLabel();
		lblTongTien = new javax.swing.JLabel();
		txtTongTien = new javax.swing.JLabel();
		txtTienNhan = new javax.swing.JTextField();
		lblTienNhan = new javax.swing.JLabel();
		lblTienThua = new javax.swing.JLabel();
		txtTienThua = new javax.swing.JLabel();
		btnThanhToan = new javax.swing.JButton();
		btnInHoaDon = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		lblThongTinKhachHang = new javax.swing.JLabel();
		lbl_txtThanhTienDichVu = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tbl_ChiTietDichVu = new javax.swing.JTable();
		lblChiTietDichVu = new javax.swing.JLabel();
		lblMaNhanVien = new javax.swing.JLabel();
		txtMaNhanVien = new javax.swing.JLabel();
		lblTenNhanVien = new javax.swing.JLabel();
		txtTenNhanVien = new javax.swing.JLabel();
		txtSDT = new javax.swing.JLabel();
		lblSoDienThoai = new javax.swing.JLabel();
		txtTenKhachHang = new javax.swing.JLabel();
		lblTenKhachHang = new javax.swing.JLabel();
		lblThongTinNhanVien = new javax.swing.JLabel();
		lblThanhTienDichVu = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();

		pnlHoaDonChinh.setPreferredSize(new java.awt.Dimension(1250, 755));
		pnlHoaDonChinh.setLayout(new java.awt.BorderLayout());

		lblThanhToanHoaDon.setFont(new java.awt.Font("Helvetica", 1, 30)); // NOI18N
		lblThanhToanHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblThanhToanHoaDon.setText("Thanh Toán Hoá Đơn");
		lblThanhToanHoaDon.setPreferredSize(new java.awt.Dimension(302, 75));
		pnlHoaDonChinh.add(lblThanhToanHoaDon, java.awt.BorderLayout.PAGE_START);

		jPanel2.setBackground(new java.awt.Color(204, 204, 204));
		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		jPanel2.setPreferredSize(new java.awt.Dimension(480, 675));
		lblThongTinHoaDon.setFont(new java.awt.Font("ITF Devanagari", 1, 18)); // NOI18N
		lblThongTinHoaDon.setText("Thông tin hoá đơn");

		lblMaHoaDon.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblMaHoaDon.setText("Mã hoá đơn :");

		lblPhong.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblPhong.setText("Phòng :         ");

		lblGioVao.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblGioVao.setText("Giờ vào:");

		lblGioRa.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblGioRa.setText("Giờ ra:");

		lblNgayTaoHoaDon.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblNgayTaoHoaDon.setText("Ngày tạo hoá đơn :");

		txtNgayTaoHoaDon.setBackground(new java.awt.Color(204, 204, 204));
		txtNgayTaoHoaDon.setBorder(null);
		txtNgayTaoHoaDon.setFont(new java.awt.Font("ITF Devanagari", 1, 18));

		txtGiaRa.setBackground(new java.awt.Color(204, 204, 204));
		txtGiaRa.setBorder(null);
		txtGiaRa.setFont(new java.awt.Font("ITF Devanagari", 1, 18));

		txtGioVao.setBackground(new java.awt.Color(204, 204, 204));
		txtGioVao.setBorder(null);
		txtGioVao.setFont(new java.awt.Font("ITF Devanagari", 1, 18));

		txtPhong.setBackground(new java.awt.Color(204, 204, 204));
		txtPhong.setBorder(null);
		txtPhong.setFont(new java.awt.Font("ITF Devanagari", 1, 18));

		txtMaHoaDon.setBackground(new java.awt.Color(204, 204, 204));
		txtMaHoaDon.setBorder(null);
		txtMaHoaDon.setFont(new java.awt.Font("ITF Devanagari", 1, 18));

		lblTongTienThanhToan.setFont(new java.awt.Font("ITF Devanagari", 1, 18)); // NOI18N
		lblTongTienThanhToan.setText("Tổng tiền thanh toán");

		lblTienPhong.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblTienPhong.setText("Tiền phòng :");

		txtTienPhong.setBackground(new java.awt.Color(204, 204, 204));
		txtTienPhong.setBorder(null);
		txtTienPhong.setFont(new java.awt.Font("ITF Devanagari", 1, 18));

		txtTienDichVu.setBackground(new java.awt.Color(204, 204, 204));
		txtTienDichVu.setBorder(null);
		txtTienDichVu.setFont(new java.awt.Font("ITF Devanagari", 1, 18));

		lblTienDichVu.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblTienDichVu.setText("Tiền dịch vụ :");

		lblTongTien.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblTongTien.setText("Tổng tiền :");

		txtTongTien.setBackground(new java.awt.Color(204, 204, 204));
		txtTongTien.setBorder(null);
		txtTongTien.setFont(new java.awt.Font("ITF Devanagari", 1, 18));

		txtTienNhan.setBackground(new java.awt.Color(255, 255, 255));
		txtTienNhan.setBorder(null);
		txtTienNhan.setFont(new java.awt.Font("ITF Devanagari", 1, 18));

		lblTienNhan.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblTienNhan.setText("Tiền nhận :");

		lblTienThua.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblTienThua.setText("Tiền thừa :");

		txtTienThua.setBackground(new java.awt.Color(204, 204, 204));
		txtTienThua.setBorder(null);
		txtTienThua.setFont(new java.awt.Font("ITF Devanagari", 1, 18));

		btnThanhToan.setBackground(new java.awt.Color(162, 74, 74));
		btnThanhToan.setFont(new java.awt.Font("Helvetica Neue", 3, 13)); // NOI18N
		btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
		btnThanhToan.setText("Thanh toán\n");
		btnThanhToan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnThanhToan.addActionListener(new ActionListener() {
			Border bdFalse = BorderFactory.createLineBorder(Color.red);
			Border bdTrue = BorderFactory.createLineBorder(Color.green);

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ValidData();

			}
		});
		btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThanhToanActionPerformed(evt);
			}
		});

		btnInHoaDon.setBackground(new java.awt.Color(162, 74, 74));
		btnInHoaDon.setFont(new java.awt.Font("Helvetica Neue", 3, 13)); // NOI18N
		btnInHoaDon.setForeground(new java.awt.Color(255, 255, 255));
		btnInHoaDon.setText("In hoá đơn\n");
		btnInHoaDon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup().addGap(36, 36, 36)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(lblTienDichVu, javax.swing.GroupLayout.PREFERRED_SIZE,
														100, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txtTienDichVu, javax.swing.GroupLayout.PREFERRED_SIZE,
														190, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(lblTienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txtTienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 190,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 190,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(lblTienNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txtTienNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 190,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 190,
														javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addGroup(jPanel2Layout.createSequentialGroup().addGap(19, 19, 19)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lblThongTinHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 178,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTongTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 230,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGroup(jPanel2Layout.createSequentialGroup().addGap(40, 40, 40).addGroup(jPanel2Layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(lblNgayTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE,
														150, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txtNgayTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE,
														190, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(lblPhong, javax.swing.GroupLayout.PREFERRED_SIZE,
																110, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(lblGioVao, javax.swing.GroupLayout.PREFERRED_SIZE,
																110, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(lblGioRa, javax.swing.GroupLayout.PREFERRED_SIZE,
																110, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(lblMaHoaDon,
																javax.swing.GroupLayout.PREFERRED_SIZE, 110,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(46, 46, 46)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(txtGioVao, javax.swing.GroupLayout.PREFERRED_SIZE,
																190, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(txtGiaRa, javax.swing.GroupLayout.PREFERRED_SIZE,
																190, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(txtPhong, javax.swing.GroupLayout.PREFERRED_SIZE,
																190, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(txtMaHoaDon,
																javax.swing.GroupLayout.PREFERRED_SIZE, 190,
																javax.swing.GroupLayout.PREFERRED_SIZE)))))))
						.addContainerGap(92, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel2Layout.createSequentialGroup().addGap(15, 15, 15)
								.addComponent(lblThongTinHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblGioVao, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtGioVao, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblGioRa, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtGiaRa, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblNgayTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtNgayTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(69, 69, 69)
								.addComponent(lblTongTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblTienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtTienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblTienDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtTienDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblTienNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtTienNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64,
										Short.MAX_VALUE)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(50, 50, 50)));

		pnlHoaDonChinh.add(jPanel2, java.awt.BorderLayout.WEST);

		jPanel1.setBackground(new java.awt.Color(204, 204, 204));
		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		jPanel1.setPreferredSize(new java.awt.Dimension(750, 675));

		lblThongTinKhachHang.setFont(new java.awt.Font("ITF Devanagari", 1, 18)); // NOI18N
		lblThongTinKhachHang.setText("Thông tin khách hàng");

		lbl_txtThanhTienDichVu.setFont(new java.awt.Font("ITF Devanagari", 1, 14)); // NOI18N

		tbl_ChiTietDichVu.setModel(model_ChiTietDichVu = new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Tên dịch vụ", "Số lượng", "Giá", "Thành tiền" }) {
			Class[] types = new Class[] { java.lang.String.class, java.lang.Long.class, java.lang.Float.class,
					java.lang.Float.class };
			boolean[] canEdit = new boolean[] { false, false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tbl_ChiTietDichVu.setGridColor(new java.awt.Color(220, 155, 155));
		jScrollPane1.setViewportView(tbl_ChiTietDichVu);

		lblChiTietDichVu.setFont(new java.awt.Font("ITF Devanagari", 1, 18)); // NOI18N
		lblChiTietDichVu.setText("Chi tiết dịch vụ");

		lblMaNhanVien.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblMaNhanVien.setText("Mã nhân viên :");

		txtMaNhanVien.setBackground(new java.awt.Color(204, 204, 204));
		txtMaNhanVien.setBorder(null);
		txtMaNhanVien.setFont(new java.awt.Font("ITF Devanagari", 1, 18));
		txtMaNhanVien.setText("");

		lblTenNhanVien.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblTenNhanVien.setText("Tên nhân viên :");

		txtTenNhanVien.setBackground(new java.awt.Color(204, 204, 204));
		txtTenNhanVien.setBorder(null);
		txtTenNhanVien.setFont(new java.awt.Font("ITF Devanagari", 1, 18));

		txtSDT.setBackground(new java.awt.Color(204, 204, 204));
		txtSDT.setBorder(null);
		txtSDT.setFont(new java.awt.Font("ITF Devanagari", 1, 18));

		lblSoDienThoai.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblSoDienThoai.setText("Số điện thoại: ");

		txtTenKhachHang.setBackground(new java.awt.Color(204, 204, 204));
		txtTenKhachHang.setBorder(null);
		txtTenKhachHang.setFont(new java.awt.Font("ITF Devanagari", 1, 18));

		lblTenKhachHang.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
		lblTenKhachHang.setText("Tên khách hàng :");

		lblThongTinNhanVien.setFont(new java.awt.Font("ITF Devanagari", 1, 18)); // NOI18N
		lblThongTinNhanVien.setText("Thông tin nhân viên");

		lblThanhTienDichVu.setFont(new java.awt.Font("ITF Devanagari", 1, 14)); // NOI18N
		lblThanhTienDichVu.setText("Tổng thành tiền dịch vụ: ");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										jPanel1Layout.createSequentialGroup().addGap(28, 28, 28)
												.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 683,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(31, 31, 31))
								.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lblThongTinKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 216,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lblThongTinNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 216,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lblChiTietDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 216,
												javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(0, 0, Short.MAX_VALUE))))
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(64, 64, 64).addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addComponent(lblMaNhanVien).addGap(18, 18, 18)
								.addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 179,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(lblTenNhanVien)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 211,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createSequentialGroup().addComponent(lblTenKhachHang)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 179,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(lblSoDienThoai)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 229,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lbl_txtThanhTienDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(45, 45, 45))
				.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addContainerGap(391, Short.MAX_VALUE)
								.addComponent(lblThanhTienDichVu).addGap(183, 183, 183))));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(18, 18, 18).addComponent(lblThongTinKhachHang)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSoDienThoai)
								.addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTenKhachHang))
						.addGap(23, 23, 23).addComponent(lblThongTinNhanVien)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblMaNhanVien)
								.addComponent(txtMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTenNhanVien)
								.addComponent(txtTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(19, 19, 19)
						.addComponent(lblChiTietDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
						.addComponent(lbl_txtThanhTienDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(54, 54, 54))
				.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addContainerGap(601, Short.MAX_VALUE)
								.addComponent(lblThanhTienDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(52, 52, 52))));

		pnlHoaDonChinh.add(jPanel1, java.awt.BorderLayout.EAST);

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 680, Short.MAX_VALUE));

		pnlHoaDonChinh.add(jPanel3, java.awt.BorderLayout.CENTER);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(pnlHoaDonChinh, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pnlHoaDonChinh,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}// </editor-fold>

	Border bdFalse = BorderFactory.createLineBorder(Color.red);
	Border bdTrue = BorderFactory.createLineBorder(Color.green);
	private HoaDonThuePhong_DAO hoaDonThanhToan_dao;

	public boolean ValidData() {
		String tienNhan = txtTienNhan.getText().trim();
		if (tienNhan.length() > 0) {
			if (!tienNhan.matches("\\d+")) {
				JOptionPane.showMessageDialog(pnlChinh, "Error: Tiền nhận không hợp lệ ! Vui lòng nhập lại");
				txtTienNhan.requestFocus(true);
				txtTienNhan.setBorder(bdFalse);
				return false;
			} else {
				txtTienNhan.setBorder(bdTrue);
			}
			double TienNhan = Double.parseDouble(tienNhan);
			if (TienNhan < tongTien) {
				JOptionPane.showMessageDialog(pnlChinh,
						"Error: Tiền nhận phải lớn hơn tổng tiền trả ! Vui lòng nhập lại");
				txtTienNhan.requestFocus(true);
				txtTienNhan.setBorder(bdFalse);
				return false;
			} else {
				txtTienNhan.setBorder(bdTrue);
			}
		} else if (tienNhan.length() == 0) {
			JOptionPane.showMessageDialog(pnlChinh, "Error: Vui lòng nhập tiền nhận ! Vui lòng nhập lại");
			txtTienNhan.requestFocus(true);
			txtTienNhan.setBorder(bdFalse);
			return false;
		}
		double TienNhan = Double.parseDouble(tienNhan);
		double tienThua = TienNhan - tongTien;
		String TienThua = String.valueOf(tienThua);
		txtTienThua.setText(TienThua);
		String tp = txtTienPhong.getText();
		Float tphong = Float.parseFloat(tp);
		String tdv = txtTienDichVu.getText();
		Float tdvu = Float.parseFloat(tdv);
		String tn = txtTienNhan.getText();
		Float tnhan = Float.parseFloat(tn);
		String tt = txtTongTien.getText();
		Float ttien = Float.parseFloat(tt);

		HoaDonThanhToan hdtt = new HoaDonThanhToan(hd.getMaHoaDon(), hd.getNgayTaoHoaDon(), tonggio, tphong, tdvu,
				tnhan, ttien, new HoaDonThuePhong(hd.getMaHoaDon()));
		ArrayList<HoaDonThanhToan> dsHDTT = new ArrayList<>();
		HoaDonThanhToan_DAO HoaDonThanhToan_dao = new HoaDonThanhToan_DAO();
		dsHDTT = HoaDonThanhToan_dao.getAllHoaDonThanhToan();
		Boolean ok = true;
		for (HoaDonThanhToan hdttoan : dsHDTT)
			if (hd.getMaHoaDon().equals(hdttoan.getMaHoaDonTT())) {
				ok = false;
				break;
			}
		if (ok == false) {
			JOptionPane.showMessageDialog(pnlChinh, "Hoá đơn đã được thanh toán!");
		} else {
			HoaDonThanhToan_dao.addHoaDon(hdtt);
			DonDatPhong Ddp = donDatPhong_dao.getDonDatPhongtheoMaPH(maPH);
			PhongHat ph = phong_dao.getPhongtheoMa(hd.getPhongHat().getMaPhongHat());
			PhongHat phongHat = new PhongHat();
			int SLDDP = donDatPhong_dao.getSoLuongMaDDPTheoMaPhong(maPH);
			if (SLDDP  == 1 ) {
				phongHat = new PhongHat(ph.getMaPhongHat(), ph.getTenPhongHat(), ph.getLoaiPhong(),
						ph.getGiaPhong(), "Phòng trống", ph.getSucChua());
			} else {
				phongHat = new PhongHat(ph.getMaPhongHat(), ph.getTenPhongHat(), ph.getLoaiPhong(),
						ph.getGiaPhong(), "Phòng chờ", ph.getSucChua());
			}
			phong_dao.capNhat_PhongHat(phongHat);
			System.out.println(SLDDP);
			donDatPhong_dao.deletePhieuDatPhong(Ddp.getMaDonDatPhong());
			JOptionPane.showMessageDialog(pnlChinh, "Thanh Toán Thành Công!");
			ptp.setVisible(false);
			pnlChinh.setVisible(true);
			pnlHoaDonChinh.setVisible(false);
			updateHA();

		}

		return true;
	}

	
	private void txtNgayTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtGiaRaActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtGioVaoActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtPhongActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtTienPhongActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtTienDichVuActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtTienNhanActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtTienThuaActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtTenNhanVienActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnInHoaDon;
	private javax.swing.JButton btnThanhToan;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblChiTietDichVu;
	private javax.swing.JLabel lblGioRa;
	private javax.swing.JLabel lblGioVao;
	private javax.swing.JLabel lblMaHoaDon;
	private javax.swing.JLabel lblMaNhanVien;
	private javax.swing.JLabel lblNgayTaoHoaDon;
	private javax.swing.JLabel lblPhong;
	private javax.swing.JLabel lblSoDienThoai;
	private javax.swing.JLabel lblTenKhachHang;
	private javax.swing.JLabel lblTenNhanVien;
	private javax.swing.JLabel lblThanhTienDichVu;
	private javax.swing.JLabel lblThanhToanHoaDon;
	private javax.swing.JLabel lblThongTinHoaDon;
	private javax.swing.JLabel lblThongTinKhachHang;
	private javax.swing.JLabel lblThongTinNhanVien;
	private javax.swing.JLabel lblTienDichVu;
	private javax.swing.JLabel lblTienNhan;
	private javax.swing.JLabel lblTienPhong;
	private javax.swing.JLabel lblTienThua;
	private javax.swing.JLabel lblTongTien;
	private javax.swing.JLabel lblTongTienThanhToan;
	private javax.swing.JLabel lbl_txtThanhTienDichVu;
	protected javax.swing.JPanel pnlHoaDonChinh;
	private javax.swing.JTable tbl_ChiTietDichVu;
	private javax.swing.JLabel txtGiaRa;
	private javax.swing.JLabel txtGioVao;
	private javax.swing.JLabel txtMaHoaDon;
	private javax.swing.JLabel txtMaNhanVien;
	private javax.swing.JLabel txtNgayTaoHoaDon;
	private javax.swing.JLabel txtPhong;
	private javax.swing.JLabel txtSDT;
	private javax.swing.JLabel txtTenKhachHang;
	private javax.swing.JLabel txtTenNhanVien;
	private javax.swing.JLabel txtTienDichVu;
	private javax.swing.JTextField txtTienNhan;
	private javax.swing.JLabel txtTienPhong;
	private javax.swing.JLabel txtTienThua;
	private javax.swing.JLabel txtTongTien;
	private int tonggio;

}
