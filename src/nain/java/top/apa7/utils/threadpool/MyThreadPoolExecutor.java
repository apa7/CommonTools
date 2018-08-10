package cn.lidd.threadpool;


import java.util.concurrent.*;

/**
 * 
 * ��չ�̳߳�,��ÿ�����������ʱ��
 * �ж��̳߳����Ƿ�ִ�������е��߳�����
 * @author: lidong
 * @version: 2010-7-2 ����10:29:51
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {
	/**
	 * �̳߳��������Ƿ�ȫ������.
	 */
	private boolean hasFinish = false;

	/**
	 * ���캯��
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 * @param handler
	 */
	public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				handler);
	}

    /**
     * ���캯��
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     * @param threadFactory
     * @param handler
     */
	public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
			RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
	}

	/**
	 * ���캯��
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 * @param threadFactory
	 */
	public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory);
	}

	/**
	 * ���캯��
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 */
	public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * java.util.concurrent.ThreadPoolExecutor#afterExecute(java.lang.Runnable,
	 * java.lang.Throwable)
	 */
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		synchronized (this) {
			this.getActiveCount();
			if (this.getActiveCount() == 1)// ��ִ��������֮������һ���߳�
			{
				this.hasFinish = true;
				this.notify();
			}// if
		}// synchronized
	}

	/**
	 * ���ܣ� �ж��Ƿ�ִ�������е��߳�����û��ִ����Ļ������̹߳���
	 *       �̳߳ش�С����>1
	 * @author lidong
	 * void
	 * ������
	 */
	public boolean isEndTask() {
		synchronized (this) {
			while (!this.hasFinish) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return this.hasFinish;
		}
	}

}