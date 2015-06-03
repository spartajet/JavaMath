package com.gxz.mymath.matrix;

import java.math.BigDecimal;

import com.gxz.mymath.accuracy.AccuracyCaculate;
import com.gxz.mymath.determinant.AccuracyDeterminant;
import com.gxz.mymath.determinant.Determinant;

public class AccuracyMatrix extends AbstractMatrix {

	private BigDecimal[][] matrixArray;

	public AccuracyMatrix(int r, int c) {
		super(r, c);
		// TODO Auto-generated constructor stub
		this.matrixArray = new BigDecimal[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				matrixArray[i][j]=new BigDecimal("0");
			}
		}
	}

	public AccuracyMatrix(int n) {
		super(n);
		// TODO Auto-generated constructor stub
		this.matrixArray = new BigDecimal[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrixArray[i][j]=new BigDecimal("0");
			}
		}
	}

	public AccuracyMatrix(AbstractMatrix matrix) {
		super(matrix);
		// TODO Auto-generated constructor stub
		this.matrixArray = new BigDecimal[matrix.getRow()][matrix.getColumn()];
		for (int i = 0; i < matrix.getRow(); i++) {
			for (int j = 0; j < matrix.getColumn(); j++) {
				this.set(i, j, matrix.get(i, j));
			}
		}
	}

	@Override
	public void set(int r, int c, double value) {
		// TODO Auto-generated method stub
		this.matrixArray[r][c] = new BigDecimal(value);
	}

	public void set(int r, int c, BigDecimal value) {
		// TODO Auto-generated method stub
		this.matrixArray[r][c] = value;
	}

	@Override
	public double get(int r, int c) {
		// TODO Auto-generated method stub
		return this.matrixArray[r][c].setScale(8, BigDecimal.ROUND_HALF_EVEN)
				.doubleValue();
	}

	public BigDecimal getDecimal(int r, int c) {
		return this.matrixArray[r][c];
	}

	@Override
	public void SetUnit() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.getRow(); i++) {
			for (int j = 0; j < this.getColumn(); j++) {
				if (i == j) {
					this.matrixArray[i][j] = new BigDecimal("1");
				} else {
					this.matrixArray[i][j] = new BigDecimal("0");
				}
			}
		}
	}

	@Override
	public void RowExchange(int row1, int row2) throws Exception {
		// TODO Auto-generated method stub
		BigDecimal tempdouble;
		if (row1 >= this.getRow() || row2 >= this.getRow()) {
			throw new Exception("给定索引超出范围");
		} else {
			for (int i = 0; i < this.getColumn(); i++) {
				tempdouble = this.matrixArray[row1][i];
				this.matrixArray[row1][i] = this.matrixArray[row2][i];
				this.matrixArray[row2][i] = tempdouble;
			}
		}
	}

	@Override
	public void RowMultiple(int row, double mul) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.getColumn(); i++) {
			this.matrixArray[row][i].multiply(new BigDecimal(mul));
		}
	}

	@Override
	public void RowMultipleAdd(int row1, int row2, double mul) {
		// TODO Auto-generated method stub
		BigDecimal mulvalue = new BigDecimal(mul);
		for (int i = 0; i < this.getColumn(); i++) {
			this.matrixArray[row1][i] =
					this.matrixArray[row1][i].add((this.matrixArray[row2][i]
							.multiply(mulvalue)));
		}
	}

	@Override
	public AccuracyMatrix getTransposeMatrix() {
		// TODO Auto-generated method stubMatrix resultMatrix = new
		// Matrix(this.getColumn(), this.getRow());
		AccuracyMatrix resultMatrix =
				new AccuracyMatrix(this.getColumn(), this.getRow());
		// this.getTransposeMatrix() = new Matrix(this.getColumn(),
		// this.getRow());
		for (int i = 0; i < this.getColumn(); i++) {
			for (int j = 0; j < this.getRow(); j++) {
				resultMatrix.set(i, j, this.get(j, i));
			}
		}
		return resultMatrix;
	}

	@Override
	public AbstractMatrix Plus(AbstractMatrix matrix) throws Exception {
		// TODO Auto-generated method stub
		AccuracyMatrix resultMatrix;
		if (this.getColumn() != matrix.getColumn()
				|| this.getRow() != matrix.getRow()) {
			throw new Exception("两个矩阵大小不一致，不能相加");
		} else {
			resultMatrix = new AccuracyMatrix(this.getColumn(), this.getRow());
			for (int i = 0; i < this.getRow(); i++) {
				for (int j = 0; j < this.getColumn(); j++) {
					resultMatrix
							.set(i,
									j,
									this.getDecimal(i, j).add(
											((AccuracyMatrix) matrix)
													.getDecimal(i, j)));
				}
			}
		}
		return resultMatrix;
	}

	@Override
	public AccuracyMatrix Minus(AbstractMatrix matrix) throws Exception {
		// TODO Auto-generated method stub
		AccuracyMatrix resultMatrix;
		if (this.getColumn() != matrix.getColumn()
				|| this.getRow() != matrix.getRow()) {
			throw new Exception("两个矩阵大小不一致，不能相加");
		} else {
			resultMatrix = new AccuracyMatrix(this.getRow(), this.getColumn());
			for (int i = 0; i < this.getRow(); i++) {
				for (int j = 0; j < this.getColumn(); j++) {
					resultMatrix
							.set(i,
									j,
									this.getDecimal(i, j).subtract(
											((AccuracyMatrix) matrix)
													.getDecimal(i, j)));
				}
			}
		}
		return resultMatrix;
	}

	@Override
	public AccuracyMatrix multiple(AbstractMatrix matrix) throws Exception {
		// TODO Auto-generated method stub
		if (this.getColumn() != matrix.getRow()) {
			throw new Exception("矩阵对应关系不正确,不能相乘");
		}
		AccuracyMatrix resultmMatrix =
				new AccuracyMatrix(this.getRow(), matrix.getColumn());
		for (int i = 0; i < this.getRow(); i++) {
			for (int j = 0; j < matrix.getColumn(); j++) {
				for (int k = 0; k < this.getColumn(); k++) {
					resultmMatrix.set(
							i,
							j,
							resultmMatrix.getDecimal(i, j).add(
									this.getDecimal(i, k).multiply(
											((AccuracyMatrix) matrix)
													.getDecimal(k, j))));
				}

			}
		}
		return resultmMatrix;
	}

	@Override
	public AccuracyMatrix multiple(double mul) {
		// TODO Auto-generated method stub
		AccuracyMatrix resultMatrix =
				new AccuracyMatrix(this.getRow(), this.getColumn());
		BigDecimal mulvalue = new BigDecimal(mul);
		for (int i = 0; i < this.getRow(); i++) {
			for (int j = 0; j < this.getColumn(); j++) {
				resultMatrix
						.set(i, j, this.getDecimal(i, j).multiply(mulvalue));
			}
		}
		return resultMatrix;
	}

	public AccuracyMatrix mutiple(BigDecimal mul) {
		AccuracyMatrix resultMatrix =
				new AccuracyMatrix(this.getRow(), this.getColumn());
		for (int i = 0; i < this.getRow(); i++) {
			for (int j = 0; j < this.getColumn(); j++) {
				resultMatrix.set(i, j, this.getDecimal(i, j).multiply(mul));
			}
		}
		return resultMatrix;
	}

	@Override
	public int ColumnPivot(int startrow, int searchcolumn) {
		// TODO Auto-generated method stub
		int indextemp = startrow;
		BigDecimal datatemp = this.getDecimal(startrow, searchcolumn);
		for (int i = startrow; i < this.getRow(); i++) {
			if (this.getDecimal(i, searchcolumn).compareTo(datatemp) == 1) {
				datatemp = this.getDecimal(i, searchcolumn);
				indextemp = i;
			}
		}
		return indextemp;
	}

	@Override
	public double getModule() throws Exception {
		// TODO Auto-generated method stub
		return this.getModuleDecimal().setScale(10, BigDecimal.ROUND_HALF_EVEN)
				.doubleValue();
	}

	public BigDecimal getModuleDecimal() throws Exception {
		return new AccuracyDeterminant(this).getDetDecimal();
	}

	@Override
	public AccuracyMatrix getAlgebraicComplementMinorMatrix() throws Exception {
		// TODO Auto-generated method stub
		AccuracyMatrix resultmMatrix =
				new AccuracyMatrix(this.getRow(), this.getColumn());
		AccuracyDeterminant determinant = new AccuracyDeterminant(this);
		for (int i = 0; i < this.getRow(); i++) {
			for (int j = 0; j < this.getColumn(); j++) {
				resultmMatrix.set(i, j, determinant
						.getAlgebraicComplementMinor(i, j).getDetDecimal()
						.multiply(new BigDecimal(Math.pow(-1, i + j))));
			}
		}
		return resultmMatrix;
	}

	@Override
	public AccuracyMatrix getInverse() throws Exception {
		// TODO Auto-generated method stub
		return this.getAlgebraicComplementMinorMatrix().mutiple(
				new BigDecimal("1").divide(this.getModuleDecimal(), 10,
						BigDecimal.ROUND_HALF_EVEN));
	}

	@Override
	public boolean isMirror() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.getRow(); i++) {
			for (int j = 0; j < i; j++) {
				if (this.getDecimal(i, j).compareTo(this.getDecimal(j, i)) != 0) {
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String resultsString = "";
		for (int i = 0; i < this.getRow(); i++) {
			for (int j = 0; j < this.getColumn(); j++) {
				resultsString += (String.valueOf(this.get(i, j)) + "\t");
			}
			resultsString += "\n";
		}
		return resultsString;
	}

}
