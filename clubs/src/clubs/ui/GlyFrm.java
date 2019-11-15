package clubs.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import clubs.utils.DbConn;

public class GlyFrm implements ActionListener {
	String cksql="select * from admin";
	JFrame w;
	JDialog jdl=new JDialog(w,"�û���Ϣ����",true);
	//��ʾ��Ϣ�ı�
	String[] scols={"id","�˺�","����"};
	//���ñ��������ʾ
	DefaultTableModel model = new DefaultTableModel(null, scols);
	//������ʾ���
	JTable s = new JTable(model);
	//����
    JScrollPane pane = new JScrollPane(s);
	//��ѯģ��
	JLabel checkcnolabel = new JLabel("�˺ţ�");
	JTextField checkcnotext=new JTextField();
	JButton checkbt = new JButton("��ѯ");
	JLabel no=new JLabel("");
	//����ģ��
	JLabel mgsnolb=new JLabel("�˺ţ�");
	JTextField mgsnotxt=new JTextField();
	JLabel mgpasswordlb=new JLabel("�����");
	JTextField mgpasswordtxt=new JTextField();
	JLabel ID = new JLabel("");
	JPanel jpl=new JPanel(new BorderLayout());
	JButton addbt = new JButton("���");
	JButton updbt = new JButton("�޸�");
	JButton delbt = new JButton("ɾ��");
	JButton resbt = new JButton("����");
	GlyFrm(JFrame jf){
		w=jf;
		jdl.setSize(500,400);		
		jdl.setLocation(200, 200);
		jdl.setLayout(null);
		jdl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jdl.setResizable(false);
		//����
		//��ѯ���ֲ���
		jdl.add(checkcnolabel);
		jdl.add(checkcnotext);
		jdl.add(checkbt);
		jdl.add(no);
		no.setBounds(0,0,0,0);
		checkcnolabel.setBounds(80,10,70,20);
		checkcnotext.setBounds(155,10,100,20);
		checkbt.setBounds(260,10,60,20);
		//�����б�		
		jdl.add(pane);
		pane.setBounds(20,40,460,200);
		jpl.setBounds(20, 250, 460, 120) ;
		jdl.add(jpl);
		jpl.setBorder(BorderFactory.createTitledBorder("�༭��"));
		jpl.setOpaque(false);
		//���ݹ����ֲ���
		jpl.add(mgsnolb);
		mgsnolb.setBounds(20, 30, 50, 20);
		jpl.add(mgsnotxt);
		mgsnotxt.setBounds(60, 30, 120, 20);
		jpl.add(mgpasswordlb);
		mgpasswordlb.setBounds(230, 30, 50, 20);
		jpl.add(mgpasswordtxt);
		mgpasswordtxt.setBounds(290, 30, 120, 20);
		jpl.add(addbt);
		addbt.setBounds(50, 60, 60, 20);
		jpl.add(updbt);
		updbt.setBounds(120, 60, 60, 20);
		jpl.add(delbt);
		delbt.setBounds(190, 60, 60, 20);
		jpl.add(resbt);
		resbt.setBounds(260, 60, 60, 20);
		jpl.add(ID);
		ID.setBounds(0,0,0,0);
		ID.setVisible(false);
		checkbt.addActionListener(new CheckAction());
		//���ñ������ѡ��
		s.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//�����б����������
		s.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
            	//��ȡ���ѡ����һ�е�����
              int r= s.getSelectedRow();
              //��ȡÿһ�е�ֵ���������Ҳ�༭��
              Object value= s.getValueAt(r, 0);
              ID.setText(value.toString());
              mgsnotxt.setText(s.getValueAt(r, 1).toString());
              mgpasswordtxt.setText(s.getValueAt(r, 2).toString());
            }
        });
		//Ϊ��ɾ�����ù�����Ӽ����¼�
		addbt.addActionListener(new AddAction());
		updbt.addActionListener(new UpdAction());
		delbt.addActionListener(new DelAction());
		resbt.addActionListener(new ResAction());
		getData(cksql);
		jdl.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	//��ѯ����ʵ��
	class CheckAction implements ActionListener{		
		ResultSet rs=null;		
		@Override
		public void actionPerformed(ActionEvent e) {
			no.setText(checkcnotext.getText());
			// TODO Auto-generated method coub			
			//��ȡ���ݣ��󶨵�������
			getData(cksql);
		}
	}
	//��ӹ���ʵ��
	class AddAction implements ActionListener{
		String sql="";
		ResultSet rs=null;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method coub
			String sno=mgsnotxt.getText().trim();
			String password=mgpasswordtxt.getText().trim();
			if("".equals(sno)||"".equals(password)){
				JOptionPane.showMessageDialog(null,"�û���Ϣ���ꔼ!", "ϵͳ��Ϣ", JOptionPane.WARNING_MESSAGE);	
				return;
			}
			DbConn db=new DbConn();
			if(db.checkTrue("select id from admin where uname='"+sno+"'")){
				JOptionPane.showMessageDialog(null,"�˺��Ѵ�]!", "ϵͳ��Ϣ", JOptionPane.WARNING_MESSAGE);	
				return;
			}
			int x=db.executeUpdate("insert into admin(uname,upwd) values('"+sno+"','"+password+"')");
			if(x>0){
				JOptionPane.showMessageDialog(null,"��ӳɹ�.", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);	
			}else{
				JOptionPane.showMessageDialog(null,"ϵͳ����01", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);	
			}
			//��ȡ���ݣ��󶨵�������
			getData(cksql);
			//����
			rst();
		}
	}
	//���ù���ʵ��
	class ResAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method coub
			rst();
		}
	}
	//ɾ������ʵ��
	class DelAction implements ActionListener{
		ResultSet rs=null;	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method coub
			String id=ID.getText();
			DbConn db=new DbConn();
			if("".equals(id)||!db.checkTrue("select id from admin where id="+id)){
				JOptionPane.showMessageDialog(null,"��?���Ҫɾ������Ϣ!", "ϵͳ��Ϣ", JOptionPane.WARNING_MESSAGE);	
				return;
			}
			if(id.equals(MainFrm.uid)){
				JOptionPane.showMessageDialog(null,"��ǰ��¼�û�����ɾ��!", "ϵͳ��Ϣ", JOptionPane.WARNING_MESSAGE);	
				return;
			}
			int x=db.executeUpdate("delete from admin where id="+id);
			if(x>0){
				JOptionPane.showMessageDialog(null,"ɾ���ɹ�.", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);	
				//��ȡ���ݣ��󶨵�������
				getData(cksql);
				//����
				rst();
			}else{
				JOptionPane.showMessageDialog(null,"ϵͳ����01", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);	
			}			
		}
	}
	//�޸Ĺ���ʵ��
	class UpdAction implements ActionListener{
		ResultSet rs=null;	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method coub
			String sno=mgsnotxt.getText().trim();
			String password=mgpasswordtxt.getText().trim();
			String id=ID.getText();
			if("".equals(id)){
				JOptionPane.showMessageDialog(null,"��?���Ҫ�޸ĵ���Ϣ!", "ϵͳ��Ϣ", JOptionPane.WARNING_MESSAGE);	
				return;
			}
			if("".equals(sno)||"".equals(password)){
				JOptionPane.showMessageDialog(null,"�û���Ϣ���ꔼ!", "ϵͳ��Ϣ", JOptionPane.WARNING_MESSAGE);	
				return;
			}
			DbConn db=new DbConn();
			rs=db.executeQuery("select id from admin where uname='"+sno+"'");
			try {
				while(rs.next()){
					String idi=String.valueOf(rs.getInt("id"));
					if(!id.equals(idi)){
						JOptionPane.showMessageDialog(null,"�˺��Ѵ�]!", "ϵͳ��Ϣ", JOptionPane.WARNING_MESSAGE);	
						return;
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int x=db.executeUpdate("update admin set uname='"+sno+"',upwd='"+password+"' where id="+id);
			if(x>0){
				JOptionPane.showMessageDialog(null,"�޸ĳɹ�.", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);	
				//��ȡ���ݣ��󶨵�������
				getData(cksql);
				//����
				rst();
			}else{
				JOptionPane.showMessageDialog(null,"ϵͳ����01", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);	
			}
		}
	}
	//��ȡ��ѯ������󶨵�������
	public void getData(String sql){
		//�����ѯ�н����������ԏ�����
		if(model.getRowCount()>0){
			for(int i=model.getRowCount()-1;i>=0;i--){
				model.removeRow(i);
			}
		}
		DbConn db=new DbConn();
		if(!"".equals(no.getText())){
			sql+=" where uname='"+no.getText().trim()+"'";
		}
		ResultSet rs=db.executeQuery(sql);
		if(rs!=null){
			try {
				while(rs.next()){
					model.addRow(new String[]{String.valueOf(rs.getInt("ID")),rs.getString("uname"),rs.getString("upwd")});
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(model.getColumnCount()>0){
			DefaultTableColumnModel dcm = (DefaultTableColumnModel)s .getColumnModel();//��ȡ��ģ��  
			dcm.getColumn(0).setMinWidth(0); 
			dcm.getColumn(0).setMaxWidth(0);
		}
	}
	public void rst(){
		ID.setText("");
		mgsnotxt.setText("");
		mgpasswordtxt.setText("");
	}
}
