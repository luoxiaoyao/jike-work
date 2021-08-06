package com.jikework.works.firstworks.one;

public class ByteCode {
    private static int a = 5;

    private static double b = 3.0;

    /**
     * 根据输入的数值num除以a如果大于1，则返回num与b的乘积，否则返回b
     *
     * @param num
     * @return
     */
    public static double getResult1(double num) {
        if (num / a > 1) {
            return num * b;
        }
        return b;
    }

    /**
     * 根据输入的num，获取其中最大的a的倍数,如果没有则抛出异常，有则与b进行相减并返回
     *
     * @param num
     * @return
     */
    public static int getResult2(int num) throws Exception {
        for (int i = num; i > 0; i--) {
            if (i % a == 0) {
                return (int) (i - b);
            }
        }
        throw new Exception("输入的num中没有a的对应的倍数");
    }

    public static void main(String[] args) throws Exception {
        long d = 8;
        System.out.println(getResult1(d));
        System.out.println(getResult2((int) d));
    }
}
