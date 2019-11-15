package clubs.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class MainFrm implements ActionListener {	
	public static String uid;
	public static String utype;
	public  JFrame w = new JFrame("ѧ�����Ź���ϵͳ");
	private JMenuBar mb=new JMenuBar();
	private JMenu sysmanage = new JMenu("ϵͳ����");
	private JMenuItem updpwd=new JMenuItem("�޸�����");
	private JMenuItem backlogin=new JMenuItem("ע��ϵͳ");
	private JMenu yhgl=new JMenu("�û�����");
	private JMenuItem madmin=new JMenuItem("����Ա��Ϣ�ܬq");
	private JMenuItem mstudents=new JMenuItem("ѧ����Ϣ����");
	private JMenuItem myinfo=new JMenuItem("�ҵ���Ϣ");	
	private JMenu tsgl=new JMenu("���Ź���");
	private JMenuItem mst=new JMenuItem("������Ϣ����");
	private JMenuItem mstzw=new JMenuItem("����ְ�����");
	private JMenuItem mstcy=new JMenuItem("���ų�Ա����");
	private JMenuItem myst=new JMenuItem("�ҵ�����");
	private JMenuItem myhb=new JMenuItem("�ҵĻ��");
	private JMenu jsgl=new JMenu("�����");
	private JMenuItem sthd=new JMenuItem("���Ż����");	
	private JMenuItem myhd=new JMenuItem("��鿴");	
	//���ɳ�ʼ�����
	MainFrm(String usid,String ut){
		uid=usid;
		utype=ut;
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  
		w.setLocation((dim.width - w.getWidth()) / 2-150, (dim.height - w.getHeight()) / 2-150);  				
		w.setSize(600, 500);
		w.setResizable(false);
		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
		JPanel imagePanel = (JPanel) w.getContentPane();  
		imagePanel.setOpaque(false);  
		if(!"�����".equals(utype)){
			sysmanage.add(updpwd);
		}
		sysmanage.add(backlogin);
		if("�����".equals(utype)){
			yhgl.add(madmin);
			yhgl.add(mstudents);
			tsgl.add(mst);
			tsgl.add(mstzw);
			tsgl.add(mstcy);
			jsgl.add(sthd);
		}
		if(!"�����".equals(utype)){
			yhgl.add(myinfo);
			tsgl.add(myst);
			tsgl.add(myhb);
			jsgl.add(myhd);
		}		
		mb.add(yhgl);
		mb.add(tsgl);
		mb.add(jsgl);
		mb.add(sysmanage);
		w.add(mb,BorderLayout.NORTH);
		//���˵���Ӽ���
		madmin.addActionListener(new glygl());
		updpwd.addActionListener(new updpwd());	
		backlogin.addActionListener(new backlog());		
		mstudents.addActionListener(new xsgl());
		mst.addActionListener(new stgl());
		mstzw.addActionListener(new zwgl());
		mstcy.addActionListener(new cygl());
		myinfo.addActionListener(new myinfo());
		myst.addActionListener(new myst());
		myhb.addActionListener(new myhb());
		sthd.addActionListener(new hdgl());
		myhd.addActionListener(new myhd());
		w.setLocation(200, 200);
		w.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	//����Ա��Ϣ�ܬq
	class glygl implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new GlyFrm(w);
		}
	}
	//ѧ����Ϣ����
	class xsgl implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new MstudentsFrm(w);
		}
	}
	//������Ϣ����
	class stgl implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new MclubsFrm(w);
		}
	}
	//ְ�����
	class zwgl implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new MclbjobFrm(w);
		}
	}
	//��Ա��Ϣ����
	class cygl implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new MclbmemberFrm(w);
		}
	}
	//�ҵ���Ϣ
	class myinfo implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new MstuinfoFrm(w);
		}
	}
	//�ҵ�����
	class myst implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new MystFrm(w);
		}
	}
	//�ҵĻ��
	class myhb implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new MyhbFrm(w);
		}
	}
	//���Ϣ����
	class hdgl implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new MactFrm(w);
		}
	}
	//�ҵĻ
	class myhd implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new MyhdFrm(w);
		}
	}
	//�޸�����
	class updpwd implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new UpdpwdFrm(w);
		}
	}
	//ϵͳע��
	class backlog implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			w.dispose();
			new LoginFrm();
		}
	}
}