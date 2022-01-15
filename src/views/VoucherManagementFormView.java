package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import handlers.ProductHandler;
import handlers.VoucherHandler;
import models.Product;
import models.Voucher;

public class VoucherManagementFormView extends JFrame implements ActionListener{

	private JLabel idLbl, discountLbl;
	private JTextField idTxt, discountTxt;
	private JButton generateBtn, deleteBtn;
	private JTable table;
	private DefaultTableModel dtm;
	private JPanel contentPnl, formPnl, buttonPnl;
	private JScrollPane tableScroll;
	Object[] columns = {"ID", "Discount", "Status"};
	
	private void init() {
		makeTable();
		makeForm();
		makeButton();
		
		contentPnl = new JPanel(new BorderLayout());
		contentPnl.add(BorderLayout.NORTH, tableScroll);
		contentPnl.add(BorderLayout.CENTER, formPnl);
		contentPnl.add(BorderLayout.SOUTH, buttonPnl);
		add(contentPnl);
	}
	
	private void makeTable() {
		dtm = new DefaultTableModel(columns, 0);
		table = new JTable(dtm);
		tableScroll = new JScrollPane(table);
		tableScroll.setPreferredSize(new Dimension(300, 300));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int id = (int)table.getValueAt(row, 0);
				int discount = (int)table.getValueAt(row, 1);
				
				idTxt.setText(id + "");
				discountTxt.setText(discount + "");
			}
		});
	}

	private void makeForm() {
		idLbl = new JLabel("ID");
		discountLbl = new JLabel("Discount");
		
		idTxt = new JTextField();
		discountTxt = new JTextField();
		
		formPnl = new JPanel(new GridLayout(2, 2));
		formPnl.add(idLbl);
		formPnl.add(idTxt);
		formPnl.add(discountLbl);
		formPnl.add(discountTxt);
	}

	private void makeButton() {
		generateBtn = new JButton("Generate");
		deleteBtn = new JButton("Delete");
		
		generateBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		
		buttonPnl = new JPanel(new GridLayout(1, 2));
		buttonPnl.add(generateBtn);
		buttonPnl.add(deleteBtn);
	}

	private void loadData() {
		dtm = new DefaultTableModel(columns, 0);
		List<Voucher> vouchers = VoucherHandler.getInstance().getAllVouchers();
		for (Voucher voucher : vouchers) {
			int id = voucher.getVoucherID();
			int discount = voucher.getDiscount();
			String status = voucher.getStatus();
			dtm.addRow(new Object[] {id, discount, status});
		}
		table.setModel(dtm);
	}

	public VoucherManagementFormView() {
		init();
		setSize(500,500);
		setTitle("Coffee Vibes - Voucher Management");
		loadData();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == generateBtn) {
			generateVoucher();
		}
		else if(e.getSource() == deleteBtn) {
			deleteVoucher();
		}
	}

	private void generateVoucher() {
		String discount = discountTxt.getText();

		Voucher v = VoucherHandler.getInstance().insertVoucher(discount);
		JOptionPane.showMessageDialog(this, VoucherHandler.getInstance().getMessage());
		if(v != null) {
			loadData();
		}
	}

	private void deleteVoucher() {
		String id = idTxt.getText();
		
		int dialog = JOptionPane.showConfirmDialog(this, "Confirm Delete?");
		if(dialog == JOptionPane.YES_OPTION) {
			boolean v = VoucherHandler.getInstance().deleteVoucher(id);
			JOptionPane.showMessageDialog(this, VoucherHandler.getInstance().getMessage());
			if(v) {
				loadData();
			}
			
		}
	}
}
