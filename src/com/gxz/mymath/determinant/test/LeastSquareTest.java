package com.gxz.mymath.determinant.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.gxz.mymath.arithmetic.leastsquare.LinerLeastSquare;

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
 * @类型功能描述：最小二乘法的测试类
 * @作者 郭晓忠(guoxiaozhong)
 * @修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class LeastSquareTest {

	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Logger logger=Logger.getLogger(LeastSquareTest.class);
		PropertyConfigurator.configure("log4j.properties");
		
		LinerLeastSquare leastSquare = new LinerLeastSquare();
		logger.info("开始运算");
		leastSquare.getDataFromTxt("testdata/linerLestSquareTestData1", ",");
		leastSquare.CaculateparameterMatrix();
		leastSquare.CaculateResidualMatrix();
		leastSquare.CaculateAccuracy();
		leastSquare.CaculateDiagonalcoefficientsmMatrix();
		leastSquare.CaculateStandardDivisionMatrix();
		
	}

}
