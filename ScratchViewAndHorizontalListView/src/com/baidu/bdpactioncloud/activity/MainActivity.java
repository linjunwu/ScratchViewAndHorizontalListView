package com.baidu.bdpactioncloud.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.bdpactioncloud.model.GameActivity;
import com.baidu.bdpactioncloud.view.GameActivitysView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private GameActivitysView mGameActivitysView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.baidu.bdpactioncloud.R.layout.activity_main);
        initGameActivitysView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.baidu.bdpactioncloud.R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == com.baidu.bdpactioncloud.R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initGameActivitysView() {
        mGameActivitysView = (GameActivitysView)findViewById(com.baidu.bdpactioncloud.R.id.gav_content);
        GameActivitysView.GameActivitysViewAdapter gameActivitysViewAdapter = mockGameActivitys();
        mGameActivitysView.setGameActivityList(gameActivitysViewAdapter);
    }

    private String[] MOCK_GAMEACTIVITYS = {"每日有礼", "双倍经验限时售", "每日有礼1", "双倍经验限时售1",
            "每日有礼2", "双倍经验限时售2", "每日有礼3", "双倍经验限时售3"};

    private GameActivitysView.GameActivitysViewAdapter mockGameActivitys() {

        List<GameActivity> gameActivityList = new ArrayList<GameActivity>();
        for (int i=0; i<MOCK_GAMEACTIVITYS.length; i++) {
            GameActivity gameActivity = new GameActivity();
            gameActivity.mTitle = MOCK_GAMEACTIVITYS[i];
            gameActivityList.add(gameActivity);
        }
        return new GameActivitysView.GameActivitysViewAdapter(gameActivityList);
    }
}
