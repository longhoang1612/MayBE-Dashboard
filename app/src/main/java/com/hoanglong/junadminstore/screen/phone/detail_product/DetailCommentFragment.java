package com.hoanglong.junadminstore.screen.phone.detail_product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.base.BaseFragment;
import com.hoanglong.junadminstore.data.model.comment.Comment;

import butterknife.BindView;
import butterknife.ButterKnife;
public class DetailCommentFragment extends BaseFragment implements View.OnClickListener {

    private static final String BUNDLE_COMMENT = "BUNDLE_COMMENT";
    private Comment mComment;
    @BindView(R.id.image_comment)
    ImageView mImageComment;
    @BindView(R.id.rating_comment)
    RatingBar mRatingComment;
    @BindView(R.id.text_title_comment)
    TextView mTextTitleComment;
    @BindView(R.id.text_comment)
    TextView mTextComment;
    @BindView(R.id.ic_back)
    ImageView mImageBack;
    @BindView(R.id.text_date_comment)
    TextView mTextDate;
    @BindView(R.id.text_user_comment)
    TextView mTextUser;

    public static DetailCommentFragment newInstance(Comment comment) {
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_COMMENT, comment);
        DetailCommentFragment fragment = new DetailCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        mComment = getArguments().getParcelable(BUNDLE_COMMENT);
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_detail_comment;
    }

    @Override
    protected void initComponent(View view) {
        ButterKnife.bind(this, view);
        mImageBack.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        if (mComment == null) {
            return;
        }
        if (getContext() == null) return;
        Glide.with(getContext()).load(mComment.getImageComment()).into(mImageComment);
        mRatingComment.setRating(mComment.getRating());
        mTextTitleComment.setText(mComment.getTitleComment());
        mTextComment.setText(mComment.getComment());
        mTextDate.setText(mComment.getDate());
        mTextUser.setText(mComment.getNameUser());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_back:
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
                break;
        }
    }
}
