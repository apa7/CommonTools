package cn.lidd.threadpool.impl;
import cn.lidd.threadpool.inf.ITask;
import cn.lidd.threadpool.inf.ITaskThread;


/**
 * 
 * �����߳�
 *
 * @author: lidong
 * @version: 2010-6-29 ����04:01:27
 */
public class TaskThread implements ITaskThread {

	/**
	 * ����
	 */
	private ITask task;

	/**
	 * ���캯��
	 *
	 * @param task_
	 */
	public TaskThread(ITask task_) {
		task = task_;
	}

	/**
	 * �̳���Runnable
	 */
	public void run() {
		// ִ������
		task.dotask();
	}
}