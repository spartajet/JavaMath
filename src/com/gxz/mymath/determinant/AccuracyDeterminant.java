package com.gxz.mymath.determinant;

import java.math.BigDecimal;

import com.gxz.mymath.matrix.AbstractMatrix;
import com.gxz.mymath.matrix.AccuracyMatrix;

public class AccuracyDeterminant extends AbstractDeterminant {
	private BigDecimal[][] determinantData;

	public AccuracyDeterminant(int order) {
		super(order);
		this.determinantData = new BigDecimal[order][order];
	}

	public AccuracyDeterminant(AccuracyMatrix matrix) throws Exception {
		super(matrix);
		// TODO Auto-generated constructor stub
		this.determinantData = new BigDecimal[this.getOrder()][this.getOrder()];
		for (int i = 0; i < this.getOrder(); i++) {
			for (int j = 0; j < this.getOrder(); j++) {
				this.determinantData[i][j] = matrix.getDecimal(i, j);
			}
		}
	}

	@Override
	public AccuracyDeterminant getAlgebraicComplementMinor(int row, int column)
			throws Exception {
		// TODO Auto-generated method stub
		if (row > this.getOrder() || column > this.getOrder()) {
			throw new Exception("给定索引超出范围");
		}
		AccuracyDeterminant resultDterminant =
				new AccuracyDeterminant(this.getOrder() - 1);
		for (int i = 0; i < this.getOrder() - 1; i++) {
			for (int j = 0; j < this.getOrder() - 1; j++) {
				if (i < row && j < column) {
					resultDterminant.set(i, j, this.getDecimal(i, j));
				} else if (i >= row && j < column) {
					resultDterminant.set(i, j, this.getDecimal(i + 1, j));
				} else if (i < row && j >= column) {
					resultDterminant.set(i, j, this.getDecimal(i, j + 1));
				} else {
					resultDterminant.set(i, j, this.getDecimal(i + 1, j + 1));
				}
			}
		}
		return resultDterminant;
	}

	@Override
	public double get(int row, int column) {
		// TODO Auto-generated method stub
		return this.determinantData[row][column].setScale(10,
				BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

	public BigDecimal getDecimal(int row, int column) {
		return this.determinantData[row][column];
	}

	@Override
	public void set(int row, int column, double value) {
		// TODO Auto-generated method stub
		this.set(row, column, new BigDecimal(value));
	}

	public void set(int row, int column, BigDecimal value) {
		// TODO Auto-generated method stub
		this.determinantData[row][column] = value;
	}

	@Override
	public double getDet() throws Exception {
		// TODO Auto-generated method stub
		return getDetDecimal().setScale(10, BigDecimal.ROUND_HALF_EVEN)
				.doubleValue();
	}

	public BigDecimal getDetDecimal() throws Exception {
		BigDecimal resultDet = new BigDecimal("0");
		if (this.getOrder() == 1) {
			return this.getDecimal(0, 0);
		} else if (this.getOrder() == 2) {
			return (this.getDecimal(0, 0).multiply(this.getDecimal(1, 1)))
					.subtract(this.getDecimal(0, 1).multiply(
							this.getDecimal(1, 0)));
			// return this.get(0, 0) * this.get(1, 1) - this.get(0, 1)
			// * this.get(1, 0);
		} else {
			for (int i = 0; i < this.getOrder(); i++) {
				if (this.get(0, i) != 0) {
					resultDet =
							resultDet.add(this
									.getDecimal(0, i)
									.multiply(new BigDecimal(Math.pow(-1, i)))
									.multiply(
											getAlgebraicComplementMinor(0, i)
													.getDetDecimal()));

					// resultDet +=
					// (Math.pow(-1, i) * this.get(0, i) * this
					// .getAlgebraicComplementMinor(0, i).getDet());
				}
			}
		}
		return resultDet;
	}

}
