/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;

/**
 *
 * @author PC
 */
public class JFrame_DoiMatKhau extends javax.swing.JFrame {

    private NhanVien nv;
	private NhanVien_DAO nhanVien_dao;
	private TaiKhoan tk;
	private TaiKhoan_DAO taiKhoan_dao;
	private JFrame_DangNhap dangNhap;
	/**
     * Creates new form JFrame_DoiMatKhau
     */
    public JFrame_DoiMatKhau(TaiKhoan tk) {
    	setResizable(false);
    	this.tk = tk;
    	try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	nhanVien_dao = new NhanVien_DAO();
    	taiKhoan_dao = new TaiKhoan_DAO();
    	dangNhap = new JFrame_DangNhap();
    	setUndecorated(true); // tắt thanh tiêu đề
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        addControls();
       
    }
    
    // thao tác đổi mật khẩu
    private void addControls() {
		btnHuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnXacNhan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String mkCu = String.valueOf(jPasswordFieldMKCu.getPassword());
				String mkMoi = String.valueOf(jPasswordFieldMKMoi.getPassword());
				String mkXacNhan = String.valueOf(jPasswordFieldXacNhan.getPassword());
				tk = taiKhoan_dao.getTaiKHoanTheoTenDN(dangNhap.txtUser.getText().trim());
				if (!mkMoi.equals(mkXacNhan)) {

					JOptionPane.showMessageDialog(null, "Mật khẩu xác nhận không trùng!!!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else if (!mkCu.equals(tk.getMatKhau())) {
					JOptionPane.showMessageDialog(null, "Mật khẩu cũ không chính xác!!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!!");
						tk.setMatKhau(mkMoi);
						taiKhoan_dao.DoiMK(tk);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					setVisible(false);
				}
			}
		});
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelTieuDe = new javax.swing.JLabel();
        jLabelMatKhauCu = new javax.swing.JLabel();
        jLabelMatKhauMoi = new javax.swing.JLabel();
        jLabelMatKhauMoi1 = new javax.swing.JLabel();
        jPasswordFieldXacNhan = new javax.swing.JPasswordField();
        jPasswordFieldMKMoi = new javax.swing.JPasswordField();
        jPasswordFieldMKCu = new javax.swing.JPasswordField();
        btnXacNhan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(162, 74, 74));

        jLabelTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTieuDe.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTieuDe.setText("Đổi mật khẩu");

        jLabelMatKhauCu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelMatKhauCu.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMatKhauCu.setText("Mật khẩu cũ:");

        jLabelMatKhauMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelMatKhauMoi.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMatKhauMoi.setText("Mật khẩu mới:");

        jLabelMatKhauMoi1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelMatKhauMoi1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMatKhauMoi1.setText("Xác nhận mật khẩu mới:");

        jPasswordFieldXacNhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPasswordFieldMKMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPasswordFieldMKCu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnXacNhan.setBackground(new java.awt.Color(162, 74, 74));
        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        btnXacNhan.setIcon(new javax.swing.ImageIcon("item/confirm25.png")); // NOI18N
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), java.awt.Color.black));


        btnHuy.setBackground(new java.awt.Color(162, 74, 74));
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setIcon(new javax.swing.ImageIcon("item/cancel25.png")); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), java.awt.Color.black));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(284, 284, 284)
                        .addComponent(jLabelTieuDe))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(144, 144, 144)
                            .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(74, 74, 74)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelMatKhauMoi1)
                                .addComponent(jLabelMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPasswordFieldMKCu, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPasswordFieldMKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPasswordFieldXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabelTieuDe)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldMKCu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldMKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMatKhauMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JLabel jLabelMatKhauCu;
    private javax.swing.JLabel jLabelMatKhauMoi;
    private javax.swing.JLabel jLabelMatKhauMoi1;
    private javax.swing.JLabel jLabelTieuDe;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordFieldMKCu;
    private javax.swing.JPasswordField jPasswordFieldMKMoi;
    private javax.swing.JPasswordField jPasswordFieldXacNhan;
    // End of variables declaration//GEN-END:variables
}
