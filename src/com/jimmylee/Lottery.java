package com.jimmylee;

import java.util.Random;

/**
 * һ����֤���ʵĳ齱����
 * ͨ��������е����飬���±�һ����ȡ������ȡ��ʲô�����ʲô������
 * ����ȡ��֮�󲻻���ֵڶ���
 * 
 * @author jimmylee
 * @version ����ʱ�䣺2014-6-9  ����11:33:03
 */
public class Lottery {
	private static int[] pool;

	/**
	 * ��ʼ������
	 * @param size
	 */
	public static void init(int size) {
		pool = new int[size];
		for (int i = 0; i < size; i++) {
			pool[i] = i;
		}
	}

	/**
	 * ���������е�����
	 * @param size
	 * @return
	 */
	public static int[] getPool(int size) {
		if (pool == null) {
			init(size);
			Random random = new Random();
			for (int i = 0; i < size; i++) {
				doChange(random.nextInt(size), i, pool);
			}
		}
		return pool;
	}

	/**
	 * ����λ�ã���������
	 * @param p1
	 * @param p2
	 * @param pool
	 */
	public static void doChange(int p1, int p2, int[] pool) {
		int temp = pool[p1];
		pool[p1] = pool[p2];
		pool[p2] = temp;
	}

	public static void main(String[] args) {
		int[] pool = Lottery.getPool(10000);
		for (int i = 0; i < 10000; i++) {
			if (pool[i] == 0) {
				System.out.println("5000");
			} else if (pool[i] >= 1 && pool[i] < 11) {
				System.out.println("500");
			} else if (pool[i] >= 11 && pool[i] < 111) {
				System.out.println("200");
			} else if (pool[i] >= 111 && pool[i] < 161) {
				System.out.println("100");
			} else if (pool[i] >= 161 && pool[i] < 461) {
				System.out.println("50");
			} else if (pool[i] >= 461 && pool[i] < 961) {
				System.out.println("20");
			} else if (pool[i] >= 961 && pool[i] < 1961) {
				System.out.println("10");
			} else if (pool[i] >= 1961 && pool[i] < 4961) {
				System.out.println("5");
			} else {
				System.out.println("0");
			}

		}
	}

}
