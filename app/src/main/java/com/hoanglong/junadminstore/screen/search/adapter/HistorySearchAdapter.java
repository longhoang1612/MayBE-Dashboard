package com.hoanglong.junadminstore.screen.search.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.data.model.search.HistorySearch;
import com.hoanglong.junadminstore.data.source.local.Realm.RealmHistorySearch;

import java.util.List;


public class HistorySearchAdapter extends RecyclerView.Adapter<HistorySearchAdapter.RecentViewHolder> {

    private List<HistorySearch> mListRecent;
    private OnItemClickListener mOnItemClickListener;
    private LayoutInflater mInflater;

    public HistorySearchAdapter(List<HistorySearch> listRecent, OnItemClickListener onClickListener) {
        mListRecent = listRecent;
        mOnItemClickListener = onClickListener;
    }

    @NonNull
    @Override
    public HistorySearchAdapter.RecentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mInflater.inflate(R.layout.item_recent_search, viewGroup, false);
        return new RecentViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorySearchAdapter.RecentViewHolder recentViewHolder, int i) {
        final HistorySearch recentSearch = mListRecent.get(i);
        recentViewHolder.bindData(recentSearch);
    }

    @Override
    public int getItemCount() {
        return mListRecent != null ? mListRecent.size() : 0;
    }

    class RecentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextSearch;
        private ImageView mImageDeleteSearch;
        private HistorySearch mRecentSearch;
        private OnItemClickListener mListener;

        RecentViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mListener = onItemClickListener;
            mTextSearch = itemView.findViewById(R.id.text_search);
            mImageDeleteSearch = itemView.findViewById(R.id.image_delete_recent_search);
            mImageDeleteSearch.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        private void bindData(HistorySearch recentSearch) {
            if (recentSearch == null) {
                return;
            }
            mRecentSearch = recentSearch;
            mTextSearch.setText(recentSearch.getHistorySearch());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.image_delete_recent_search:
                    RealmHistorySearch.deleteRecentSearch(mRecentSearch);
                    notifyDataSetChanged();
                    break;
                case R.id.constraint_history_search:
                    if (mListener == null) {
                        return;
                    }
                    mListener.onItemClickHistorySearchListener(mRecentSearch);
                    break;
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClickHistorySearchListener(HistorySearch historySearch);
    }
}
