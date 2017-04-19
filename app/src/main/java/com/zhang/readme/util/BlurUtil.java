package com.zhang.readme.util;

import android.graphics.Bitmap;

/**
 * Created by zhang on 2017/4/10.
 *
 * 高斯模糊图片滤镜工具类
 * @author zhang
 */

public class BlurUtil {

    /* 标准平方差默认取值，该值通常取值为1 */
    private static final float DEFAULT_SIGMA = 1.0f;

    public Bitmap imageBlur(Bitmap bitmap, int radius) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] rgbVarArr = new int[width * height];
        int[] rgbVarArr_new = new int[width * height];
        float[][] distribution = getDistribution_2D(radius, radius * DEFAULT_SIGMA);

        // 获取图片中RGB值
        bitmap.getPixels(rgbVarArr, 0, width, 0, 0, width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float r_sum = 0, g_sum = 0, b_sum = 0;

                //对模糊半径内的像素RGB值进行加权
                for (int i = -radius; i <= radius; i++) {
                    for (int j = -radius; j <= radius; j++) {
                        int x_offset = x+i;
                        int y_offset = y+j;
                        if (x_offset < 0 || y_offset < 0 || x_offset > width-1 || y_offset > height-1) continue;

                        int rgb = rgbVarArr[y_offset * width + x_offset];
                        r_sum += getRedByRgbVar(rgb)    * distribution[i+radius][j+radius];
                        g_sum += getGreenByRgbVar(rgb)  * distribution[i+radius][j+radius];
                        b_sum += getBlueByRgbVar(rgb)   * distribution[i+radius][j+radius];
                    }
                }
                rgbVarArr_new[y * width + x] = RGB2RgbVar((int) r_sum, (int) g_sum, (int) b_sum);
            }
        }
        return Bitmap.createBitmap(rgbVarArr_new, width, height, Bitmap.Config.RGB_565);
    }

    /**
     * 将RGB值转换成Java公用值
     *
     * 用32位标识色彩值
     * 32~24 透明度, 23~16 红, 15~8 绿, 7~0 蓝
     * @param r Red
     * @param g Green
     * @param b Blue
     * @return RGB
     */
    private int RGB2RgbVar(int r, int g, int b) {
        return ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF);
    }

    /**
     * 将Java公用值转换成RGB值
     *
     * @param rgb 色彩值
     * @return RGB
     */
    private int getRedByRgbVar(int rgb)   {return (rgb >> 16) & 0xFF;}
    private int getGreenByRgbVar(int rgb) {return (rgb >> 8)  & 0xFF;}
    private int getBlueByRgbVar(int rgb)  {return rgb         & 0xFF;}

    /**
     * 将RGB值 转换成 灰度值
     *
     * 公式：
     * 		Gray = R*0.299 + G*0.587 + B*0.114
     * @param r Reg
     * @param g Green
     * @param b Blue
     * @return Gray
     */
    private int Rgb2Gray(int r, int g, int b) {
        //避免浮点计算
        return (r*299 + g*587 + b*114) / 1000;
    }

    /**
     * 获取正态分布数据 (一维)
     *
     * 公式：
     * 		G(x) = (1 / sqrt(2*PI) * SIGMA ) * E ^ (-x^2 / 2* SIGMA^2)
     *
     * @param radius 模糊半径
     * @param sigma 方差
     * @return distribution_1D
     */
    public float[] getDistribution_1D(int radius, float sigma) {
        int size = radius * 2 + 1;
        float[] distribution = new float[size];

        float _2sigma_square = 2 * sigma * sigma;
        float sqrt_2PI_xSigma = (float) Math.sqrt(2 * Math.PI) * sigma;

        for (int i = -radius; i <= radius; i++) {
            float x_square = i * i;
            distribution[i+radius] = (float) Math.exp((-x_square) / _2sigma_square) / sqrt_2PI_xSigma;
        }

        return distribution;
    }

    /**
     * 获取正态分布数据 (二维)
     *
     * 公式：
     * 		G(x, y) = (1 / 2*PI * SIGMA^2 ) * E ^ ((-x^2 - y^2) / 2* SIGMA^2))
     *
     * @param radius 模糊半径
     * @param sigma 方差
     * @return distribution_1D
     */
    private float[][] getDistribution_2D(int radius, float sigma) {
        int size = radius * 2 + 1;
        float[][] distribution = new float[size][size];

        float _2sigma_square = 2 * sigma * sigma;
        float _2PI_xSigma_square = _2sigma_square * (float) Math.PI;

        float sum = 0;
        for (int i = -radius; i <= radius; i++) {
            float x_square = i * i;
            for (int j = -radius; j <= radius; j++) {
                float y_square = j * j;
                sum += distribution[i+radius][j+radius] = (float) Math.exp((-x_square -y_square) / _2sigma_square) / _2PI_xSigma_square;
            }
        }

        //让滤镜的权重总值等于1。否则的话，使用总值大于1的滤镜会让图像偏亮，小于1的滤镜会让图像偏暗
        for (int x = 0; x < distribution.length; x++) {
            for (int y = 0; y < distribution[0].length; y++) {
                distribution[x][y] /= sum;
            }
        }

        return distribution;
    }

    /**
     * 对图像模糊进行边缘像素点的预处理
     *
     * 把已有的点拷贝到另一面的对应位置，模拟出完整的矩阵
     * 例： (radius=1)
     *
     *              6 5 6 7 8 7
     * 1 2 3 4      2 1 2 3 4 3
     * 5 6 7 8  ->  6 5 6 7 8 7
     * 9 8 6 5      8 9 8 6 5 6
     * 4 3 2 1      3 4 3 2 1 2
     *              8 9 8 6 5 6
     *
     * @param rgbs 颜色值
     * @param radius 模糊半径
     * @return rgbs_new
     */
    public int[][] preEdge(int[][] rgbs, int radius) {
        int width = rgbs.length;
        int height = rgbs[0].length;

        int width_new = width + radius*2;
        int height_new = height  + radius*2;
        int[][] grays_new = new int[width_new][height_new];

        for (int i = 0; i < width_new; i++) {
            for (int j = 0; j < height_new; j++) {

            }
        }

        return grays_new;
    }
}
