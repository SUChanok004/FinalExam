package com.example.finalexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.finalexam.adapter.MemberAdapter;
import com.example.finalexam.model.MemberList;

public class UserListActivity extends AppCompatActivity {

    private ListView mMemberListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        mMemberListView = (ListView) findViewById(R.id.member_list_view);

        MemberList memberList = MemberList.getInstance(this);
        memberList.loadFromDatabase();

        MemberAdapter adapter = new MemberAdapter(
                this, R.layout.list_member, memberList.getMemberList());

        mMemberListView.setAdapter(adapter);
    }
}
