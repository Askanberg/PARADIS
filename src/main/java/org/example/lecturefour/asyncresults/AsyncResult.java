package org.example.lecturefour.asyncresults;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsyncResult<V> implements Future<V> {
    private Thread thread;
    private V result = null;

    AsyncResult(Callable<V> task) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result = task.call();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }

    public V get() throws InterruptedException {
        thread.join();
        return result;
    }

    public boolean isDone() {
        return (thread.getState() == Thread.State.TERMINATED);
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException();
    }

    public V get(long timeout, TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    public boolean isCancelled() {
        throw new UnsupportedOperationException();
    }

}
