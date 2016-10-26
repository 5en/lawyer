package com.ebaonet.lawyer.ui.view.frgament;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.CircleImageView;
import com.ebaonet.lawyer.ui.weight.TextWithLeftAndRightImageView;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/9/20.
 */
public class MineFragmentDelegate extends AppDelegate {
    private TextWithLeftAndRightImageView my_question;
    private TextWithLeftAndRightImageView my_been_questioned;
    private TextWithLeftAndRightImageView collection;
    private TextWithLeftAndRightImageView my_knowledge;
    private TextWithLeftAndRightImageView my_setting;
    private TextWithLeftAndRightImageView suggestion;
    private TextWithLeftAndRightImageView about;
    private TextWithLeftAndRightImageView share;

    private LinearLayout my_knowledge_lin;
    private CircleImageView user_avatar;
    private RelativeLayout user_relative;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void created(){
        my_question = findViewById(R.id.my_question);
        my_been_questioned = findViewById(R.id.my_been_questioned);
        collection = findViewById(R.id.collection);
        my_knowledge = findViewById(R.id.my_knowledge);
        my_setting = findViewById(R.id.my_setting);
        suggestion = findViewById(R.id.suggestion);
        about = findViewById(R.id.about);
        share = findViewById(R.id.share);
        my_knowledge_lin = findViewById(R.id.my_knowledge_lin);
        user_avatar = findViewById(R.id.user_avatar);
        user_relative = findViewById(R.id.user_relative);
    }
    @Override
    public void bindEvent(){
        EventHelper.click(mPresenter,my_question,my_been_questioned,collection,my_knowledge,my_setting,suggestion,about
        ,share,my_knowledge_lin,user_relative);
    }

    public TextWithLeftAndRightImageView getMy_question() {
        return my_question;
    }

    public TextWithLeftAndRightImageView getMy_been_questioned() {
        return my_been_questioned;
    }

    public TextWithLeftAndRightImageView getCollection() {
        return collection;
    }

    public TextWithLeftAndRightImageView getMy_knowledge() {
        return my_knowledge;
    }

    public TextWithLeftAndRightImageView getMy_setting() {
        return my_setting;
    }

    public TextWithLeftAndRightImageView getSuggestion() {
        return suggestion;
    }

    public TextWithLeftAndRightImageView getAbout() {
        return about;
    }

    public TextWithLeftAndRightImageView getShare() {
        return share;
    }

    public LinearLayout getMy_knowledge_lin() {
        return my_knowledge_lin;
    }

    public CircleImageView getUser_avatar() {
        return user_avatar;
    }

    public RelativeLayout getUser_relative() {
        return user_relative;
    }
}
