package com.gxz.mymath.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.gxz.mymath.arithmetic.leastsquare.AccuracyLinerLeastSquare;
import com.gxz.mymath.arithmetic.leastsquare.LinerLeastSquare;
import com.gxz.mymath.arithmetic.leastsquare.LinerLeastSquareInterface;

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
 * 测试apple
 * @修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class LeastSquareTest  {

	
/**
 * 
		* @方法功能描述：
		* @作者 郭晓忠(guoxiaozhong)
		* @创建日期 ：2015年4月27日 下午6:37:33</p>
		* @param args
		* @throws Exception
		* @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Logger logger=Logger.getLogger(LeastSquareTest.class);
		PropertyConfigurator.configure("log4j.properties");
		
//		LinerLeastSquare leastSquare = new LinerLeastSquare();
		LinerLeastSquareInterface leastSquareInterface=new LinerLeastSquare();
		logger.info("开始运算");
		leastSquareInterface.getDataFromTxt("testdata/linerLestSquareTestData1", ",");
		leastSquareInterface.CaculateparameterMatrix();
		leastSquareInterface.CaculateResidualMatrix();
		leastSquareInterface.CaculateAccuracy();
		leastSquareInterface.CaculateDiagonalcoefficientsmMatrix();
		leastSquareInterface.CaculateStandardDivisionMatrix();
		System.out.println();
	}

}
