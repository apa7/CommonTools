package cn.lidd.threadpool.impl;
import cn.lidd.threadpool.inf.ITask;

/**
 * ��������ʵװ��
 * @author lidong
 *
 */
public class Task implements ITask {

	private int i;

	/**
	 * ���캯��
	 * @param i
	 */
	public Task(int i) {
		this.i = i;
	}

	/**
	 * ��������
	 */
	public void dotask() {
		System.out.println("ִ������" + i);
		// ���Ե�ʱ��ÿ��ִ��ͣ��һ�뷽��۲�
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
