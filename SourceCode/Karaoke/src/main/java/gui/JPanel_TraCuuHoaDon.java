/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.undo.AbstractUndoableEdit;

import dao.HoaDonThanhToan_DAO;
import entity.HoaDonThanhToan;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author tranvanloi
 */
public class JPanel_TraCuuHoaDon extends javax.swing.JPanel {

    private HoaDonThanhToan_DAO hoaDonThanhToan_dao;
    private ArrayList<HoaDonThanhToan> listHoaDonThanhToan;
	private DefaultTableModel model_DanhSachHoaDon;
	/**
     * Creates new form Jpanel_TraCuuHoaDon
     */
    public JPanel_TraCuuHoaDon() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblThongTinHoaDon = new javax.swing.JLabel();
        pnlThongTinTraCuu = new javax.swing.JPanel();
        lblTenPhong = new javax.swing.JLabel();
        lblTenKhachHang = new javax.swing.JLabel();
        txtTenPhong = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        lblDanhSachHoaDon = new javax.swing.JLabel();
        pnlDanhSachHoaDon = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DanhSachHoaDon = new javax.swing.JTable();
        btnTimKiem = new javax.swing.JButton();
        btnLamLai = new javax.swing.JButton();
        hoaDonThanhToan_dao = new HoaDonThanhToan_DAO();

