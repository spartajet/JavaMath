package com.gxz.mymath.determinant;

import com.gxz.mymath.matrix.AbstractMatrix;
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
import com.gxz.mymath.matrix.Matrix;

public class Determinant extends AbstractDeterminant {
	private double[][] determinantData;

	// private int order;
	// private double det;

	/**
	 * 
	 * @构造函数：实例化二维数组
	 * @说明：
	 * @param order
	 */
	public Determinant(int order) {
		super(order);
		this.determinantData = new double[this.getOrder()][this.getOrder()];

	}

	/**
	 * 
	 * @构造函数：由方阵构造行列式
	 * @说明：
	 * @param matrix
	 * @throws Exception
	 */
	public Determinant(AbstractMatrix matrix) throws Exception {
		// if (matrix.getrow() != matrix.getcolumn()) {
		// throw new Exception("矩阵不是方阵，无法转换为行列式");
		// }
		// this.order = matrix.getrow();
		super(matrix);
		this.determinantData = new double[this.getOrder()][this.getOrder()];
		for (int i = 0; i < this.getOrder(); i++) {
			for (int j = 0; j < this.getOrder(); j++) {
				this.determinantData[i][j] = matrix.get(i, j);
			}
		}
	}

	@Override
	public Determinant getAlgebraicComplementMinor(int row, int column)
			throws Exception {
		if (row > this.getOrder() || column > this.getOrder()) {
			throw new Exception("给定索引超出范围");
		}
		Determinant resultDterminant = new Determinant(this.getOrder() - 1);
		for (int i = 0; i < this.getOrder() - 1; i++) {
			for (int j = 0; j < this.getOrder() - 1; j++) {
				if (i < row && j < column) {
					resultDterminant.set(i, j, this.get(i, j));
				} else if (i >= row && j < column) {
					resultDterminant.set(i, j, this.get(i + 1, j));
				} else if (i < row && j >= column) {
					resultDterminant.set(i, j, this.get(i, j + 1));
				} else {
					resultDterminant.set(i, j, this.get(i + 1, j + 1));
				}
			}
		}
		return resultDterminant;
	}

	@Override
	public double get(int row, int column) {
		return this.determinantData[row][column];
	}

	@Override
	public void set(int row, int column, double value) {
		this.determinantData[row][column] = value;
	}

	/**
	 * 
	 * @方法功能描述：求行列式的值
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月9日 上午10:24:44</p>
	 * @return
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public double getDet() throws Exception {
		double resultDet = 0;
		if (this.getOrder() == 1) {
			return this.get(0, 0);
		} else if (this.getOrder() == 2) {
			return this.get(0, 0) * this.get(1, 1) - this.get(0, 1)
					* this.get(1, 0);
		} else {
			for (int i = 0; i < this.getOrder(); i++) {
				if (this.get(0, i) != 0) {
					resultDet +=
							(Math.pow(-1, i) * this.get(0, i) * this
									.getAlgebraicComplementMinor(0, i).getDet());
				}
			}
		}
		return resultDet;
	}

}
