package top.goluck.lifecycle_aware2018_04_16;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import top.goluck.lifecycle_aware2018_04_16.lifecycle.TestLifecycle;
import top.goluck.lifecycle_aware2018_04_16.viewmodel.Test;
import top.goluck.lifecycle_aware2018_04_16.viewmodel.TestViewModel;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new TestLifecycle(this);
        Log.e("test","MainActivity.onCreate()" );

        test = findViewById(R.id.test);

        TestViewModel model = ViewModelProviders.of(this).get(TestViewModel.class);
        model.getTests().observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(@Nullable List<Test> tests) {
                test.setText(test.getText().toString() + tests.toString());
            }
        });

//        TestDB testdb= Room.databaseBuilder(this,TestDB.class,"tests").build();
//        final TestDao testDao = testdb.getTestDao();
//        Flowable.range(10,11).subscribeOn(Schedulers.io()).map(new Function<Integer, Long>() {
//            @Override
//            public Long apply(Integer integer) throws Exception {
//                long size = testDao.insert(new Test(1+integer, "测试存数据库的数据"+integer));
//                return size;
//            }
//        }).subscribe();

//        testDao.getAllTests().subscribeOn(Schedulers.io()).blockingSubscribe(new Consumer<List<Test>>() {
//            @Override
//            public void accept(final List<Test> tests) throws Exception {
//                test.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        test.setText(test.getText().toString() + tests.toString());
//                    }
//                });
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("test","MainActivity.onStart()" );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("test","MainActivity.onRestart()" );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("test","MainActivity.onResume()" );

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("test","MainActivity.onStop()" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("test","MainActivity.onDestroy()" );
    }

    @Override
    public LifecycleRegistry getLifecycle(){
        return lifecycleRegistry;
    }
}
