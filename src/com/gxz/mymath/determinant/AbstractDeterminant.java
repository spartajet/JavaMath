package com.gxz.mymath.determinant;

import com.gxz.mymath.matrix.AbstractMatrix;
import com.gxz.mymath.matrix.Matrix;

public abstract class AbstractDeterminant {
	private int order;
	private double det;

	public AbstractDeterminant(int order) {
		this.order = order;
	}

	public AbstractDeterminant(AbstractMatrix matrix) throws Exception {
		if (matrix.getRow() != matrix.getColumn()) {
			throw new Exception("矩阵不是方阵，无法转换为行列式");
		}
		this.order = matrix.getRow();
	}

	/**
	 * 
	 * @方法功能描述：求代数余子行列式
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午11:21:44</p>
	 * @param row
	 * @param column
	 * @return
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract AbstractDeterminant getAlgebraicComplementMinor(int row,
			int column) throws Exception;

	/**
	 * 
	 * @方法功能描述：获取行列式的元素值
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午11:24:03</p>
	 * @param row
	 * @param column
	 * @return
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract double get(int row, int column);

	/**
	 * 
	 * @方法功能描述：设置行列式的元素值
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午11:24:24</p>
	 * @param row
	 * @param column
	 * @param value
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract void set(int row, int column, double value);
	public abstract double getDet() throws Exception;

	public int getOrder() {
		return order;
	}


	
}
