package com.gxz.mymath.arithmetic.leastsquare;

public interface LinerLeastSquareInterface {
	public void getDataFromTxt(String txtpath, String splitString);

	public void initMatrix();

	public void CaculateparameterMatrix() throws Exception;

	public void CaculateResidualMatrix() throws Exception;

	public void CaculateAccuracy();

	public void CaculateDiagonalcoefficientsmMatrix() throws Exception;

	public void CaculateStandardDivisionMatrix();
}
