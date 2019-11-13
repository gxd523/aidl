package com.demo.aidl.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AidlService extends Service {
    private List<Book> bookList;
    private final BookController.Stub stub = new BookController.Stub() {
        @Override
        public List<Book> getBookList() {
            return bookList;
        }

        @Override
        public void addBookInOut(Book book) {
            if (book != null) {
                String rename = "朝花夕拾";
                Log.d("gxd", "服务器将客户端添加的新书-->" + book.getName() + ", 改名为-->" + rename);
                book.setName(rename);
                bookList.add(book);
            } else {
                Log.d("gxd", "接收到一个空对象");
            }
        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
        bookList = new ArrayList<>();

        Book book1 = new Book("平凡的世界");
        Book book2 = new Book("海底两万里");
        Book book3 = new Book("红楼梦");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
}