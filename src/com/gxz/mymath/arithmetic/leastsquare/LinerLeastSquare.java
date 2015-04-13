package com.gxz.mymath.arithmetic.leastsquare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gxz.mymath.matrix.Matrix;

/**
 *                            _ooOoo_
 *                           o8888888o
 *                           88" . "88
 *                           (| -_- |)
 *                           O\  =  /O
 *                        ____/`---'\____
 *                      .'  \\|     |//  `.
 *                     /  \\|||  :  |||//  \
 *                    /  _||||| -:- |||||-  \
 *                    |   | \\\  -  /// |   |
 *                    | \_|  ''\---/''  |   |
 *                    \  .-\__  `-`  ___/-. /
 *                  ___`. .'  /--.--\  `. . __
 *               ."" '<  `.___\_<|>_/___.'  >'"".
 *              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *              \  \ `-.   \_ __\ /__ _/   .-` /  /
 *         ======`-.____`-.___\_____/___.-`____.-'======
 *                            `=---='
 *         ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                        佛祖保佑       永无BUG
 */

/**
 * 
 * @类型功能描述：线性最小二乘法
 * @作者 郭晓忠(guoxiaozhong)
 * @修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class LinerLeastSquare {
	/**
	 * A矩阵
	 */
	private Matrix aMatrix;
	/**
	 * Y矩阵
	 */
	private Matrix yMatrix;
	/**
	 * 所求参数矩阵
	 */
	private Matrix parameterMatrix;
	/**
	 * 残差矩阵
	 */
	private Matrix residualMatrix;
	/**
	 * 精度 残差的标准差,也就是精度
	 */
	private double accuracy;
	/**
	 * 对角线系数矩阵
	 */
	private Matrix diagonalcoefficientsmMatrix;
	/**
	 * 标准差矩阵
	 */
	private Matrix standardDivisionMatrix;

	private List<String[]> alldatasList;
	private int lineparanumber;
	private int datalineNumbers;
	private Logger logger = Logger.getLogger(LinerLeastSquare.class);

	/**
	 * 
	 * @方法功能描述：从TXT文件读取测量数据
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月10日 上午9:05:47</p>
	 * @param txtpath
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void getDataFromTxt(String txtpath, String splitString) {
		File datafFile = new File(txtpath);
		InputStreamReader reader;
		BufferedReader bufferedReader;
		String dataline;
		String[] lineStrings;
		if (datafFile.isFile() && datafFile.exists()) {
			try {
				reader = new InputStreamReader(new FileInputStream(datafFile));
				bufferedReader = new BufferedReader(reader);
				alldatasList = new ArrayList<>();
				while ((dataline = bufferedReader.readLine()) != null) {
					lineStrings = dataline.split(splitString);
					alldatasList.add(lineStrings);
				}
				logger.info("读取到" + alldatasList.size() + "行数据");
				initMatrix();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void initMatrix() {
		this.lineparanumber = this.alldatasList.get(0).length;
		logger.info("每行测量数据包含 " + lineparanumber + "个数据，其中最后一个数据位Y值");
		this.datalineNumbers = this.alldatasList.size();

		this.aMatrix = new Matrix(this.datalineNumbers, this.lineparanumber);

		logger.info("A矩阵为 " + this.datalineNumbers + "*" + this.lineparanumber
				+ "型矩阵");
		this.yMatrix = new Matrix(this.datalineNumbers, 1);
		logger.info("Y矩阵为 " + this.datalineNumbers + "*" + "1  型矩阵");
		this.parameterMatrix = new Matrix(lineparanumber, 1);
		logger.info("参数矩阵为 " + this.datalineNumbers + "*" + "1  型矩阵");

		this.residualMatrix = new Matrix(datalineNumbers, 1);
		logger.info("残差矩阵为 " + this.datalineNumbers + "*" + "1  型矩阵");
		/**
		 * 对角线矩阵实例化
		 */
		this.diagonalcoefficientsmMatrix =
				new Matrix(lineparanumber, lineparanumber);
		logger.info("对角线系数矩阵为 " + lineparanumber + "*" + lineparanumber
				+ " 型矩阵");
		/**
		 * 标准差矩阵实例化
		 */
		this.standardDivisionMatrix = new Matrix(lineparanumber, 1);
		logger.info("标准差矩阵为 " + this.lineparanumber + "*" + "1  型矩阵");
		String[] tempstrStrings;
		for (int i = 0; i < alldatasList.size(); i++) {
			tempstrStrings = alldatasList.get(i);
			for (int j = 0; j < aMatrix.getcolumn(); j++) {
				if (j == 0) {
					aMatrix.set(i, j, 1);
				} else {
					aMatrix.set(i, j, Double.parseDouble(tempstrStrings[j - 1]));
				}

			}
			yMatrix.set(i, 0,
					Double.parseDouble(tempstrStrings[lineparanumber - 1]));
		}
		logger.info("A矩阵为：\n" + aMatrix);
		logger.info("Y矩阵为：\n" + yMatrix);
	}

	/**
	 * 
	 * @方法功能描述：计算参数矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月9日 下午2:58:12</p>
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void CaculateparameterMatrix() throws Exception {
		Matrix ATA =
				Matrix.multipleMatrix(aMatrix.getTransposeMatrix(), aMatrix);
		logger.info("AT*A矩阵结果:\n" + ATA);
		Matrix cinverseMatrix = ATA.getInverse();
		logger.info("C(AT*A矩阵)的逆矩阵：\n" + cinverseMatrix);
		Matrix cinverseAT =
				Matrix.multipleMatrix(cinverseMatrix,
						aMatrix.getTransposeMatrix());
		logger.info("C逆*AT的结果：\n" + cinverseAT);
		this.parameterMatrix = Matrix.multipleMatrix(cinverseAT, yMatrix);
		logger.info("参数矩阵结果：\n" + parameterMatrix);
	}

	/**
	 * 
	 * @方法功能描述：计算残差矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月10日 上午9:04:56</p>
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void CaculateResidualMatrix() throws Exception {
		this.residualMatrix =
				Matrix.MinusMatrix(yMatrix,
						Matrix.multipleMatrix(aMatrix, parameterMatrix));
		logger.info("残差矩阵结果：\n" + residualMatrix);
	}

	/**
	 * 
	 * @方法功能描述：计算精度，残差的标准差
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月10日 上午9:13:46</p>
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void CaculateAccuracy() {
		double vpower = 0;
		for (int i = 0; i < this.residualMatrix.getrow(); i++) {
			vpower += (Math.pow(this.residualMatrix.get(i, 0), 2));
		}
		this.accuracy =
				Math.sqrt(vpower / (aMatrix.getrow() - aMatrix.getcolumn()));
		logger.info("根据残差矩阵计算得到的精度即标准差为：" + accuracy);
	}

	/**
	 * 
	 * @方法功能描述：获取对角线系数
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月10日 上午10:00:01</p>
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void CaculateDiagonalcoefficientsmMatrix() throws Exception {
		this.diagonalcoefficientsmMatrix =
				Matrix.multipleMatrix(aMatrix.getTransposeMatrix(), aMatrix)
						.getInverse();
		logger.info("对角线系数矩阵为：\n" + diagonalcoefficientsmMatrix);
	}

	/**
	 * 
	 * @方法功能描述：计算各个参数的标准差
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月10日 上午11:07:55</p>
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void CaculateStandardDivisionMatrix() {
		// this.standardDivisionMatrix =
		// new Matrix(this.parameterMatrix.getrow(), 1);
		for (int i = 0; i < this.standardDivisionMatrix.getrow(); i++) {
			this.standardDivisionMatrix.set(
					i,
					0,
					this.accuracy
							* Math.sqrt(this.diagonalcoefficientsmMatrix.get(i,
									i)));
		}
		logger.info("参数标准差矩阵为：\n" + standardDivisionMatrix);
	}

	public Matrix getaMatrix() {
		return aMatrix;
	}

	public void setaMatrix(Matrix aMatrix) {
		this.aMatrix = aMatrix;
	}

	public Matrix getyMatrix() {
		return yMatrix;
	}

	public void setyMatrix(Matrix yMatrix) {
		this.yMatrix = yMatrix;
	}

	public Matrix getParameterMatrix() {
		return parameterMatrix;
	}

	public Matrix getResidualMatrix() {
		return residualMatrix;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public Matrix getDiagonalcoefficientsmMatrix() {
		return diagonalcoefficientsmMatrix;
	}

	public Matrix getStandardDivisionMatrix() {
		return standardDivisionMatrix;
	}

}
