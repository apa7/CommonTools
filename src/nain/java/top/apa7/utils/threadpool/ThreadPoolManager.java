package cn.lidd.threadpool;


import cn.lidd.threadpool.impl.Task;
import cn.lidd.threadpool.impl.TaskThread;
import cn.lidd.threadpool.inf.ITask;
import cn.lidd.threadpool.inf.ITaskThread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 
 * �̳߳ع���.
 *
 * @author: lidong
 * @version: 2010-6-29 ����08:53:55
 */
public class ThreadPoolManager
{

	/**
	 * �������̳߳ش�С.<br>
	 * ����������е��߳�����С�� CORE_POOL_SIZE����ô���ϴ����߳���������
	 */
	private final static int CORE_POOL_SIZE = 6;

	/**
	 * �̳߳ص�����С.<br>
	 */
	private final static int MAXIMUM_POOL_SIZE = 10;

	/**
	 * �����߳̽����ĳ�ʱʱ��.<br>
	 * ��һ���߳����¿���������һ����ʱ�䣨KEEP_ALIVE_TIME��ʱ���̳߳ػ��жϣ�<br>
	 * �����ǰ���е��߳������� CORE_POOL_SIZE����ô����߳̾ͱ�ͣ����
	 */
	private final static int KEEP_ALIVE_TIME = 600;

	/**
	 * ��ʾ KEEP_ALIVE_TIME�ĵ�λ.<br>
	 * TimeUnit.SECONDS: ��(s)
	 */
	private final static TimeUnit UNIT = TimeUnit.SECONDS;

	/**
	 * �������Ķ���.<br>
	 * ��һ���߳��������ʱ������Ӷ�����ȡ��һ��������ִ�С�
	 */
	private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();

	/**
	 * �̳߳�����.
	 */
	private MyThreadPoolExecutor threadPool = null;

	/**
	 *
	 * Ĭ�Ϲ��캯����<br>
	 * ����Ĭ���̳߳�:<br>
	 * <ul>
	 * <li>corePoolSize �������̳߳ش�С : <b>6</b></li>
	 * <li>maximumPoolSize �̳߳ص�����С : <b>10</b></li>
	 * <li>keepAliveTime �����߳̽����ĳ�ʱʱ�� : <b>600</b></li>
	 * <li>unit keepAliveTime�ĵ�λ: <b>��(s)</b></li>
	 * <li>workQueue �������Ķ���: <b>LinkedBlockingQueue</b></li>
	 * </ul>
	 */
	public ThreadPoolManager ()
	{
		// ����Ĭ���̳߳�
		// ������������������ LinkedBlockingQueue�ĺô�����û�д�С���ơ�
		// �����Ļ�����Ϊ���в����������� execute()�����׳��쳣��
		// ���̳߳������е��߳���Ҳ��Զ���ᳬ�� corePoolSize ����
		// maximumPoolSize�� keepAliveTime�� unit ����Ҳ��û�������ˡ�
		threadPool = new MyThreadPoolExecutor(CORE_POOL_SIZE, // �������̳߳ش�С
				MAXIMUM_POOL_SIZE,// �̳߳ص�����С
				KEEP_ALIVE_TIME, // �����߳̽����ĳ�ʱʱ��
				UNIT, // ��ʾ KEEP_ALIVE_TIME�ĵ�λ��(s)
				workQueue // �������Ķ���
		);
	}

	/**
	 *
	 * ���캯����<br>
	 * ����ָ����С���̳߳�:<br>
	 *
	 * @param corePoolSize
	 *            �������̳߳ش�С��Ĭ�ϴ�СΪ<b>6</b>
	 *
	 */
	public ThreadPoolManager(int corePoolSize) {
		// ����ָ����С���̳߳�
		// ������������������ LinkedBlockingQueue�ĺô�����û�д�С���ơ�
		// �����Ļ�����Ϊ���в����������� execute()�����׳��쳣��
		// ���̳߳������е��߳���Ҳ��Զ���ᳬ�� corePoolSize ����
		// maximumPoolSize�� keepAliveTime�� unit ����Ҳ��û�������ˡ�
		if (corePoolSize <= 0) {
			// ���ָ����СС�ڵ���0���̳߳ش�СΪĬ��ֵ
			corePoolSize = CORE_POOL_SIZE;
		}
		// ����ָ����С���̳߳�
		threadPool = new MyThreadPoolExecutor(corePoolSize, // �������̳߳ش�С
				MAXIMUM_POOL_SIZE,// �̳߳ص�����С
				KEEP_ALIVE_TIME, // �����߳̽����ĳ�ʱʱ��
				UNIT, // ��ʾ KEEP_ALIVE_TIME�ĵ�λ��(s)
				workQueue // �������Ķ���
		);
	}

	/**
	 *
	 * ���ܣ� ִ���̳߳�����.
	 *
	 * @author lidong
	 * @param taskType
	 *            ��������
	 * @param paramInfo
	 *            ������Ϣ�������������Ĳ���
	 *
	 */
	public void execute() {

			// ѭ�����еı�,�������� �����̳߳�������
			for (int i = 1; i <= 100; i++) {
				// ��������
				ITask task = createTask(i);
				// �����������߳�
				ITaskThread taskThread = createThread(task);
				// ���߳�׷�ӵ��̳߳���
				threadPool.execute(taskThread);
			}

			// �ȴ��������,���̲߳ż�������
			threadPool.isEndTask();
			// �ر����ӳ�
			threadPool.shutdown();

	}

	/**
	 *
	 * ���ܣ� ��������
	 *
	 * @author lidong
	 * @param i
	 *
	 * @return ITask
	 */
	public ITask createTask (final int i)
	{
		return new Task(i);
	}

	/**
	 *
	 * ���ܣ� ���������߳�
	 *
	 * @author lidong
	 * @param task
	 * @return ITaskThread ������
	 */
	private ITaskThread createThread (final ITask task)
	{
		// ���������߳�
		final ITaskThread thread = new TaskThread(task);
		return thread;
	}

	/**
	 *
	 * ���ܣ� �����ر��̳߳�,��������еĲ��ٴ���
	 * @author lidong
	 * void
	 * ������
	 */
	public void shutdownNow ()
	{
		// �ر����ӳ�
		this.threadPool.shutdownNow();
	}

	/**
	 *
	 * ���ܣ����������������������Ժ�ر��̳߳�
	 * @author lidong
	 * void
	 * ������
	 */
	public void shutdown ()
	{
		// �ر����ӳ�
		this.threadPool.shutdown();
	}
}
