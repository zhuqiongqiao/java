package ѧ����Ϣ����ϵͳ;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Menu extends JFrame implements ActionListener {
    Addstu ����ѧ������;
    Updatastu �޸�ѧ������;
    Delstu ɾ��ѧ������;
    AddC ���ӿγ̽���;
    DelC ɾ���γ̽���;
    UpdateC �޸Ŀγ̽���;
    AddSC ����ѡ�ν���;
    DelSC ɾ��ѡ�ν���;
    UpdateSC �޸�ѡ�ν���;
    Selstu ѧ����ѯ����;
    JPanel pCenter;
    CardLayout card = null;
    JLabel label = null;
    JMenuBar mb = new JMenuBar();//�˵���
    JMenu m1 = new JMenu("ѧ������");
    JMenuItem add1 = new JMenuItem("���   ");
    JMenuItem updata1 = new JMenuItem("����   ");
    JMenuItem delete1 = new JMenuItem("ɾ��   ");
    JMenu m2 = new JMenu("�γ̹���");
    JMenuItem add2 = new JMenuItem("���  ");
    JMenuItem updata2 = new JMenuItem("����   ");
    JMenuItem delete2 = new JMenuItem("ɾ��  ");
    JMenu m3 = new JMenu("ѡ�ι���");
    JMenuItem add3 = new JMenuItem("���   ");
    JMenuItem updata3 = new JMenuItem("����  ");
    JMenuItem delete3 = new JMenuItem("ɾ��   ");
    JMenu m4 = new JMenu("��ѯ����");
    JMenuItem ѧ����ѯ = new JMenuItem("��ѯ��Ϣ   ");
    JMenuItem m5 = new JMenuItem("ϵͳ�˳�");
    Font t = new Font("����", Font.PLAIN, 20);
  

    public Menu() {
        setTitle("1904070271������");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("�����������:   " + e);
        }


        JPanel imPanel=(JPanel) this.getContentPane();//ע������������ǿתΪJPanel�ſ���ʵ�����������͸��
        imPanel.setOpaque(false);//�����������Ϊ͸��


        addMenu1();
        addMenu2();
        addMenu3();
        addMenu4();
        addJMenuBar();
        setJMenuBar(mb);



//����¼�
        add1.addActionListener(this);
        updata1.addActionListener(this);
        delete1.addActionListener(this);
        m5.addActionListener(this);
        add2.addActionListener(this);
        delete2.addActionListener(this);
        updata2.addActionListener(this);
        add3.addActionListener(this);
        delete3.addActionListener(this);
        updata3.addActionListener(this);
        ѧ����ѯ.addActionListener(this);



        card = new CardLayout();
        pCenter = new JPanel();
        pCenter.setLayout(card);



        ����ѧ������ = new Addstu();
        �޸�ѧ������ = new Updatastu();
        ɾ��ѧ������ = new Delstu();
        ���ӿγ̽��� = new AddC();
        ɾ���γ̽��� = new DelC();
        �޸Ŀγ̽��� = new UpdateC();
        ����ѡ�ν��� = new AddSC();
        ɾ��ѡ�ν��� = new DelSC();
        �޸�ѡ�ν��� = new UpdateSC();
        ѧ����ѯ���� = new Selstu();
      
        ImageIcon ico=new ImageIcon("C:\\Users\\������\\Desktop\\����.jpg");      

        JButton btn=new JButton("ѧ����Ϣ����ϵͳ",ico);
        btn.setBounds(400,1500, ico.getIconWidth(), ico.getIconHeight());
        btn.setFocusPainted(false);//���ó�͸��
        btn.setBorderPainted(false);//�۽���Ӱ����ʾ
        btn.setContentAreaFilled(false);//��߿���ʾ��

        btn.setFont(new Font("����", Font.PLAIN, 50));
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        
        btn.setForeground(Color.white);//������ɫ

        pCenter.add("��ӭ����", btn);
        pCenter.add("����ѧ������", ����ѧ������);
        pCenter.add("�޸�ѧ������", �޸�ѧ������);
        pCenter.add("ɾ��ѧ������", ɾ��ѧ������);

        pCenter.add("���ӿγ̽���", ���ӿγ̽���);
        pCenter.add("ɾ���γ̽���", ɾ���γ̽���);
        pCenter.add("�޸Ŀγ̽���", �޸Ŀγ̽���);
        pCenter.add("����ѡ�ν���", ����ѡ�ν���);
        pCenter.add("ɾ��ѡ�ν���", ɾ��ѡ�ν���);
        pCenter.add("�޸�ѡ�ν���", �޸�ѡ�ν���);
        pCenter.add("ѧ����ѯ����", ѧ����ѯ����);
        add(pCenter, BorderLayout.CENTER);



        setBounds(500, 180, 1000, 700);
        setVisible(true);
//        validate();
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {//�رճ���ʱ�Ĳ���
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        validate();
  

    }


    private void addJMenuBar() {
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);
        mb.add(m5);
    }

    private void addMenu4() {
        m4.add(ѧ����ѯ);
        m4.setFont(t);
        m5.setFont(t);
    }

    private void addMenu3() {
        m3.add(add3);
        m3.add(updata3);
        m3.add(delete3);
        m3.setFont(t);
    }

    private void addMenu2() {//���˵����뵽�˵�����
        m2.add(add2);
        m2.add(updata2);
        m2.add(delete2);
        m2.setFont(t);
    }

    private void addMenu1() {
        m1.add(add1);
        m1.add(updata1);
        m1.add(delete1);
        m1.setFont(t);//����
    }


    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == m5) {
            System.exit(0);
        } else {
            if (obj == add1) {
//                setLayout(new FlowLayout());
//                setLayout(null);
                card.show(pCenter, "����ѧ������");
//                
            } else {
                if (obj == updata1) {
                    card.show(pCenter, "�޸�ѧ������");
                } else {
                    if (obj == delete1) {
                        card.show(pCenter, "ɾ��ѧ������");
                    } else {
                        if (obj == add2) {
                            card.show(pCenter, "���ӿγ̽���");
                        } else {
                            if (obj == delete2) {
                                card.show(pCenter, "ɾ���γ̽���");
                            } else {
                                if (obj == updata2) {
                                    card.show(pCenter, "�޸Ŀγ̽���");
                                } else {
                                    if (obj == add3) {
                                        card.show(pCenter, "����ѡ�ν���");
                                    } else {
                                        if (obj == delete3) {
                                            card.show(pCenter, "ɾ��ѡ�ν���");
                                        } else {
                                            if (obj == updata3) {
                                                card.show(pCenter, "�޸�ѡ�ν���");
                                            } else {
                                                if (obj == ѧ����ѯ) {
                                                    card.show(pCenter, "ѧ����ѯ����");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Menu();
    }
}