        setPreferredSize(new java.awt.Dimension(1250, 755));

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 755));

        lblThongTinHoaDon.setFont(new java.awt.Font("ITF Devanagari", 0, 18)); // NOI18N
        lblThongTinHoaDon.setText("Thông tin tra cứu");

        pnlThongTinTraCuu.setBackground(new java.awt.Color(204, 204, 204));
        pnlThongTinTraCuu.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlThongTinTraCuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlThongTinTraCuuMouseExited(evt);
            }
        });

        lblTenPhong.setFont(new java.awt.Font("ITF Devanagari", 1, 18)); // NOI18N
        lblTenPhong.setText("Tên phòng :");

        lblTenKhachHang.setFont(new java.awt.Font("ITF Devanagari", 1, 18)); // NOI18N
        lblTenKhachHang.setText("Tên Khách Hàng :");

        txtTenPhong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtTenKhachHang.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnlThongTinTraCuuLayout = new javax.swing.GroupLayout(pnlThongTinTraCuu);
        pnlThongTinTraCuuLayout.setHorizontalGroup(
        	pnlThongTinTraCuuLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnlThongTinTraCuuLayout.createSequentialGroup()
        			.addGap(65)
        			.addGroup(pnlThongTinTraCuuLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblTenPhong, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblTenKhachHang, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
        			.addGroup(pnlThongTinTraCuuLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(pnlThongTinTraCuuLayout.createSequentialGroup()
        					.addGap(17)
        					.addComponent(txtTenPhong, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
        				.addGroup(pnlThongTinTraCuuLayout.createSequentialGroup()
        					.addGap(18)
        					.addComponent(txtTenKhachHang, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(591, Short.MAX_VALUE))
        );
        pnlThongTinTraCuuLayout.setVerticalGroup(
        	pnlThongTinTraCuuLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnlThongTinTraCuuLayout.createSequentialGroup()
        			.addGap(26)
        			.addGroup(pnlThongTinTraCuuLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblTenPhong)
        				.addComponent(txtTenPhong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(pnlThongTinTraCuuLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblTenKhachHang)
        				.addComponent(txtTenKhachHang, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(44, Short.MAX_VALUE))
        );
        pnlThongTinTraCuu.setLayout(pnlThongTinTraCuuLayout);

        lblDanhSachHoaDon.setFont(new java.awt.Font("ITF Devanagari", 0, 18)); // NOI18N
        lblDanhSachHoaDon.setText("Danh sách hoá đơn");

        pnlDanhSachHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        pnlDanhSachHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlDanhSachHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlDanhSachHoaDonMouseExited(evt);
            }
        });

		tbl_DanhSachHoaDon.setModel(model_DanhSachHoaDon =  new javax.swing.table.DefaultTableModel(
            new Object [][] {
               
            },
            new String [] {
                "Tên Khách Hàng", "Tên Phòng", "Giờ Vào", "Giờ ra", "Thành tiền", 
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_DanhSachHoaDon);
        listHoaDonThanhToan = hoaDonThanhToan_dao.getAllHoaDonThanhToan();
        for (HoaDonThanhToan hdtt : listHoaDonThanhToan)
        {
        	model_DanhSachHoaDon.addRow(new Object[] {hdtt.getHoaDonThuePhong().getKhachHang().getTenKhachHang(),hdtt.getHoaDonThuePhong().getPhongHat().getTenPhongHat(),
        			hdtt.getHoaDonThuePhong().getGioVao(),hdtt.getHoaDonThuePhong().getGioRa(),hdtt.getTongTien()});
        }

        javax.swing.GroupLayout pnlDanhSachHoaDonLayout = new javax.swing.GroupLayout(pnlDanhSachHoaDon);
        pnlDanhSachHoaDonLayout.setHorizontalGroup(
        	pnlDanhSachHoaDonLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnlDanhSachHoaDonLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
        			.addContainerGap())
        );
        pnlDanhSachHoaDonLayout.setVerticalGroup(
        	pnlDanhSachHoaDonLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnlDanhSachHoaDonLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(48, Short.MAX_VALUE))
        );
        pnlDanhSachHoaDon.setLayout(pnlDanhSachHoaDonLayout);

        btnTimKiem.setBackground(new java.awt.Color(162, 74, 74));
        btnTimKiem.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setIcon(new javax.swing.ImageIcon("item/search25.png")); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tenPhong = txtTenPhong.getText();
				String tenKhachHang = txtTenKhachHang.getText();
				
				if (tenPhong.equals("") && (tenKhachHang.equals(""))) {
					model_DanhSachHoaDon.setRowCount(0);
					listHoaDonThanhToan = hoaDonThanhToan_dao.getAllHoaDonThanhToan();
			        for (HoaDonThanhToan hdtt : listHoaDonThanhToan)
			        {
			        	model_DanhSachHoaDon.addRow(new Object[] {hdtt.getHoaDonThuePhong().getKhachHang().getTenKhachHang(),hdtt.getHoaDonThuePhong().getPhongHat().getTenPhongHat(),
			        			hdtt.getHoaDonThuePhong().getGioVao(),hdtt.getHoaDonThuePhong().getGioRa(),hdtt.getTongTien()});
			        }
				}else if (tenPhong.equals("")==false && (tenKhachHang.equals(""))){
					model_DanhSachHoaDon.setRowCount(0);
					listHoaDonThanhToan = hoaDonThanhToan_dao.getAllHoaDonThanhToan();
			        for (HoaDonThanhToan hdtt : listHoaDonThanhToan)
			        	if (hdtt.getHoaDonThuePhong().getPhongHat().getTenPhongHat().equals(tenPhong)) {
			        		model_DanhSachHoaDon.addRow(new Object[] {hdtt.getHoaDonThuePhong().getKhachHang().getTenKhachHang(),hdtt.getHoaDonThuePhong().getPhongHat().getTenPhongHat(),
				        			hdtt.getHoaDonThuePhong().getGioVao(),hdtt.getHoaDonThuePhong().getGioRa(),hdtt.getTongTien()});
			        	}
			        
			        
				}else if (tenPhong.equals("") && (tenKhachHang.equals("")==false)) {
					model_DanhSachHoaDon.setRowCount(0);
					listHoaDonThanhToan = hoaDonThanhToan_dao.getAllHoaDonThanhToan();
			        for (HoaDonThanhToan hdtt : listHoaDonThanhToan)
			        	if (hdtt.getHoaDonThuePhong().getKhachHang().getTenKhachHang().equals(tenKhachHang)) {
			        		model_DanhSachHoaDon.addRow(new Object[] {hdtt.getHoaDonThuePhong().getKhachHang().getTenKhachHang(),hdtt.getHoaDonThuePhong().getPhongHat().getTenPhongHat(),
				        			hdtt.getHoaDonThuePhong().getGioVao(),hdtt.getHoaDonThuePhong().getGioRa(),hdtt.getTongTien()});
			        	}
				}else if (tenPhong.equals("")==false && (tenKhachHang.equals("")==false)) {
					model_DanhSachHoaDon.setRowCount(0);
					listHoaDonThanhToan = hoaDonThanhToan_dao.getAllHoaDonThanhToan();
			        for (HoaDonThanhToan hdtt : listHoaDonThanhToan)
			        	if (hdtt.getHoaDonThuePhong().getKhachHang().getTenKhachHang().equals(tenKhachHang)&&hdtt.getHoaDonThuePhong().getPhongHat().getTenPhongHat().equals(tenPhong)) {
			        		model_DanhSachHoaDon.addRow(new Object[] {hdtt.getHoaDonThuePhong().getKhachHang().getTenKhachHang(),hdtt.getHoaDonThuePhong().getPhongHat().getTenPhongHat(),
				        			hdtt.getHoaDonThuePhong().getGioVao(),hdtt.getHoaDonThuePhong().getGioRa(),hdtt.getTongTien()});
			        	}
				}
				
				
			}
		});

        btnLamLai.setBackground(new java.awt.Color(162, 74, 74));
        btnLamLai.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btnLamLai.setForeground(new java.awt.Color(255, 255, 255));
        btnLamLai.setIcon(new javax.swing.ImageIcon("item/refresh25.png")); // NOI18N
        btnLamLai.setText("Làm lại");
        btnLamLai.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLamLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtTenPhong.setText("");
				txtTenKhachHang.setText("");
				
			}
		});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(58)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(lblThongTinHoaDon, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblDanhSachHoaDon, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
        						.addComponent(pnlThongTinTraCuu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(pnlDanhSachHoaDon, GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(350)
        					.addComponent(btnTimKiem, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
        					.addGap(221)
        					.addComponent(btnLamLai, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(66)
        			.addComponent(lblThongTinHoaDon)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(pnlThongTinTraCuu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(43)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnLamLai)
        				.addComponent(btnTimKiem))
        			.addGap(6)
        			.addComponent(lblDanhSachHoaDon)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(pnlDanhSachHoaDon, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel1.setLayout(jPanel1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1262, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pnlThongTinTraCuuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThongTinTraCuuMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlThongTinTraCuuMouseExited

    private void pnlDanhSachHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDanhSachHoaDonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlDanhSachHoaDonMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamLai;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDanhSachHoaDon;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTenPhong;
    private javax.swing.JLabel lblThongTinHoaDon;
    private javax.swing.JPanel pnlDanhSachHoaDon;
    private javax.swing.JPanel pnlThongTinTraCuu;
    private javax.swing.JTable tbl_DanhSachHoaDon;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenPhong;
    // End of variables declaration//GEN-END:variables
}