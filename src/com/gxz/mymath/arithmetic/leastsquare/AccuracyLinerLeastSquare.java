package com.gxz.mymath.arithmetic.leastsquare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gxz.mymath.matrix.AccuracyMatrix;
import com.gxz.mymath.matrix.Matrix;

public class AccuracyLinerLeastSquare implements LinerLeastSquareInterface {

	/**
	 * A矩阵
	 */
	private AccuracyMatrix aMatrix;
	/**
	 * Y矩阵
	 */
	private AccuracyMatrix yMatrix;
	/**
	 * 所求参数矩阵
	 */
	private AccuracyMatrix parameterMatrix;
	/**
	 * 残差矩阵
	 */
	private AccuracyMatrix residualMatrix;
	/**
	 * 精度 残差的标准差,也就是精度
	 */
	private BigDecimal accuracy;
	/**
	 * 对角线系数矩阵
	 */
	private AccuracyMatrix diagonalcoefficientsmMatrix;
	/**
	 * 标准差矩阵
	 */
	private AccuracyMatrix standardDivisionMatrix;

	private List<String[]> alldatasList;
	private int lineparanumber;
	private int datalineNumbers;
	private Logger logger = Logger.getLogger(LinerLeastSquare.class);

	@Override
	public void getDataFromTxt(String txtpath, String splitString) {
		// TODO Auto-generated method stub
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

	@Override
	public void initMatrix() {
		// TODO Auto-generated method stub
		this.lineparanumber = this.alldatasList.get(0).length;
		logger.info("每行测量数据包含 " + lineparanumber + "个数据，其中最后一个数据位Y值");
		this.datalineNumbers = this.alldatasList.size();

		this.aMatrix =
				new AccuracyMatrix(this.datalineNumbers, this.lineparanumber);

		logger.info("A矩阵为 " + this.datalineNumbers + "*" + this.lineparanumber
				+ "型矩阵");
		this.yMatrix = new AccuracyMatrix(this.datalineNumbers, 1);
		logger.info("Y矩阵为 " + this.datalineNumbers + "*" + "1  型矩阵");
		this.parameterMatrix = new AccuracyMatrix(lineparanumber, 1);
		logger.info("参数矩阵为 " + this.datalineNumbers + "*" + "1  型矩阵");

		this.residualMatrix = new AccuracyMatrix(datalineNumbers, 1);
		logger.info("残差矩阵为 " + this.datalineNumbers + "*" + "1  型矩阵");
		/**
		 * 对角线矩阵实例化
		 */
		this.diagonalcoefficientsmMatrix =
				new AccuracyMatrix(lineparanumber, lineparanumber);
		logger.info("对角线系数矩阵为 " + lineparanumber + "*" + lineparanumber
				+ " 型矩阵");
		/**
		 * 标准差矩阵实例化
		 */
		this.standardDivisionMatrix = new AccuracyMatrix(lineparanumber, 1);
		logger.info("标准差矩阵为 " + this.lineparanumber + "*" + "1  型矩阵");
		String[] tempstrStrings;
		for (int i = 0; i < alldatasList.size(); i++) {
			tempstrStrings = alldatasList.get(i);
			for (int j = 0; j < aMatrix.getColumn(); j++) {
				if (j == 0) {
					aMatrix.set(i, j, new BigDecimal("1"));
				} else {
					aMatrix.set(i, j, new BigDecimal(tempstrStrings[j - 1]));
				}

			}
			yMatrix.set(i, 0,
					new BigDecimal(tempstrStrings[lineparanumber - 1]));
		}
		logger.info("A矩阵为：\n" + aMatrix);
		logger.info("Y矩阵为：\n" + yMatrix);
	}

	@Override
	public void CaculateparameterMatrix() throws Exception {
		// TODO Auto-generated method stub
		AccuracyMatrix ATA = aMatrix.getTransposeMatrix().multiple(aMatrix);
		logger.info("AT*A矩阵结果:\n" + ATA);
		AccuracyMatrix cinverseMatrix = ATA.getInverse();
		logger.info("C(AT*A矩阵)的逆矩阵：\n" + cinverseMatrix);
		AccuracyMatrix cinverseAT =
				cinverseMatrix.multiple(aMatrix.getTransposeMatrix());
		logger.info("C逆*AT的结果：\n" + cinverseAT);
		this.parameterMatrix = cinverseAT.multiple(yMatrix);
		logger.info("参数矩阵结果：\n" + parameterMatrix);
	}

	@Override
	public void CaculateResidualMatrix() throws Exception {
		// TODO Auto-generated method stub
		this.residualMatrix = yMatrix.Minus(aMatrix.multiple(parameterMatrix));
		logger.info("残差矩阵结果：\n" + residualMatrix);
	}

	@Override
	public void CaculateAccuracy() {
		// TODO Auto-generated method stub
		BigDecimal vpower = new BigDecimal("0");
		for (int i = 0; i < this.residualMatrix.getRow(); i++) {
			vpower =
					vpower.add(this.residualMatrix.getDecimal(i, 0).multiply(
							this.residualMatrix.getDecimal(i, 0)));
		}
		this.accuracy =
				new BigDecimal(Math.sqrt((vpower.divide(
						new BigDecimal(aMatrix.getRow() - aMatrix.getColumn()),
						10, BigDecimal.ROUND_HALF_EVEN)).setScale(10,
						BigDecimal.ROUND_HALF_EVEN).doubleValue()));
		logger.info("根据残差矩阵计算得到的精度即标准差为：" + accuracy);
	}

	@Override
	public void CaculateDiagonalcoefficientsmMatrix() throws Exception {
		// TODO Auto-generated method stub
		this.diagonalcoefficientsmMatrix =
				aMatrix.getTransposeMatrix().multiple(aMatrix).getInverse();
		logger.info("对角线系数矩阵为：\n" + diagonalcoefficientsmMatrix);
	}

	@Override
	public void CaculateStandardDivisionMatrix() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.standardDivisionMatrix.getRow(); i++) {
			this.standardDivisionMatrix
					.set(i, 0, this.accuracy.multiply(new BigDecimal(Math
							.sqrt(this.diagonalcoefficientsmMatrix.get(i, i)))));
		}
		logger.info("参数标准差矩阵为：\n" + standardDivisionMatrix);
	}

}
