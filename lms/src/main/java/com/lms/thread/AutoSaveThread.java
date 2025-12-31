package com.lms.thread;

import com.lms.service.CourseService;

/**
 * A simple background thread that periodically flushes cache to DB.
 */
public class AutoSaveThread extends Thread {
    private final CourseService courseService;
    private volatile boolean running = true;
    private final long intervalMs;

    public AutoSaveThread(CourseService cs, long intervalMs) {
        this.courseService = cs;
        this.intervalMs = intervalMs;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(intervalMs);
                System.out.println("AutoSaveThread: flushing course cache to DB...");
                courseService.flushCacheToDb();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception ex) {
                System.err.println("AutoSaveThread error: " + ex.getMessage());
            }
        }
    }

    public void shutdown() { running = false; interrupt(); }
}
