package com.gxz.mymath.determinant.test;

import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Writer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggerFactory;

import com.gxz.mymath.arithmetic.leastsquare.LinerLeastSquare;

/**
 * 
 * @类型功能描述：
 * @作者 郭晓忠(guoxiaozhong)
 * @修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class LeastSquareTestWin implements ActionListener {

	private JFrame frame;
	private JTextField dataFiletextField;
	private JTextArea logTextArea;
	private JButton selectDataFileBtn;
	private JFileChooser jFileChooser;
	private File dataFile;
	private String dataFilePath;
	private Logger logger;
	static Logger log = Logger.getRootLogger();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeastSquareTestWin window = new LeastSquareTestWin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LeastSquareTestWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("最小二乘测试程序");
		frame.setBounds(100, 100, 839, 529);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		logTextArea = new JTextArea();
		logTextArea.setBounds(10, 101, 803, 226);
		logTextArea.setLineWrap(true);
		// frame.getContentPane().add(logTextArea);
		// frame.getContentPane().add(new JScrollPane(logTextArea));

		dataFiletextField = new JTextField();
		dataFiletextField.setBounds(10, 36, 423, 21);
		frame.getContentPane().add(dataFiletextField);
		dataFiletextField.setColumns(10);

		selectDataFileBtn = new JButton("选择测量数据");
		selectDataFileBtn.setBounds(443, 35, 105, 23);
		frame.getContentPane().add(selectDataFileBtn);
		selectDataFileBtn.setActionCommand("selectDataFile");
		selectDataFileBtn.addActionListener(this);

		JLabel label = new JLabel("运算日志");
		label.setBounds(31, 76, 54, 15);
		frame.getContentPane().add(label);

		JScrollPane scrollPane = new JScrollPane(logTextArea);
		scrollPane.setBounds(10, 123, 787, 358);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("selectDataFile")) {
			jFileChooser = new JFileChooser();
			jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int state = jFileChooser.showDialog(new Label(), "选择数据文件");
			dataFile = jFileChooser.getSelectedFile();
			this.dataFiletextField.setText(dataFilePath);
			if (dataFile.isFile()) {
				dataFilePath = dataFile.getAbsolutePath();
				this.dataFiletextField.setText(dataFilePath);
				this.logTextArea.setText("");
				try {
					this.initlog();
					this.caculateLestSquare(dataFilePath);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}

	/**
	 * 
	 * @方法功能描述：初始化日志
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月15日 下午1:15:53</p>
	 * @throws IOException
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	private void initlog() throws IOException {
		logger = Logger.getLogger(LeastSquareTestWin.class);
		PropertyConfigurator.configure("log4j1.properties");
		log.addAppender(new JTextAreaAppender(this.logTextArea));
	}

	private void caculateLestSquare(String filepath) throws Exception {
		LinerLeastSquare leastSquare = new LinerLeastSquare();
		logger.info("开始运算");
		leastSquare.getDataFromTxt(filepath, ",");
		leastSquare.CaculateparameterMatrix();
		leastSquare.CaculateResidualMatrix();
		leastSquare.CaculateAccuracy();
		leastSquare.CaculateDiagonalcoefficientsmMatrix();
		leastSquare.CaculateStandardDivisionMatrix();
	}
}
