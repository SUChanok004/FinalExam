package com.example.finalexam.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finalexam.R;
import com.example.finalexam.model.Member;

import java.util.ArrayList;

/**
 * Created by computer on 18/12/2559.
 */

public class MemberAdapter extends ArrayAdapter<Member>{

    private Context mContext;

    private int mLayoutResId;

    private ArrayList<Member> mMemberList;

    public MemberAdapter(Context context, int resource, ArrayList<Member> memberList) {
        super(context, resource, memberList);

        this.mContext = context;
        this.mLayoutResId = resource;
        this.mMemberList = memberList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemLayout = convertView;

        if ( itemLayout == null ){
            itemLayout = View.inflate(mContext, mLayoutResId, null);
        }

        TextView memberNameTv = (TextView) itemLayout.findViewById(R.id.member_name_text_view);

        Member member = mMemberList.get(position);
        memberNameTv.setText(member.getName());

        return itemLayout;
    }
}
