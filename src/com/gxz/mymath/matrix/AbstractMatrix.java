package com.gxz.mymath.matrix;

public abstract class AbstractMatrix {
	/**
	 * 矩阵列
	 */
	private int column;
	/**
	 * 矩阵行
	 */
	private int row;
	/**
	 * 转置矩阵
	 */
	private AbstractMatrix transposeMatrix;

	// public AbstractMatrix() {
	// // TODO Auto-generated constructor stub
	// }
	/**
	 * 
	 * @构造函数：构造函数
	 * @说明：构造矩阵
	 * @param r
	 *            行
	 * @param c
	 *            列
	 */
	public AbstractMatrix(int r, int c) {
		this.row = r;
		this.column = c;
	}

	/**
	 * 
	 * @构造函数：
	 * @说明：构造矩阵
	 * @param n
	 *            矩阵的阶
	 */
	public AbstractMatrix(int n) {
		this.column = n;
		this.row = n;
	}

	/**
	 * 
	 * @构造函数：构造函数
	 * @说明： 复制矩阵
	 * @param matrix
	 */
	public AbstractMatrix(AbstractMatrix matrix) {
		this.column = matrix.column;
		this.row = matrix.row;
	}

	/**
	 * 
	 * @方法功能描述：设置矩阵的元素值
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:21:32</p>
	 * @param r
	 *            行
	 * @param c
	 *            列
	 * @param value
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract void set(int r, int c, double value);

	/**
	 * 
	 * @方法功能描述：获取矩阵的元素值
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:24:33</p>
	 * @param r
	 *            行
	 * @param c
	 *            列
	 * @return
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract double get(int r, int c);

	/**
	 * 
	 * @方法功能描述：设置为单位阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:25:09</p>
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract void SetUnit();

	/**
	 * 
	 * @方法功能描述：调换矩阵的两行
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:26:44</p>
	 * @param row1
	 * @param row2
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract void RowExchange(int row1, int row2) throws Exception;

	/**
	 * 
	 * @方法功能描述：某行元素乘以一个数
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:27:02</p>
	 * @param row
	 *            行
	 * @param mul
	 *            常数
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract void RowMultiple(int row, double mul);

	/**
	 * @方法功能描述：
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:29:04</p>
	 * @param row1
	 * @param row2
	 * @param mul
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract void RowMultipleAdd(int row1, int row2, double mul);

	/**
	 * 
	 * @方法功能描述：获取转置矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:30:08</p>
	 * @return
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract AbstractMatrix getTransposeMatrix();

	/**
	 * 
	 * @方法功能描述：加上一个矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:32:14</p>
	 * @param matrix
	 * @return
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract AbstractMatrix Plus(AbstractMatrix matrix) throws Exception;

	/**
	 * 
	 * @方法功能描述：减去一个矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:32:55</p>
	 * @param matrix
	 * @return
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract AbstractMatrix Minus(AbstractMatrix matrix)
			throws Exception;

	/**
	 * 
	 * @方法功能描述：乘以一个矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:33:13</p>
	 * @param matrix
	 * @return
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract AbstractMatrix multiple(AbstractMatrix matrix)
			throws Exception;

	/**
	 * 
	 * @方法功能描述：矩阵乘以常数
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:33:30</p>
	 * @param mul
	 * @return
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract AbstractMatrix multiple(double mul);

	/**
	 * 
	 * @方法功能描述：查找矩阵列主元
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:34:03</p>
	 * @param startrow
	 * @param searchcolumn
	 * @return
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract int ColumnPivot(int startrow, int searchcolumn);

	/**
	 * 
	 * @方法功能描述：获取矩阵的模
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:35:22</p>
	 * @return
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract double getModule() throws Exception;

	/**
	 * 
	 * @方法功能描述：获取矩阵的代数余子式矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:36:20</p>
	 * @return
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract AbstractMatrix getAlgebraicComplementMinorMatrix()
			throws Exception;

	/**
	 * 
	 * @方法功能描述：计算矩阵的逆
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:37:06</p>
	 * @return
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract AbstractMatrix getInverse() throws Exception;

	/**
	 * 
	 * @方法功能描述：判断是否是对称矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年6月2日 下午10:37:16</p>
	 * @return
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public abstract boolean isMirror();

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setTransposeMatrix(AbstractMatrix transposeMatrix) {
		this.transposeMatrix = transposeMatrix;
	}

}
