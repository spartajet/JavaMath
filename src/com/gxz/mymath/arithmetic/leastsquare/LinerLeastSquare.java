package com.gxz.mymath.arithmetic.leastsquare;

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

	/**
	 * 
	 * @方法功能描述：从TXT文件读取测量数据
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月10日 上午9:05:47</p>
	 * @param txtpath
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void getDataFromTxt(String txtpath) {

	}

	/**
	 * 
	 * @方法功能描述：计算参数矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月9日 下午2:58:12</p>
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	private void CaculateparameterMatrix() throws Exception {
		this.parameterMatrix =
				Matrix.multipleMatrix(Matrix.multipleMatrix(Matrix
						.multipleMatrix(aMatrix.getTransposeMatrix(), aMatrix),
						aMatrix.getTransposeMatrix()), yMatrix);
	}

	/**
	 * 
	 * @方法功能描述：计算残差矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月10日 上午9:04:56</p>
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	private void CaculateResidualMatrix() throws Exception {
		this.residualMatrix = new Matrix(yMatrix.getrow(), yMatrix.getcolumn());
		this.residualMatrix =
				Matrix.MinusMatrix(yMatrix,
						Matrix.multipleMatrix(aMatrix, parameterMatrix));

	}

	/**
	 * 
	 * @方法功能描述：计算精度，残差的标准差
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月10日 上午9:13:46</p>
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	private void CaculateAccuracy() {
		double vpower = 0;
		for (int i = 0; i < this.residualMatrix.getrow(); i++) {
			vpower += (Math.pow(this.residualMatrix.get(i, 0), 2));
		}
		this.accuracy =
				Math.sqrt(vpower / (aMatrix.getrow() - aMatrix.getcolumn()));
	}

	/**
	 * 
	 * @方法功能描述：获取对角线系数
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月10日 上午10:00:01</p>
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	private void CaculateDiagonalcoefficientsmMatrix() throws Exception {
		this.diagonalcoefficientsmMatrix =
				Matrix.multipleMatrix(aMatrix.getTransposeMatrix(), aMatrix)
						.getInverse();
	}

	/**
	 * 
	 * @方法功能描述：计算各个参数的标准差
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月10日 上午11:07:55</p>
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	private void CaculateStandardDivisionMatrix() {
		this.standardDivisionMatrix =
				new Matrix(this.parameterMatrix.getrow(), 1);
		for (int i = 0; i < this.standardDivisionMatrix.getrow(); i++) {
			this.standardDivisionMatrix.set(
					i,
					0,
					this.accuracy
							* Math.sqrt(this.diagonalcoefficientsmMatrix.get(i,
									i)));
		}
	}

}
