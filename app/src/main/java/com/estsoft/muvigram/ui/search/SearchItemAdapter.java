package com.estsoft.muvigram.ui.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.estsoft.muvigram.R;
import com.estsoft.muvigram.ui.profile.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JEONGYI on 2016. 10. 28..
 */

public class SearchItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String PROFILE_IMAGE = "https://scontent.xx.fbcdn.net/v/t1.0-9/12011354_171091463233969_4930354003965117617_n.jpg?oh=5d04533c62af8fed3eeab63f36df659a&oe=589FE419";
    private static final String TAG_IMAGE = "https://cdn1.iconfinder.com/data/icons/glyph-1-1/24/Hash_hashtag_sharp_tag_teg-512.png";

    private final static int PEOPLE_INDEX = 0;
    private final static int TAGS_INDEX = 1;
    private final static int SOUNDS_INDEX = 2;

    private Context context;
    private int index;
    private List<SearchListItem> mSearchListItemList;
    private ArrayList<SearchListItem> mSearchResultList;

    public SearchItemAdapter(List<SearchListItem> mSearchListItemList, int index, Context mContext) {
        this.context = mContext;
        this.mSearchListItemList = mSearchListItemList;
        this.index = index;
        this.mSearchResultList = new ArrayList<SearchListItem>();
        mSearchResultList.addAll(mSearchListItemList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int position){

        View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.search_fragment_item, parent, false);
        return new SearchItemAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {

        ViewHolder mHolder = (ViewHolder)holder;
        mHolder.setIsRecyclable(false);

        if(index == PEOPLE_INDEX) {
            Picasso.with(context)
                    .load(PROFILE_IMAGE)
                    .transform(new CircleTransform()).into(mHolder.profile);
        }else if(index == TAGS_INDEX){
            Picasso.with(context)
                    .load(TAG_IMAGE)
                    .into(mHolder.profile);
        }else if(index == SOUNDS_INDEX){
            Picasso.with(context)
                    .load(PROFILE_IMAGE)
                    .into(mHolder.profile);
        }

        mHolder.title.setText (mSearchListItemList.get(position).getTitle());
        mHolder.subtitle.setText(mSearchListItemList.get(position).getSubTitle());

        mHolder.layout.setOnClickListener(v -> {

        });
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemViewType (int position) {
        return position;
    }


    @Override
    public int getItemCount () {
        return mSearchListItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.profile_image)
        ImageView profile;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.subtitle) TextView subtitle;
        @BindView(R.id.search_item_layout)
        LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mSearchListItemList.clear();
        if (charText.length() == 0) {
            mSearchListItemList.addAll(mSearchResultList);
        } else {
            for (SearchListItem item : mSearchResultList) {
                String title = item.getTitle();
                if (title.toLowerCase().contains(charText)) {
                    mSearchListItemList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

}
